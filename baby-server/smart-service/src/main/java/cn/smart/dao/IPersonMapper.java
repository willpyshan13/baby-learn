package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.PersonQueryCondition;
import cn.smart.entity.ClassInfoDO;
import cn.smart.entity.PersonDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ye
 * @date 2018-4-8 16:14:27
 */
@Repository
public interface IPersonMapper extends IBaseDao<PersonDO, PersonQueryCondition> {

    List<PersonDO> getPersonToClass(PersonQueryCondition personQueryCondition);

    int countPersonToClass(PersonQueryCondition personQueryCondition);

    List<ClassInfoDO> queryPersonClass(PersonQueryCondition personQueryCondition);

}
