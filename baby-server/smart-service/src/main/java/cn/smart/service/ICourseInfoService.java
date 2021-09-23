package cn.smart.service;

import cn.smart.bo.*;
import cn.smart.common.base.service.ICrudService;
import cn.smart.common.utils.R;
import cn.smart.condition.CourseInfoQueryCondition;
import cn.smart.condition.GameInfoQueryCondition;
import cn.smart.entity.CourseInfoDO;

import java.util.List;

/**
* @author Ye
* @date 2018-4-3 15:41:06
*/
public interface ICourseInfoService extends ICrudService<CourseInfoDO, CourseInfoQueryCondition> {

    /**
     * 添加课程游戏信息
     * @param courseGame
     */
    void addGameInfo(CourseGameBO courseGame);

    void assignGame(Long courseId, Long[] gameId);

    List<CourseInfoDO> getCourseToClass(CourseInfoQueryCondition courseInfoQueryCondition);

    int countCourseToClass(CourseInfoQueryCondition courseInfoQueryCondition);

    List<CourseGameBO> queryCourseGame(GameInfoQueryCondition gameInfoQueryCondition);

    int countCourseGame(GameInfoQueryCondition gameInfoQueryCondition);

    void removeGamesBatch(Long[] ids);

    List<CourseInfoBO> queryPersonCourseList(CourseInfoQueryCondition courseInfoQueryCondition);

    int countPersonCourseList(CourseInfoQueryCondition courseInfoQueryCondition);

    List<GameInfoBO> queryCourseGameDetail(GameInfoQueryCondition query);

    LearnDetailBO getCourseDetail(Long personId, CourseInfoDO courseInfoDO, Integer type);

    void updateCourseGroup(ClassInfoBO params);

    List<CourseInfoDO> queryCourseList(CourseInfoQueryCondition stageQueryCondition);

    R updateCourseInfo(CourseInfoDO courseInfo);

    R saveCourseInfo(CourseInfoDO courseInfo);
}