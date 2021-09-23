package cn.smart.dao;

import cn.smart.bo.ClassCourseBO;
import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.ClassInfoQueryCondition;
import cn.smart.condition.ClassPersonQueryCondition;
import cn.smart.condition.CourseInfoQueryCondition;
import cn.smart.condition.PersonQueryCondition;
import cn.smart.entity.ClassInfoDO;
import cn.smart.entity.ClassPersonDO;
import cn.smart.entity.PersonDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ye
 * @date 2018-4-11 14:41:27
 */
@Repository
public interface IClassInfoMapper extends IBaseDao<ClassInfoDO, ClassInfoQueryCondition> {

    List<PersonDO> queryClassPersonByClassId(PersonQueryCondition personQueryCondition);

    Integer countClassPerson(PersonQueryCondition personQueryCondition);

    List<ClassCourseBO> queryClassCourseByClassId(CourseInfoQueryCondition courseInfoQueryCondition);

    Integer countClassCourse(CourseInfoQueryCondition courseInfoQueryCondition);

    List<ClassPersonDO> queryClassCoursePerson(ClassPersonQueryCondition query);

    List<ClassInfoDO> queryAllClassPerson(ClassInfoQueryCondition query);

}
