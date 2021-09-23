package cn.smart.service.impl;

import cn.smart.condition.PageviewsQueryCondition;
import cn.smart.dao.IPageviewsMapper;
import cn.smart.entity.PageviewsDO;
import cn.smart.service.IPageviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-5-25 15:03:47
*/

@Service
public class PageviewsServiceImpl  implements IPageviewsService {

    @Autowired
    private IPageviewsMapper pageviewsMapper;

    @Override
    public void save(PageviewsDO pageviewsDO) {
        pageviewsMapper.save(pageviewsDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        pageviewsMapper.save(map);
    }

    @Override
    public void saveBatch(List<PageviewsDO> list) {
        pageviewsMapper.saveBatch(list);
    }

    @Override
    public int update(PageviewsDO pageviewsDO) {
        return pageviewsMapper.update(pageviewsDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return pageviewsMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return pageviewsMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return pageviewsMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return pageviewsMapper.deleteBatch(ids);
    }

    @Override
    public PageviewsDO queryById(Object id) {
        return pageviewsMapper.queryById(id);
    }

    @Override
    public List<PageviewsDO> queryList(PageviewsQueryCondition pageviewsQueryCondition) {
        return pageviewsMapper.queryList(pageviewsQueryCondition);
    }

    @Override
    public int queryTotal(PageviewsQueryCondition pageviewsQueryCondition) {
        return pageviewsMapper.queryTotal(pageviewsQueryCondition);
    }

    @Override
    public int total() {
        return pageviewsMapper.total();
    }

    @Override
    public  List<Map<String, Object>> queryViewsGroupByCreateDay(PageviewsQueryCondition pageviewsQueryCondition) {
        return pageviewsMapper.queryViewsGroupByCreateDay(pageviewsQueryCondition);
    }

    @Override
    public List<Map<String, Object>> countRegistered(PageviewsQueryCondition pageviewsQueryCondition) {
        return pageviewsMapper.countRegistered(pageviewsQueryCondition);
    }
}
