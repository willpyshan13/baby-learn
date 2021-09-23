package cn.smart.support.scheduler.job;

import cn.smart.condition.PushLogQueryCondition;
import cn.smart.condition.RoomQueryCondition;
import cn.smart.dao.IClassInfoMapper;
import cn.smart.dao.IPushLogMapper;
import cn.smart.dao.IRoomMapper;
import cn.smart.entity.ClassInfoDO;
import cn.smart.entity.MessageDO;
import cn.smart.entity.PushLogDO;
import cn.smart.entity.RoomDO;
import cn.smart.service.IPushService;
import cn.smart.support.jpush.PResult;
import cn.smart.support.jpush.PushComponent;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息推送 Job
 * @author Ye
 */
@Component
public class MessageJob {

    private static final Logger logger = LogManager.getLogger(MessageJob.class);

    public static final String NAME = "messageJob";

    public static final String CLASS_BEGIN_METHOD = "classBeginMessage";

    @Autowired
    private IRoomMapper roomMapper;

    @Autowired
    private IClassInfoMapper classInfoMapper;

    @Autowired
    private IPushService pushService;

    @Autowired
    private PushComponent pushComponent;

    @Autowired
    private IPushLogMapper pushLogMapper;

    /**
     * 推送上课开始消息
     * @param id 教室id
     */
    public void classBeginMessage(Long id) {

        logger.info("==> Class begin message push start. classId={}", id);

        PResult result = new PResult(200, "success");
        PushLogDO pushLog = new PushLogDO();
        pushLog.setPushMsg("直播教室推送开始上课消息。");
        pushLog.setPushType(Integer.valueOf(MessageDO.MessageType.CLASS_BEGIN));
        pushLog.setCreateTime(new Date());
        // id 为空
        if (id == null) {
            result.setCode(500);
            result.setMessage("Classroom ID does not exist.");
            pushLog.setResult(JSONObject.toJSONString(result));
            pushLogMapper.save(pushLog);
            return;
        }
        pushLog.setRoomId(id);
        RoomQueryCondition roomQuery = new RoomQueryCondition();
        roomQuery.setId(id);
        RoomDO room = roomMapper.queryById(roomQuery);
        // 房间不存在
        if (room == null) {
            result.setCode(500);
            result.setMessage("Classroom does not exist. Room ID: " + id);
            pushLog.setResult(JSONObject.toJSONString(result));
            pushLogMapper.save(pushLog);
            return;
        }
        // 取消一个教室只推送一次消息限制
//        PushLogQueryCondition pushLogQuery = new PushLogQueryCondition();
//        pushLogQuery.setRoomId(id);
//        pushLogQuery.setResult("200");
//        List<PushLogDO> listPushLog = pushLogMapper.queryList(pushLogQuery);
//        // 教室已经推送过消息
//        if (listPushLog.size() > 0) {
//            result.setCode(500);
//            result.setMessage("The classroom has been sent messages. Classroom ID: " + id);
//            pushLog.setResult(JSONObject.toJSONString(result));
//            pushLogMapper.save(pushLog);
//            return;
//        }
        ClassInfoDO classInfo = classInfoMapper.queryById(room.getClassId());
        // 教室对应班级不能存在
        if (classInfo == null) {
            result.setCode(500);
            result.setMessage("Class not found. Room ID: " + id);
            pushLog.setResult(JSONObject.toJSONString(result));
            pushLogMapper.save(pushLog);
            return;
        }
        // 教室对应班级的推送标签不存在
        if (null == classInfo.getPushTag()) {
            result.setCode(500);
            result.setMessage("Push tag does not exits. Class ID: " + classInfo.getId());
            pushLog.setResult(JSONObject.toJSONString(result));
            pushLogMapper.save(pushLog);
            return;
        }
        // 推送上课开始消息
        Map<String, String> extras = new HashMap<>(16);
        extras.put("type", MessageDO.MessageType.CLASS_BEGIN);
        extras.put("imageUrl", room.getLogoUrl());
        extras.put("id", String.valueOf(id));
        extras.put("name", room.getRoomName());
        pushComponent.pushMessageAndroidWithTags("直播课堂", "机灵宝宝喊你来上直播课啦", extras, classInfo.getPushTag());

        logger.info("Class begin message push end. classId={}", id);
    }

    public IPushService getPushService() {
        return pushService;
    }

    public void setPushService(IPushService pushService) {
        this.pushService = pushService;
    }
}
