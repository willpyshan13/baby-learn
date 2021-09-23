package cn.smart.support.wx.handler;

import cn.smart.condition.PersonQueryCondition;
import cn.smart.dao.IPersonMapper;
import cn.smart.entity.PersonDO;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Binary Wang
 */
@Component
public class UnsubscribeHandler extends AbstractHandler {

  @Autowired
  private IPersonMapper personMapper;

  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                  Map<String, Object> context, WxMpService wxMpService,
                                  WxSessionManager sessionManager) {
    String openId = wxMessage.getFromUser();



    this.logger.info("取消关注用户 OPENID: " + openId);
    // 可以更新本地数据库为取消关注状态
    PersonQueryCondition query = new PersonQueryCondition();
    query.setWxMpId(openId);
    List<PersonDO> personList = personMapper.queryList(query);

    if (personList.size() > 0) {
      PersonDO person = personList.get(0);
      person.setWxMpId("");
      personMapper.update(person);
    }
    return null;
  }

}
