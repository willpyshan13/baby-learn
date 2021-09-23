package cn.smart.service.impl;

import cn.smart.condition.TeacherQueryCondition;
import cn.smart.dao.ITeacherMapper;
import cn.smart.entity.TeacherDO;
import cn.smart.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-4-10 17:22:12
*/

@Service
public class TeacherServiceImpl  implements ITeacherService {

    @Autowired
    private ITeacherMapper teacherMapper;

    @Override
    public void save(TeacherDO teacherDO) {
        teacherMapper.save(teacherDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        teacherMapper.save(map);
    }

    @Override
    public void saveBatch(List<TeacherDO> list) {
        teacherMapper.saveBatch(list);
    }

    @Override
    public int update(TeacherDO teacherDO) {
        return teacherMapper.update(teacherDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return teacherMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return teacherMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return teacherMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return teacherMapper.deleteBatch(ids);
    }

    @Override
    public TeacherDO queryById(Object id) {
        return teacherMapper.queryById(id);
    }

    @Override
    public List<TeacherDO> queryList(TeacherQueryCondition teacherQueryCondition) {
        return teacherMapper.queryList(teacherQueryCondition);
    }

    @Override
    public int queryTotal(TeacherQueryCondition teacherQueryCondition) {
        return teacherMapper.queryTotal(teacherQueryCondition);
    }

    @Override
    public int total() {
        return teacherMapper.total();
    }
}
