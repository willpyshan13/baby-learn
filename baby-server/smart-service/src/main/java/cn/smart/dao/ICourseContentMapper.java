package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.CourseContentQueryCondition;
import cn.smart.entity.CourseContentDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ye
 * @date 2018-4-3 17:00:50
 */
@Repository
public interface ICourseContentMapper extends IBaseDao<CourseContentDO, CourseContentQueryCondition> {

    List<CourseContentDO> queryContentByCourseIds(Long[] ids);
}
