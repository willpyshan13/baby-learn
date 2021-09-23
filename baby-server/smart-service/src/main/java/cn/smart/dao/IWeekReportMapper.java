package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.WeekReportQueryCondition;
import cn.smart.entity.WeekReportDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-8-29 14:01:21
 */
@Repository
public interface IWeekReportMapper extends IBaseDao<WeekReportDO, WeekReportQueryCondition> {

}
