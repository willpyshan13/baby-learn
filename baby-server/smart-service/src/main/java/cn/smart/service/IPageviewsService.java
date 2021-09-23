package cn.smart.service;

import cn.smart.common.base.service.ICrudService;
import cn.smart.condition.PageviewsQueryCondition;
import cn.smart.entity.PageviewsDO;

import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-5-25 15:03:47
*/
public interface IPageviewsService extends ICrudService<PageviewsDO, PageviewsQueryCondition> {

    List<Map<String, Object>> queryViewsGroupByCreateDay(PageviewsQueryCondition pageviewsQueryCondition);
    List<Map<String, Object>> countRegistered(PageviewsQueryCondition pageviewsQueryCondition);
}