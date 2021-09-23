package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.PersonCourseStageGroupQueryCondition;
import cn.smart.entity.PersonCourseStageGroupDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-9-14 15:58:29
 */
@Repository
public interface IPersonCourseStageGroupMapper extends IBaseDao<PersonCourseStageGroupDO, PersonCourseStageGroupQueryCondition> {

}
