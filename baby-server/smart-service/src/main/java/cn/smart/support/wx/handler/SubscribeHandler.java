package cn.smart.support.wx.handler;

import cn.smart.condition.PersonQueryCondition;
import cn.smart.dao.IPersonMapper;
import cn.smart.entity.PersonDO;
import cn.smart.support.wx.builder.TextBuilder;
import cn.smart.support.wx.service.WeixinService;
import com.alibaba.fastjson.JSONObject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Binary Wang
 */
@Component
public class SubscribeHandler extends AbstractHandler {

  @Autowired
  private IPersonMapper personMapper;

  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
                                  WxSessionManager sessionManager) throws WxErrorException {

    this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());
    String messageContent = null;
    WeixinService weixinService = (WeixinService) wxMpService;
    // 获取微信用户基本信息
    WxMpUser userWxInfo = weixinService.getUserService().userInfo(wxMessage.getFromUser(), null);

    PersonDO person;
    if (userWxInfo != null) {
      String eventKey = wxMessage.getEventKey();
      if (!StringUtils.isBlank(eventKey)) {
        JSONObject json = JSONObject.parseObject(eventKey.replace("qrscene_", ""));
        if ("subscribe".equals(json.getString("action").toLowerCase())) {
          // 查询该微信是否已经绑定用户
          PersonQueryCondition query = new PersonQueryCondition();
          query.setWxMpId(userWxInfo.getOpenId());
          List<PersonDO> wxPersonList = personMapper.queryList(query);
          if (wxPersonList.size() > 0) {
            messageContent = "你已关注" + wxPersonList.get(0).getNickName() + "宝贝，请先解绑后再试";
          } else {
            query.setWxMpId(null);
            query.setMobileNumber(json.getString("mobilePhone"));
            List<PersonDO> personList = personMapper.queryList(query);
            if (personList.size() > 0) {
              person = personList.get(0);
              if (StringUtils.isBlank(person.getWxMpId())) {
                person.setWxMpId(userWxInfo.getOpenId());
                personMapper.update(person);
                messageContent = "你已成功关注了" + person.getNickName() + "宝贝，机灵宝宝会将宝贝的学习情况发送给你。可以发送\"解绑\"，取消关注宝贝。";
              } else {
                messageContent = "该用户已被其他微信号绑定，请先使用原绑定微信解绑后再试";
              }
            }
          }
        }
      }
    }

    WxMpXmlOutMessage responseResult = null;
    try {
      responseResult = handleSpecial(wxMessage);
    } catch (Exception e) {
      this.logger.error(e.getMessage(), e);
    }

    if (responseResult != null) {
      return responseResult;
    }

    if (messageContent != null) {
      try {
        return new TextBuilder().build(messageContent, wxMessage, weixinService);
      } catch (Exception e) {
        this.logger.error(e.getMessage(), e);
      }
    }

    return null;
  }

  /**
   * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
   */
  protected WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage) throws Exception {
    logger.info(" WxMpXmlOutMessage handleSpecial------------");
    return null;
  }

}
