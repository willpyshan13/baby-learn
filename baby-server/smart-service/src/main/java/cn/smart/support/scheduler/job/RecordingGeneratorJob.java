package cn.smart.support.scheduler.job;

import cn.smart.common.exception.CustomException;
import cn.smart.common.utils.DateUtil;
import cn.smart.dao.IClassInfoMapper;
import cn.smart.dao.IPersonRecordingMapper;
import cn.smart.dao.IRecordingMapper;
import cn.smart.dao.IRoomMapper;
import cn.smart.entity.ClassInfoDO;
import cn.smart.entity.PersonRecordingDO;
import cn.smart.entity.RecordingDO;
import cn.smart.entity.RoomDO;
import cn.smart.support.talkcloud.business.RoomBusiness;
import cn.smart.support.talkcloud.entity.RecordResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author Ye
 */
@Component
public class RecordingGeneratorJob {

    private static final Logger logger = LogManager.getLogger(RecordingGeneratorJob.class);

    public static final String NAME = "recordingGeneratorJob";

    public static final String METHOD = "generateRecording";

    @Autowired
    private IRoomMapper roomMapper;

    @Autowired
    private IClassInfoMapper classInfoMapper;

    @Autowired
    private RoomBusiness roomBusiness;

    @Autowired
    private IRecordingMapper recordingMapper;

    @Autowired
    private IPersonRecordingMapper personRecordingMapper;

    @Transactional(rollbackFor = CustomException.class)
    public boolean generateRecording(Long roomId) {

        logger.info("RecordingGeneratorJob.generateRecording start. roomId={}", roomId);

        Date currentTime = new Date();
        RoomDO room = roomMapper.queryById(roomId);
        if (room == null) {
            logger.warn("Room not found!");
            return false;
        }

        ClassInfoDO classInfo = classInfoMapper.queryById(room.getClassId());
        if (classInfo == null || StringUtils.isEmpty(classInfo.getPersonIds())) {
            logger.warn("Class not found or class person not exist!");
            return false;
        }

        RecordResult result = roomBusiness.getRoomRecordList(roomId.intValue());
        if (result == null) {
            logger.warn("Room recording file not found!");
            return false;
        }

        if (result.getResult() == 0) {
            String currentDate = DateUtil.format(room.getStartTime(), DateUtil.DatePatten.YYYY_MM_DD);
            List<RecordResult.RecordlistBean> currentDateRecord = new ArrayList<>(result.getRecordlist().size());

            List<RecordResult.RecordlistBean> recordList = result.getRecordlist();
            recordList.forEach(r -> {
                Date d = DateUtil.timestamp2Data(Long.valueOf(r.getStarttime()));
                String recordDate = DateUtil.format(d, DateUtil.DatePatten.YYYY_MM_DD);
                if (currentDate.equals(recordDate)) {
                    currentDateRecord.add(r);
                }
            });

            if (currentDateRecord.size() > 0) {
                // 按文件大小降序
                currentDateRecord.sort((o1, o2) -> {
                    try {
                        long o1Size = Long.parseLong(o1.getSize());
                        long o2Size = Long.parseLong(o2.getSize());
                        return Long.compare(o2Size, o1Size);
                    } catch (NumberFormatException e) {
                        return -1;
                    }
                });

                try {
                    List<RecordingDO> recordingList = new ArrayList<>(recordList.size());
                    RecordingDO recording;
                    for (RecordResult.RecordlistBean record : currentDateRecord) {
                        recording = new RecordingDO();
                        recording.setRoomId(roomId);
                        recording.setCourseId(classInfo.getOpenCourseId());
                        recording.setTitle(record.getRecordtitle());
                        recording.setHttpsPlayPath(record.getHttps_playpath());
                        recording.setPlayPath(record.getPlaypath());
                        recording.setRecordPath(record.getRecordpath());
                        recording.setDuration(Double.valueOf(record.getDuration()));
                        recording.setSize(Double.valueOf(record.getSize()));
                        recording.setRecordDate(DateUtil.timestamp2Data(Long.valueOf(record.getStarttime())));
                        recording.setCreateTime(currentTime);
                        recordingList.add(recording);
                    }
                    recordingMapper.saveBatch(recordingList);

                    RecordingDO effectiveRecord = recordingList.get(0);
                    String[] ids = classInfo.getPersonIds().split(",");

                    List<PersonRecordingDO> personRecordingList = new ArrayList<>(ids.length);
                    PersonRecordingDO personRecording;
                    for (String id : ids) {
                        personRecording = new PersonRecordingDO();
                        personRecording.setPersonId(Long.valueOf(id));
                        personRecording.setCourseId(classInfo.getOpenCourseId());
                        personRecording.setRecordingId(effectiveRecord.getId());
                        personRecording.setCreateDate(effectiveRecord.getRecordDate());
                        personRecording.setCreateTime(currentTime);
                        personRecording.setRoomId(roomId);
                        personRecordingList.add(personRecording);
                    }
                    personRecordingMapper.saveBatch(personRecordingList);

                } catch (Exception e) {
                    logger.error("Insert into database error! reason : {}", e.getMessage());
                    e.printStackTrace();
                    throw new CustomException(e.getMessage());
                }
                logger.info("RecordingGeneratorJob.generateRecording end. Generate success. roomId={}", roomId);
                return true;
            } else {
                logger.error("RecordingGeneratorJob.generateRecording end. Generate failed. Current record is empty. roomId={}", roomId);
                return false;
            }
        } else {
            logger.error("RecordingGeneratorJob.generateRecording end. Generate failed. Room records are empty. roomId={}", roomId);
            return false;
        }
    }

    @Transactional(rollbackFor = CustomException.class)
    public boolean generateRecordingBackUp(Long roomId) {

        logger.info("RecordingGeneratorJob.generateRecording start. roomId={}", roomId);

        Date currentTime = new Date();
        RoomDO room = roomMapper.queryById(roomId);
        if (room == null) {
            logger.warn("Room not found!");
            return false;
        }

        ClassInfoDO classInfo = classInfoMapper.queryById(room.getClassId());
        if (classInfo == null || StringUtils.isEmpty(classInfo.getPersonIds())) {
            logger.warn("Class not found or class person not exist!");
            return false;
        }

        RecordResult result = roomBusiness.getRoomRecordList(roomId.intValue());
        if (result == null) {
            logger.warn("Room recording file not found!");
            return false;
        }

        if (result.getResult() == 0) {
            String currentDate = DateUtil.format(room.getStartTime(), DateUtil.DatePatten.YYYY_MM_DD);
            List<RecordResult.RecordlistBean> currentDateRecord = new ArrayList<>(result.getRecordlist().size());

            List<RecordResult.RecordlistBean> recordList = result.getRecordlist();
            recordList.forEach(r -> {
                Date d = DateUtil.timestamp2Data(Long.valueOf(r.getStarttime()));
                String recordDate = DateUtil.format(d, DateUtil.DatePatten.YYYY_MM_DD);
                if (currentDate.equals(recordDate)) {
                    currentDateRecord.add(r);
                }
            });

            if (currentDateRecord.size() > 0) {
                // 按文件大小降序
                currentDateRecord.sort((o1, o2) -> {
                    try {
                        long o1Size = Long.parseLong(o1.getSize());
                        long o2Size = Long.parseLong(o2.getSize());
                        return Long.compare(o2Size, o1Size);
                    } catch (NumberFormatException e) {
                        return -1;
                    }
                });

                try {
                    List<RecordingDO> recordingList = new ArrayList<>(recordList.size());
                    RecordingDO recording;
                    for (RecordResult.RecordlistBean record : currentDateRecord) {
                        recording = new RecordingDO();
                        recording.setRoomId(roomId);
                        recording.setCourseId(classInfo.getOpenCourseId());
                        recording.setTitle(record.getRecordtitle());
                        recording.setHttpsPlayPath(record.getHttps_playpath());
                        recording.setPlayPath(record.getPlaypath());
                        recording.setRecordPath(record.getRecordpath());
                        recording.setDuration(Double.valueOf(record.getDuration()));
                        recording.setSize(Double.valueOf(record.getSize()));
                        recording.setRecordDate(DateUtil.timestamp2Data(Long.valueOf(record.getStarttime())));
                        recording.setCreateTime(currentTime);
                        recordingList.add(recording);
                    }
                    recordingMapper.saveBatch(recordingList);

                    RecordingDO effectiveRecord = recordingList.get(0);
                    String[] ids = classInfo.getPersonIds().split(",");

                    List<PersonRecordingDO> personRecordingList = new ArrayList<>(ids.length);
                    PersonRecordingDO personRecording;
                    for (String id : ids) {
                        personRecording = new PersonRecordingDO();
                        personRecording.setPersonId(Long.valueOf(id));
                        personRecording.setCourseId(classInfo.getOpenCourseId());
                        personRecording.setRecordingId(effectiveRecord.getId());
                        personRecording.setCreateDate(effectiveRecord.getRecordDate());
                        personRecording.setCreateTime(currentTime);
                        personRecording.setRoomId(roomId);
                        personRecordingList.add(personRecording);
                    }
                    personRecordingMapper.saveBatch(personRecordingList);

                } catch (Exception e) {
                    logger.error("Insert into database error! reason : {}", e.getMessage());
                    e.printStackTrace();
                    throw new CustomException(e.getMessage());
                }
                logger.info("RecordingGeneratorJob.generateRecording end. Generate success. roomId={}", roomId);
                return true;
            } else {
                logger.error("RecordingGeneratorJob.generateRecording end. Generate failed. Current record is empty. roomId={}", roomId);
                return false;
            }
        } else {
            logger.error("RecordingGeneratorJob.generateRecording end. Generate failed. Room records are empty. roomId={}", roomId);
            return false;
        }
    }

}
