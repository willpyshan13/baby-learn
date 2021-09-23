package cn.smart.service.impl;

import cn.smart.bo.*;
import cn.smart.common.exception.CustomException;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.common.utils.Validator;
import cn.smart.condition.*;
import cn.smart.dao.*;
import cn.smart.entity.*;
import cn.smart.service.IPersonService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* @author Ye
* @date 2018-4-2 15:44:19
*/

@Service
public class PersonServiceImpl  implements IPersonService {

    @Autowired
    private IPersonMapper personMapper;

    @Autowired
    private IPersonCourseStageMapper personCourseStageMapper;

    @Autowired
    private IClassPersonMapper classPersonMapper;

    @Autowired
    private IPersonLearnRecordMapper personLearnRecordMapper;

    @Autowired
    private ICourseInfoMapper courseInfoMapper;

    @Autowired
    private IClockRecordMapper clockRecordMapper;

    @Autowired
    private IIntegralMapper integralMapper;

    @Autowired
    private IClassInfoMapper classInfoMapper;

    @Autowired
    private ICourseStageGroupMapper courseStageGroupMapper;

    @Autowired
    private IPersonCourseStageGroupMapper personCourseStageGroupMapper;

    @Override
    public void save(PersonDO personDO) {
        personMapper.save(personDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        personMapper.save(map);
    }

    @Override
    public void saveBatch(List<PersonDO> list) {
        personMapper.saveBatch(list);
    }

    @Override
    public int update(PersonDO personDO) {
        return personMapper.update(personDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return personMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return personMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return personMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return personMapper.deleteBatch(ids);
    }

    @Override
    public PersonDO queryById(Object id) {
        return personMapper.queryById(id);
    }

    @Override
    public List<PersonDO> queryList(PersonQueryCondition personQueryCondition) {
        return personMapper.queryList(personQueryCondition);
    }

    @Override
    public int queryTotal(PersonQueryCondition personQueryCondition) {
        return personMapper.queryTotal(personQueryCondition);
    }

    @Override
    public int total() {
        return personMapper.total();
    }

    @Override
    public List<CourseStageDO> boughtCourseStage(Long id) {
        return personCourseStageMapper.queryPersonBoughtCourse(id);
    }

    @Override
    public PageUtils getPersonToClass(PersonQueryCondition personQueryCondition) {

        List<PersonDO> personList = personMapper.getPersonToClass(personQueryCondition);

        ClassInfoQueryCondition q = new ClassInfoQueryCondition();
        List<ClassInfoDO> personClassList = classInfoMapper.queryAllClassPerson(q);

        for (PersonDO p : personList) {
            for (ClassInfoDO cp : personClassList) {
                String className;
                if (p.getPersonId().equals(cp.getPersonId())) {
                    if (StringUtils.isEmpty(p.getClassName())) {
                        className = cp.getClassName();
                    } else {
                        className =  p.getClassName() + "、" + cp.getClassName();
                    }
                    p.setClassName(className);
                }
            }
        }
        int total = countPersonToClass(personQueryCondition);
        PageUtils pageUtils = new PageUtils(personList, total, personQueryCondition.getLimit(), personQueryCondition.getPage());
        return pageUtils;
    }

    @Override
    public int countPersonToClass(PersonQueryCondition personQueryCondition) {
        return personMapper.countPersonToClass(personQueryCondition);
    }

    @Transactional(rollbackFor = CustomException.class)
    @Override
    public void buyCourse(PersonCourseStageDO personCourseStageDO) {
        try{
            personCourseStageMapper.save(personCourseStageDO);

            CourseStageGroupQueryCondition stageGroupQuery = new CourseStageGroupQueryCondition();
            stageGroupQuery.setStageId(personCourseStageDO.getCourseStageId());
            List<CourseStageGroupDO> stageGroupList = courseStageGroupMapper.queryList(stageGroupQuery);
            List<PersonCourseStageGroupDO> pcsGroupList = new ArrayList<>(stageGroupList.size());

            PersonCourseStageGroupDO pcsGroup;
            for (CourseStageGroupDO csg : stageGroupList) {
                pcsGroup = new PersonCourseStageGroupDO();
                pcsGroup.setStageId(personCourseStageDO.getCourseStageId());
                pcsGroup.setGroupId(csg.getId());
                pcsGroup.setPersonId(personCourseStageDO.getPersonId());
                pcsGroup.setCurrentPoints(0);
                pcsGroup.setCreateBy(personCourseStageDO.getCreateBy());
                pcsGroup.setCreateTime(new Date());
                pcsGroupList.add(pcsGroup);
            }

            if (pcsGroupList.size() == 0) {
                throw new NullPointerException("购买课阶未分单元");
            }
            personCourseStageGroupMapper.saveBatch(pcsGroupList);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }

    }

    @Override
    public R deletePerson(Long id) {

        ClassPersonQueryCondition query = new ClassPersonQueryCondition();
        query.setPersonId(id);
        int total = classPersonMapper.queryTotal(query);
        if (total > 0) {
            return R.error("当前学生已被分配至班级, 请先移出所在班级");
        }
        Map<String, Object> map = new HashMap<>(1);
        map.put("personId", id);
        try {
            // 删除购买课程
            personCourseStageMapper.delete(map);
            // 删除学习记录
            personLearnRecordMapper.delete(map);
            // 删除打卡记录
            clockRecordMapper.delete(map);
            // 删除积分记录
            integralMapper.delete(map);
            // 删除用户信息
            deleteById(id);
        } catch (Exception e) {
            throw new CustomException("删除用户信息失败");
        }

        return R.ok("删除成功");
    }

    @Override
    public List<CourseInfoBO> queryLearnRecords(CourseInfoQueryCondition courseInfoQueryCondition) {

        courseInfoQueryCondition.setOrderField("ci.stage_id, ci.serial_number");
        List<CourseInfoBO> personCourses = courseInfoMapper.queryPersonCourseList(courseInfoQueryCondition);

        PersonLearnRecordQueryCondition query = new PersonLearnRecordQueryCondition();
        query.setType(1);
        query.setPersonId(courseInfoQueryCondition.getPersonId());

        List<PersonLearnRecordDO> learnRecords = personLearnRecordMapper.queryList(query);

        CourseInfoBO courseInfo;
        for (CourseInfoBO personCourse : personCourses) {
            courseInfo = personCourse;
            courseInfo.setLearned(0);
            for (PersonLearnRecordDO learnRecord : learnRecords) {
                if (courseInfo.getId().equals(learnRecord.getCourseId()) && learnRecord.getType().equals(1)) {
                    courseInfo.setLearnedTime(learnRecord.getCreateTime());
                    courseInfo.setLearned(1);
                }
            }
        }
        return personCourses;
    }

    @Transactional(rollbackFor = CustomException.class)
    @Override
    public void deleteBuyStage(Long personId, Long stageId) {
        try{
            Map<String, Object> map = new HashMap<>(16);
            map.put("personId", personId);
            map.put("courseStageId", stageId);
            personCourseStageMapper.delete(map);

            map = new HashMap<>(2);
            map.put("personId", personId);
            map.put("stageId", stageId);
            personCourseStageGroupMapper.delete(map);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public CourseReportBO queryCourseReport(Long personId, Long courseId) {

        CourseReportBO courseReport = new CourseReportBO();

        PersonDO person = personMapper.queryById(personId);
        if (Validator.isEmpty(person)) {
            throw new CustomException("用户不存在");
        }
        // 用户信息设置
        courseReport.setPersonId(personId);
        courseReport.setPersonName(person.getUserName());
        courseReport.setAvatar(person.getPhotoUrl());

        CourseInfoDO courseInfo = courseInfoMapper.queryById(courseId);
        if (Validator.isEmpty(courseInfo)) {
            throw new CustomException("课程不存在");
        }
        // 课程信息
        courseReport.setCourseId(courseId);
        courseReport.setCourseName(courseInfo.getCourseName());


        // 课程游戏
        GameInfoQueryCondition queryGame = new GameInfoQueryCondition();
        queryGame.setCourseId(courseId);
        List<CourseGameBO> listGamInfo = courseInfoMapper.queryCourseGame(queryGame);
        Map<Long, CourseGameBO> mapGameInfo = new HashMap<>(listGamInfo.size());
        for (CourseGameBO gi : listGamInfo) {
            mapGameInfo.put(gi.getGameId(), gi);
        }

        System.out.println(JSONObject.toJSON(mapGameInfo));

        PersonLearnRecordQueryCondition queryLearnRecord = new PersonLearnRecordQueryCondition();
        queryLearnRecord.setPersonId(personId);
        queryLearnRecord.setCourseId(courseId);
        // 课程学习记录
        List<PersonLearnRecordDO> listPersonLearnRecord = personLearnRecordMapper.queryList(queryLearnRecord);
        PersonLearnRecordDO personLearnRecord = null;
        ReportBO report;
        for (PersonLearnRecordDO p : listPersonLearnRecord) {
            if (p.getType() == 1) {
                personLearnRecord = p;
            } else {
                report = JSONObject.parseObject(p.getResultData(), ReportBO.class);
                if (Validator.isNotEmpty(report)) {
                    List<ReportDetailBO> listReportDetail = new ArrayList<>(16);
                    for (ReportDetailBO rd : report.getReportDetail()) {
                        CourseGameBO gameInfo = mapGameInfo.get(rd.getId());
                        // 若题目不存在, 则不显示之前做题的记录
                        if (Validator.isNotEmpty(gameInfo)) {
                            rd.setName(gameInfo.getName());
                            listReportDetail.add(rd);
                        }
                    }
                    report.setReportDetail(listReportDetail);
                }

                // 单词辨析
                if (p.getType() == 2) {
                    courseReport.setPlayGameReport(report);
                    // 大声说
                } else if (p.getType() == 3) {
                    courseReport.setSpeakUpReport(report);
                }
            }
        }

        courseReport.setTotalScore(personLearnRecord != null ? personLearnRecord.getScore() != null ? personLearnRecord.getScore() : 0 : 0);

        return courseReport;
    }

    @Override
    public PageUtils queryPersonList(PersonQueryCondition personQueryCondition) {

        List<PersonDO> personList = personMapper.queryList(personQueryCondition);

        ClassInfoQueryCondition q = new ClassInfoQueryCondition();
        List<ClassInfoDO> personClassList = classInfoMapper.queryAllClassPerson(q);

        for (PersonDO p : personList) {
            for (ClassInfoDO cp : personClassList) {
                String className;
                if (p.getPersonId().equals(cp.getPersonId())) {
                    if (StringUtils.isEmpty(p.getClassName())) {
                        className = cp.getClassName();
                    } else {
                        className =  p.getClassName() + "、" + cp.getClassName();
                    }
                    p.setClassName(className);
                }
            }
        }

        int total = personMapper.queryTotal(personQueryCondition);
        PageUtils pageUtils = new PageUtils(personList, total, personQueryCondition.getLimit(), personQueryCondition.getPage());




        return pageUtils;
    }
}
