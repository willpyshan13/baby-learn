package cn.smart.controller.mobile;


import cn.smart.common.annotation.LoginUser;
import cn.smart.common.annotation.Logs;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.api.result.ApiStatus;
import cn.smart.common.utils.Validator;
import cn.smart.bo.*;
import cn.smart.service.IApiPersonService;
import cn.smart.bo.ReportBO;
import cn.smart.condition.*;
import cn.smart.entity.*;
import cn.smart.service.*;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @author Ye
 */
@Controller
@RequestMapping("/mobile/learn/")
public class ApiCourseController {

    private static final Logger LOGGER = LogManager.getLogger(ApiRegisterController.class);

    @Autowired
    private ICourseInfoService courseInfoService;

    @Autowired
    private IPersonCourseStageService personCourseStageService;

    @Autowired
    private IGameDetailService gameDetailService;

    @Autowired
    private IApiPersonService personService;

    /**
     * 获取课阶课程列表
     * @param personDO 登录用户信息
     * @param level 课阶id
     * @param currentPage 当前页
     * @param pageSize 分页大小
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public ApiResult getCourseList(@LoginUser PersonDO personDO,
                                   @RequestParam(value = "level") Long level,
                                   @RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "999") Integer pageSize) {
        if (Validator.isEmpty(personDO)) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID,"token 失效, 请重新登录");
        }
        // level 为0 则直接获取用户当前学习课程阶段下的课程
        Long courseStageId = level == 0 ? personDO.getLearningStage() : level;
        if (Validator.isEmpty(courseStageId)) {
            List<CourseStageDO> personCourseStageList = personCourseStageService.queryPersonBoughtCourse(personDO.getPersonId());
            if (personCourseStageList.size() > 0) {
                courseStageId = personCourseStageList.get(0).getId();
            } else {
                return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "当前用户没有购买课阶!");
            }
        }

        CourseInfoQueryCondition courseInfoQueryCondition = new CourseInfoQueryCondition();
        courseInfoQueryCondition.setPersonId(personDO.getPersonId());
        courseInfoQueryCondition.setStageId(courseStageId);
        courseInfoQueryCondition.setPage(currentPage);
        courseInfoQueryCondition.setLimit(pageSize);
        courseInfoQueryCondition.setOrderField("ci.serial_number");
        List<CourseInfoBO> list = courseInfoService.queryPersonCourseList(courseInfoQueryCondition);

        // 更新用户当前学习的课阶
        personDO.setLearningStage(courseStageId);
        personService.updatePerson(personDO, null, null);

        // 获取用户课程学习总分数
        PersonLearnRecordQueryCondition learnRecordsQuery = new PersonLearnRecordQueryCondition();
        learnRecordsQuery.setPersonId(personDO.getPersonId());
        List<PersonLearnRecordDO> listPersonLearnRecord = personService.personLearnRecord(learnRecordsQuery);
        // key:课程id value:课程得分
        Map<Long, Integer> mapCourseScore = new HashMap<>(listPersonLearnRecord.size());
        Set<Long> mapSpeakUpLearned = new HashSet<>(listPersonLearnRecord.size());
        for (PersonLearnRecordDO p : listPersonLearnRecord) {
            if (p.getType() == 1) {
                mapCourseScore.put(p.getCourseId(), p.getScore() == null ? 0 : p.getScore());
            } else if (p.getType() == 3) {
                mapSpeakUpLearned.add(p.getCourseId());
            }
        }

        List<Map<String, Object>> contentList = new ArrayList<>(list.size());
        Map<String, Object> map;
        for (CourseInfoBO course : list) {
            map = new HashMap<>(list.size());
            map.put("Id", course.getId());
            map.put("category", course.getCategory());
            map.put("createTime", course.getCreateTime());
            map.put("endTime", course.getEndTime());
            map.put("imagePath", course.getImageUrl());
            map.put("name", course.getCourseName());
            map.put("path", course.getDescription());
            map.put("startDay", course.getStartTime());
            map.put("startTime", course.getStartTime());
            map.put("status", course.getStatus());
            map.put("type", course.getType());
            map.put("updateTime", course.getUpdateTime());
            map.put("groupId", course.getGroupId());
            // 是否开放 0 否 1 开放
            map.put("opened", course.getOpened());
            // 课程总分
            Integer score = mapCourseScore.get(course.getId());
            map.put("totalScore", score == null ? 0 : score);
            // 课程资源地址
            map.put("resourceUrl", course.getResourceUrl());
            // 是否已学(是否完成大声说)
            map.put("learned", mapSpeakUpLearned.contains(course.getId()));
            contentList.add(map);
        }
        int total = courseInfoService.queryTotal(courseInfoQueryCondition);
        Map<String, Object> resultMap = new HashMap<>(16);
        resultMap.put("content", contentList);
        resultMap.put("firstPage", true);
        resultMap.put("lastPage", true);
        resultMap.put("number", 0);
        resultMap.put("numberOfElements", 15);
        resultMap.put("size", pageSize);
        resultMap.put("sort", null);
        resultMap.put("totalElements", total);
        resultMap.put("totalPages", total== 0?1:total/pageSize);

        return new ApiResult(resultMap);
    }

    /**
     * 课程详情接口
     * @param personDO 个人信息
     * @param courseId 课程id
     * @param type 课程游戏类型 0: 单词辨析 1: 大声说
     * @return
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public ApiResult getCourseDetail(@LoginUser PersonDO personDO,
                                     @RequestParam(value = "learnId") Long courseId,
                                     @RequestParam(value = "type", required = false, defaultValue = "0") Integer type) {

        if (Validator.isEmpty(personDO)) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID,"token 失效, 请重新登录");
        }
        CourseInfoQueryCondition courseInfoQueryCondition = new CourseInfoQueryCondition();
        courseInfoQueryCondition.setId(courseId);
        CourseInfoDO courseInfoDO = courseInfoService.queryById(courseId);
        if (Validator.isEmpty(courseInfoDO)) {
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "课程不存在");
        }

        LearnDetailBO bean = courseInfoService.getCourseDetail(personDO.getPersonId(), courseInfoDO, type);

        // 兼容旧版本接口 新版不需要 type 字段
        // 新版大声说 用 listSpeakUp 数组
        if (type == 1) {
            bean.setGameLists(bean.getListSpeakUp());
            bean.setListSpeakUp(null);
        }

        return new ApiResult(bean);
    }


    @RequestMapping(value = "/levelList")
    @ResponseBody
    public ApiResult getLearnLevel(
            @LoginUser PersonDO person,
            @RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {

        if (person == null || Validator.isEmpty(person.getPersonId())) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "请重新登录!");
        }

        List<CourseStageDO> personCourseStageList = personCourseStageService.queryPersonBoughtCourse(person.getPersonId());
        List<Map<String, Object>> list = new ArrayList<>(personCourseStageList.size());
        Map<String, Object> map;
        for (CourseStageDO stage : personCourseStageList) {
            map = new HashMap<>();
            map.put("levelId", stage.getId());
            map.put("levelName", stage.getStageName());
            map.put("description", stage.getDescription());
            map.put("createTime", stage.getCreateTime());
            // 当前学习课阶
            if (null != person.getLearningStage() && person.getLearningStage().equals(stage.getId())) {
                map.put("currentStage", true);
            } else {
                map.put("currentStage", false);
            }
            list.add(map);
        }

        return new ApiResult(list);
    }

    /**
     * 课程记录接口
     * @param personDO 用户
     * @param learnId 课程id
     * @param type 类型 1 课程 2 游戏 3 大声说
     * @return
     */
    @PostMapping(value = "/learnRecord")
    @ResponseBody
    @Logs(value = "记录课程学习情况")
    @Deprecated
    public ApiResult setLearnRecord(@LoginUser PersonDO personDO,
                                    @RequestParam(value = "learnId") Long learnId,
                                    @RequestParam(value = "type") Integer type) {
        if (Validator.isEmpty(personDO)) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "用户信息无效，请重新登录");
        }

        try {
            personService.savePersonLearnRecord(personDO, learnId, type, null, null);
            return new ApiResult("success");

        } catch (Exception e) {
            LOGGER.error(e);
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "服务器异常");
        }

    }

    /**
     * 课程记录接口
     * @param personDO 用户
     * @param learnId 课程id
     * @param type 类型 1 课程 2 游戏 3 大声说
     * @param report 完成情况结果
     * @return
     */
    @PostMapping(value = "/v1/learnRecord")
    @ResponseBody
    @Logs(value = "记录课程学习情况v1")
    public ApiResult setLearnRecord1(@LoginUser PersonDO personDO,
                                     @RequestParam(value = "learnId") Long learnId,
                                     @RequestParam(value = "type") Integer type,
                                     @RequestParam(value = "report", required = false) String report,
                                     @RequestParam(value = "addNumber", required = false) Integer addNumber) {
        if (Validator.isEmpty(personDO)) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "用户信息无效，请重新登录");
        }
        ReportBO reportBO = null;
        if (type != 1) {
            try {
                reportBO = JSONObject.parseObject(report, ReportBO.class);
                if (Validator.isEmpty(reportBO.getReportDetail())) {
                    return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "报告详情不能为空!");
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "报告格式错误!");
            }
            if (Validator.isEmpty(report)) {
                return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "报告详情不能为空");
            }
            if (Validator.isEmpty(addNumber)) {
                return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "增加积分数不能为空");
            }
        }
        addNumber = addNumber == null ? 0 : addNumber;
        ApiResult result;
        try {
            result = personService.savePersonLearnRecord(personDO, learnId, type, reportBO, addNumber);
            return result;
        } catch (Exception e) {
            LOGGER.error(e);
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "服务器异常");
        }

    }
}
