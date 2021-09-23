package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.IntegralQueryCondition;
import cn.smart.entity.IntegralDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-9-13 19:35:38
 */
@Repository
public interface IIntegralMapper extends IBaseDao<IntegralDO, IntegralQueryCondition> {

}
