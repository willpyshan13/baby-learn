package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.CourseGameQueryCondition;
import cn.smart.entity.CourseGameDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ye
 * @date 2018-4-18 16:01:45
 */
@Repository
public interface ICourseGameMapper extends IBaseDao<CourseGameDO, CourseGameQueryCondition> {

    void insertBatch(List<CourseGameDO> courseGameDOList);
}
