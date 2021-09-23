package cn.smart.service.impl;

import cn.smart.common.exception.CustomException;
import cn.smart.common.utils.DateUtil;
import cn.smart.condition.*;
import cn.smart.dao.*;
import cn.smart.entity.*;
import cn.smart.service.IAppService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* @author Ye
* @date 2018-5-15 9:28:36
*/

@Service
public class AppServiceImpl  implements IAppService {

    private static Logger logger = LogManager.getLogger(AppServiceImpl.class);

    @Autowired
    private IPersonLearnRecordMapper personLearnRecordMapper;

    @Autowired
    private IIntegralMapper integralMapper;

    @Autowired
    private IPersonCourseClockMapper personCourseClockMapper;

    @Autowired
    private IPersonCourseStageGroupMapper personCourseStageGroupMapper;

    @Autowired
    private ICourseInfoMapper courseInfoMapper;

    @Autowired
    private ICourseStageGroupMapper courseStageGroupMapper;

    @Autowired
    private IPersonCourseStageMapper personCourseStageMapper;

    @Autowired
    private IClassInfoMapper classInfoMapper;

    @Autowired
    private IClassCourseMapper classCourseMapper;

    @Autowired
    private IClassPersonMapper classPersonMapper;

    @Autowired
    private IPersonMapper personMapper;

    @Autowired
    private ICourseStageMapper courseStageMapper;

    @Autowired
    private IAppMapper appMapper;

    @Override
    public void save(AppDO appDO) {
        appMapper.save(appDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        appMapper.save(map);
    }

    @Override
    public void saveBatch(List<AppDO> list) {
        appMapper.saveBatch(list);
    }

    @Override
    public int update(AppDO appDO) {
        return appMapper.update(appDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return appMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return appMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return appMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return appMapper.deleteBatch(ids);
    }

    @Override
    public AppDO queryById(Object id) {
        return appMapper.queryById(id);
    }

    @Override
    public List<AppDO> queryList(AppQueryCondition appQueryCondition) {
        // 2018年9月27日 课程签到版本发布 数据整理工具 待删除
//        // 课阶课程分周
//        CourseStageQueryCondition courseStageQueryCondition = new CourseStageQueryCondition();
//        courseStageQueryCondition.setId(1L);
//        List<CourseStageDO> courseStageList = courseStageMapper.queryList(courseStageQueryCondition);
//        for (CourseStageDO courseStageDO : courseStageList) {
//            Long stageId = courseStageDO.getId();
//            CourseInfoQueryCondition courseInfoQueryCondition = new CourseInfoQueryCondition();
//            courseInfoQueryCondition.setStageId(stageId);
//            courseInfoQueryCondition.setOrderField("serial_number");
//            List<CourseInfoDO> courseInfoList = courseInfoMapper.queryList(courseInfoQueryCondition);
//
//            int n = 1;
//            int m = 1;
//            for (CourseInfoDO courseInfoDO : courseInfoList) {
//                courseInfoDO.setWeeks(m);
//                courseInfoMapper.update(courseInfoDO);
//                if (n%5 == 0) {
//                    m++;
//                    if (m==5) {
//                        m = 1;
//                    }
//                }
//                n++;
//            }
//        }
//
//
//        // 班级数据整理
//        analysisClassInfoData();
//
//        // 学生积分数据整理
//        PersonQueryCondition personQueryCondition = new PersonQueryCondition();
//        List<PersonDO> personList = personMapper.queryList(personQueryCondition);
//        for (PersonDO personDO : personList) {
//            try{
//                logger.info("-----------------------> {} , ID : {}, 用户开始整理学习数据...", personDO.getNickName(), personDO.getPersonId());
////                if (personDO.getPersonId().equals(104L)) {
//                    analysisPersonLearnData(personDO.getPersonId());
////                }
//            } catch (Exception e) {
//                logger.info("===================> {} , ID : {} 用户整理数据失败 !!! 原因: ", personDO.getNickName(), personDO.getPersonId(), e.getMessage());
//            }
//            logger.info("-----------------------> {} , ID : {}, 整理学习数据结束... ", personDO.getNickName(), personDO.getPersonId());
//        }

        return appMapper.queryList(appQueryCondition);
    }

    private void analysisClassInfoData() {
        ClassInfoQueryCondition classInfoQueryCondition = new ClassInfoQueryCondition();
        List<ClassInfoDO> classList = classInfoMapper.queryList(classInfoQueryCondition);
        for (ClassInfoDO c : classList) {
            logger.info("-----------------------> {} 数据整理开始 ... ", c.getClassName());

            // 补充班级打开课程数据
            if (c.getOpenCourseId() == null) {
                ClassCourseQueryCondition classCourseQueryCondition = new ClassCourseQueryCondition();
                classCourseQueryCondition.setClassId(c.getId());
                List<ClassCourseDO> classCourseList = classCourseMapper.queryList(classCourseQueryCondition);

                Date d = null;
                Long openCourseId = null;
                for (ClassCourseDO cc : classCourseList) {
                    if (cc.getStatus()) {
                        if (d == null) {
                            d = cc.getUpdateTime();
                            openCourseId = cc.getCourseInfoId();
                        } else {
                            if (cc.getUpdateTime().getTime() > d.getTime()) {
                                openCourseId = cc.getCourseInfoId();
                            }
                        }
                    }
                }
                if (openCourseId != null) {
                    c.setOpenCourseId(openCourseId);
                    classInfoMapper.update(c);
                }
                logger.info("class id : {} open course Id : {}",c.getId(), openCourseId);
            }
            // 补充班级学生数据
            ClassPersonQueryCondition classPersonQueryCondition = new ClassPersonQueryCondition();
            classPersonQueryCondition.setClassId(c.getId());
            List<ClassPersonDO> classPersonList = classPersonMapper.queryList(classPersonQueryCondition);
            Long[] ids = new Long[classPersonList.size()];
            int i = 0;
            for (ClassPersonDO classPersonDO : classPersonList) {
                ids[i++] = classPersonDO.getPersonId();
            }
            c.setPersonIds(StringUtils.join(ids, ","));
            classInfoMapper.update(c);

            logger.info("-----------------------> {} 数据整理结束 ... ", c.getClassName());
        }
    }

    private void analysisPersonLearnData(Long personId) {
        // 补充用户购买课阶后 用户-课阶单元表信息
        PersonCourseStageQueryCondition personCourseStageQueryCondition = new PersonCourseStageQueryCondition();
        personCourseStageQueryCondition.setPersonId(personId);
        List<PersonCourseStageDO> personCourseStageDOS = personCourseStageMapper.queryList(personCourseStageQueryCondition);
        // 用户购买课阶
        for (PersonCourseStageDO pcStage : personCourseStageDOS) {

            PersonCourseStageGroupQueryCondition personCourseStageGroupQueryCondition = new PersonCourseStageGroupQueryCondition();
            personCourseStageGroupQueryCondition.setPersonId(personId);
            personCourseStageGroupQueryCondition.setStageId(pcStage.getCourseStageId());
            // 用户购买课阶单元
            List<PersonCourseStageGroupDO> personCourseStageGroupDOS = personCourseStageGroupMapper.queryList(personCourseStageGroupQueryCondition);
            // 用户购买课阶单元为空 新增到数据库
            if (personCourseStageGroupDOS.size() == 0) {
                CourseStageGroupQueryCondition courseStageGroupQueryCondition = new CourseStageGroupQueryCondition();
                courseStageGroupQueryCondition.setStageId(pcStage.getCourseStageId());
                List<CourseStageGroupDO> courseStageGroupDOS = courseStageGroupMapper.queryList(courseStageGroupQueryCondition);

                List<PersonCourseStageGroupDO> personCourseStageGroupDOList = new ArrayList<>();
                PersonCourseStageGroupDO personCourseStageGroupDO;
                for (CourseStageGroupDO csg : courseStageGroupDOS) {
                    personCourseStageGroupDO = new PersonCourseStageGroupDO();
                    personCourseStageGroupDO.setPersonId(personId);
                    personCourseStageGroupDO.setStageId(csg.getStageId());
                    personCourseStageGroupDO.setGroupId(csg.getId());
                    personCourseStageGroupDO.setGroupCode(csg.getCode());
                    personCourseStageGroupDO.setCurrentPoints(0);
                    personCourseStageGroupDO.setCreateBy(1L);
                    personCourseStageGroupDO.setCreateTime(new Date());

                    personCourseStageGroupDOList.add(personCourseStageGroupDO);
                }
                if (personCourseStageGroupDOList.size() > 0) {
                    personCourseStageGroupMapper.saveBatch(personCourseStageGroupDOList);
                }
            }
        }


        Set<Long> courseIds = new HashSet<>();
        PersonLearnRecordQueryCondition q = new PersonLearnRecordQueryCondition();
        q.setOrderField("create_time");
        q.setOrder("desc");
        q.setPersonId(personId);
        // 用户学习记录课程
        List<PersonLearnRecordDO> learnList = personLearnRecordMapper.queryList(q);
        learnList.forEach(r -> {
            courseIds.add(r.getCourseId());
        });

        for (Long courseId : courseIds) {

            double score = 0;
            int t = 0;
            PersonLearnRecordDO personLearnRecordDO = null;
            PersonLearnRecordDO playGame = null;
            PersonLearnRecordDO speakUp = null;

            for (PersonLearnRecordDO recordDO : learnList) {
                if (recordDO.getCourseId().equals(courseId)) {
                    if (recordDO.getType() == 1) {
                        personLearnRecordDO = recordDO;
                    }
                    if (recordDO.getType() == 2) {
                        playGame = recordDO;
                        score += recordDO.getScore() == null? 0: recordDO.getScore();
                        t += 1;
                    } else if (recordDO.getType() == 3) {
                        speakUp = recordDO;
                        score += recordDO.getScore() == null? 0: recordDO.getScore();
                        t += 1;
                    }
                }
            }
            // 查询课程信息
            CourseInfoDO courseInfo = courseInfoMapper.queryById(courseId);


            PersonCourseClockQueryCondition personCourseClockQueryCondition = new PersonCourseClockQueryCondition();
            personCourseClockQueryCondition.setGroupId(courseInfo.getGroupId());
            personCourseClockQueryCondition.setPersonId(personId);
            personCourseClockQueryCondition.setStageId(courseInfo.getStageId());
            // 用户课程打卡信息
            List<PersonCourseClockDO> personCourseClockDOList = personCourseClockMapper.queryList(personCourseClockQueryCondition);

            if (courseId.equals(189L)) {
                System.out.println("===================");
            }


            if (personLearnRecordDO != null && t == 2) {

                PersonCourseClockDO pp = null;
                for (PersonCourseClockDO clockDO : personCourseClockDOList) {
                    if(clockDO.getCourseId().equals(courseId)) {
                        pp = clockDO;
                    }
                }

                if (pp != null) {
                    IntegralQueryCondition integralQuery = new IntegralQueryCondition();
                    integralQuery.setPersonId(personLearnRecordDO.getPersonId());
                    integralQuery.setSource(1);
                    integralQuery.setCourseId(pp.getCourseId());
                    // 查询打卡课程是否获得过积分
                    List<IntegralDO> integralList = integralMapper.queryList(integralQuery);
                    // 未获得积分
                    if (integralList.size() == 0) {
                        IntegralDO i = new IntegralDO();
                        i.setNumber(1);
                        i.setSource(1);
                        i.setType(2);
                        i.setCourseId(courseInfo.getId());
                        i.setPersonId(personId);
                        i.setCreateTime(pp.getCreateTime());
                        i.setCreateDate(DateUtil.format(pp.getCreateTime(), "yyyy-MM-dd"));
                        // 给与用户积分 并在打卡课程所在课阶单元增加积分
                        addIntegral(i, courseInfo.getStageId(), personId, courseInfo.getGroupId());
                    }
                } else {
                    // 为完成大声说的课程添加打卡信息
                    PersonCourseClockDO personCourseClockDO = new PersonCourseClockDO();
                    personCourseClockDO.setPersonId(personId);
                    personCourseClockDO.setGroupId(courseInfo.getGroupId());
                    personCourseClockDO.setStageId(courseInfo.getStageId());
                    personCourseClockDO.setCourseId(courseId);
                    personCourseClockDO.setPoints(1);
                    personCourseClockDO.setClockDate(DateUtil.format(personLearnRecordDO.getCreateTime(), "yyyy-MM-dd"));
                    personCourseClockDO.setCreateBy(personId);
                    personCourseClockMapper.save(personCourseClockDO);

                    IntegralDO i = new IntegralDO();
                    i.setNumber(1);
                    i.setSource(1);
                    i.setType(2);
                    i.setCourseId(courseId);
                    i.setPersonId(personId);
                    i.setCreateTime(speakUp.getCreateTime());
                    i.setCreateDate(DateUtil.format(speakUp.getCreateTime(), "yyyy-MM-dd"));
                    // 给与用户积分 并在打卡课程所在课阶单元增加积分
                    addIntegral(i, courseInfo.getStageId(), personId, courseInfo.getGroupId());
                }
            }

            // 完成"玩游戏" "大声说" 纠正获得积分错误数据
            if (t == 2) {
                logger.info("course Id : {}, score : {}", courseId, score);
                Double result = score / 2;
                boolean min = result < 1;
                if (min) {
                    personLearnRecordDO.setScore(1);
                } else {
                    // 课程总星星 均值 向下取整
                    personLearnRecordDO.setScore((int)Math.floor(result));
                }
                // 重新修正用户课程获得分数
                personLearnRecordMapper.update(personLearnRecordDO);

                // 清空积分列表 重新计算用户获得积分 完成"玩游戏" "大声说" 未得满星----获得1积分 满星----获得2积分
                // 积分类型为 2(糖果积分) 来源为 2(课程积分)
                IntegralDO i = new IntegralDO();
                i.setNumber(0);
                if (personLearnRecordDO.getScore() > 0 && personLearnRecordDO.getScore() < 3) {
                    i.setNumber(1);
                } else if (personLearnRecordDO.getScore() == 3){
                    i.setNumber(2);
                }

                i.setCourseId(personLearnRecordDO.getCourseId());
                i.setPersonId(personLearnRecordDO.getPersonId());
                i.setLearnRecordId(speakUp.getRecordId());
                i.setType(2);
                i.setSource(2);
                i.setCreateDate(DateUtil.format(speakUp.getCreateTime(), "yyyy-MM-dd"));
                i.setCreateTime(speakUp.getCreateTime());
                // 如果增加积分不为0
                if (i.getNumber() != 0) {
                    IntegralQueryCondition integralQuery = new IntegralQueryCondition();
                    integralQuery.setPersonId(personLearnRecordDO.getPersonId());
                    integralQuery.setSource(i.getSource());
                    integralQuery.setCourseId(courseId);
                    // 判断当前课程ID和来源下用户是否已经获得过课程积分
                    List<IntegralDO> integralList = integralMapper.queryList(integralQuery);
                    // 获得过一次课程积分
                    if (integralList.size() == 1) {
                        IntegralDO i1 = integralList.get(0);
                        // 如果获得积分时间不相同
                        if (!i1.getCreateDate().equals(i.getCreateDate())) {
                            // 增加课程学习积分
                            addIntegral(i, courseInfo.getStageId(), personId, courseInfo.getGroupId());
                        }
                        // 未获得过积分
                    } else if (integralList.size() == 0) {
                        // 增加课程学习积分
                        addIntegral(i, courseInfo.getStageId(), personId, courseInfo.getGroupId());
                    }
                }
            }
        }
    }


    private void addIntegral(IntegralDO integralDO, Long stageId, Long personId, Long groupId) {
        // 增加课程学习积分
        integralMapper.save(integralDO);

        PersonDO person = personMapper.queryById(personId);
        person.setCandyPoints(person.getCandyPoints() != null ? person.getCandyPoints() + integralDO.getNumber() : integralDO.getNumber());
        personMapper.update(person);

        // 增加课程对应单元积分
        PersonCourseStageGroupQueryCondition personCourseStageGroupQueryCondition = new PersonCourseStageGroupQueryCondition();
        personCourseStageGroupQueryCondition.setStageId(stageId);
        personCourseStageGroupQueryCondition.setPersonId(personId);
        personCourseStageGroupQueryCondition.setGroupId(groupId);
        List<PersonCourseStageGroupDO> personCourseStageGroupList = personCourseStageGroupMapper.queryList(personCourseStageGroupQueryCondition);

        if (personCourseStageGroupList.size() == 1) {
            PersonCourseStageGroupDO pcsGroup = personCourseStageGroupList.get(0);

            pcsGroup.setCurrentPoints(pcsGroup.getCurrentPoints() != null ? pcsGroup.getCurrentPoints() + integralDO.getNumber() : integralDO.getNumber());
            personCourseStageGroupMapper.update(pcsGroup);
        }
    }


    @Override
    public int queryTotal(AppQueryCondition appQueryCondition) {
        return appMapper.queryTotal(appQueryCondition);
    }

    @Override
    public int total() {
        return appMapper.total();
    }
}
