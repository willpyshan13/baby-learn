package cn.smart.controller;

import cn.smart.bo.CourseGameBO;
import cn.smart.common.annotation.Logs;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.condition.CourseInfoQueryCondition;
import cn.smart.condition.GameInfoQueryCondition;
import cn.smart.entity.CourseInfoDO;
import cn.smart.service.ICourseInfoService;
import cn.smart.service.IGameInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程相关操作
 * @author Ye
 * @date 2018-4-8 09:46:20
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    private static final Logger logger = LogManager.getLogger(CourseController.class);
    private final ICourseInfoService courseInfoService;

    @Autowired
    public CourseController(ICourseInfoService courseInfoService) {
        this.courseInfoService = courseInfoService;
    }

    @Autowired
    private IGameInfoService gameInfoService;

    /**
     * 课程列表
     * @param stageQueryCondition 查询条件
     * @return R
     */
    @GetMapping("/list")
    @ResponseBody
    public R courseInfoList(CourseInfoQueryCondition stageQueryCondition) {

        stageQueryCondition.setOrderField("serial_number");
        List<CourseInfoDO> stageList = courseInfoService.queryCourseList(stageQueryCondition);

        int total = courseInfoService.queryTotal(stageQueryCondition);

        PageUtils pageUtils = new PageUtils(stageList, total, stageQueryCondition.getLimit(), stageQueryCondition.getPage());

        return R.ok().put("courseList", pageUtils);
    }

    /**
     * 新增课程信息
     * @param courseInfo 课程信息
     * @return R
     */
    @PostMapping("/add")
    @ResponseBody
    @Logs(value = "新增课程")
    public R courseInfoAdd(@RequestBody CourseInfoDO courseInfo) {
        try {
            return courseInfoService.saveCourseInfo(courseInfo);
        } catch (Exception e) {
            return R.error(-1, "新增失败!");
        }
    }

    /**
     * 更新课程
     * @param courseInfo 课程信息
     * @return R
     */
    @PostMapping("/update")
    @ResponseBody
    @Logs(value = "更新课程")
    public R courseInfoUpdate(@RequestBody CourseInfoDO courseInfo) {
        try {
            return courseInfoService.updateCourseInfo(courseInfo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return R.error(-1, "更新失败!");
        }
    }

    /**
     * 删除课程
     * @param id 课程id
     * @return R
     */
    @PostMapping("/deleteById")
    @ResponseBody
    @Logs(value = "删除课程")
    public R deleteCourseInfo(Long id) {
        try {
            courseInfoService.deleteById(id);
        } catch (Exception e) {
            return R.error(-1, "删除失败!");
        }
        return R.ok("删除成功!");
    }

    /**
     * 为课程分配游戏
     * @param courseGameBO courseId 课程id
     * @param courseGameBO gameIds 游戏id结合
     * @return
     */
    @PostMapping("/game/assign")
    @ResponseBody
    @Logs(value = "课程分配游戏")
    public R assignGame(@RequestBody CourseGameBO courseGameBO) {

        courseInfoService.assignGame(courseGameBO.getCourseId(), courseGameBO.getGameIds());
        return R.ok();
    }

    @GetMapping("/game/list")
    @ResponseBody
    public R courseGameList(GameInfoQueryCondition gameInfoQueryCondition) {

        List<CourseGameBO> list = courseInfoService.queryCourseGame(gameInfoQueryCondition);

        int total = courseInfoService.countCourseGame(gameInfoQueryCondition);

        PageUtils pageUtils = new PageUtils(list, total, gameInfoQueryCondition.getLimit(), gameInfoQueryCondition.getPage());

        return R.ok().put("courseGame", pageUtils);
    }

    @PostMapping("/game/remove")
    @ResponseBody
    @Logs(value = "移除课程游戏")
    public R courseGameRemoveBatch(@RequestBody Long[] ids) {

        try{
            courseInfoService.removeGamesBatch(ids);
        } catch (Exception e) {
            return R.error("移除课程游戏失败");
        }

        return R.ok();
    }

    /**
     * 获取可分配班级的课程
     * @param courseInfoQueryCondition
     * @return
     */
    @GetMapping("/getCourseToClass")
    @ResponseBody
    public R getCourseToClass(CourseInfoQueryCondition courseInfoQueryCondition) {
        courseInfoQueryCondition.setOrderField("serial_number");
        List<CourseInfoDO> courseInfoDOList = courseInfoService.getCourseToClass(courseInfoQueryCondition);
        int total = courseInfoService.countCourseToClass(courseInfoQueryCondition);
        PageUtils pageUtils = new PageUtils(courseInfoDOList, total, courseInfoQueryCondition.getLimit(), courseInfoQueryCondition.getPage());
        return R.ok().put("courseToClassList", pageUtils);
    }

}
