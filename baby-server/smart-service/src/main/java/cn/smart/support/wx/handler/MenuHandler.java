package cn.smart.support.wx.handler;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Binary Wang
 */
@Component
public class MenuHandler extends AbstractHandler {
  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                  Map<String, Object> context, WxMpService weixinService,
                                  WxSessionManager sessionManager) {
    String msg = "";
    if (wxMessage.getEventKey().equalsIgnoreCase("developing_function")) {
      msg = "即将上线，敬请期待!";
    }


    if (WxConsts.MenuButtonType.VIEW.equals(wxMessage.getEvent())) {
      return null;
    }

    return WxMpXmlOutMessage.TEXT().content(msg)
      .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
      .build();
  }


}
