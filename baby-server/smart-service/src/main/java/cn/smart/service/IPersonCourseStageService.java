package cn.smart.service;

import cn.smart.common.base.service.ICrudService;
import cn.smart.condition.PersonCourseStageQueryCondition;
import cn.smart.entity.CourseStageDO;
import cn.smart.entity.PersonCourseStageDO;

import java.util.List;

/**
* @author Ye
* @date 2018-4-8 11:09:48
*/
public interface IPersonCourseStageService extends ICrudService<PersonCourseStageDO, PersonCourseStageQueryCondition> {

    List<CourseStageDO> queryPersonBoughtCourse(Long id);

    int queryPersonBoughtCourseTotal(Long id);
}