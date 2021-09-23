package cn.smart.service.impl;

import cn.smart.bo.RecordingBO;
import cn.smart.bo.RoomBo;
import cn.smart.bo.RoomListBO;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.exception.CustomException;
import cn.smart.common.utils.DateUtil;
import cn.smart.common.utils.R;
import cn.smart.condition.ClassPersonQueryCondition;
import cn.smart.condition.PersonRecordingQueryCondition;
import cn.smart.condition.RoomQueryCondition;
import cn.smart.dao.IClassPersonMapper;
import cn.smart.dao.IPersonRecordingMapper;
import cn.smart.dao.IRoomCoursewareMapper;
import cn.smart.dao.IRoomMapper;
import cn.smart.entity.ClassPersonDO;
import cn.smart.entity.RoomCoursewareDO;
import cn.smart.entity.RoomDO;
import cn.smart.service.IRoomService;
import cn.smart.service.ISchedulerService;
import cn.smart.support.scheduler.SchedulerServiceImpl;
import cn.smart.support.scheduler.TaskScheduled;
import cn.smart.support.scheduler.job.MessageJob;
import cn.smart.support.talkcloud.business.RoomBusiness;
import cn.smart.support.talkcloud.utils.ErrorCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* @author Ye
* @date 2018-4-9 19:43:22
*/

@Service
public class RoomServiceImpl  implements IRoomService {

    private static Logger logger = LogManager.getLogger(RoomServiceImpl.class);

    @Autowired
    private IRoomMapper roomMapper;

    @Autowired
    private IRoomCoursewareMapper roomCoursewareMapper;

    @Autowired
    private RoomBusiness roomBusiness;

    @Autowired
    private IPersonRecordingMapper personRecordingMapper;

    @Autowired
    private ISchedulerService schedulerService;

    @Autowired
    private IClassPersonMapper classPersonMapper;

    @Override
    public R updateRoom(RoomBo room) {
        RoomDO roomDO = room.getRoom();

        // 拓课云修改教室信息
        Map result = roomBusiness.modifyRoom(roomDO);
        Integer resultCode = (Integer) result.get("result");
        // 执行成功
        if (resultCode == 0) {
            // 获取拓课云 教室信息
            Map cloudRoom = roomBusiness.getRoomBySerial(roomDO.getId().intValue());
            if (0 == (Integer) cloudRoom.get("result")) {
                // 设置相关地址
                roomDO.setTeacherUrl("global.talk-cloud.net/"+roomDO.getId()+"/10431/1/0");
                roomDO.setConfuserUrl("global.talk-cloud.net/"+roomDO.getId()+"/10431/" + (roomDO.getPasswordRequired() == 1?"1":"0") +"/2");
            }
            // 更新教室信息
            roomMapper.update(roomDO);

            ClassPersonQueryCondition classPersonQueryCondition = new ClassPersonQueryCondition();
            classPersonQueryCondition.setClassId(roomDO.getClassId());
            List<ClassPersonDO> classPersonList = classPersonMapper.queryList(classPersonQueryCondition);
            if (classPersonList.size() > 0) {

                // 创建调度任务， 推送开课消息
                if (DateUtil.addMinutes(roomDO.getStartTime(), -7).getTime() > new Date().getTime()) {
                    schedulerService.addClassBeginNotificationJob(room.getRoom());
                    schedulerService.generatePersonRecording(room.getRoom());
                }
            }
            return R.ok("操作成功!");
        } else {
            return R.error(ErrorCode.getErrorMessage(resultCode));
        }

    }

    @Override
    public R saveRoom(RoomBo room) {
        RoomDO roomDO = room.getRoom();
        Map result = roomBusiness.createRoom(roomDO);
        Integer resultCode = (Integer) result.get("result");
        // 执行成功
        if (resultCode == 0) {
            // 获取拓课云 教室信息
            Map cloudRoom = roomBusiness.getRoomBySerial((Integer) result.get("serial"));
            if (0 == (Integer) cloudRoom.get("result")) {
               // 设置相关地址
                roomDO.setTeacherUrl("global.talk-cloud.net/"+cloudRoom.get("serial")+"/10431/1/0");
                roomDO.setConfuserUrl("global.talk-cloud.net/"+cloudRoom.get("serial")+"/10431/" + (roomDO.getPasswordRequired() == 1?"1":"0") +"/2");
            }
            // 保存教室信息
            roomDO.setId(Long.valueOf((Integer) result.get("serial")));
            roomDO.setCreateTime(new Date());
            roomMapper.save(roomDO);

            ClassPersonQueryCondition classPersonQueryCondition = new ClassPersonQueryCondition();
            classPersonQueryCondition.setClassId(roomDO.getClassId());
            List<ClassPersonDO> classPersonList = classPersonMapper.queryList(classPersonQueryCondition);
            if (classPersonList.size() > 0) {
                // 创建调度任务， 推送开课消息
                if (DateUtil.addMinutes(roomDO.getStartTime(), -7).getTime() > new Date().getTime()) {
                    schedulerService.addClassBeginNotificationJob(room.getRoom());
                    schedulerService.generatePersonRecording(room.getRoom());
                }
            }

            return R.ok("操作成功!");
        } else {
            return R.error(ErrorCode.getErrorMessage(resultCode));
        }
    }

    @Override
    public void assignedCourseware(RoomBo roomBo) {

        List<RoomCoursewareDO> roomCoursewareDOList = new ArrayList<>(roomBo.getCoursewareIds().length);

        RoomCoursewareDO roomCoursewareDO;
        for (int i = 0, len = roomBo.getCoursewareIds().length; i < len; i++) {
            roomCoursewareDO = new RoomCoursewareDO();
            roomCoursewareDO.setRoomId(roomBo.getRoomId());
            roomCoursewareDO.setCoursewareId(roomBo.getCoursewareIds()[i]);
            roomCoursewareDOList.add(roomCoursewareDO);
        }
        roomCoursewareMapper.saveBatch(roomCoursewareDOList);

        // 同步拓课云
        Map result =roomBusiness.roomBindFile(roomBo.getRoomId(), null, roomBo.getCoursewareIds());
        Integer resultCode = (Integer) result.get("result");
        // 执行成功
        if (resultCode != 0) {
            throw new CustomException("拓课云关联文档失败! code = " + resultCode.toString() + ", msg : " + ErrorCode.getErrorMessage(resultCode));
        }

    }

    @Override
    public void cancelRoomCourseware(Long roomId, Long[] roomCoursewareIds, Long[] coursewareIds) {

        roomCoursewareMapper.deleteBatch(roomCoursewareIds);

        // 同步拓课云
        Map result =roomBusiness.roomDeleteFile(roomId, coursewareIds);
        Integer resultCode = (Integer) result.get("result");
        // 执行成功
        if (resultCode != 0) {
            throw new CustomException("拓课云取消关联文档失败! code = " + resultCode.toString() + ", msg : " + ErrorCode.getErrorMessage(resultCode));
        }
    }

    @Override
    public int deleteById(Object id) {
        // 删除拓课云教室信息
        Map result = roomBusiness.roomDelete(((Long) id).intValue());
        Integer resultCode = (Integer) result.get("result");
        // 执行成功
        if (resultCode == 0) {
            // 删除本地信息

            try{
                RoomDO room = roomMapper.queryById(id);
                TaskScheduled taskJob = new TaskScheduled();
                taskJob.setTaskName(room.getId().toString());
                taskJob.setTaskGroup("room_msg");
                schedulerService.deleteJob(taskJob);
            } catch (Exception e) {
                logger.error("删除教室定时任务失败! 教室id: {} 错误信息: {}", id, e.getMessage());
            }

            return roomMapper.deleteById(id);
        } else {
            throw new CustomException("拓课云删除教室失败! code = " + resultCode.toString() + ", msg : " + ErrorCode.getErrorMessage(resultCode));
        }
    }

    @Override
    public void save(RoomDO roomDO) {
        roomMapper.save(roomDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        roomMapper.save(map);
    }

    @Override
    public void saveBatch(List<RoomDO> list) {
        roomMapper.saveBatch(list);
    }

    @Override
    public int update(RoomDO roomDO) {
        return roomMapper.update(roomDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return roomMapper.update(map);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return roomMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return roomMapper.deleteBatch(ids);
    }

    @Override
    public RoomDO queryById(Object id) {
        return roomMapper.queryById(id);
    }

    @Override
    public List<RoomDO> queryList(RoomQueryCondition roomQueryCondition) {
        return roomMapper.queryList(roomQueryCondition);
    }

    @Override
    public int queryTotal(RoomQueryCondition roomQueryCondition) {
        return roomMapper.queryTotal(roomQueryCondition);
    }

    @Override
    public int total() {
        return roomMapper.total();
    }

    @Override
    public ApiResult getPersonRoomList(Long personId) {

        // 查询学生的所有教室(直播)
        List<RoomListBO> roomList = roomMapper.queryRoomByPersonId(personId);

        PersonRecordingQueryCondition prQuery = new PersonRecordingQueryCondition();
        prQuery.setPersonId(personId);
        List<RecordingBO> prList = personRecordingMapper.queryPersonRecordingList(prQuery);

        List<RoomListBO> resultList = new ArrayList<>();

        // 获取各个房间的录制件
        for(RoomListBO room : roomList) {
            // 设置直播房间状态
            try {
                // 提前七分钟开课
                Date current = new Date();
                long currentTime = current.getTime();
                long previewTime = DateUtil.addMinutes(current, 7).getTime();
                long startTime = DateUtil.parseDate(room.getStartTime(), DateUtil.DatePatten.YYYY_MM_DD_HH_MM_SS).getTime();
                long endTime = DateUtil.parseDate(room.getEndTime(), DateUtil.DatePatten.YYYY_MM_DD_HH_MM_SS).getTime();
                if (endTime <= currentTime) {
                    // 直播完成 可查看录制件
                    room.setStatus(1);
                    prList.forEach(record -> {
                        if (record.getCreateDate().equals(DateUtil.format(startTime, DateUtil.DatePatten.YYYY_MM_DD))
                            && record.getRoomId().equals(room.getId())) {
                            room.setVideoUrl(record.getRecordPath());
                        }
                    });
                } else if (startTime <= previewTime) {
                    // 正在直播
                    room.setStatus(2);
                } else {
                    // 未开播
                    room.setStatus(3);
                }
                resultList.add(room);
            } catch (Exception e) {
                e.printStackTrace();
                throw new CustomException("时间格式化错误!");
            }

        }
        return new ApiResult(resultList);
    }
}
