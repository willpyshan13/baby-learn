package cn.smart.service.impl;

import cn.smart.service.IWeekReportService;

import cn.smart.dao.IWeekReportMapper;
import cn.smart.condition.WeekReportQueryCondition;
import cn.smart.entity.WeekReportDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-8-29 14:01:21
*/

@Service
public class WeekReportServiceImpl  implements IWeekReportService {

    @Autowired
    private IWeekReportMapper weekReportMapper;

    @Override
    public void save(WeekReportDO weekReportDO) {
        weekReportMapper.save(weekReportDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        weekReportMapper.save(map);
    }

    @Override
    public void saveBatch(List<WeekReportDO> list) {
        weekReportMapper.saveBatch(list);
    }

    @Override
    public int update(WeekReportDO weekReportDO) {
        return weekReportMapper.update(weekReportDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return weekReportMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return weekReportMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return weekReportMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return weekReportMapper.deleteBatch(ids);
    }

    @Override
    public WeekReportDO queryById(Object id) {
        return weekReportMapper.queryById(id);
    }

    @Override
    public List<WeekReportDO> queryList(WeekReportQueryCondition weekReportQueryCondition) {
        return weekReportMapper.queryList(weekReportQueryCondition);
    }

    @Override
    public int queryTotal(WeekReportQueryCondition weekReportQueryCondition) {
        return weekReportMapper.queryTotal(weekReportQueryCondition);
    }

    @Override
    public int total() {
        return weekReportMapper.total();
    }
}
