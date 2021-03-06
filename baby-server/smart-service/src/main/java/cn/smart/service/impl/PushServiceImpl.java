package cn.smart.service.impl;

import cn.smart.bo.PushBO;
import cn.smart.common.utils.DateUtils;
import cn.smart.common.utils.R;
import cn.smart.common.utils.Validator;
import cn.smart.condition.PersonQueryCondition;
import cn.smart.condition.PushLogQueryCondition;
import cn.smart.dao.IClassInfoMapper;
import cn.smart.dao.IPersonMapper;
import cn.smart.dao.IPushLogMapper;
import cn.smart.dao.IRoomMapper;
import cn.smart.entity.*;
import cn.smart.service.IPushService;
import cn.smart.support.jpush.PResult;
import cn.smart.support.jpush.PushComponent;
import cn.smart.utils.JPushUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Ye
 */
@Service
public class PushServiceImpl implements IPushService {

    private static final int PUSH_SUCCESS = 200;

    private static Logger logger = LogManager.getLogger(PushServiceImpl.class);
    
    @Autowired
    private IRoomMapper roomMapper;

    @Autowired
    private IClassInfoMapper classInfoMapper;

    @Autowired
    private IPushLogMapper pushLogMapper;

    @Autowired
    private IPersonMapper personMapper;
    
    @Autowired
    private PushComponent pushComponent;

    private static ExecutorService executorService;

    static {
        executorService = new ThreadPoolExecutor(2, 20, 5L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(20), new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    @Override
    public R pushStartLearningMsg(Long roomId) {

        boolean success;
        PushBO pushBO = new PushBO();

        PushLogDO pushLog = new PushLogDO();
        pushLog.setRoomId(roomId);
        pushLog.setPushMsg("???????????????????????????????????????");
        pushLog.setPushType(PushLogDO.PushType.CLASS_BEGIN);
        pushLog.setCreateTime(new Date());

        if (null == roomId) {
            Map<String, String> map = new HashMap<>(2);
            map.put("code", "500");
            map.put("msg", "Room id is null.");
            pushLog.setResult(JSONObject.toJSONString(map));
            savePushLog(pushLog);
            return R.error("?????????????????????????????????????????????");
        }

        PushLogQueryCondition pushLogQuery = new PushLogQueryCondition();
        pushLogQuery.setRoomId(roomId);
        pushLogQuery.setResult("200");
        List<PushLogDO> listPushLog = pushLogMapper.queryList(pushLogQuery);
        if (listPushLog.size() > 0) {
            Map<String, String> map = new HashMap<>(2);
            map.put("code", "500");
            map.put("msg", "room already pushed message. room id " + roomId);
            pushLog.setResult(JSONObject.toJSONString(map));
            savePushLog(pushLog);
            return R.error("?????????????????????????????????????????????????????????????????????" + DateUtils.format(listPushLog.get(0).getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
        }

        RoomDO room = roomMapper.queryById(roomId);
        if (Validator.isEmpty(room)) {
            Map<String, String> map = new HashMap<>(2);
            map.put("code", "500");
            map.put("msg", "The room does not exits. room id " + roomId);
            pushLog.setResult(JSONObject.toJSONString(map));
            savePushLog(pushLog);
            return R.error("?????????????????????????????????????????????");
        }

        pushBO.setType(PushLogDO.PushType.CLASS_BEGIN);
        pushBO.setMsg(room.getId().toString());
        pushBO.setTitle(room.getRoomName());
        pushBO.setImageUrl(room.getLogoUrl());
        pushBO.setImageUrl(room.getLogoUrl());

        ClassInfoDO classInfo = classInfoMapper.queryById(room.getClassId());
        if (classInfo == null) {
            Map<String, String> map = new HashMap<>(2);
            map.put("code", "500");
            map.put("msg", "Class not found. room id " + roomId);
            pushLog.setResult(JSONObject.toJSONString(map));
            savePushLog(pushLog);
            return R.error("????????????????????????, ????????????????????????");
        }
        if (Validator.isEmpty(classInfo.getPushTag())) {
            Map<String, String> map = new HashMap<>(2);
            map.put("code", "500");
            map.put("msg", "Push tag does not exits. room id " + roomId);
            pushLog.setResult(JSONObject.toJSONString(map));
            savePushLog(pushLog);
            return R.error("????????????????????????, ????????????????????????");
        }

        pushLog.setPushTag(classInfo.getPushTag());

        Map<String, Object> map = JPushUtils.pushMsgWithTag(JSONObject.toJSONString(pushBO), "title", classInfo.getPushTag());

        success = (int)map.get("code") == PUSH_SUCCESS;
        if (success) {
            pushLog.setResult(map.get("code").toString());
        } else {
            pushLog.setResult(JSONObject.toJSONString(map));
        }

        savePushLog(pushLog);

        return R.ok("???????????????????????????");
    }

    private void savePushLog(PushLogDO pushLog) {
        executorService.submit(() -> {
            try {
                pushLogMapper.save(pushLog);
            } catch (Exception var1) {
                logger.error("push notification message error. result is : " + var1.getMessage());
            }
        });
    }

    @Override
    public Boolean pushLoginOutMsg(Long personId, String pushAlias) {

        String msg = "??????????????????????????????????????? ??????????????????";
        PushBO p = new PushBO();
        p.setType(PushLogDO.PushType.LOG_OUT);
        p.setMsg(msg);
        Map<String, Object> map = JPushUtils.pushNotificationWithAlias(JSONObject.toJSONString(p), pushAlias);

        PushLogDO pushLog = new PushLogDO();
        pushLog.setPersonId(personId);
        pushLog.setPushAlias(pushAlias);
        pushLog.setPushMsg(msg);
        pushLog.setPushType(PushLogDO.PushType.LOG_OUT);
        pushLog.setCreateTime(new Date());
        boolean success;
        success =  (int)map.get("code") == PUSH_SUCCESS;

        if (success) {
            pushLog.setResult(map.get("code").toString());
        } else {
            pushLog.setResult(JSONObject.toJSONString(map));
        }

        savePushLog(pushLog);


        return success;
    }

    @Override
    public void pushUpdateTag(Long[] personIds) {

        executorService.submit(() -> {
            // ???????????????????????????
            PersonQueryCondition personQuery = new PersonQueryCondition();
            personQuery.setExitsPersonIds(personIds);
            List<PersonDO> listPerson = personMapper.queryList(personQuery);

            if (listPerson.size() > 0 ) {
                for (PersonDO person : listPerson) {
                    if (person.getPushAlias() != null) {
                        Map<String, String> extras = new HashMap<>(16);
                        extras.put("type", MessageDO.MessageType.UPDATE_TAGS);
                        pushComponent.pushMessageAndroidWithAlias("", "", extras, person.getPushAlias());
                    } else {
                        logger.error("??????????????????Tag??????, ??????????????????????????????! ??????: {}", person);
                    }
                }
            } else {
                logger.error("??????????????????tag??????, ????????????????????????????????????! ????????????: {}", JSONObject.toJSONString(personIds));
            }
        });
    }

    @Override
    public R pushMessage(PushLogDO push) {
        return null;
    }

    @Override
    public R pushVoiceMessage(Long personId, String title, String message, String url) {

        PersonDO person = personMapper.queryById(personId);
        if (null == person) {
            return R.error("?????????????????????");
        }
        if (null == person.getPushAlias()) {
            return R.error("?????????????????????????????????");
        }
        Map<String, String> extras = new HashMap<>(16);
        extras.put("url", url);
        PResult result = pushComponent.pushMessageAndroidWithAlias(title, message, extras, person.getPushAlias());

//        PushLogDO pushLogDO = new PushLogDO();
//
//        pushLogDO.setPushAlias(person.getPushAlias());
//        pushLogDO.setPushType(PushLogDO.PushType.VOICE_MSG);
//        pushLogDO.setPushMsg(message);
//        pushLogDO.setPushData(url);
//        pushLogDO.setPersonId(personId);
//        pushLogDO.setCreateTime(new Date());
//        pushLogDO.setResult(JSONObject.toJSONString(result));
//
//        savePushLog(pushLogDO);

        if (result.getCode() == HttpStatus.SC_OK) {
            return R.ok("????????????!");
        } else {
            return R.error("????????????!");
        }
    }
}
