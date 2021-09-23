package cn.smart.support.scheduler;

import cn.smart.support.scheduler.entity.SchedulerLog;
import cn.smart.utils.NativeUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.sql.Timestamp;
import java.util.concurrent.*;

/**
 * @author Admin
 */
public class JobListener implements org.quartz.JobListener {

    private Logger logger = LogManager.getLogger(JobListener.class);

    @Lazy
    @Autowired
    private SchedulerServiceImpl schedulerService;

    private static String JOB_LOG;

    private static String JOB_STATUS_RUNNING, JOB_STATUS_SUCCESS, JOB_STATUS_ERROR;

    private static ExecutorService executorService;

    static {
        executorService = new ThreadPoolExecutor(2, 20, 5L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(20), new ThreadPoolExecutor.DiscardOldestPolicy());
        JOB_LOG = "jobLog";
        JOB_STATUS_RUNNING = "I";
        JOB_STATUS_SUCCESS = "S";
        JOB_STATUS_ERROR = "E";
    }

    public JobListener() {
    }

    @Override
    public String getName() {
        return "taskListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String targetObject = jobDataMap.getString("targetObject");
        String targetMethod = jobDataMap.getString("targetMethod");
        if (logger.isInfoEnabled()) {
            logger.info("定时任务开始执行：{}.{}", targetObject, targetMethod);
        }

        SchedulerLog log = new SchedulerLog();
        log.setStartTime(context.getFireTime());
        log.setGroupName(targetObject);
        log.setTaskName(targetMethod);
        log.setStatus(JOB_STATUS_RUNNING);
        log.setServerHost(NativeUtil.getHostName());
        log.setServerDuid(NativeUtil.getDUID());
        this.schedulerService.updateLog(log);
        jobDataMap.put(JOB_LOG, log);

    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException exp) {
        Timestamp end = new Timestamp(System.currentTimeMillis());
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String targetObject = jobDataMap.getString("targetObject");
        String targetMethod = jobDataMap.getString("targetMethod");
        Long id = jobDataMap.getLong("id");
        if (logger.isInfoEnabled()) {
            logger.info("定时任务执行结束：{}.{}", targetObject, targetMethod);
        }

        final SchedulerLog log = (SchedulerLog)jobDataMap.get(JOB_LOG);
        if (log != null) {
            log.setTargetId(id);
            log.setEndTime(end);
            if (exp != null) {
                logger.error("定时任务失败: [" + targetObject + "." + targetMethod + "]", exp);
                String contactEmail = jobDataMap.getString("contactEmail");
                if (StringUtils.isNotBlank(contactEmail)) {
                    String topic = String.format("调度[%s.%s]发生异常", targetMethod, targetMethod);
                    logger.error(topic);
                }
                log.setStatus(JOB_STATUS_ERROR);
                log.setFireInfo(exp.getMessage());
            } else if (JOB_STATUS_RUNNING.equals(log.getStatus())) {
                log.setStatus(JOB_STATUS_SUCCESS);
            }
        }

        executorService.submit(() -> {
            if (log != null) {
                try {
                    JobListener.this.schedulerService.updateLog(log);
                } catch (Exception var2) {
                    logger.error("Update TaskRunLog cause error. The log object is : " + JSON.toJSONString(log), var2);
                }
            }

        });
    }
}
