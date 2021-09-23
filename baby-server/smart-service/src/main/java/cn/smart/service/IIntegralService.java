package cn.smart.service;

import cn.smart.common.base.service.ICrudService;
import cn.smart.common.utils.R;
import cn.smart.condition.IntegralQueryCondition;
import cn.smart.entity.ExchangeDetailDO;
import cn.smart.entity.IntegralDO;

/**
* @author Ye
* @date 2018-6-28 19:40:16
*/
public interface IIntegralService extends ICrudService<IntegralDO, IntegralQueryCondition> {


    R exchangeGoods(ExchangeDetailDO exchangeDetail);

    R deleteIntegralById(Long id);
}