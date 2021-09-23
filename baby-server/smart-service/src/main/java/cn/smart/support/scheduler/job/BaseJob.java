package cn.smart.support.scheduler.job;

import cn.smart.support.scheduler.TaskScheduled;
import cn.smart.utils.CacheUtil;
import cn.smart.utils.MathUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.UncheckedIOException;
import java.util.Map;

/**
 * @author admin
 */
@Component
public class BaseJob implements Job {

    private Logger logger = LogManager.getLogger(BaseJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        long start = System.currentTimeMillis();
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String taskType = jobDataMap.getString("taskType");
        String targetObject = jobDataMap.getString("targetObject");
        String targetMethod = jobDataMap.getString("targetMethod");
        Long id = (Long) jobDataMap.get("id");
        String key = targetMethod + "." + targetObject;

        try {
            this.logger.info("定时任务[{}.{}]开始", targetObject, targetMethod);
            //if (CacheUtil.getLock(key, null)) {
            //    try {
                    ApplicationContext applicationContext = (ApplicationContext)context.getScheduler().getContext().get("applicationContextKey");
                    Object refer;
                    if (TaskScheduled.TaskType.LOCAL.equals(taskType)) {
                        refer = applicationContext.getBean(targetObject);
                        if (id == null) {
                            refer.getClass().getDeclaredMethod(targetMethod).invoke(refer);
                        } else {
                            refer.getClass().getDeclaredMethod(targetMethod, Long.class).invoke(refer, id);
                        }
                    } else {
                        refer = applicationContext.getBean(targetObject);
                        refer.getClass().getDeclaredMethod(targetMethod).invoke(refer);
                    }

                    Double time = (double)(System.currentTimeMillis() - start) / 1000.0D;
                    this.logger.info("定时任务[{}.{}]用时：{}s", targetObject, targetMethod, time.toString());
            //    } finally {
            //        this.unLock(key);
            //   }
            //}

        } catch (Exception var15) {
            throw new JobExecutionException(var15);
        }

    }

    private void unLock(String key) {
        try {
            CacheUtil.unLock(key);
        } catch (Exception var5) {
            this.logger.error("", var5);
            try {
                Thread.sleep(MathUtil.getRandom(100, 2000).longValue());
            } catch (Exception e2) {
                logger.error("", e2);
            }
            unLock(key);
        }

    }
}
