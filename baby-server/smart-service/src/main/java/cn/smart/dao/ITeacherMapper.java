package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.TeacherQueryCondition;
import cn.smart.entity.TeacherDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-4-10 17:22:12
 */
@Repository
public interface ITeacherMapper extends IBaseDao<TeacherDO, TeacherQueryCondition> {

}
