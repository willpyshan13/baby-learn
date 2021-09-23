package cn.smart.service.impl;

import cn.smart.condition.UsageLogQueryCondition;
import cn.smart.dao.IUsageLogMapper;
import cn.smart.entity.UsageLogDO;
import cn.smart.service.IUsageLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-6-15 17:55:05
*/

@Service
public class UsageLogServiceImpl  implements IUsageLogService {

    @Autowired
    private IUsageLogMapper usageLogMapper;

    @Override
    public void save(UsageLogDO usageLogDO) {
        usageLogMapper.save(usageLogDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        usageLogMapper.save(map);
    }

    @Override
    public void saveBatch(List<UsageLogDO> list) {
        usageLogMapper.saveBatch(list);
    }

    @Override
    public int update(UsageLogDO usageLogDO) {
        return usageLogMapper.update(usageLogDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return usageLogMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return usageLogMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return usageLogMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return usageLogMapper.deleteBatch(ids);
    }

    @Override
    public UsageLogDO queryById(Object id) {
        return usageLogMapper.queryById(id);
    }

    @Override
    public List<UsageLogDO> queryList(UsageLogQueryCondition usageLogQueryCondition) {
        return usageLogMapper.queryList(usageLogQueryCondition);
    }

    @Override
    public int queryTotal(UsageLogQueryCondition usageLogQueryCondition) {
        return usageLogMapper.queryTotal(usageLogQueryCondition);
    }

    @Override
    public int total() {
        return usageLogMapper.total();
    }
}
