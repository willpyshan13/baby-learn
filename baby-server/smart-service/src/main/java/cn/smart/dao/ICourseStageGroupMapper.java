package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.CourseStageGroupQueryCondition;
import cn.smart.entity.CourseStageGroupDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-9-21 15:00:38
 */
@Repository
public interface ICourseStageGroupMapper extends IBaseDao<CourseStageGroupDO, CourseStageGroupQueryCondition> {

}
