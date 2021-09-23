package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.PageviewsQueryCondition;
import cn.smart.entity.PageviewsDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Ye
 * @date 2018-5-25 15:03:47
 */
@Repository
public interface IPageviewsMapper extends IBaseDao<PageviewsDO, PageviewsQueryCondition> {

    List<Map<String, Object>> queryViewsGroupByCreateDay(PageviewsQueryCondition pageviewsQueryCondition);
    List<Map<String, Object>> countRegistered(PageviewsQueryCondition pageviewsQueryCondition);
}
