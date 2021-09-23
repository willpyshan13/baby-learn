package cn.smart.service.impl;

import cn.smart.condition.PushLogQueryCondition;
import cn.smart.dao.IPushLogMapper;
import cn.smart.entity.PushLogDO;
import cn.smart.service.IPushLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-7-10 17:56:54
*/

@Service
public class PushLogServiceImpl  implements IPushLogService {

    @Autowired
    private IPushLogMapper pushLogMapper;

    @Override
    public void save(PushLogDO pushLogDO) {
        pushLogMapper.save(pushLogDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        pushLogMapper.save(map);
    }

    @Override
    public void saveBatch(List<PushLogDO> list) {
        pushLogMapper.saveBatch(list);
    }

    @Override
    public int update(PushLogDO pushLogDO) {
        return pushLogMapper.update(pushLogDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return pushLogMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return pushLogMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return pushLogMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return pushLogMapper.deleteBatch(ids);
    }

    @Override
    public PushLogDO queryById(Object id) {
        return pushLogMapper.queryById(id);
    }

    @Override
    public List<PushLogDO> queryList(PushLogQueryCondition pushLogQueryCondition) {
        return pushLogMapper.queryList(pushLogQueryCondition);
    }

    @Override
    public int queryTotal(PushLogQueryCondition pushLogQueryCondition) {
        return pushLogMapper.queryTotal(pushLogQueryCondition);
    }

    @Override
    public int total() {
        return pushLogMapper.total();
    }
}
