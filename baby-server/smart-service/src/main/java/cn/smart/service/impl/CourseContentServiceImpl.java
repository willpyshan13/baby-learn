package cn.smart.service.impl;

import cn.smart.condition.CourseContentQueryCondition;
import cn.smart.dao.ICourseContentMapper;
import cn.smart.entity.CourseContentDO;
import cn.smart.service.ICourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-4-3 17:00:50
*/

@Service
public class CourseContentServiceImpl  implements ICourseContentService {

    @Autowired
    private ICourseContentMapper courseContentMapper;

    @Override
    public void save(CourseContentDO courseContentDO) {
        courseContentMapper.save(courseContentDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        courseContentMapper.save(map);
    }

    @Override
    public void saveBatch(List<CourseContentDO> list) {
        courseContentMapper.saveBatch(list);
    }

    @Override
    public int update(CourseContentDO courseContentDO) {
        return courseContentMapper.update(courseContentDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return courseContentMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return courseContentMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return courseContentMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return courseContentMapper.deleteBatch(ids);
    }

    @Override
    public CourseContentDO queryById(Object id) {
        return courseContentMapper.queryById(id);
    }

    @Override
    public List<CourseContentDO> queryList(CourseContentQueryCondition courseContentQueryCondition) {
        return courseContentMapper.queryList(courseContentQueryCondition);
    }

    @Override
    public int queryTotal(CourseContentQueryCondition courseContentQueryCondition) {
        return courseContentMapper.queryTotal(courseContentQueryCondition);
    }

    @Override
    public int total() {
        return courseContentMapper.total();
    }
}
