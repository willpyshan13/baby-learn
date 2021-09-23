package cn.smart.service.impl;

import cn.smart.service.IExchangeDetailService;

import cn.smart.dao.IExchangeDetailMapper;
import cn.smart.condition.ExchangeDetailQueryCondition;
import cn.smart.entity.ExchangeDetailDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-8-7 19:46:43
*/

@Service
public class ExchangeDetailServiceImpl  implements IExchangeDetailService {

    @Autowired
    private IExchangeDetailMapper exchangeDetailMapper;

    @Override
    public void save(ExchangeDetailDO exchangeDetailDO) {
        exchangeDetailMapper.save(exchangeDetailDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        exchangeDetailMapper.save(map);
    }

    @Override
    public void saveBatch(List<ExchangeDetailDO> list) {
        exchangeDetailMapper.saveBatch(list);
    }

    @Override
    public int update(ExchangeDetailDO exchangeDetailDO) {
        return exchangeDetailMapper.update(exchangeDetailDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return exchangeDetailMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return exchangeDetailMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return exchangeDetailMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return exchangeDetailMapper.deleteBatch(ids);
    }

    @Override
    public ExchangeDetailDO queryById(Object id) {
        return exchangeDetailMapper.queryById(id);
    }

    @Override
    public List<ExchangeDetailDO> queryList(ExchangeDetailQueryCondition exchangeDetailQueryCondition) {
        return exchangeDetailMapper.queryList(exchangeDetailQueryCondition);
    }

    @Override
    public int queryTotal(ExchangeDetailQueryCondition exchangeDetailQueryCondition) {
        return exchangeDetailMapper.queryTotal(exchangeDetailQueryCondition);
    }

    @Override
    public int total() {
        return exchangeDetailMapper.total();
    }
}
