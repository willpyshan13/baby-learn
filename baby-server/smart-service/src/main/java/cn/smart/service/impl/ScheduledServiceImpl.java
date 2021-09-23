package cn.smart.service.impl;

import cn.smart.common.utils.DateUtil;
import cn.smart.common.utils.DateUtils;
import cn.smart.common.utils.R;
import cn.smart.entity.RoomDO;
import cn.smart.service.ISchedulerService;
import cn.smart.support.scheduler.SchedulerServiceImpl;
import cn.smart.support.scheduler.TaskScheduled;
import cn.smart.support.scheduler.job.MessageJob;
import cn.smart.support.scheduler.job.RecordingGeneratorJob;
import cn.smart.utils.CronUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Ye
 */
@Service
public class ScheduledServiceImpl extends SchedulerServiceImpl implements ISchedulerService {

    private static final int PUSH_REDUCE_MINUTE = 7;

    @Override
    public R addClassBeginNotificationJob(RoomDO room) {

        TaskScheduled taskJob = new TaskScheduled();
        taskJob.setTaskName(room.getId().toString());
        taskJob.setTaskGroup("room_msg");
        taskJob.setJobType(TaskScheduled.JobType.JOB);
        taskJob.setTaskType(TaskScheduled.TaskType.LOCAL);
        taskJob.setStatus("1");
        taskJob.setTargetObject(MessageJob.NAME);
        taskJob.setTargetMethod(MessageJob.CLASS_BEGIN_METHOD);
        taskJob.setTaskDesc("直播教室<" + room.getRoomName() + ">推送上课开始消息。");
        taskJob.setId(room.getId());

        Date pushTime = DateUtils.addMinutes(room.getStartTime(), -PUSH_REDUCE_MINUTE);
        String cron = CronUtil.formatWithPatten(pushTime, "00 mm HH dd MM ? yyyy");
        taskJob.setTaskCron(cron);
        try{
            updateJob(taskJob);
        } catch (Exception e) {
            return R.error("创建上课开始消息失败! " + e);
        }
        return R.ok();
    }

    @Override
    public R generatePersonRecording(RoomDO room) {

        TaskScheduled taskJob = new TaskScheduled();
        taskJob.setTaskName(room.getId().toString());
        taskJob.setTaskGroup("recording_generator");
        taskJob.setJobType(TaskScheduled.JobType.JOB);
        taskJob.setTaskType(TaskScheduled.TaskType.LOCAL);
        taskJob.setStatus("1");
        taskJob.setTargetObject(RecordingGeneratorJob.NAME);
        taskJob.setTargetMethod(RecordingGeneratorJob.METHOD);
        taskJob.setTaskDesc("直播教室<" + room.getRoomName() + ">录制件关联用户调度。");
        taskJob.setId(room.getId());

        Date endTime = DateUtil.addHours(room.getEndTime(), 10);
        String cron = CronUtil.formatWithPatten(endTime, "00 mm HH dd MM ? yyyy");
        taskJob.setTaskCron(cron);
        try{
            updateJob(taskJob);
        } catch (Exception e) {
            return R.error("用户关联教室录制件失败! " + e);
        }
        return R.ok();
    }
}
