package cn.smart.support.jpush;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.smart.common.utils.R;
import cn.smart.dao.IPushLogMapper;
import cn.smart.entity.PushLogDO;
import cn.smart.service.impl.MessageServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Ye
 */
@Component
public class PushComponent extends BaseJPush {

    @Value("${master.secret}")
    private String masterSecret;

    @Value("${app.key}")
    private String appKey;

    private static Logger logger = LogManager.getLogger(MessageServiceImpl.class);

    private static ExecutorService executorService;

    static {
        executorService = new ThreadPoolExecutor(2, 20, 5L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(20), new ThreadPoolExecutor.DiscardOldestPolicy());
    }


    @Autowired
    private IPushLogMapper pushLogMapper;


    @Override
    public JPushClient getJPushClient() {
        return new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());
    }

    public PResult pushAlertAndroidWithAlias(String title, String message, Map<String, String> extras, String... alias) {
        return sendNotificationAndroidAlias(title, message, extras, alias);
    }

    public PResult pushMessageAndroidWithAlias(String title, String message, Map<String, String> extras, String... alias) {

        PResult result;
        if (extras == null) {
            result = sendMessageAndroid(title, message, alias);
        } else {
            result = sendMessageAndroid(title, message, extras, alias);
        }

        savePushLogs(result, title, message, alias, extras);


        return result;
    }

    public PResult pushMessageAndroidWithTags(String title, String message, Map<String, String> extras, String... tags) {

        PResult result = sendMessageAndroidWithTags(title, message, extras, tags);

        savePushLogs(result, title, message, tags, extras);

        return result;
    }

    private void savePushLogs(PResult result, String title, String message, String[] tags, Map<String, String> extras) {

        executorService.submit(() -> {
            try {
                PushLogDO pushLogDO = new PushLogDO();
                pushLogDO.setPushAlias(StringUtils.join(tags, ","));
                pushLogDO.setPushType(Integer.valueOf(extras.get("type")));
                pushLogDO.setPushMsg(message);
                pushLogDO.setPushTitle(title);
                if (extras != null) {
                    pushLogDO.setPushData(JSONObject.toJSONString(extras));
                }
                pushLogDO.setCreateTime(new Date());
                if (result != null) {
                    pushLogDO.setResult(JSONObject.toJSONString(result));
                }
                logger.info("save push log -------> {}", JSONObject.toJSONString(pushLogDO));
                pushLogMapper.save(pushLogDO);
            } catch (Exception e) {
                logger.error(" save push log error. {}", e.getMessage());
            }

        });
    }
}
