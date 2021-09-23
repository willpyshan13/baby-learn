package cn.smart.support.wx.handler;

import com.google.gson.Gson;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Binary Wang
 */
public abstract class AbstractHandler implements WxMpMessageHandler {

  protected final Gson gson = new Gson();
  protected Logger logger = LogManager.getLogger(getClass());

}
