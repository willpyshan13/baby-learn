package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.ApiTokenQueryCondition;
import cn.smart.entity.ApiTokenDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-4-8 14:45:10
 */
@Repository
public interface IApiTokenMapper extends IBaseDao<ApiTokenDO, ApiTokenQueryCondition> {

}
