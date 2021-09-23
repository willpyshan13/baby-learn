package cn.smart.service.impl;

import cn.smart.service.IRecordingService;

import cn.smart.dao.IRecordingMapper;
import cn.smart.condition.RecordingQueryCondition;
import cn.smart.entity.RecordingDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-10-12 13:44:22
*/

@Service
public class RecordingServiceImpl  implements IRecordingService {

    @Autowired
    private IRecordingMapper recordingMapper;

    @Override
    public void save(RecordingDO recordingDO) {
        recordingMapper.save(recordingDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        recordingMapper.save(map);
    }

    @Override
    public void saveBatch(List<RecordingDO> list) {
        recordingMapper.saveBatch(list);
    }

    @Override
    public int update(RecordingDO recordingDO) {
        return recordingMapper.update(recordingDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return recordingMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return recordingMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return recordingMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return recordingMapper.deleteBatch(ids);
    }

    @Override
    public RecordingDO queryById(Object id) {
        return recordingMapper.queryById(id);
    }

    @Override
    public List<RecordingDO> queryList(RecordingQueryCondition recordingQueryCondition) {
        return recordingMapper.queryList(recordingQueryCondition);
    }

    @Override
    public int queryTotal(RecordingQueryCondition recordingQueryCondition) {
        return recordingMapper.queryTotal(recordingQueryCondition);
    }

    @Override
    public int total() {
        return recordingMapper.total();
    }

    @Override
    public void saveSelective(RecordingDO recording) {
        recordingMapper.insertSelective(recording);
    }
}
