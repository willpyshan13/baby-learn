package cn.smart.service;

import cn.smart.bo.CourseInfoBO;
import cn.smart.bo.CourseReportBO;
import cn.smart.common.base.service.ICrudService;
import cn.smart.common.utils.PageUtils;
import cn.smart.common.utils.R;
import cn.smart.condition.CourseInfoQueryCondition;
import cn.smart.condition.PersonQueryCondition;
import cn.smart.entity.CourseStageDO;
import cn.smart.entity.PersonCourseStageDO;
import cn.smart.entity.PersonDO;

import java.util.List;

/**
* @author Ye
* @date 2018-4-2 15:44:19
*/
public interface IPersonService extends ICrudService<PersonDO, PersonQueryCondition> {

    List<CourseStageDO> boughtCourseStage(Long id);

    PageUtils getPersonToClass(PersonQueryCondition personQueryCondition);

    int countPersonToClass(PersonQueryCondition personQueryCondition);

    void buyCourse(PersonCourseStageDO personCourseStageDO);

    R deletePerson(Long id);

    List<CourseInfoBO> queryLearnRecords(CourseInfoQueryCondition courseInfoQueryCondition);

    void deleteBuyStage(Long personId, Long stageId);

    CourseReportBO queryCourseReport(Long userId, Long courseId);

    PageUtils queryPersonList(PersonQueryCondition personQueryCondition);
}