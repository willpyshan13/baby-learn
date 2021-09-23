package cn.smart.support.scheduler.mapper;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.support.scheduler.entity.SchedulerLog;
import cn.smart.support.scheduler.entity.SchedulerLogQueryCondition;
import org.springframework.stereotype.Repository;

/**
 * @author Admin
 */
@Repository
public interface ISchedulerLogMapper extends IBaseDao<SchedulerLog, SchedulerLogQueryCondition> {

}
