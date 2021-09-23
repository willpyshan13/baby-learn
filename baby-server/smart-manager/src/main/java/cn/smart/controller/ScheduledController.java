package cn.smart.controller;


import cn.smart.bo.ScheduledBO;
import cn.smart.common.annotation.Logs;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.condition.ClassInfoQueryCondition;
import cn.smart.entity.ClassInfoDO;
import cn.smart.service.IClassInfoService;
import cn.smart.service.ISchedulerService;
import cn.smart.support.scheduler.TaskScheduled;
import cn.smart.support.scheduler.config.SchedulerConfig;
import cn.smart.support.scheduler.entity.SchedulerLogQueryCondition;
import cn.smart.support.scheduler.job.OpenClassCourseJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ye
 */
@Controller
@RequestMapping(value="/scheduled")
public class ScheduledController {

    @Autowired
    private ISchedulerService schedulerService;

    @Autowired
    private IClassInfoService classInfoService;

    @Autowired
    private SchedulerConfig schedulerConfig;

    /**
     * 任务列表
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public R taskList() {


        List<TaskScheduled> list = schedulerService.getAllTaskDetail();

        List<ScheduledBO> scheduledList = new ArrayList<>(list.size());

        List<ClassInfoDO> classList = classInfoService.queryList(new ClassInfoQueryCondition());

        ScheduledBO s;
        for (TaskScheduled scheduled : list) {
            s = new ScheduledBO();
            s.setId(scheduled.getId());
            s.setTaskName(scheduled.getTaskName());
            s.setPreviousFireTime(scheduled.getPreviousFireTime());
            s.setNextFireTime(scheduled.getNextFireTime());
            s.setTargetObject(scheduled.getTargetObject());
            s.setTargetMethod(scheduled.getTargetMethod());
            s.setTaskCron(scheduled.getTaskCron());
            s.setTaskGroup(scheduled.getTaskGroup());
            s.setTaskType(scheduled.getTaskType());
            s.setStatus(scheduled.getStatus());
            s.setTaskDesc(scheduled.getTaskDesc());

            if (OpenClassCourseJob.NAME.equals(s.getTargetObject()) && OpenClassCourseJob.OPEN_CLASS_COURSE.equals(s.getTargetMethod())) {
                for (ClassInfoDO c : classList) {
                    if (c.getId().equals(s.getId())) {
                        s.setClassName(c.getClassName());
                        s.setOpenCourseName(c.getCourseName());
                    }
                }
            }
            scheduledList.add(s);
        }

        PageUtils pageUtils = new PageUtils(scheduledList, list.size(), list.size(), 1);

        return R.ok().put("scheduler", pageUtils);
    }

    /**
     * 任务日志
     * @return
     */
    @GetMapping("/logs/list")
    @ResponseBody
    public R schedulerLog(SchedulerLogQueryCondition schedulerLogQueryCondition) {

        PageUtils pageUtils = schedulerService.querySchedulerLog(schedulerLogQueryCondition);

        return R.ok().put("schedulerLogs", pageUtils);
    }


    @PostMapping("/open/course")
    @ResponseBody
    @Logs(value = "新增班级自动开课任务")
    public R addOpenCourseTask(Long classId) {

        TaskScheduled scheduled = new TaskScheduled();
        scheduled.setTaskName("OpenClassCourse"+classId);
        scheduled.setTaskGroup("course_open");
        scheduled.setId(classId);
        scheduled.setJobType(TaskScheduled.JobType.JOB);
        scheduled.setTaskType(TaskScheduled.TaskType.LOCAL);
        scheduled.setTargetMethod(OpenClassCourseJob.OPEN_CLASS_COURSE);
        scheduled.setTargetObject(OpenClassCourseJob.NAME);
        scheduled.setTaskDesc("自动开课班级课程任务");
        scheduled.setTaskCron(this.schedulerConfig.getOpenCourseTimes());
        scheduled.setStatus("1");

        try {
            schedulerService.updateJob(scheduled);
        } catch (Exception e) {
            return R.error("新增班级自动开课任务失败");
        }

        return R.ok("操作成功");
    }


    /**
     * 新增任务
     * @param scheduled
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    @Logs(value = "新增任务")
    public R addTask(@RequestBody TaskScheduled scheduled) {

        try {
            schedulerService.updateJob(scheduled);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("操作失败！");
        }
        return R.ok("操作成功！");
    }

    /**
     * 暂停任务
     * @param scheduled
     * @return
     */
    @PostMapping("/close")
    @ResponseBody
    @Logs(value = "暂停任务")
    public R closeJob(@RequestBody TaskScheduled scheduled) {
        try {
            schedulerService.closeTask(scheduled);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("暂停任务失败！" + e.getMessage());
        }
        return R.ok("暂停成功！");
    }

    /**
     * 删除任务
     * @param scheduled
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    @Logs(value = "删除任务")
    public R deleteTask(@RequestBody TaskScheduled scheduled) {
        try {
            schedulerService.deleteJob(scheduled);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("删除任务失败！" + e.getMessage());
        }
        return R.ok("删除成功");
    }

    /**
     * 启动任务
     * @param scheduled
     * @return
     */
    @PostMapping("/open")
    @ResponseBody
    @Logs(value = "启动任务")
    public R updateTask(@RequestBody TaskScheduled scheduled) {
        try {
            schedulerService.openJob(scheduled);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("启动任务失败！" + e.getMessage());
        }
        return R.ok("启动成功");
    }

    /**
     * 立即执行任务
     * @param scheduled
     * @return
     */
    @PostMapping("/run")
    @ResponseBody
    @Logs(value = "立即执行任务")
    public R runJob(@RequestBody TaskScheduled scheduled) {
        try {
            schedulerService.executeJob(scheduled);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("执行任务失败！" + e.getMessage());
        }
        return R.ok("执行任务成功");
    }



}
