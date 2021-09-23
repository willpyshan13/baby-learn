package cn.smart.service;

import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.entity.RoomDO;
import cn.smart.support.scheduler.TaskScheduled;
import cn.smart.support.scheduler.entity.SchedulerLog;
import cn.smart.support.scheduler.entity.SchedulerLogQueryCondition;

import java.util.List;

/**
 * @author Ye
 */
public interface ISchedulerService {

    /**
     * 获取所有作业列表
     * @return
     */
    List<TaskScheduled> getAllTaskDetail();

    /**
     * 执行作业
     * @param taskScheduler
     */
    void executeJob(TaskScheduled taskScheduler);

    /**
     * 恢复作业
     * @param taskScheduled
     */
    void openJob(TaskScheduled taskScheduled);

    /**
     * 关闭作业
     * @param scheduled
     */
    void closeTask(TaskScheduled scheduled);

    /**
     * 删除作业
     * @param taskScheduled
     */
    void deleteJob(TaskScheduled taskScheduled);

    /**
     * 更新作业
     * @param taskScheduled
     */
    void updateJob(TaskScheduled taskScheduled);

    /**
     * 更新定时任务日志
     * @param record
     */
    SchedulerLog updateLog(SchedulerLog record);

    /**
     * 查询定时任务日志
     * @param queryCondition
     * @return
     */
    PageUtils querySchedulerLog(SchedulerLogQueryCondition queryCondition);

    /**
     * 添加推送直播开课通知
     * @param room
     * @return
     */
    R addClassBeginNotificationJob(RoomDO room);

    R generatePersonRecording(RoomDO room);
}
