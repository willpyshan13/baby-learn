package cn.smart.service.impl;

import cn.smart.bo.CoursewareBO;
import cn.smart.common.exception.CustomException;
import cn.smart.common.utils.FileType;
import cn.smart.common.utils.Validator;
import cn.smart.condition.CoursewareQueryCondition;
import cn.smart.dao.ICoursewareMapper;
import cn.smart.dao.IRoomCoursewareMapper;
import cn.smart.entity.CoursewareDO;
import cn.smart.entity.RoomCoursewareDO;
import cn.smart.service.ICoursewareService;
import cn.smart.support.talkcloud.business.RoomBusiness;
import cn.smart.support.talkcloud.utils.ErrorCode;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-4-25 9:37:34
*/

@Service
public class CoursewareServiceImpl  implements ICoursewareService {

    private final Logger log = LogManager.getLogger(CoursewareServiceImpl.class);

    @Autowired
    private ICoursewareMapper coursewareMapper;

    @Autowired
    private IRoomCoursewareMapper roomCoursewareMapper;

    @Autowired
    private RoomBusiness roomBusiness;

    @Override
    public void saveCourseware(Long roomId, CoursewareDO coursewareDO) {

         // 保存课件信息
        coursewareDO.setCreateTime(new Date());
        save(coursewareDO);
        if (!Validator.isEmpty(roomId)) {
            // 保存课件与教室关联关系
            RoomCoursewareDO roomCourseware = new RoomCoursewareDO();
            roomCourseware.setRoomId(roomId);
            roomCourseware.setCoursewareId(coursewareDO.getId());
            roomCourseware.setCreateTime(new Date());
            roomCoursewareMapper.save(roomCourseware);
            Map result;
            // 同步拓课云
            if (!Validator.isEmpty(coursewareDO.getIsDefault()) && coursewareDO.getIsDefault() == 1) {
                result =roomBusiness.roomBindFile(roomId, coursewareDO.getId(), new Long[] {coursewareDO.getId()});
            } else {
                result =roomBusiness.roomBindFile(roomId, null, new Long[] {coursewareDO.getId()});
            }
            Integer resultCode = (Integer) result.get("result");
            // 执行失败
            if (resultCode != 0) {
                throw new CustomException("拓课云上传课件失败! code = " + resultCode.toString() + ", msg : " + ErrorCode.getErrorMessage(resultCode));
            }

        }
    }

    @Override
    public List<CoursewareBO> queryCoursewareByRoom(CoursewareQueryCondition coursewareQueryCondition) {

        List<CoursewareBO> listCourseware = coursewareMapper.queryCoursewareByRoom(coursewareQueryCondition);

        // 同步拓课云教室课件列表
//        syncCoursewareTalkCloud(coursewareQueryCondition, listCourseware);


        return listCourseware;
    }

    private void syncCoursewareTalkCloud(CoursewareQueryCondition coursewareQueryCondition, List<CoursewareBO> listCourseware) {
        if (listCourseware.size() == 0 && coursewareQueryCondition.getRoomId() != null) {
            Map result = roomBusiness.getRoomFile(coursewareQueryCondition.getRoomId());
            if (0 == (Integer) result.get("result")) {
                CoursewareBO courseware;
                CoursewareDO coursewareDO;
                try {
                    JSONArray json = JSONArray.parseArray(result.get("roomfile").toString());

                    for (Object aJson : json) {
                        JSONObject o = (JSONObject) aJson;
                        courseware = new CoursewareBO();
                        courseware.setId(o.getLong("fileid"));
                        courseware.setName(o.getString("filename"));
                        courseware.setDynamicPpt(o.getInteger("dynamicppt"));
                        courseware.setType(FileType.checkType(o.getString("filetype")));
                        courseware.setIsOpen(o.getInteger("1"));
                        courseware.setResourceUrl(o.getString("filepath"));
                        courseware.setUploadType(0);
                        courseware.setStatus(o.getInteger("status"));
                        courseware.setSize(o.getDouble("size"));
                        courseware.setCreateTime(o.getDate("uploadtime"));


                        coursewareDO = new CoursewareDO();
                        coursewareDO.setId(courseware.getId());
                        coursewareDO.setName(courseware.getName());
                        coursewareDO.setDynamicPpt(courseware.getDynamicPpt());
                        coursewareDO.setType(courseware.getType());
                        coursewareDO.setIsOpen(courseware.getIsOpen());
                        coursewareDO.setResourceUrl(courseware.getResourceUrl());
                        coursewareDO.setUploadType(0);
                        coursewareDO.setStatus(courseware.getStatus());
                        coursewareDO.setSize(courseware.getSize());
                        coursewareDO.setCreateTime(courseware.getCreateTime());
                        try {
                            coursewareMapper.save(coursewareDO);
                        } catch (Exception e) {
                            log.error(e.getMessage());
                        }


                        RoomCoursewareDO roomCoursewareDO = new RoomCoursewareDO();
                        roomCoursewareDO.setCoursewareId(coursewareDO.getId());
                        roomCoursewareDO.setRoomId(coursewareQueryCondition.getRoomId());
                        roomCoursewareDO.setCreateTime(coursewareDO.getCreateTime());
                        try {
                            roomCoursewareMapper.save(roomCoursewareDO);
                        } catch (Exception e) {
                            log.error(e.getMessage());
                        }

                        courseware.setRoomCoursewareId(roomCoursewareDO.getId());
                        listCourseware.add(courseware);

                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        }
    }

    @Override
    public int countCoursewareByRoom(CoursewareQueryCondition coursewareQueryCondition) {
        return coursewareMapper.countCoursewareByRoom(coursewareQueryCondition);
    }

    @Override
    public List<CoursewareDO> getCoursewareToRoom(CoursewareQueryCondition coursewareQueryCondition) {
        return coursewareMapper.getCoursewareToRoom(coursewareQueryCondition);
    }

    @Override
    public int countCoursewareToRoom(CoursewareQueryCondition coursewareQueryCondition) {
        return coursewareMapper.countCoursewareToRoom(coursewareQueryCondition);
    }

    @Override
    public void save(CoursewareDO coursewareDO) {
        coursewareMapper.save(coursewareDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        coursewareMapper.save(map);
    }

    @Override
    public void saveBatch(List<CoursewareDO> list) {
        coursewareMapper.saveBatch(list);
    }

    @Override
    public int update(CoursewareDO coursewareDO) {
        return coursewareMapper.update(coursewareDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return coursewareMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("coursewareId", id);
        roomCoursewareMapper.delete(map);
        return coursewareMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return coursewareMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return coursewareMapper.deleteBatch(ids);
    }

    @Override
    public CoursewareDO queryById(Object id) {
        return coursewareMapper.queryById(id);
    }

    @Override
    public List<CoursewareDO> queryList(CoursewareQueryCondition coursewareQueryCondition) {
        return coursewareMapper.queryList(coursewareQueryCondition);
    }

    @Override
    public int queryTotal(CoursewareQueryCondition coursewareQueryCondition) {
        return coursewareMapper.queryTotal(coursewareQueryCondition);
    }

    @Override
    public int total() {
        return coursewareMapper.total();
    }
}
