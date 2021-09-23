package cn.smart.controller.h5;

import cn.smart.common.annotation.AuthIgnore;
import cn.smart.common.annotation.LoginUser;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.api.result.ApiStatus;
import cn.smart.common.utils.Validator;
import cn.smart.bo.CourseStageDetailBO;
import cn.smart.condition.CourseStageQueryCondition;
import cn.smart.dao.IPersonCourseStageMapper;
import cn.smart.entity.CourseStageDO;
import cn.smart.entity.PersonDO;
import cn.smart.service.ICourseContentService;
import cn.smart.service.ICourseInfoService;
import cn.smart.service.ICourseStageService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/h5/api/course/stage")
public class H5ApiCourseStageController {

    private final ICourseInfoService courseInfoService;

    private final ICourseStageService courseStageService;

    private final ICourseContentService courseContentService;

    private final IPersonCourseStageMapper personCourseStageMapper;

    @Autowired
    public H5ApiCourseStageController(ICourseInfoService courseInfoService, ICourseStageService courseStageService, ICourseContentService courseContentService, IPersonCourseStageMapper personCourseStageMapper) {
        this.courseInfoService = courseInfoService;
        this.courseStageService = courseStageService;
        this.courseContentService = courseContentService;
        this.personCourseStageMapper = personCourseStageMapper;
    }

    /**
     * 获取阶段课程列表
     * @return R
     */
    @AuthIgnore
    @GetMapping("/list")
    public ApiResult courseStageList() {
        CourseStageQueryCondition stageQueryCondition = new CourseStageQueryCondition();
        stageQueryCondition.setSelling(1); // 显示可售的课阶
        List<CourseStageDO> courseList = courseStageService.queryList(stageQueryCondition);
        return new ApiResult(courseList);
    }

    /**
     * 根据阶段查询阶段课程详情
     * @param person 当前登录用户
     * @param id 课程阶段 level
     * @return R
     */
    @AuthIgnore
    @GetMapping("/detail")
    public ApiResult courseStageDetail(@LoginUser PersonDO person, @RequestParam("id") String id) {

        if (Validator.isEmpty(id)) {
            return new ApiResult(-1, ApiStatus.SC_COURSE_STAGE_ID_NULL, "课阶编号不能为空!");
        }
        // 查询阶段课程详细信息
        CourseStageDetailBO courseStage = courseStageService.courseStageDetail(person, Long.valueOf(id));
        if (courseStage == null) {
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "课阶不存在");
        }

        return new ApiResult(courseStage);
    }



}
