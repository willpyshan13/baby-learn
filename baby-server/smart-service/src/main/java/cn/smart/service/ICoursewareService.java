package cn.smart.service;

import cn.smart.bo.CoursewareBO;
import cn.smart.common.base.service.ICrudService;
import cn.smart.condition.CoursewareQueryCondition;
import cn.smart.entity.CoursewareDO;

import java.util.List;

/**
* @author Ye
* @date 2018-4-25 9:37:34
*/
public interface ICoursewareService extends ICrudService<CoursewareDO, CoursewareQueryCondition> {


    void saveCourseware(Long roomId, CoursewareDO coursewareDO);

    List<CoursewareBO> queryCoursewareByRoom(CoursewareQueryCondition coursewareQueryCondition);

    int countCoursewareByRoom(CoursewareQueryCondition coursewareQueryCondition);

    List<CoursewareDO> getCoursewareToRoom(CoursewareQueryCondition coursewareQueryCondition);

    int countCoursewareToRoom(CoursewareQueryCondition coursewareQueryCondition);
}