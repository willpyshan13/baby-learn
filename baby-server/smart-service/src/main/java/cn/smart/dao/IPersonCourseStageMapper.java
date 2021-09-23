package cn.smart.dao;

import cn.smart.bo.CourseStageInfoBO;
import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.PersonCourseStageQueryCondition;
import cn.smart.entity.CourseStageDO;
import cn.smart.entity.PersonCourseStageDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ye
 * @date 2018-4-4 17:22:04
 */
@Repository
public interface IPersonCourseStageMapper extends IBaseDao<PersonCourseStageDO, PersonCourseStageQueryCondition> {

    List<CourseStageDO> queryPersonBoughtCourse(Long id);

    int queryPersonBoughtCourseTotal(Long id);

    List<CourseStageInfoBO> queryCourseStagePersonBought(PersonCourseStageQueryCondition query);

    int countCourseStagePersonBought(PersonCourseStageQueryCondition query);
}
