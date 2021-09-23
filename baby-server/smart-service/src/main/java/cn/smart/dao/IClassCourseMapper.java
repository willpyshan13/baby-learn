package cn.smart.dao;

import cn.smart.bo.ClassInfoBO;
import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.ClassCourseQueryCondition;
import cn.smart.entity.ClassCourseDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ye
 * @date 2018-4-20 14:38:29
 */
@Repository
public interface IClassCourseMapper extends IBaseDao<ClassCourseDO, ClassCourseQueryCondition> {

    void deleteByClassIdOrPersonIdsBatch(ClassInfoBO classInfoBO);

    void deleteByClassId(Long classId);

    void deleteByCourseInfoId(Long courseInfoId);

    List<Long> queryClassByStageId(Long stageId);
}
