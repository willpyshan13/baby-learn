package cn.smart.support.scheduler;

import cn.smart.common.exception.CustomException;
import cn.smart.support.scheduler.job.BaseJob;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.JobListener;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.InitializingBean;

import java.util.*;

/**
 * @author Admin
 */
public class SchedulerManager implements InitializingBean {

    private Logger logger = LogManager.getLogger(SchedulerManager.class);

    private Scheduler scheduler;

    private List<JobListener> listJobListener;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.listJobListener != null && this.listJobListener.size() > 0) {
            this.logger.debug("Initialize task scheduler [" + this.scheduler.getSchedulerName() + "] , Add listener size ：" + this.listJobListener.size());

            for (JobListener listener : this.listJobListener) {
                this.logger.debug("Add JobListener : " + listener.getName());
                this.scheduler.getListenerManager().addJobListener(listener);
            }
        }
    }

    public boolean updateTask(TaskScheduled taskScheduled) {
        logger.info(taskScheduled);

        String jobGroup = taskScheduled.getTaskGroup();
        if (StringUtils.isEmpty(jobGroup)) {
            jobGroup = "baseJob";
        }
        String jobName = taskScheduled.getTaskName();
        if (StringUtils.isEmpty(jobName)) {
            jobName = String.valueOf(System.currentTimeMillis());
        }
        String cronExpression = taskScheduled.getTaskCron();
        String targetObject = taskScheduled.getTargetObject();
        String targetMethod = taskScheduled.getTargetMethod();
        String jobDescription = taskScheduled.getTaskDesc();
        String jobType = taskScheduled.getJobType();
        String taskType = taskScheduled.getTaskType();
        JobDataMap jobDataMap = new JobDataMap();
        if (TaskScheduled.TaskType.DUBBO.equals(taskType)) {
            jobDataMap.put("targetSystem", taskScheduled.getTargetSystem());
        }
        jobDataMap.put("targetObject", targetObject);
        jobDataMap.put("targetMethod", targetMethod);
        jobDataMap.put("taskType", taskType);
        jobDataMap.put("contactName", taskScheduled.getContactName());
        jobDataMap.put("contactEmail", taskScheduled.getContactEmail());
        jobDataMap.put("id", taskScheduled.getId());

        JobBuilder jobBuilder = null;
        if (TaskScheduled.JobType.JOB.equals(jobType)) {
            jobBuilder = JobBuilder.newJob(BaseJob.class);
        } else if (TaskScheduled.JobType.STATEFUL_JOB.equals(jobType)) {
            jobBuilder = JobBuilder.newJob(StatefulJob.class);
        }
        if (jobBuilder != null) {
            JobDetail jobDetail = jobBuilder.withIdentity(jobName, jobGroup).withDescription(jobDescription)
                    .storeDurably(true).usingJobData(jobDataMap).build();

            Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                    .withIdentity(jobName, jobGroup).withDescription(jobDescription).forJob(jobDetail)
                    .usingJobData(jobDataMap).build();

            try {
                JobDetail detail = scheduler.getJobDetail(new JobKey(jobName, jobGroup));
                if (detail == null) {
                    scheduler.scheduleJob(jobDetail, trigger);
                } else {
                    Trigger oldTrigger = scheduler.getTrigger(new TriggerKey(jobName, jobGroup));
                    if (null == oldTrigger) {
                        delJob(taskScheduled);
                        scheduler.scheduleJob(jobDetail, trigger);
                    } else {
                        scheduler.addJob(jobDetail, true);
                        scheduler.rescheduleJob(new TriggerKey(jobName, jobGroup), trigger);
                    }
                }
                return true;
            } catch (SchedulerException e) {
                logger.error("SchedulerException", e);
                throw new CustomException(e.getMessage());
            }
        }


        return false;
    }

    public List<TaskScheduled> getAllJobDetail() {
        List<TaskScheduled> result = new LinkedList<>();
        try {
            GroupMatcher<JobKey> matcher = GroupMatcher.jobGroupContains("");
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            for (JobKey jobKey : jobKeys) {
                JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    TaskScheduled job = new TaskScheduled();
                    job.setTaskName(jobKey.getName());
                    job.setTaskGroup(jobKey.getGroup());
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    job.setStatus(triggerState.name());
                    if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger = (CronTrigger)trigger;
                        String cronExpression = cronTrigger.getCronExpression();
                        job.setTaskCron(cronExpression);
                    }
                    job.setPreviousFireTime(trigger.getPreviousFireTime());
                    job.setNextFireTime(trigger.getNextFireTime());
                    JobDataMap jobDataMap = trigger.getJobDataMap();
                    job.setTaskType(jobDataMap.getString("taskType"));
                    job.setTargetSystem(jobDataMap.getString("targetSystem"));
                    job.setTargetObject(jobDataMap.getString("targetObject"));
                    job.setTargetMethod(jobDataMap.getString("targetMethod"));
                    job.setContactName(jobDataMap.getString("contactName"));
                    job.setContactEmail(jobDataMap.getString("contactEmail"));
                    Object i = jobDataMap.get("id");
                    job.setId(i != null ? (long)i : null);
                    job.setTaskDesc(jobDetail.getDescription());

                    String jobClass = jobDetail.getJobClass().getSimpleName();
                    if ("StatefulJob".equals(jobClass)) {
                        job.setJobType(TaskScheduled.JobType.STATEFUL_JOB);
                    } else if ("BaseJob".equals(jobClass)) {
                        job.setJobType(TaskScheduled.JobType.JOB);
                    }
                    result.add(job);
                }
            }
        } catch (Exception e) {
            logger.error("Try to load All JobDetail cause error : ", e);
        }
        return result;
    }


    public void resumeJob(TaskScheduled scheduleJob) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getTaskName(), scheduleJob.getTaskGroup());
            this.scheduler.resumeTrigger(triggerKey);
        } catch (Exception var3) {
            this.logger.error("Try to resume Job cause error : ", var3);
        }
    }

    public void runJob(TaskScheduled scheduleJob) {
        try {
            JobKey jobKey = JobKey.jobKey(scheduleJob.getTaskName(), scheduleJob.getTaskGroup());
            this.scheduler.triggerJob(jobKey);
        } catch (Exception var3) {
            this.logger.error("Try to resume Job cause error : ", var3);
        }
    }

    public void delJob(TaskScheduled scheduleJob) {
        try {
            JobKey jobKey = JobKey.jobKey(scheduleJob.getTaskName(), scheduleJob.getTaskGroup());
            TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getTaskName(), scheduleJob.getTaskGroup());
            this.scheduler.pauseTrigger(triggerKey);
            this.scheduler.unscheduleJob(triggerKey);
            this.scheduler.deleteJob(jobKey);
        } catch (Exception var4) {
            this.logger.error("Try to resume Job cause error : ", var4);
        }
    }

    public void stopJob(TaskScheduled scheduleJob) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getTaskName(), scheduleJob.getTaskGroup());
            this.scheduler.pauseTrigger(triggerKey);
        } catch (Exception var3) {
            this.logger.error("Try to stop Job cause error : ", var3);
        }
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void setListJobListener(List<JobListener> listJobListener) {
        this.listJobListener = listJobListener;
    }
}
