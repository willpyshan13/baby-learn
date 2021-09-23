package cn.smart.support.wx.handler;

import cn.smart.condition.PersonQueryCondition;
import cn.smart.entity.PersonDO;
import cn.smart.entity.WeekReportDO;
import cn.smart.service.IPersonService;
import cn.smart.service.IWeekReportService;
import cn.smart.support.wx.builder.TextBuilder;
import cn.smart.support.wx.service.WeixinService;
import cn.smart.support.wx.service.WeixinTemplateMsgService;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import me.chanjar.weixin.mp.constant.WxMpEventConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Binary Wang
 */
@Component
public class MsgHandler extends AbstractHandler {


    @Autowired
    private IPersonService personService;

    @Autowired
    private WeixinTemplateMsgService weixinTemplateMsgService;

    @Autowired
    private IWeekReportService weekReportService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {

        WeixinService weixinService = (WeixinService) wxMpService;

        if (!wxMessage.getMsgType().equals(WxConsts.XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地
        }

        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
        if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
                && weixinService.hasKefuOnline()) {
            return WxMpXmlOutMessage
                    .TRANSFER_CUSTOMER_SERVICE().fromUser(wxMessage.getToUser())
                    .toUser(wxMessage.getFromUser()).build();
        }

        if ("TEMPLATESENDJOBFINISH".equals(wxMessage.getEvent())) {
            return null;
        }

        String messageContent = null;

        if (wxMessage.getContent() != null) {
            if (wxMessage.getContent().trim().equals("解绑")) {

                PersonQueryCondition query = new PersonQueryCondition();
                query.setWxMpId(wxMessage.getFromUser());
                List<PersonDO> personList = personService.queryList(query);
                if (personList.size() > 0) {
                    PersonDO person = personList.get(0);
                    if (StringUtils.isNotBlank(person.getWxMpId())) {
                        person.setWxMpId("");
                        personService.update(person);
                    }
                    messageContent = "你已成功取消关注"+ person.getNickName() +"宝贝";
                } else {
                    messageContent = "你还未关注宝贝";
                }
            }
        }
        if (messageContent == null) {
            return null;
        } else {
            return new TextBuilder().build(messageContent, wxMessage, weixinService);
        }
    }

}
