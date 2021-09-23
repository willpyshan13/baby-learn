package cn.smart.service.impl;

import cn.smart.service.ISysLogsService;

import cn.smart.dao.ISysLogsMapper;
import cn.smart.condition.SysLogsQueryCondition;
import cn.smart.entity.SysLogsDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-8-23 18:41:26
*/

@Service
public class SysLogsServiceImpl  implements ISysLogsService {

    @Autowired
    private ISysLogsMapper sysLogsMapper;

    @Override
    public void save(SysLogsDO sysLogsDO) {
        sysLogsMapper.save(sysLogsDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        sysLogsMapper.save(map);
    }

    @Override
    public void saveBatch(List<SysLogsDO> list) {
        sysLogsMapper.saveBatch(list);
    }

    @Override
    public int update(SysLogsDO sysLogsDO) {
        return sysLogsMapper.update(sysLogsDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return sysLogsMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return sysLogsMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return sysLogsMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return sysLogsMapper.deleteBatch(ids);
    }

    @Override
    public SysLogsDO queryById(Object id) {
        return sysLogsMapper.queryById(id);
    }

    @Override
    public List<SysLogsDO> queryList(SysLogsQueryCondition sysLogsQueryCondition) {
        return sysLogsMapper.queryList(sysLogsQueryCondition);
    }

    @Override
    public int queryTotal(SysLogsQueryCondition sysLogsQueryCondition) {
        return sysLogsMapper.queryTotal(sysLogsQueryCondition);
    }

    @Override
    public int total() {
        return sysLogsMapper.total();
    }

    @Override
    public void saveApiLog(SysLogsDO log) {
        sysLogsMapper.saveApiLog(log);
    }

    @Override
    public int updateApiLog(SysLogsDO log) {
        return sysLogsMapper.updateApiLog(log);
    }

    @Override
    public List<SysLogsDO> queryApiLogList(SysLogsQueryCondition query) {
        return sysLogsMapper.queryApiLogList(query);
    }

    @Override
    public int countApiLog(SysLogsQueryCondition query) {
        return sysLogsMapper.countApiLog(query);
    }
}
