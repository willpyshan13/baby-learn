package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.ExchangeDetailQueryCondition;
import cn.smart.entity.ExchangeDetailDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-8-8 20:22:08
 */
@Repository
public interface IExchangeDetailMapper extends IBaseDao<ExchangeDetailDO, ExchangeDetailQueryCondition> {

}
