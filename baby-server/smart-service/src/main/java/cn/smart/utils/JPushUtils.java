package cn.smart.utils;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.*;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc:
 * author: yushan.peng
 * Create Time: 2017/7/21 16:16.
 */
public class JPushUtils {

    private static final Logger logger = LogManager.getLogger(JPushUtils.class);

    public static String MASTER_SECRET ="8a87ce131ea47e38a02ccace";
    public static String APP_KEY = "09c3ca1b816432c99bea3d67";

    public static void messagePush(String message){
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());

        // For push, all you need do is to build PushPayload object.
        PushPayload payload = buildPushObject_all_all_alert(message);

        try {
            PushResult result = jpushClient.sendPush(payload);
//            LOG.info("Got result - " + result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later
//            LOG.error("Connection error, should retry later", e);

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
//            LOG.error("Should review the error, and fix the request", e);
//            LOG.info("HTTP Status: " + e.getStatus());
//            LOG.info("Error Code: " + e.getErrorCode());
//            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }

    public static Map<String, Object> pushMsgWithTag(String msg, String title, String tag) {

        Map<String, Object> map = new HashMap<>(16);
        JPushClient jPushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
        PushPayload playLoad = buildPushObject_android_tag_alertWithTitle(msg, title, tag);

        PushResult result;
        try {
            result = jPushClient.sendPush(playLoad);
            map.put("code", result.getResponseCode());
        } catch (APIRequestException e) {
            map.put("code", e.getErrorCode());
            map.put("msg", e.getErrorMessage());
            logger.error("推送消息失败 APIRequestException : 标签: {} 失败原因: {}", tag, map);
        } catch (APIConnectionException e) {
            map.put("code", "500");
            map.put("msg", e.getMessage());
            logger.error("推送消息失败 APIConnectionException : 标签: {} 失败原因: {}", tag, map);
        }

        logger.info("消息推送结果：{}", map);

        return map;
    }

    public static Map<String, Object> pushNotificationWithAlias(String msg, String alias) {

        Map<String, Object> map = new HashMap<>(16);

        JPushClient jPushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());

        PushPayload playLoad = buildPushObject_all_alias_alert(msg, alias);

        PushResult result;
        try {
            result = jPushClient.sendPush(playLoad);
            map.put("code", result.getResponseCode());
        } catch (APIRequestException e) {
            map.put("code", e.getErrorCode());
            map.put("msg", e.getErrorMessage());
            logger.error("推送消息失败 APIRequestException : 别名: {} 失败原因: {}", alias, map);
        } catch (APIConnectionException e) {
            map.put("code", "500");
            map.put("msg", e.getMessage());
            logger.error("推送消息失败 APIConnectionException : 别名: {} 失败原因: {}", alias, map);
        }
        logger.info("消息推送结果：{}", map);
        return map;

    }

    public static Map<String, Object> sendMessageAndroid(String title, String message, Map<String, String> extras, String... alias) {

        Map<String, Object> map = new HashMap<>(16);

        JPushClient jPushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());

        Audience audience;
        if (alias != null && alias.length > 0) {
            audience = Audience.alias(alias);
        } else {
            audience = Audience.all();
        }

        PushPayload payload = PushPayload.newBuilder().setAudience(audience).setPlatform(Platform.android())
                .setMessage(Message.newBuilder()
                        .setTitle(title)
                        .setMsgContent(message)
                        .build())
                .build();

        PushResult result;
        try {
            result = jPushClient.sendPush(payload);
            map.put("code", result.getResponseCode());
        } catch (APIRequestException e) {
            map.put("code", e.getErrorCode());
            map.put("msg", e.getErrorMessage());
            logger.error("推送消息失败 APIRequestException : 别名: {} 失败原因: {}", alias, map);
        } catch (APIConnectionException e) {
            map.put("code", "500");
            map.put("msg", e.getMessage());
            logger.error("推送消息失败 APIConnectionException : 别名: {} 失败原因: {}", alias, map);
        }
        logger.info("消息推送结果：{}", map);
        return map;
    }

    /**
     *所有平台，所有设备，内容为 ALERT 的通知
     * @param message
     * @return
     */
    public static PushPayload buildPushObject_all_all_alert(String message) {
        return PushPayload.alertAll(message);
    }

    /**
     *所有平台，推送目标是别名为 "alias1"，通知内容为 ALERT
     * @param message
     * @return
     */
    public static PushPayload buildPushObject_all_alias_alert(String message,String alias) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.alert(message))
                .build();
    }

    /**
     * 平台是 Android，目标是 tag 为 "tag1" 的设备，内容是 Android 通知 ALERT，并且标题为 TITLE。
     * @param message
     * @param title
     * @return
     */
    public static PushPayload buildPushObject_android_tag_alertWithTitle(String message,String title,String tag) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag(tag))
                .setNotification(Notification.android(message, title, null))
                .build();
    }

    /**
     * 平台是 iOS，推送目标是 "tag1", "tag_all" 的交集，推送内容同时包括通知与消息
     * - 通知信息是 ALERT，角标数字为 5，通知声音为 "happy"，并且附加字段 from = "jpush"；
     * 消息内容是 MSG_CONTENT。通知是 APNs 推送通道的，消息是 jpush 应用内消息通道的。
     * APNs 的推送环境是“生产”（如果不显式设置的话，Library 会默认指定为开发）
     * @return
     */
    public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage(String alert,String content) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.tag_and("tag1", "tag_all"))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(alert)
                                .setBadge(5)
                                .setSound("happy")
                                .addExtra("from", "jpush")
                                .build())
                        .build())
                .setMessage(Message.content(content))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .build();
    }

    /**
     * 平台是 Andorid 与 iOS，推送目标是 （"tag1" 与 "tag2" 的并集）交（"alias1" 与 "alias2" 的并集），
     * 推送内容是 - 内容为 MSG_CONTENT 的消息，并且附加字段 from = jpush
     * @return
     */
    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras(String content) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(content)
                        .addExtra("from", "jpush")
                        .build())
                .build();
    }

    public static void testSendWithSMS() {
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        try {
            SMS sms = SMS.content("Test SMS", 10);
            PushResult result = jpushClient.sendAndroidMessageWithAlias("Test SMS", "test sms", sms, "alias1");
//            LOG.info("Got result - " + result);
        } catch (APIConnectionException e) {
//            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
//            LOG.error("Error response from jpush server. Should review and fix it. ", e);
//            LOG.info("HTTP Status: " + e.getStatus());
//            LOG.info("Error Code: " + e.getErrorCode());
//            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }
}
