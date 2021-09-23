package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.WeekReportSuggestionsQueryCondition;
import cn.smart.entity.WeekReportSuggestionsDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-8-30 18:33:12
 */
@Repository
public interface IWeekReportSuggestionsMapper extends IBaseDao<WeekReportSuggestionsDO, WeekReportSuggestionsQueryCondition> {

}
