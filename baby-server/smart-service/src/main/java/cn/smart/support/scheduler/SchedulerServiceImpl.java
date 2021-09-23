package cn.smart.support.scheduler;

import cn.smart.common.utils.PageUtils;
import cn.smart.support.scheduler.entity.SchedulerLog;
import cn.smart.support.scheduler.entity.SchedulerLogQueryCondition;
import cn.smart.support.scheduler.mapper.ISchedulerLogMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调度任务操作类
 *
 * @author Admin
 */
public class SchedulerServiceImpl implements ApplicationContextAware {

    @Autowired
    private SchedulerManager schedulerManager;

    @Autowired
    private ISchedulerLogMapper schedulerLogMapper;

    private ApplicationContext applicationContext;

    /**
     * 获取所有作业列表
     */
    public List<TaskScheduled> getAllTaskDetail() {
        return this.schedulerManager.getAllJobDetail();
    }

    /**
     * 执行作业
     */
    public void executeJob(TaskScheduled taskScheduler) {
        this.schedulerManager.runJob(taskScheduler);
    }

    /**
     * 恢复作业
     */
    public void openJob(TaskScheduled taskScheduled) {
        this.schedulerManager.resumeJob(taskScheduled);
    }

    /**
     * 关闭作业
     */
    public void closeTask(TaskScheduled scheduled) {
        this.schedulerManager.stopJob(scheduled);
    }

    /**
     * 删除作业
     */
    public void deleteJob(TaskScheduled taskScheduled) {
        this.schedulerManager.delJob(taskScheduled);
    }

    /**
     * 更新作业
     */
    public void updateJob(TaskScheduled taskScheduled) {
        this.schedulerManager.updateTask(taskScheduled);
    }

    /**
     * 调度任务执行日志
     */
    public SchedulerLog updateLog(SchedulerLog record) {
        if (record.getId() == null) {
            this.schedulerLogMapper.save(record);
        } else {
            this.schedulerLogMapper.update(record);
        }
        return record;
    }

    public PageUtils querySchedulerLog(SchedulerLogQueryCondition queryCondition) {

        List<SchedulerLog> listSchedulerLog = schedulerLogMapper.queryList(queryCondition);

        int total = schedulerLogMapper.queryTotal(queryCondition);

        return new PageUtils(listSchedulerLog, total, queryCondition.getLimit(), queryCondition.getPage());

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
