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

        // 查询课阶信息
        CourseStageDO courseStageInfo = courseStageMapper.queryByLevel(id);
        // 课阶不存在 直接返回
        if (Validator.isEmpty(courseStageInfo)) {
           return null;
        }
        courseStage.setCourseStage(courseStageInfo);

        // 设置课阶教师
        if (Validator.isNotEmpty(courseStageInfo.getTeacherId())) {
            TeacherDO teacher = teacherMapper.queryById(courseStageInfo.getTeacherId());
            courseStage.setTeacher(teacher);
        }

        
        // 当前用户购买课阶信息
        if (!Validator.isEmpty(person)) {
            PersonCourseStageQueryCondition personStageQuery = new PersonCourseStageQueryCondition();
            personStageQuery.setPersonId(person.getPersonId());
            List<PersonCourseStageDO> personStageList = personCourseStageMapper.queryList(personStageQuery);
            for (PersonCourseStageDO personCourseStage : personStageList) {
                // 当前用户购买已购买课阶, 课程内容可看
                if (courseStageInfo.getId().equals(personCourseStage.getCourseStageId())) {
                    courseStage.setBoughtCourseStage(true);
                }
            }
        }

        // 课程信息
        List<CourseInfoDetailBO> listCourseInfo = new ArrayList<>();
        CourseInfoQueryCondition courseInfoQueryCondition = new CourseInfoQueryCondition();
        courseInfoQueryCondition.setStageId(courseStageInfo.getId());
        courseInfoQueryCondition.setOrderField("serial_number");
        List<CourseInfoDO> listCourse = courseInfoMapper.queryList(courseInfoQueryCondition);

        // 用户学习记录
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


        // 设置课阶课程信息
        courseStage.setCourseInfoList(listCourseInfo);

        return courseStage;
    }

    @Override
    public ApiResult queryClockStageCourseList(PersonDO person) {

        boolean openCourseClockIn = false;
        ClockCourseStageBO clockCourseStage = new ClockCourseStageBO();
        clockCourseStage.setStageId(person.getLearningStage());

        // 查询课阶所有课程
        CourseInfoQueryCondition courseQuery = new CourseInfoQueryCondition();
        courseQuery.setOrderField("serial_number");
        courseQuery.setStageId(person.getLearningStage());
        List<CourseInfoDO> allCourseList = courseInfoMapper.queryList(courseQuery);

        // 查询课阶下的单元
        CourseStageGroupQueryCondition stageGroupQuery = new CourseStageGroupQueryCondition();
        stageGroupQuery.setOrderField("stage_id, code");
        stageGroupQuery.setStageId(person.getLearningStage());
        List<CourseStageGroupDO> allStageGroupList = courseStageGroupMapper.queryList(stageGroupQuery);

        // 查询用户课阶下个单元积分情况
        PersonCourseStageGroupQueryCondition personGroupQuery = new PersonCourseStageGroupQueryCondition();
        personGroupQuery.setOrderField("pcsg.stage_id, csg.code");
        personGroupQuery.setStageId(person.getLearningStage());
        personGroupQuery.setPersonId(person.getPersonId());
        List<PersonCourseStageGroupDO> allPersonGroupList = personCourseStageGroupMapper.queryList(personGroupQuery);

        // 查询课阶单元下所有兑换的商品
        CourseStageGroupGoodsQueryCondition groupGoodsQuery = new CourseStageGroupGoodsQueryCondition();
        groupGoodsQuery.setStageId(person.getLearningStage());
        List<CourseStageGroupGoodsDO> allGroupGoodsList = courseStageGroupGoodsMapper.queryList(groupGoodsQuery);

        // 用户当前课阶打卡的课程
        PersonCourseClockQueryCondition courseClockQuery = new PersonCourseClockQueryCondition();
        courseClockQuery.setStageId(person.getLearningStage());
        courseClockQuery.setPersonId(person.getPersonId());
        List<PersonCourseClockDO> allCourseClockList = personCourseClockMapper.queryList(courseClockQuery);

        // 用户课程学习记录
        // PersonLearnRecordQueryCondition learnRecordQuery = new PersonLearnRecordQueryCondition();
        // learnRecordQuery.setPersonId(person.getPersonId());
        // learnRecordQuery.setType(1);
        // List<PersonLearnRecordDO> allRecordList = personLearnRecordMapper.queryList(learnRecordQuery);

        // 当前用户所在班级信息 当前班级最新开课的课程id信息
        ClassInfoQueryCondition classInfoQuery = new ClassInfoQueryCondition();
        classInfoQuery.setPersonId(person.getPersonId());
        classInfoQuery.setCourseStageId(person.getLearningStage());
        List<ClassInfoDO> classInfoList = classInfoMapper.queryAllClassPerson(classInfoQuery);

        // 查询用户所有课程积分获取情况
        IntegralQueryCondition integralQuery = new IntegralQueryCondition();
        integralQuery.setPersonId(person.getPersonId());
        // 课程积分
        integralQuery.setSource(2);
        List<IntegralDO> allIntegralList = integralMapper.queryList(integralQuery);

        ClassInfoDO classInfo;
        if (classInfoList.size() != 1 || classInfoList.get(0) == null) {
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "相同课阶的班级同时拥有当前用户, 操作失败!");
        } else {
            classInfo = classInfoList.get(0);
        }
        clockCourseStage.setCurrentOpenCourseId(classInfo.getOpenCourseId());

        // 查询当前班级课程解锁状态
        ClassCourseQueryCondition classQuery = new ClassCourseQueryCondition();
        classQuery.setClassId(classInfo.getId());
        List<ClassCourseDO> classCourseList = classCourseMapper.queryList(classQuery);

        // 组装信息

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

            // 当前单元的课程
            group.setCourseList(groupCourseList);
            // 当前单元可兑换商品
            group.setGoodsList(groupGoodsList);
            // 当前单元是否包含当前班级打开课程
            group.setCurrentGroup(currentGroup);

            groupList.add(group);
        }

        // 当前课阶下打开的课程是否打卡
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
                return new ApiResult(-1, ApiStatus.SC_COURSE_CLOCK_IN, "当前课程已经签到");
            }

            // 用户课程打卡
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

            // 增加积分明细
            IntegralDO integral = new IntegralDO();
            integral.setNumber(1);
            integral.setType(2);
            integral.setSource(1);
            integral.setCreateDate(DateUtil.format(new Date()));
            integral.setCreateTime(new Date());
            integral.setPersonId(person.getPersonId());
            integral.setCourseId(courseId);
            integralMapper.save(integral);

            // 用户增加积分
            int point = person.getCandyPoints() == null ? 1 : person.getCandyPoints() + 1;
            person.setCandyPoints(point);
            personMapper.update(person);

            // 对应课阶单元增加积分
            PersonCourseStageGroupQueryCondition pcsQuery = new PersonCourseStageGroupQueryCondition();
            pcsQuery.setGroupId(groupId);
            pcsQuery.setPersonId(person.getPersonId());
            pcsQuery.setStageId(stageId);
            List<PersonCourseStageGroupDO> pcsGroupList = personCourseStageGroupMapper.queryList(pcsQuery);
            if (pcsGroupList.size() != 1) {
                throw new CustomException("用户课阶单元信息错误, 签到失败!");
            }
            PersonCourseStageGroupDO pcsGroup = pcsGroupList.get(0);
            Integer stageGroupPoints = pcsGroup.getCurrentPoints() == null ? 1 : pcsGroup.getCurrentPoints() + 1;
            pcsGroup.setCurrentPoints(stageGroupPoints);
            personCourseStageGroupMapper.update(pcsGroup);
        } catch (Exception e) {
            logger.error("用户签到获取积分失败, 原因: {}", e.getMessage());
            throw new CustomException("签到失败!");
        }
        return new ApiResult("签到成功");
    }

    @Override
    public ApiResult queryPersonGroupProgress(PersonDO person, Long groupId) {
        // 查询当前用户班级打开课程
        ClassInfoQueryCondition classQuery = new ClassInfoQueryCondition();
        classQuery.setPersonId(person.getPersonId());
        classQuery.setCourseStageId(person.getLearningStage());
        List<ClassInfoDO> classInfoList = classInfoMapper.queryAllClassPerson(classQuery);
        if (classInfoList.size() != 1) {
            logger.error("获取兑换积分进度失败! 用户同时加入相同课阶的两个班级。 personId={}, stageId={}", person.getPersonId(), person.getLearningStage());
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "用户存在重复课阶的班级, 获取商品积分记录失败");
        }

        ClassInfoDO classInfo = classInfoList.get(0);
        // 查询当前班级打开课程
        CourseInfoDO courseInfo = courseInfoMapper.queryById(classInfo.getOpenCourseId());
        boolean currentGroup = courseInfo.getGroupId() != null && courseInfo.getGroupId().equals(groupId);

        // 查询用户课程签到记录
        boolean clockIn = false;
        String currentDate = DateUtils.format(new Date(), "yyyy-MM-dd");

        // 用户当天学习课阶课程开课情况
        ClassInfoQueryCondition classInfoQueryCondition = new ClassInfoQueryCondition();
        classInfoQueryCondition.setOpenTime(currentDate);
        classInfoQueryCondition.setCourseId(courseInfo.getId());
        classInfoQueryCondition.setPersonId(person.getPersonId());
        if (person.getLearningStage() != null) {
            classInfoQueryCondition.setCourseStageId(person.getLearningStage());
        }

        List<ClassInfoDO> classPersonList = classInfoMapper.queryAllClassPerson(classInfoQueryCondition);
        // 获取当天当前学习课阶开课课程
        ClassInfoDO temp = null;
        if (classPersonList.size() > 0) {
            temp = classPersonList.get(0);
        }
        // 当前学习课阶没有开课, 默认为签到
        if (temp == null || temp.getOpenCourseId() == null) {
            clockIn = true;
        } else {
            // 查询开课课程签到记录
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
            logger.error("获取兑换积分进度失败! 用户当前课阶单元划分错误。 personId={}, stageId={}, groupId={}");
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "单元礼品兑换进度获取失败");
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
        // 当前开课课程是否签到
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

            // 查询购买当前课阶的用户
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
            throw new CustomException("新增课阶分组信息失败, 原因: " + e.getMessage());
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
