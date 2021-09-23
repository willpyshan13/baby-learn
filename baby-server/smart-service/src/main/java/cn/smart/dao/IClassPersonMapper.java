package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.ClassPersonQueryCondition;
import cn.smart.entity.ClassPersonDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-4-11 14:41:37
 */
@Repository
public interface IClassPersonMapper extends IBaseDao<ClassPersonDO, ClassPersonQueryCondition> {

    void deleteByClassId(Long classId);
}
