package cn.smart.dao;

import cn.smart.bo.CoursewareBO;
import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.CoursewareQueryCondition;
import cn.smart.entity.CoursewareDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ye
 * @date 2018-4-25 9:37:33
 */
@Repository
public interface ICoursewareMapper extends IBaseDao<CoursewareDO, CoursewareQueryCondition> {

    List<CoursewareBO> queryCoursewareByRoom(CoursewareQueryCondition coursewareQueryCondition);

    int countCoursewareByRoom(CoursewareQueryCondition coursewareQueryCondition);

    List<CoursewareDO> getCoursewareToRoom(CoursewareQueryCondition coursewareQueryCondition);

    int countCoursewareToRoom(CoursewareQueryCondition coursewareQueryCondition);
}
