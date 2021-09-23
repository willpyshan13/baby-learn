package cn.smart.controller.mobile;

import cn.smart.common.annotation.AuthIgnore;
import cn.smart.common.annotation.LoginUser;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.api.result.ApiStatus;
import cn.smart.entity.PersonDO;
import cn.smart.entity.WeekReportDO;
import cn.smart.service.IApiPersonService;
import cn.smart.service.IPersonService;
import cn.smart.service.IWeekReportService;
import cn.smart.support.wx.service.WeixinService;
import com.alibaba.fastjson.JSONObject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Binary Wang
 */
@RestController
@RequestMapping("/mobile/wechat/portal")
public class WxMpPortalController {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private IPersonService personService;
    
    @Autowired
    private IWeekReportService weekReportService;

    @Autowired
    private WeixinService wxService;

    @AuthIgnore
    @ResponseBody
    @GetMapping(produces = "text/plain;charset=utf-8")
    public String authGet(@RequestParam(name = "signature", required = false) String signature,
                          @RequestParam(name = "timestamp", required = false) String timestamp,
                          @RequestParam(name = "nonce", required = false) String nonce,
                          @RequestParam(name = "echostr", required = false) String echostr) {
        this.logger.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature, timestamp, nonce, echostr);

        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalArgumentException("请求参数非法，请核实!");
        }

        if (this.wxService.checkSignature(timestamp, nonce, signature)) {
            return echostr;
        }

        return "非法请求";
    }

    @AuthIgnore
    @ResponseBody
    @PostMapping(produces = "application/xml; charset=UTF-8")
    public String post(@RequestBody String requestBody, @RequestParam("signature") String signature,
                       @RequestParam(name = "encrypt_type", required = false) String encType,
                       @RequestParam(name = "msg_signature", required = false) String msgSignature,
                       @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce) {
        this.logger.info("\n接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}],"
                        + " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
                signature, encType, msgSignature, timestamp, nonce, requestBody);

        if (!this.wxService.checkSignature(timestamp, nonce, signature)) {
            throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
        }

        String out = null;
        if (encType == null) {
            // 明文传输的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
            WxMpXmlOutMessage outMessage = this.wxService.route(inMessage);
            if (outMessage == null) {
                return "";
            }

            out = outMessage.toXml();
        } else if ("aes".equals(encType)) {
            // aes加密的消息
            WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(requestBody,
                    this.wxService.getWxMpConfigStorage(), timestamp, nonce, msgSignature);
            this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
            WxMpXmlOutMessage outMessage = this.wxService.route(inMessage);
            if (outMessage == null) {
                return "";
            }

            out = outMessage.toEncryptedXml(this.wxService.getWxMpConfigStorage());
        }

        this.logger.debug("\n组装回复信息：{}", out);

        return out;
    }


    /**
     * 生成带参数的二维码(带手机号码)
     *
     * @param person 用户信息
     * @return
     */
    @GetMapping("/subscribe/qrcode")
    public ApiResult getSubscribeQrCode(@LoginUser PersonDO person) {

        if (person.getPersonId() == null) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "用户信息无效，请重新登录");
        }

        String accessToken = "";

        try {
            accessToken = wxService.getAccessToken();
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        logger.info("access_token : {}", accessToken);

        String sceneStr;

        Map<String, String> map = new HashMap<>(1);
        map.put("mobilePhone", person.getMobileNumber());
        map.put("action", "subscribe");
        sceneStr = JSONObject.toJSONString(map);
        WxMpQrCodeTicket result = null;

        try {
            result = wxService.getQrcodeService().qrCodeCreateTmpTicket(sceneStr, 2592000);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        if (result != null) {
            return new ApiResult(result);
        } else {
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "二维码生成失败");
        }
    }

    @AuthIgnore
    @GetMapping("/send/template")
    public String sendTemplateMsg() {

        PersonDO person = personService.queryById(1L);

        WeekReportDO weekReport = weekReportService.queryById(person.getWeekReportId());

        WxMpTemplateMessage msg = new WxMpTemplateMessage();


        List<WxMpTemplateData> data = new ArrayList<>();

        data.add(new WxMpTemplateData("userName", person.getNickName(), ""));
        data.add(new WxMpTemplateData("days", weekReport.getDays().toString()));
        data.add(new WxMpTemplateData("words", weekReport.getWords()));
//        data.add(new WxMpTemplateData("bh", "keyword3333"));
//        data.add(new WxMpTemplateData("totalIntegral", "remark"));
//        data.add(new WxMpTemplateData("leftIntegral", "remark"));

        msg.setData(data);

        msg.setToUser(person.getWxMpId());
        msg.setUrl("http://www.smart-dog.cn/test/html/courseweekly.html");
//        msg.setUrl("http://73f22f55.ngrok.io/smart-api/h5/api/report/weeks?id=" + person.getPersonId());
        msg.setTemplateId("rrZau2M4QCK2l2K9_gJl20-9HIeAUdfd8rudNgrjtPo");
//        msg.setTemplateId("NpFmVfOC6nHbFKyKe7flyzUBM3NClm0TzRV6cM8zg34");

        String result = null;
        try {
            result = wxService.getTemplateMsgService().sendTemplateMsg(msg);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        return result;
    }
}
