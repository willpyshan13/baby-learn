package cn.smart.service.impl;

import cn.smart.condition.PersonCourseStageQueryCondition;
import cn.smart.dao.IPersonCourseStageMapper;
import cn.smart.entity.CourseStageDO;
import cn.smart.entity.PersonCourseStageDO;
import cn.smart.service.IPersonCourseStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-4-8 11:09:48
*/

@Service
public class PersonCourseStageServiceImpl  implements IPersonCourseStageService {

    @Autowired
    private IPersonCourseStageMapper personCourseStageMapper;

    @Override
    public void save(PersonCourseStageDO personCourseStageDO) {
        personCourseStageMapper.save(personCourseStageDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        personCourseStageMapper.save(map);
    }

    @Override
    public void saveBatch(List<PersonCourseStageDO> list) {
        personCourseStageMapper.saveBatch(list);
    }

    @Override
    public int update(PersonCourseStageDO personCourseStageDO) {
        return personCourseStageMapper.update(personCourseStageDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return personCourseStageMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return personCourseStageMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return personCourseStageMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return personCourseStageMapper.deleteBatch(ids);
    }

    @Override
    public PersonCourseStageDO queryById(Object id) {
        return personCourseStageMapper.queryById(id);
    }

    @Override
    public List<PersonCourseStageDO> queryList(PersonCourseStageQueryCondition personCourseStageQueryCondition) {
        return personCourseStageMapper.queryList(personCourseStageQueryCondition);
    }

    @Override
    public int queryTotal(PersonCourseStageQueryCondition personCourseStageQueryCondition) {
        return personCourseStageMapper.queryTotal(personCourseStageQueryCondition);
    }

    @Override
    public int total() {
        return personCourseStageMapper.total();
    }

    @Override
    public List<CourseStageDO> queryPersonBoughtCourse(Long id) {
        return personCourseStageMapper.queryPersonBoughtCourse(id);
    }

    @Override
    public int queryPersonBoughtCourseTotal(Long id) {
        return personCourseStageMapper.queryPersonBoughtCourseTotal(id);
    }
}
