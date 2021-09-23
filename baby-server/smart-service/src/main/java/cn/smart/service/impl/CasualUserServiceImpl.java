package cn.smart.service.impl;

import cn.smart.service.ICasualUserService;

import cn.smart.dao.ICasualUserMapper;
import cn.smart.condition.CasualUserQueryCondition;
import cn.smart.entity.CasualUserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-8-21 14:16:21
*/

@Service
public class CasualUserServiceImpl  implements ICasualUserService {

    @Autowired
    private ICasualUserMapper casualUserMapper;

    @Override
    public void save(CasualUserDO casualUserDO) {
        casualUserMapper.save(casualUserDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        casualUserMapper.save(map);
    }

    @Override
    public void saveBatch(List<CasualUserDO> list) {
        casualUserMapper.saveBatch(list);
    }

    @Override
    public int update(CasualUserDO casualUserDO) {
        return casualUserMapper.update(casualUserDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return casualUserMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return casualUserMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return casualUserMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return casualUserMapper.deleteBatch(ids);
    }

    @Override
    public CasualUserDO queryById(Object id) {
        return casualUserMapper.queryById(id);
    }

    @Override
    public List<CasualUserDO> queryList(CasualUserQueryCondition casualUserQueryCondition) {
        return casualUserMapper.queryList(casualUserQueryCondition);
    }

    @Override
    public int queryTotal(CasualUserQueryCondition casualUserQueryCondition) {
        return casualUserMapper.queryTotal(casualUserQueryCondition);
    }

    @Override
    public int total() {
        return casualUserMapper.total();
    }
}
