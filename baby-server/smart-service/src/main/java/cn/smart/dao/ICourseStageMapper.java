package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.CourseStageQueryCondition;
import cn.smart.entity.CourseStageDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-4-3 15:41:07
 */
@Repository
public interface ICourseStageMapper extends IBaseDao<CourseStageDO, CourseStageQueryCondition> {

    CourseStageDO queryByLevel(Long id);
}
