package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.WeekReportDetailQueryCondition;
import cn.smart.entity.WeekReportDetailDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-8-29 14:06:25
 */
@Repository
public interface IWeekReportDetailMapper extends IBaseDao<WeekReportDetailDO, WeekReportDetailQueryCondition> {

}
