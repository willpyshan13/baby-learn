package cn.smart.controller.mobile;

import cn.smart.common.annotation.LoginUser;
import cn.smart.common.annotation.Logs;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.api.result.ApiStatus;
import cn.smart.common.exception.CustomException;
import cn.smart.common.utils.Validator;
import cn.smart.entity.PersonDO;
import cn.smart.service.ICourseStageService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 课程签到相关接口
 * @author Ye
 */
@RestController
@RequestMapping("/mobile/clock")
public class ApiCourseClockController {

    @Autowired
    private ICourseStageService courseStageService;

    /**
     * 获取用户当前课阶下
     * 正在打开课程所在单元的所有课程签到情况
     * @param person
     * @return
     */
    @GetMapping("/course")
    public ApiResult getClockCourse(@LoginUser PersonDO person) {

        if (Validator.isEmpty(person)) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "token 无效, 请重新登录!");
        }

        return courseStageService.queryClockStageCourseList(person);
    }

    /**
     * 课程签到接口
     * @param person 用户信息
     * @param stageId 课阶id
     * @param courseId 课程id
     * @param groupId 单元id
     * @return
     */
    @PostMapping("/course")
    @Logs(value = "课程签到")
    public ApiResult clockCourse(@LoginUser PersonDO person,
                                 @RequestParam("stageId") Long stageId,
                                 @RequestParam("courseId") Long courseId,
                                 @RequestParam("groupId") Long groupId) {

        if (Validator.isEmpty(person)) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "token 无效, 请重新登录!");
        }
        ApiResult result;
        try {
            result = courseStageService.clockStageGroupCourse(person, stageId, groupId, courseId);
        } catch (CustomException e) {
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return result;
    }

    /**
     * 获取用户课阶个单元下获取积分进度
     * @param person 用户信息
     * @return
     */
    @GetMapping("/progress")
    public ApiResult queryGroupProgress(@LoginUser PersonDO person,
                                        @RequestParam("groupId") Long groupId) {

        return courseStageService.queryPersonGroupProgress(person, groupId);
    }
}
