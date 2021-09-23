package cn.smart.service.impl;

import cn.smart.bo.ClassCourseBO;
import cn.smart.bo.ClassInfoBO;
import cn.smart.common.exception.CustomException;
import cn.smart.common.utils.TokenGenerator;
import cn.smart.common.utils.Validator;
import cn.smart.condition.*;
import cn.smart.dao.*;
import cn.smart.entity.*;
import cn.smart.service.IClassInfoService;
import cn.smart.support.jpush.PushComponent;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
* @author Ye
* @date 2018-4-11 14:41:27
*/

@Service
public class ClassInfoServiceImpl  implements IClassInfoService {

    private static final Logger logger = LogManager.getLogger(ClassInfoServiceImpl.class);

    @Autowired
    private IClassInfoMapper classInfoMapper;

    @Autowired
    private IClassPersonMapper classPersonMapper;
    
    @Autowired
    private ICourseInfoMapper courseInfoMapper;

    @Autowired
    private IClassCourseMapper classCourseMapper;

    @Autowired
    private PushComponent pushComponent;

    @Autowired
    private IPersonMapper personMapper;

    private static ExecutorService executorService = new ThreadPoolExecutor(2, 20, 5L,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>(20), new ThreadPoolExecutor.DiscardOldestPolicy());

    @Transactional(rollbackFor = CustomException.class)
    @Override
    public void assignedPerson(ClassInfoBO classInfoBO) {

        ClassInfoDO classInfo = classInfoMapper.queryById(classInfoBO.getClassId());
        if (classInfo == null) {
            throw new CustomException("当前班级不存在!");
        }

        ClassPersonQueryCondition classPersonQuery = new ClassPersonQueryCondition();
        classPersonQuery.setExistsPersonId(classInfoBO.getStudentIds());
        List<ClassPersonDO> classPersonList = classPersonMapper.queryList(classPersonQuery);

        List<ClassPersonDO> classPersonDOList = new ArrayList<>(classInfoBO.getStudentIds().length);

        ClassPersonDO classPersonDO;
        for (int i = 0, len = classInfoBO.getStudentIds().length; i < len; i++) {
            // 学生不能分配到相同课阶的班级
            for (ClassPersonDO cp : classPersonList) {
                if (cp.getCourseStageId() != null && classInfo.getCourseStageId() != null
                        && cp.getCourseStageId().equals(classInfo.getCourseStageId()) && cp.getPersonId().equals(classInfoBO.getStudentIds()[i])) {
                    throw new CustomException("存在用户已经分配相同课阶班级, 操作失败");
                }
            }

            classPersonDO= new ClassPersonDO();
            classPersonDO.setClassId(classInfoBO.getClassId());
            classPersonDO.setPersonId(classInfoBO.getStudentIds()[i]);
            classPersonDOList.add(classPersonDO);
        }
        classPersonMapper.saveBatch(classPersonDOList);

        // 更新当前班级学生id集合字段
        String personIds = StringUtils.isEmpty(classInfo.getPersonIds()) ? "" : classInfo.getPersonIds() + ",";
        personIds = personIds + StringUtils.join(classInfoBO.getStudentIds(), ",");
        classInfo.setPersonIds(personIds);
        classInfoMapper.update(classInfo);

        // 推送更新用户标签
        executorService.submit(() -> {
            // 查询需要推送的用户
            PersonQueryCondition personQuery = new PersonQueryCondition();
            personQuery.setExitsPersonIds(classInfoBO.getStudentIds());
            List<PersonDO> listPerson = personMapper.queryList(personQuery);

            if (listPerson.size() > 0 ) {
                for (PersonDO person : listPerson) {
                    if (person.getPushAlias() != null) {
                        Map<String, String> extras = new HashMap<>(16);
                        extras.put("type", MessageDO.MessageType.UPDATE_TAGS);
                        extras.put("tags", classInfo.getPushTag());
                        extras.put("action", "add");
                        pushComponent.pushMessageAndroidWithAlias("", "", extras, person.getPushAlias());
                    } else {
                        logger.error("推送更新班级Tag失败, 用户没有设置推送别名! 用户: {}", person);
                    }
                }
            } else {
                logger.error("推送更新班级tag失败, 没有查询到需要推送的用户! 用户集合: {}", JSONObject.toJSONString(classInfoBO.getStudentIds()));
            }
        });
    }

    @Override
    public void updateClassInfo(ClassInfoDO classInfo) {

        if (StringUtils.isEmpty(classInfo.getPushTag())) {
            // 生成班级推送标签
            classInfo.setPushTag(TokenGenerator.generateValue().substring(0,27)+System.currentTimeMillis());
        }

        ClassInfoDO oldClass = queryById(classInfo.getId());

        classInfoMapper.updateAll(classInfo);

        if (null == oldClass.getCourseStageId()) {
            if (null != classInfo.getCourseStageId()) {
                CourseInfoQueryCondition courseInfoQuery = new CourseInfoQueryCondition();
                courseInfoQuery.setStageId(classInfo.getCourseStageId());
                List<CourseInfoDO> courseInfoList = courseInfoMapper.queryList(courseInfoQuery);
                if (courseInfoList!= null && courseInfoList.size() > 0) {
                    List<ClassCourseDO> classCourseDOList = new ArrayList<>(courseInfoList.size());
                    ClassCourseDO classCourseDO;
                    for (CourseInfoDO courseInfoDO : courseInfoList) {
                        classCourseDO = new ClassCourseDO();
                        classCourseDO.setClassId(classInfo.getId());
                        classCourseDO.setCourseInfoId(courseInfoDO.getId());
                        classCourseDO.setStatus(false);
                        classCourseDO.setCreateTime(new Date());
                        classCourseDOList.add(classCourseDO);
                    }
                    classCourseMapper.saveBatch(classCourseDOList);
                }
            }
        } else {
            if (null == classInfo.getCourseStageId()) {
                classCourseMapper.deleteByClassId(classInfo.getId());
            } else {
                CourseInfoQueryCondition courseInfoQuery = new CourseInfoQueryCondition();
                courseInfoQuery.setStageId(classInfo.getCourseStageId());
                List<CourseInfoDO> courseInfoList = courseInfoMapper.queryList(courseInfoQuery);
                if (courseInfoList!= null && courseInfoList.size() > 0) {
                    List<ClassCourseDO> classCourseDOList = new ArrayList<>(courseInfoList.size());
                    ClassCourseDO classCourseDO;
                    for (CourseInfoDO courseInfoDO : courseInfoList) {
                        classCourseDO = new ClassCourseDO();
                        classCourseDO.setClassId(classInfo.getId());
                        classCourseDO.setCourseInfoId(courseInfoDO.getId());
                        classCourseDO.setStatus(false);
                        classCourseDO.setCreateTime(new Date());
                        classCourseDOList.add(classCourseDO);
                    }
                    classCourseMapper.deleteByClassId(classInfo.getId());
                    classCourseMapper.saveBatch(classCourseDOList);
                }
            }
        }
    }

    @Override
    public List<ClassCourseBO> queryClassCourseByClassId(CourseInfoQueryCondition courseInfoQueryCondition) {

        return classInfoMapper.queryClassCourseByClassId(courseInfoQueryCondition);
    }

    @Override
    public List<PersonDO> queryClassPersonByClassId(PersonQueryCondition personQueryCondition) {
        return classInfoMapper.queryClassPersonByClassId(personQueryCondition);
    }

    @Override
    public Integer countClassPerson(PersonQueryCondition personQueryCondition) {
        return classInfoMapper.countClassPerson(personQueryCondition);
    }

    @Override
    public Integer countClassCourse(CourseInfoQueryCondition courseInfoQueryCondition) {
        return classInfoMapper.countClassCourse(courseInfoQueryCondition);
    }

    @Override
    public void assignedCourse(ClassInfoBO classInfoBO) {


        CourseInfoQueryCondition query = new CourseInfoQueryCondition();
        query.setStageId(classInfoBO.getStageId());

        // 更新班级课阶
        ClassInfoDO classInfo = classInfoMapper.queryById(classInfoBO.getClassId());
        classInfo.setCourseStageId(classInfoBO.getStageId());
        classInfoMapper.update(classInfo);

        // 更新班级课程
        List<CourseInfoDO> courseList = courseInfoMapper.queryList(query);
        List<ClassCourseDO> classCourseList = new ArrayList<>(courseList.size());

        ClassCourseDO classCourseDO;
        for (int i = 0, len = courseList.size(); i < len; i++) {
            classCourseDO = new ClassCourseDO();
            classCourseDO.setClassId(classInfoBO.getClassId());
            classCourseDO.setCourseInfoId(courseList.get(i).getId());
            classCourseDO.setStatus(false);
            classCourseDO.setCreateTime(new Date());
            classCourseList.add(classCourseDO);
        }
        if (classCourseList.size() > 0) {
            classCourseMapper.deleteByClassId(classInfoBO.getClassId());
            classCourseMapper.saveBatch(classCourseList);
        }
    }

    @Override
    public void cancelClassCourse(ClassInfoBO classInfoBO) {
        classCourseMapper.deleteBatch(classInfoBO.getClassCourseIds());
    }

    @Transactional(rollbackFor = CustomException.class)
    @Override
    public void cancelClassStudent(ClassInfoBO classInfoBO) {

        try {
            ClassInfoDO classInfo = classInfoMapper.queryById(classInfoBO.getClassId());
            if (StringUtils.isNotEmpty(classInfo.getPersonIds())) {
                String[] ids = classInfo.getPersonIds().split(",");
                List<String> personIds = new ArrayList<>(ids.length);
                for (String id : ids) {
                    for (Long aLong : classInfoBO.getStudentIds()) {
                        if (!Long.valueOf(id).equals(aLong)) {
                            personIds.add(id);
                        }
                    }
                }
                if (personIds.size() > 0) {
                    classInfo.setPersonIds(StringUtils.join(personIds, ","));
                    classInfoMapper.update(classInfo);
                }
            }
            classCourseMapper.deleteByClassIdOrPersonIdsBatch(classInfoBO);
            // 推送更新用户标签
            executorService.submit(() -> {
                // 查询需要推送的用户
                PersonQueryCondition personQuery = new PersonQueryCondition();
                personQuery.setExitsPersonIds(classInfoBO.getStudentIds());
                List<PersonDO> listPerson = personMapper.queryList(personQuery);

                if (listPerson.size() > 0 ) {
                    for (PersonDO person : listPerson) {
                        if (person.getPushAlias() != null) {
                            Map<String, String> extras = new HashMap<>(16);
                            extras.put("type", MessageDO.MessageType.UPDATE_TAGS);
                            extras.put("tags", classInfo.getPushTag());
                            extras.put("action", "delete");
                            pushComponent.pushMessageAndroidWithAlias("", "", extras, person.getPushAlias());
                        } else {
                            logger.error("推送更新班级Tag失败, 用户没有设置推送别名! 用户: {}", person);
                        }
                    }
                } else {
                    logger.error("推送更新班级tag失败, 没有查询到需要推送的用户! 用户集合: {}", JSONObject.toJSONString(classInfoBO.getStudentIds()));
                }
            });
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public void updateClassCourseStatus(ClassCourseDO classCourseDO) {
        classCourseMapper.update(classCourseDO);

        if (null != classCourseDO.getClassId()) {
            ClassInfoDO classInfo = classInfoMapper.queryById(classCourseDO.getClassId());
            ClassCourseQueryCondition query = new ClassCourseQueryCondition();
            query.setClassId(classCourseDO.getClassId());
            query.setOrderField("ci.serial_number");
            query.setOrder("desc");
            List<ClassCourseDO> classCourseList = classCourseMapper.queryList(query);
            for (ClassCourseDO course : classCourseList) {
                if (course.getStatus()) {
                    classInfo.setOpenCourseId(course.getCourseInfoId());
                    classInfoMapper.update(classInfo);
                    break;
                }
            }
        }
    }

    @Override
    public void saveClassInfo(ClassInfoDO classInfo) {

        // 生成班级推送标签
        classInfo.setPushTag(TokenGenerator.generateValue().substring(0,27)+System.currentTimeMillis());
        save(classInfo);

        // 关联班级课阶课程
        CourseInfoQueryCondition query = new CourseInfoQueryCondition();
        if (Validator.isNotEmpty(classInfo.getCourseStageId())) {
            query.setStageId(classInfo.getCourseStageId());
            List<CourseInfoDO> courseList = courseInfoMapper.queryList(query);
            List<ClassCourseDO> classCourseList = new ArrayList<>(courseList.size());

            ClassCourseDO classCourseDO;
            for (int i = 0, len = courseList.size(); i < len; i++) {
                classCourseDO = new ClassCourseDO();
                classCourseDO.setClassId(classInfo.getId());
                classCourseDO.setCourseInfoId(courseList.get(i).getId());
                classCourseDO.setStatus(false);
                classCourseDO.setCreateTime(new Date());
                classCourseList.add(classCourseDO);
            }
            if (classCourseList.size() > 0) {
                classCourseMapper.saveBatch(classCourseList);
            }
        }

    }

    @Override
    public void assignedPersonToDefaultClass(Long[] personIds) {

        ClassInfoQueryCondition classInfoQueryCondition = new ClassInfoQueryCondition();
        classInfoQueryCondition.setOneToMany(true);
        classInfoQueryCondition.setType(ClassInfoQueryCondition.CLASS_TYPE.ONE_TO_MANY);
        List<ClassInfoDO> classList = classInfoMapper.queryList(classInfoQueryCondition);

        if (classList.size() == 1) {
            ClassInfoDO classInfo = classList.get(0);

            ClassInfoBO classInfoBO = new ClassInfoBO();
            classInfoBO.setClassId(classInfo.getId());
            classInfoBO.setStudentIds(personIds);
            try{
                assignedPerson(classInfoBO);
            } catch (CustomException e) {
                logger.error(e.getMsg());
            }
        } else {
            logger.warn("未设置默认一对多班级, 用户分配班级失败!");
        }
    }

    @Override
    public void save(ClassInfoDO classInfoDO) {
        classInfoMapper.save(classInfoDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        classInfoMapper.save(map);
    }

    @Override
    public void saveBatch(List<ClassInfoDO> list) {
        classInfoMapper.saveBatch(list);
    }

    @Override
    public int update(ClassInfoDO classInfoDO) {
        return classInfoMapper.update(classInfoDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return classInfoMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {

        ClassInfoDO classInfo = classInfoMapper.queryById(id);
        // 推送 删除标签
        executorService.submit(() -> {
            if (classInfo.getPushTag() != null) {
                Map<String, String> extras = new HashMap<>(16);
                extras.put("type", MessageDO.MessageType.UPDATE_TAGS);
                extras.put("tags", classInfo.getPushTag());
                extras.put("action", "delete");
                pushComponent.pushMessageAndroidWithTags("", "", extras, classInfo.getPushTag());
            } else {
                logger.error("推送删除标签异常! 班级为设置标签 id: {} ", id);
            }
        });
        int i;
        try {
            // 删除班级课程
            classCourseMapper.deleteByClassId((Long) id);
            // 删除班级学生
            classPersonMapper.deleteByClassId((Long) id);
            // 删除班级
            i = classInfoMapper.deleteById(id);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
        return i;
    }

    @Override
    public int delete(Map<String, Object> map) {
        return classInfoMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return classInfoMapper.deleteBatch(ids);
    }

    @Override
    public ClassInfoDO queryById(Object id) {
        return classInfoMapper.queryById(id);
    }

    @Override
    public List<ClassInfoDO> queryList(ClassInfoQueryCondition classInfoQueryCondition) {
        return classInfoMapper.queryList(classInfoQueryCondition);
    }

    @Override
    public int queryTotal(ClassInfoQueryCondition classInfoQueryCondition) {
        return classInfoMapper.queryTotal(classInfoQueryCondition);
    }

    @Override
    public int total() {
        return classInfoMapper.total();
    }


}
