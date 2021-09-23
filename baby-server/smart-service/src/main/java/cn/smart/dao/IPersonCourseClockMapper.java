package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.PersonCourseClockQueryCondition;
import cn.smart.entity.PersonCourseClockDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-9-14 10:03:36
 */
@Repository
public interface IPersonCourseClockMapper extends IBaseDao<PersonCourseClockDO, PersonCourseClockQueryCondition> {

}
