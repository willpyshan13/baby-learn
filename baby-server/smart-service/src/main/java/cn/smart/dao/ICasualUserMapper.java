package cn.smart.dao;


import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.CasualUserQueryCondition;
import cn.smart.entity.CasualUserDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-8-3 14:23:32
 */
@Repository
public interface ICasualUserMapper extends IBaseDao<CasualUserDO, CasualUserQueryCondition> {

}
