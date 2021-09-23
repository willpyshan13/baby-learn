package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.UsageLogQueryCondition;
import cn.smart.entity.UsageLogDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-6-15 17:55:05
 */
@Repository
public interface IUsageLogMapper extends IBaseDao<UsageLogDO, UsageLogQueryCondition> {

}
