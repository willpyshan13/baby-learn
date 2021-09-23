package cn.smart.support.wx.service;

import cn.smart.support.wx.config.WxMpConfig;
import cn.smart.support.wx.handler.*;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.kefu.result.WxMpKfOnlineList;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.constant.WxMpEventConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static me.chanjar.weixin.common.api.WxConsts.*;

/**
 * @author Binary Wang
 */
@Service
public class WeixinService extends WxMpServiceImpl {

  private Logger logger = LogManager.getLogger(this.getClass());

  @Autowired
  protected LogHandler logHandler;

  @Autowired
  protected NullHandler nullHandler;

  @Autowired
  protected KfSessionHandler kfSessionHandler;

  @Autowired
  protected StoreCheckNotifyHandler storeCheckNotifyHandler;

  @Autowired
  private WxMpConfig wxConfig;

  @Autowired
  private LocationHandler locationHandler;

  @Autowired
  private MenuHandler menuHandler;

  @Autowired
  private MsgHandler msgHandler;

  @Autowired ScanHandler scanHandler;

  @Autowired
  private UnsubscribeHandler unsubscribeHandler;

  @Autowired
  private SubscribeHandler subscribeHandler;

  private WxMpMessageRouter router;

  @PostConstruct
  public void init() {
    final WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
    // 设置微信公众号的appid
    config.setAppId(this.wxConfig.getAppid());
    // 设置微信公众号的app corpSecret
    config.setSecret(this.wxConfig.getAppsecret());
    // 设置微信公众号的token
    config.setToken(this.wxConfig.getToken());
    // 设置消息加解密密钥
    config.setAesKey(this.wxConfig.getAesKey());
    super.setWxMpConfigStorage(config);

    this.refreshRouter();
  }

  private void refreshRouter() {
    final WxMpMessageRouter newRouter = new WxMpMessageRouter(this);

    // 记录所有事件的日志
    newRouter.rule().handler(this.logHandler).next();

    // 接收客服会话管理事件
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
      .event(WxMpEventConstants.CustomerService.KF_CREATE_SESSION)
      .handler(this.kfSessionHandler).end();
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
      .event(WxMpEventConstants.CustomerService.KF_CLOSE_SESSION)
      .handler(this.kfSessionHandler).end();
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
      .event(WxMpEventConstants.CustomerService.KF_SWITCH_SESSION)
      .handler(this.kfSessionHandler).end();

    // 门店审核事件
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
      .event(WxMpEventConstants.POI_CHECK_NOTIFY)
      .handler(this.storeCheckNotifyHandler)
      .end();

    // 自定义菜单事件
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
      .event(MenuButtonType.CLICK).handler(this.menuHandler).end();

    // 点击菜单连接事件
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
      .event(MenuButtonType.VIEW).handler(this.nullHandler).end();

    // 关注事件
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
      .event(EventType.SUBSCRIBE).handler(this.subscribeHandler)
      .end();

    // 取消关注事件
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
      .event(EventType.UNSUBSCRIBE).handler(this.unsubscribeHandler)
      .end();

    // 上报地理位置事件
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
      .event(EventType.LOCATION).handler(this.locationHandler).end();

    // 接收地理位置消息
    newRouter.rule().async(false).msgType(XmlMsgType.LOCATION)
      .handler(this.locationHandler).end();

    // 扫码事件
    newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
      .event(EventType.SCAN).handler(this.scanHandler).end();

    // 默认
    newRouter.rule().async(false).handler(this.msgHandler).end();

    this.router = newRouter;
  }

  public WxMpXmlOutMessage route(WxMpXmlMessage message) {
    try {
      return this.router.route(message);
    } catch (Exception e) {
      this.logger.error(e.getMessage(), e);
    }

    return null;
  }

  public boolean hasKefuOnline() {
    try {
      WxMpKfOnlineList kfOnlineList = this.getKefuService().kfOnlineList();
      return kfOnlineList != null && kfOnlineList.getKfOnlineList().size() > 0;
    } catch (Exception e) {
      this.logger.error("获取客服在线状态异常: " + e.getMessage(), e);
    }

    return false;
  }

}
