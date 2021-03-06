package cn.smart.service.impl;

import cn.smart.bo.*;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.api.result.ApiStatus;
import cn.smart.common.exception.CustomException;
import cn.smart.common.utils.DateUtil;
import cn.smart.common.utils.DateUtils;
import cn.smart.common.utils.Validator;
import cn.smart.condition.*;
import cn.smart.dao.*;
import cn.smart.entity.*;
import cn.smart.service.ICourseStageService;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* @author Ye
* @date 2018-4-3 15:41:07
*/

@Service
public class CourseStageServiceImpl  implements ICourseStageService {

    private static final Logger logger = LogManager.getLogger(CourseStageServiceImpl.class);

    private final ICourseStageMapper courseStageMapper;
    
    private final ICourseInfoMapper courseInfoMapper;
    
    private final ICourseContentMapper courseContentMapper;

    private final ITeacherMapper teacherMapper;
    
    private final IPersonCourseStageMapper personCourseStageMapper;

    @Autowired
    private IPersonLearnRecordMapper personLearnRecordMapper;

    @Autowired
    private IClassCourseMapper classCourseMapper;

    @Autowired
    private IClassInfoMapper classInfoMapper;

    @Autowired
    private ICourseStageGroupMapper courseStageGroupMapper;

    @Autowired
    private IPersonCourseStageGroupMapper personCourseStageGroupMapper;
    
    @Autowired
    private IPersonCourseClockMapper personCourseClockMapper;

    @Autowired
    private ICourseStageGroupGoodsMapper courseStageGroupGoodsMapper;

    @Autowired
    private IIntegralMapper integralMapper;

    @Autowired
    private IPersonMapper personMapper;

    @Autowired
    private IGoodsMapper goodsMapper;

    @Autowired
    public CourseStageServiceImpl(ICourseStageMapper courseStageMapper, ICourseInfoMapper courseInfoMapper, ICourseContentMapper courseContentMapper, ITeacherMapper teacherMapper, IPersonCourseStageMapper personCourseStageMapper) {
        this.courseStageMapper = courseStageMapper;
        this.courseInfoMapper = courseInfoMapper;
        this.courseContentMapper = courseContentMapper;
        this.teacherMapper = teacherMapper;
        this.personCourseStageMapper = personCourseStageMapper;
    }

    @Override
    public void save(CourseStageDO courseStageDO) {
        courseStageMapper.save(courseStageDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        courseStageMapper.save(map);
    }

    @Override
    public void saveBatch(List<CourseStageDO> list) {
        courseStageMapper.saveBatch(list);
    }

    @Override
    public int update(CourseStageDO courseStageDO) {
        return courseStageMapper.update(courseStageDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return courseStageMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return courseStageMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return courseStageMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return courseStageMapper.deleteBatch(ids);
    }

    @Override
    public CourseStageDO queryById(Object id) {
        return courseStageMapper.queryById(id);
    }

    @Override
    public List<CourseStageDO> queryList(CourseStageQueryCondition courseStageQueryCondition) {
        return courseStageMapper.queryList(courseStageQueryCondition);
    }

    @Override
    public int queryTotal(CourseStageQueryCondition courseStageQueryCondition) {
        return courseStageMapper.queryTotal(courseStageQueryCondition);
    }

    @Override
    public int total() {
        return courseStageMapper.total();
    }

    @Override
    public CourseStageDetailBO courseStageDetail(PersonDO person, Long id) {

        CourseStageDetailBO courseStage = new CourseStageDetailBO();
        courseStage.setBoughtCourseStage(false);

        // ??????????????????
        CourseStageDO courseStageInfo = courseStageMapper.queryByLevel(id);
        // ??????????????? ????????????
        if (Validator.isEmpty(courseStageInfo)) {
           return null;
        }
        courseStage.setCourseStage(courseStageInfo);

        // ??????????????????
        if (Validator.isNotEmpty(courseStageInfo.getTeacherId())) {
            TeacherDO teacher = teacherMapper.queryById(courseStageInfo.getTeacherId());
            courseStage.setTeacher(teacher);
        }

        
        // ??????????????????????????????
        if (!Validator.isEmpty(person)) {
            PersonCourseStageQueryCondition personStageQuery = new PersonCourseStageQueryCondition();
            personStageQuery.setPersonId(person.getPersonId());
            List<PersonCourseStageDO> personStageList = personCourseStageMapper.queryList(personStageQuery);
            for (PersonCourseStageDO personCourseStage : personStageList) {
                // ?????????????????????????????????, ??????????????????
                if (courseStageInfo.getId().equals(personCourseStage.getCourseStageId())) {
                    courseStage.setBoughtCourseStage(true);
                }
            }
        }

        // ????????????
        List<CourseInfoDetailBO> listCourseInfo = new ArrayList<>();
        CourseInfoQueryCondition courseInfoQueryCondition = new CourseInfoQueryCondition();
        courseInfoQueryCondition.setStageId(courseStageInfo.getId());
        courseInfoQueryCondition.setOrderField("serial_number");
        List<CourseInfoDO> listCourse = courseInfoMapper.queryList(courseInfoQueryCondition);

        // ??????????????????
        List<PersonLearnRecordDO> listLearnRecord = new ArrayList<>();
        if (Validator.isNotEmpty(person)) {
            PersonLearnRecordQueryCondition personLearnQuery = new PersonLearnRecordQueryCondition();
            personLearnQuery.setPersonId(person.getPersonId());
            listLearnRecord = personLearnRecordMapper.queryList(personLearnQuery);
        }

        if (listCourse.size() > 0) {
            for (CourseInfoDO c : listCourse) {
                CourseInfoBO course = new CourseInfoBO();

                course.setId(c.getId());
                course.setCourseName(c.getCourseName());
                course.setDescription(c.getDescription());
                course.setStageId(c.getStageId());
                course.setTeacherId(c.getTeacherId());
                course.setStatus(c.getStatus());
                course.setType(c.getType());
                course.setStartTime(c.getStartTime());
                course.setEndTime(c.getEndTime());
                course.setDuration(c.getDuration());
                course.setSerialNumber(c.getSerialNumber());
                course.setViewingAuthority(c.getViewingAuthority());
                course.setVideoUrl(c.getVideoUrl());
                course.setImageUrl(c.getImageUrl());
                course.setTtsText(c.getTtsText());
                course.setWord(c.getWord());
                course.setLearned(0);
                course.setPlayed(0);

                for (PersonLearnRecordDO learnRecord : listLearnRecord) {
                    if (learnRecord.getCourseId().equals(course.getId())
                            && learnRecord.getPersonId().equals(person.getPersonId())
                            && learnRecord.getType().equals(1)) {
                        course.setLearned(1);
                    } else if (learnRecord.getCourseId().equals(course.getId())
                            && learnRecord.getPersonId().equals(person.getPersonId())
                            && learnRecord.getType().equals(2)) {
                        course.setPlayed(1);
                    }
                }

                CourseInfoDetailBO courseInfo = new CourseInfoDetailBO();
                courseInfo.setCourseInfo(course);
                listCourseInfo.add(courseInfo);

            }
        }


        // ????????????????????????
        courseStage.setCourseInfoList(listCourseInfo);

        return courseStage;
    }

    @Override
    public ApiResult queryClockStageCourseList(PersonDO person) {

        boolean openCourseClockIn = false;
        ClockCourseStageBO clockCourseStage = new ClockCourseStageBO();
        clockCourseStage.setStageId(person.getLearningStage());

        // ????????????????????????
        CourseInfoQueryCondition courseQuery = new CourseInfoQueryCondition();
        courseQuery.setOrderField("serial_number");
        courseQuery.setStageId(person.getLearningStage());
        List<CourseInfoDO> allCourseList = courseInfoMapper.queryList(courseQuery);

        // ????????????????????????
        CourseStageGroupQueryCondition stageGroupQuery = new CourseStageGroupQueryCondition();
        stageGroupQuery.setOrderField("stage_id, code");
        stageGroupQuery.setStageId(person.getLearningStage());
        List<CourseStageGroupDO> allStageGroupList = courseStageGroupMapper.queryList(stageGroupQuery);

        // ??????????????????????????????????????????
        PersonCourseStageGroupQueryCondition personGroupQuery = new PersonCourseStageGroupQueryCondition();
        personGroupQuery.setOrderField("pcsg.stage_id, csg.code");
        personGroupQuery.setStageId(person.getLearningStage());
        personGroupQuery.setPersonId(person.getPersonId());
        List<PersonCourseStageGroupDO> allPersonGroupList = personCourseStageGroupMapper.queryList(personGroupQuery);

        // ??????????????????????????????????????????
        CourseStageGroupGoodsQueryCondition groupGoodsQuery = new CourseStageGroupGoodsQueryCondition();
        groupGoodsQuery.setStageId(person.getLearningStage());
        List<CourseStageGroupGoodsDO> allGroupGoodsList = courseStageGroupGoodsMapper.queryList(groupGoodsQuery);

        // ?????????????????????????????????
        PersonCourseClockQueryCondition courseClockQuery = new PersonCourseClockQueryCondition();
        courseClockQuery.setStageId(person.getLearningStage());
        courseClockQuery.setPersonId(person.getPersonId());
        List<PersonCourseClockDO> allCourseClockList = personCourseClockMapper.queryList(courseClockQuery);

        // ????????????????????????
        // PersonLearnRecordQueryCondition learnRecordQuery = new PersonLearnRecordQueryCondition();
        // learnRecordQuery.setPersonId(person.getPersonId());
        // learnRecordQuery.setType(1);
        // List<PersonLearnRecordDO> allRecordList = personLearnRecordMapper.queryList(learnRecordQuery);

        // ?????????????????????????????? ?????????????????????????????????id??????
        ClassInfoQueryCondition classInfoQuery = new ClassInfoQueryCondition();
        classInfoQuery.setPersonId(person.getPersonId());
        classInfoQuery.setCourseStageId(person.getLearningStage());
        List<ClassInfoDO> classInfoList = classInfoMapper.queryAllClassPerson(classInfoQuery);

        // ??????????????????????????????????????????
        IntegralQueryCondition integralQuery = new IntegralQueryCondition();
        integralQuery.setPersonId(person.getPersonId());
        // ????????????
        integralQuery.setSource(2);
        List<IntegralDO> allIntegralList = integralMapper.queryList(integralQuery);

        ClassInfoDO classInfo;
        if (classInfoList.size() != 1 || classInfoList.get(0) == null) {
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "?????????????????????????????????????????????, ????????????!");
        } else {
            classInfo = classInfoList.get(0);
        }
        clockCourseStage.setCurrentOpenCourseId(classInfo.getOpenCourseId());

        // ????????????????????????????????????
        ClassCourseQueryCondition classQuery = new ClassCourseQueryCondition();
        classQuery.setClassId(classInfo.getId());
        List<ClassCourseDO> classCourseList = classCourseMapper.queryList(classQuery);

        // ????????????

        List<ClockGroupBO> groupList = new ArrayList<>(allStageGroupList.size());
        ClockGroupBO group;
        for(PersonCourseStageGroupDO pcsGroup : allPersonGroupList) {
            group = new ClockGroupBO();
            group.setGroupId(pcsGroup.getGroupId());
            for (CourseStageGroupDO csGroup : allStageGroupList) {
                if (csGroup.getId().equals(pcsGroup.getGroupId())) {
                    group.setGroupName(csGroup.getName());
                    group.setGroupCode(csGroup.getCode());
                }
            }
            group.setCurrentPoints(pcsGroup.getCurrentPoints());

            boolean currentGroup = false;

            List<ClockGroupCourseBO> groupCourseList = new ArrayList<>(16);
            ClockGroupCourseBO groupCourse;
            for (CourseInfoDO courseInfo : allCourseList) {
                boolean open = false;
                String openDate = "";
                if (courseInfo.getGroupId().equals(group.getGroupId())) {
                    groupCourse = new ClockGroupCourseBO();
                    groupCourse.setCourseId(courseInfo.getId());
                    groupCourse.setCourseName(courseInfo.getCourseName());
                    groupCourse.setStampImageUrl(courseInfo.getStampImageUrl());
                    groupCourse.setCardImageUrl(courseInfo.getCardImageUrl());
                    groupCourse.setClockIn(false);

                    int star = 0;
                    for (PersonCourseClockDO cs : allCourseClockList) {
                        if (groupCourse.getCourseId().equals(cs.getCourseId())) {
                            groupCourse.setClockIn(true);
                            star += 1;
                            if (cs.getCourseId().equals(clockCourseStage.getCurrentOpenCourseId())) {
                                openCourseClockIn = true;
                            }
                        }
                    }
                    for(IntegralDO integral : allIntegralList) {
                        if (null != integral.getCourseId() && integral.getCourseId().equals(groupCourse.getCourseId())) {
                            star += integral.getNumber();
                        }
                    }
                    groupCourse.setStar(star);
                    for (ClassCourseDO cs : classCourseList) {
                        if (courseInfo.getId().equals(cs.getCourseInfoId()) && cs.getStatus()) {
                            open = true;
                            openDate = DateUtil.format(cs.getUpdateTime(), DateUtil.DatePatten.YYYY_MM_DD);
                        }
                    }
                    groupCourse.setOpen(open);
                    groupCourse.setOpenDate(openDate);

                    if (classInfo.getOpenCourseId().equals(courseInfo.getId())) {
                        currentGroup = true;
                    }
                    groupCourseList.add(groupCourse);
                }
            }

            List<ClockGroupGoodsBO> groupGoodsList = new ArrayList<>(16);
            ClockGroupGoodsBO groupGoods;
            for (CourseStageGroupGoodsDO csgGoods : allGroupGoodsList) {
                if (group.getGroupId().equals(csgGoods.getGroupId())) {
                    groupGoods = new ClockGroupGoodsBO();
                    groupGoods.setGoodsId(csgGoods.getGoodsId());
                    groupGoods.setGoodsName(csgGoods.getGoodsName());
                    groupGoods.setGoodsImageUrl(csgGoods.getGoodsImageUrl());
                    groupGoods.setExchangePoints(csgGoods.getExchangePoints());

                    groupGoodsList.add(groupGoods);
                }
            }

            // ?????????????????????
            group.setCourseList(groupCourseList);
            // ???????????????????????????
            group.setGoodsList(groupGoodsList);
            // ????????????????????????????????????????????????
            group.setCurrentGroup(currentGroup);

            groupList.add(group);
        }

        // ??????????????????????????????????????????
        clockCourseStage.setClockIn(openCourseClockIn);
        clockCourseStage.setGroupList(groupList);

        return new ApiResult(clockCourseStage);
    }

    @Transactional(rollbackFor = CustomException.class)
    @Override
    public ApiResult clockStageGroupCourse(PersonDO person, Long stageId, Long groupId, Long courseId) {

        try {
            PersonCourseClockQueryCondition pccQuery = new PersonCourseClockQueryCondition();
            pccQuery.setPersonId(person.getPersonId());
            pccQuery.setStageId(stageId);
            pccQuery.setGroupId(groupId);
            pccQuery.setCourseId(courseId);
            List<PersonCourseClockDO> pccList = personCourseClockMapper.queryList(pccQuery);
            if (pccList.size() > 0) {
                return new ApiResult(-1, ApiStatus.SC_COURSE_CLOCK_IN, "????????????????????????");
            }

            // ??????????????????
            Date date = new Date();
            PersonCourseClockDO courseClock = new PersonCourseClockDO();
            courseClock.setCourseId(courseId);
            courseClock.setGroupId(groupId);
            courseClock.setPoints(1);
            courseClock.setPersonId(person.getPersonId());
            courseClock.setStageId(stageId);
            courseClock.setClockDate(DateUtil.format(date, "yyyy-MM-dd"));
            courseClock.setCreateBy(person.getPersonId());
            courseClock.setCreateTime(date);
            personCourseClockMapper.save(courseClock);

            // ??????????????????
            IntegralDO integral = new IntegralDO();
            integral.setNumber(1);
            integral.setType(2);
            integral.setSource(1);
            integral.setCreateDate(DateUtil.format(new Date()));
            integral.setCreateTime(new Date());
            integral.setPersonId(person.getPersonId());
            integral.setCourseId(courseId);
            integralMapper.save(integral);

            // ??????????????????
            int point = person.getCandyPoints() == null ? 1 : person.getCandyPoints() + 1;
            person.setCandyPoints(point);
            personMapper.update(person);

            // ??????????????????????????????
            PersonCourseStageGroupQueryCondition pcsQuery = new PersonCourseStageGroupQueryCondition();
            pcsQuery.setGroupId(groupId);
            pcsQuery.setPersonId(person.getPersonId());
            pcsQuery.setStageId(stageId);
            List<PersonCourseStageGroupDO> pcsGroupList = personCourseStageGroupMapper.queryList(pcsQuery);
            if (pcsGroupList.size() != 1) {
                throw new CustomException("??????????????????????????????, ????????????!");
            }
            PersonCourseStageGroupDO pcsGroup = pcsGroupList.get(0);
            Integer stageGroupPoints = pcsGroup.getCurrentPoints() == null ? 1 : pcsGroup.getCurrentPoints() + 1;
            pcsGroup.setCurrentPoints(stageGroupPoints);
            personCourseStageGroupMapper.update(pcsGroup);
        } catch (Exception e) {
            logger.error("??????????????????????????????, ??????: {}", e.getMessage());
            throw new CustomException("????????????!");
        }
        return new ApiResult("????????????");
    }

    @Override
    public ApiResult queryPersonGroupProgress(PersonDO person, Long groupId) {
        // ????????????????????????????????????
        ClassInfoQueryCondition classQuery = new ClassInfoQueryCondition();
        classQuery.setPersonId(person.getPersonId());
        classQuery.setCourseStageId(person.getLearningStage());
        List<ClassInfoDO> classInfoList = classInfoMapper.queryAllClassPerson(classQuery);
        if (classInfoList.size() != 1) {
            logger.error("??????????????????????????????! ???????????????????????????????????????????????? personId={}, stageId={}", person.getPersonId(), person.getLearningStage());
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "?????????????????????????????????, ??????????????????????????????");
        }

        ClassInfoDO classInfo = classInfoList.get(0);
        // ??????????????????????????????
        CourseInfoDO courseInfo = courseInfoMapper.queryById(classInfo.getOpenCourseId());
        boolean currentGroup = courseInfo.getGroupId() != null && courseInfo.getGroupId().equals(groupId);

        // ??????????????????????????????
        boolean clockIn = false;
        String currentDate = DateUtils.format(new Date(), "yyyy-MM-dd");

        // ??????????????????????????????????????????
        ClassInfoQueryCondition classInfoQueryCondition = new ClassInfoQueryCondition();
        classInfoQueryCondition.setOpenTime(currentDate);
        classInfoQueryCondition.setCourseId(courseInfo.getId());
        classInfoQueryCondition.setPersonId(person.getPersonId());
        if (person.getLearningStage() != null) {
            classInfoQueryCondition.setCourseStageId(person.getLearningStage());
        }

        List<ClassInfoDO> classPersonList = classInfoMapper.queryAllClassPerson(classInfoQueryCondition);
        // ??????????????????????????????????????????
        ClassInfoDO temp = null;
        if (classPersonList.size() > 0) {
            temp = classPersonList.get(0);
        }
        // ??????????????????????????????, ???????????????
        if (temp == null || temp.getOpenCourseId() == null) {
            clockIn = true;
        } else {
            // ??????????????????????????????
            PersonCourseClockQueryCondition personCourseClockQueryCondition = new PersonCourseClockQueryCondition();
            personCourseClockQueryCondition.setPersonId(person.getPersonId());
            personCourseClockQueryCondition.setCourseId(temp.getOpenCourseId());
            personCourseClockQueryCondition.setClockDate(currentDate);
            List<PersonCourseClockDO> personCourseClockList = personCourseClockMapper.queryList(personCourseClockQueryCondition);
            if (personCourseClockList.size() > 0) {
                clockIn = true;
            }
        }

        PersonCourseStageGroupQueryCondition pcsGroupQuery = new PersonCourseStageGroupQueryCondition();
        pcsGroupQuery.setPersonId(person.getPersonId());
        pcsGroupQuery.setGroupId(groupId);
        pcsGroupQuery.setStageId(person.getLearningStage());
        List<PersonCourseStageGroupDO> pcsGroupList = personCourseStageGroupMapper.queryList(pcsGroupQuery);
        if (pcsGroupList.size() != 1) {
            logger.error("??????????????????????????????! ??????????????????????????????????????? personId={}, stageId={}, groupId={}");
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "????????????????????????????????????");
        }

        PersonCourseStageGroupDO pcsGroup = pcsGroupList.get(0);

        CourseStageGroupGoodsQueryCondition csgGoodsQuery = new CourseStageGroupGoodsQueryCondition();
        csgGoodsQuery.setStageId(person.getLearningStage());
        csgGoodsQuery.setGroupId(pcsGroup.getGroupId());
        List<CourseStageGroupGoodsDO> csgGoodsList = courseStageGroupGoodsMapper.queryList(csgGoodsQuery);

        Map<String, Object> map = new HashMap<>(16);
        if (csgGoodsList.size() > 0) {
            CourseStageGroupGoodsDO csgGoods = csgGoodsList.get(0);
            map.put("goalPoints", csgGoods.getExchangePoints());
        } else {
            map.put("goalPoints", 0);
        }
        map.put("groupId", pcsGroup.getGroupId());
        map.put("groupCode", pcsGroup.getGroupCode());
        map.put("currentPoints", pcsGroup.getCurrentPoints());
        // ??????????????????????????????
        map.put("clockIn", clockIn);
        map.put("currentGroup", currentGroup);
        return new ApiResult(map);
    }

    @Override
    public List<CourseStageGroupDO> queryCourseStageGroupList(Long stageId) {

        CourseStageGroupQueryCondition groupQuery = new CourseStageGroupQueryCondition();
        groupQuery.setStageId(stageId);
        groupQuery.setOrderField("code");

        return courseStageGroupMapper.queryList(groupQuery);
    }

    @Transactional(rollbackFor = CustomException.class)
    @Override
    public void createStageGroup(CourseStageGroupDO group) {

        try {
            courseStageGroupMapper.save(group);

            // ?????????????????????????????????
            PersonCourseStageQueryCondition pcsQuery = new PersonCourseStageQueryCondition();
            pcsQuery.setCourseStageId(group.getStageId());
            List<PersonCourseStageDO> personCourseStageList = personCourseStageMapper.queryList(pcsQuery);

            List<PersonCourseStageGroupDO> pcsgList = new ArrayList<>(personCourseStageList.size());
            if (personCourseStageList.size() > 0) {
                PersonCourseStageGroupDO pcsg;
                for (PersonCourseStageDO pcs : personCourseStageList) {
                    pcsg = new PersonCourseStageGroupDO();
                    if (pcs.getPersonId() != null) {
                        pcsg.setStageId(pcs.getCourseStageId());
                        pcsg.setGroupId(group.getId());
                        pcsg.setPersonId(pcs.getPersonId());
                        pcsg.setCurrentPoints(0);
                        pcsg.setCreateTime(new Date());
                        pcsg.setCreateBy(group.getCreateBy());

                        PersonCourseStageGroupQueryCondition pcsgQuery = new PersonCourseStageGroupQueryCondition();
                        pcsgQuery.setPersonId(pcsg.getPersonId());
                        pcsgQuery.setStageId(pcsg.getStageId());
                        pcsgQuery.setGroupId(group.getId());
                        List<PersonCourseStageGroupDO> pList = personCourseStageGroupMapper.queryList(pcsgQuery);
                        if (pList.size() == 0) {
                            pcsgList.add(pcsg);
                        }
                    }
                }
            }
            personCourseStageGroupMapper.saveBatch(pcsgList);
        } catch (Exception e) {
            throw new CustomException("??????????????????????????????, ??????: " + e.getMessage());
        }

    }

    @Override
    public void updateStageGroup(CourseStageGroupDO group) {
        courseStageGroupMapper.update(group);
    }

    @Override
    public void deleteStageGroup(Long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("groupId", id);
        personCourseStageGroupMapper.delete(map);
        courseStageGroupMapper.deleteById(id);
    }

    @Override
    public List<GoodsDO> queryCourseStageGroupGoodsList(GoodsQueryCondition query) {
        return goodsMapper.queryGoodsWithStageGroup(query);
    }

    @Override
    public int countCourseStageGroupGoods(GoodsQueryCondition query) {
        return goodsMapper.countGoodsWithStageGroup(query);
    }

    @Override
    public void saveStageGroupGoodsBatch(List<CourseStageGroupGoodsDO> groupGoods) {
        courseStageGroupGoodsMapper.saveBatch(groupGoods);
    }

    @Override
    public void deleteGroupGoodsById(Long id) {
        courseStageGroupGoodsMapper.deleteById(id);
    }

    @Override
    public void updateStageGroupGoods(CourseStageGroupGoodsDO groupGoods) {
        courseStageGroupGoodsMapper.update(groupGoods);
    }

    @Override
    public CourseStageGroupDO queryCourseStageGroupById(Long id) {
        return courseStageGroupMapper.queryById(id);
    }
}
