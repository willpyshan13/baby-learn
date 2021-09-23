package cn.smart.service.impl;

import cn.smart.service.IPersonRecordingService;

import cn.smart.dao.IPersonRecordingMapper;
import cn.smart.condition.PersonRecordingQueryCondition;
import cn.smart.entity.PersonRecordingDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-10-12 17:14:16
*/

@Service
public class PersonRecordingServiceImpl  implements IPersonRecordingService {

    @Autowired
    private IPersonRecordingMapper personRecordingMapper;

    @Override
    public void save(PersonRecordingDO personRecordingDO) {
        personRecordingMapper.save(personRecordingDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        personRecordingMapper.save(map);
    }

    @Override
    public void saveBatch(List<PersonRecordingDO> list) {
        personRecordingMapper.saveBatch(list);
    }

    @Override
    public int update(PersonRecordingDO personRecordingDO) {
        return personRecordingMapper.update(personRecordingDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return personRecordingMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return personRecordingMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return personRecordingMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return personRecordingMapper.deleteBatch(ids);
    }

    @Override
    public PersonRecordingDO queryById(Object id) {
        return personRecordingMapper.queryById(id);
    }

    @Override
    public List<PersonRecordingDO> queryList(PersonRecordingQueryCondition personRecordingQueryCondition) {
        return personRecordingMapper.queryList(personRecordingQueryCondition);
    }

    @Override
    public int queryTotal(PersonRecordingQueryCondition personRecordingQueryCondition) {
        return personRecordingMapper.queryTotal(personRecordingQueryCondition);
    }

    @Override
    public int total() {
        return personRecordingMapper.total();
    }
}
