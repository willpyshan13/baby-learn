package cn.smart.service;

import cn.smart.bo.ClassCourseBO;
import cn.smart.bo.ClassInfoBO;
import cn.smart.common.base.service.ICrudService;
import cn.smart.condition.ClassInfoQueryCondition;
import cn.smart.condition.CourseInfoQueryCondition;
import cn.smart.condition.PersonQueryCondition;
import cn.smart.entity.ClassCourseDO;
import cn.smart.entity.ClassInfoDO;
import cn.smart.entity.PersonDO;

import java.util.List;

/**
* @author Ye
* @date 2018-4-11 14:41:27
*/
public interface IClassInfoService extends ICrudService<ClassInfoDO, ClassInfoQueryCondition> {

    void assignedPerson(ClassInfoBO classInfoBO);

    void updateClassInfo(ClassInfoDO classInfo);

    List<ClassCourseBO> queryClassCourseByClassId(CourseInfoQueryCondition courseInfoQueryCondition);

    List<PersonDO> queryClassPersonByClassId(PersonQueryCondition personQueryCondition);

    Integer countClassPerson(PersonQueryCondition personQueryCondition);

    Integer countClassCourse(CourseInfoQueryCondition courseInfoQueryCondition);

    void assignedCourse(ClassInfoBO classInfoBO);

    void cancelClassCourse(ClassInfoBO classInfoBO);

    void cancelClassStudent(ClassInfoBO classInfoBO);

    void updateClassCourseStatus(ClassCourseDO classCourseDO);

    void saveClassInfo(ClassInfoDO classInfo);

    void assignedPersonToDefaultClass(Long[] personId);
}