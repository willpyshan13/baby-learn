package cn.smart.support.jpush;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosAlert;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

/**
 * @author Ye
 */
public abstract class BaseJPush {

    private final static Logger logger = LogManager.getLogger(BaseJPush.class);

    public abstract JPushClient getJPushClient();

    public PResult sendNotificationAll(String title, String alert, Map<String, String> extras, String... alias) {

        Audience audience;
        if (alias != null && alias.length > 0) {
            audience = Audience.alias(alias);
        } else {
            audience = Audience.all();
        }

        IosAlert iosAlert = IosAlert.newBuilder().setTitleAndBody(title, "", alert).build();
        PushPayload payload = PushPayload.newBuilder().setAudience(audience)
                .setPlatform(Platform.all()).setNotification(
                        Notification.newBuilder().addPlatformNotification(
                                    IosNotification.newBuilder().setAlert(iosAlert).addExtras(extras).build())
                                .addPlatformNotification(
                                    AndroidNotification.newBuilder().setTitle(title).setAlert(alert).addExtras(extras).build()
                                ).build()).setOptions(Options.newBuilder().setApnsProduction(true).build())
                .build();

        return push(payload);
    }

    public PResult sendMessageAndroid(String title, String message, Map<String, String> extras, String... alias) {

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
                        .addExtras(extras)
                        .build())
                .build();

        return push(payload);
    }

    public PResult sendMessageAndroid(String title, String message, String... alias) {

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

        return push(payload);
    }

    public PResult sendMessageAndroidWithTags(String title, String message, Map<String, String> extras, String... tags) {

        Audience audience;
        if (tags != null && tags.length > 0) {
            audience = Audience.tag(tags);
        } else {
            audience = null;
        }

        Message m;
        if (extras == null) {
            m = Message.newBuilder().setTitle(title).setMsgContent(message).build();
        } else {
            m = Message.newBuilder().setTitle(title).setMsgContent(message).addExtras(extras).build();
        }

        PushPayload payload = PushPayload.newBuilder().setAudience(audience).setPlatform(Platform.android())
                .setMessage(m)
                .build();

        return push(payload);
    }


    public PResult sendNotificationAndroidAlias(String title, String alert, Map<String, String> extras, String... alias) {

        Audience audience;
        if (alias != null && alias.length > 0) {
            audience = Audience.alias(alias);
        } else {
            audience = Audience.all();
        }

        PushPayload payload = PushPayload.newBuilder().setAudience(audience).setPlatform(Platform.android())
                .setNotification(Notification.newBuilder().addPlatformNotification(
                        AndroidNotification.newBuilder()
                        .setTitle(title)
                        .setAlert(alert)
                        .addExtras(extras)
                        .build()
                ).build()).build();

        return push(payload);
    }

    protected PResult sendNotificationAndroid(String title, String alert, Map<String, String> extras,
                                                 String... registrationId) {
        Audience audience;
        if (registrationId != null && registrationId.length > 0) {
            audience = Audience.registrationId(registrationId);
        } else {
            audience = Audience.all();
        }

        PushPayload payload = PushPayload.newBuilder().setAudience(audience).setPlatform(Platform.all())
                .setNotification(Notification.newBuilder().addPlatformNotification(
                                AndroidNotification.newBuilder()
                                        .setTitle(title)
                                        .setAlert(alert)
                                        .addExtras(extras)
                                        .build()
                        ).build()).setOptions(Options.newBuilder().setApnsProduction(true).build()).build();

        return push(payload);
    }

    private PResult push(PushPayload payload) {
        PushResult result;
        try {
            result = getJPushClient().sendPush(payload);
            if (result.getResponseCode() == HttpStatus.SC_OK) {
                return new PResult(result.getResponseCode(), "success");
            } else {
                return new PResult(HttpStatus.SC_INTERNAL_SERVER_ERROR, "push message failed.");
            }
        } catch (APIRequestException e) {
            logger.error("jpush error ========> {}", e.getMessage());
            return new PResult(e.getErrorCode(), e.getErrorMessage());
        } catch (APIConnectionException ignored) {
            return new PResult(HttpStatus.SC_INTERNAL_SERVER_ERROR, "push message failed.");
        }
    }
}
