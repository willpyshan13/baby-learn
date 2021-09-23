package cn.smart.dao;

import cn.smart.bo.ClassInfoBO;
import cn.smart.bo.CourseGameBO;
import cn.smart.bo.CourseInfoBO;
import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.CourseInfoQueryCondition;
import cn.smart.condition.GameInfoQueryCondition;
import cn.smart.entity.CourseInfoDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ye
 * @date 2018-4-3 15:41:06
 */
@Repository
public interface ICourseInfoMapper extends IBaseDao<CourseInfoDO, CourseInfoQueryCondition> {

    List<CourseInfoDO> getCourseToClass(CourseInfoQueryCondition courseInfoQueryCondition);

    int countCourseToClass(CourseInfoQueryCondition courseInfoQueryCondition);

    List<CourseGameBO> queryCourseGame(GameInfoQueryCondition gameInfoQueryCondition);

    int countCourseGame(GameInfoQueryCondition gameInfoQueryCondition);

    List<CourseInfoBO> queryPersonCourseList(CourseInfoQueryCondition courseInfoQueryCondition);

    int countPersonCourseList(CourseInfoQueryCondition courseInfoQueryCondition);

    int updateCourseGroup(ClassInfoBO params);
    int updateCourseGroupNull(ClassInfoBO params);
}
