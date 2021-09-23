package cn.smart.service.impl;

import cn.smart.common.exception.CustomException;
import cn.smart.common.utils.DateUtil;
import cn.smart.common.utils.R;
import cn.smart.condition.IntegralQueryCondition;
import cn.smart.dao.IExchangeDetailMapper;
import cn.smart.dao.IIntegralMapper;
import cn.smart.dao.IPersonMapper;
import cn.smart.entity.ExchangeDetailDO;
import cn.smart.entity.IntegralDO;
import cn.smart.entity.PersonDO;
import cn.smart.service.IIntegralService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-6-28 19:40:16
*/

@Service
public class IntegralServiceImpl  implements IIntegralService {

    private static final Logger logger = LogManager.getLogger(IntegralServiceImpl.class);

    @Autowired
    private IIntegralMapper integralMapper;

    @Autowired
    private IPersonMapper personMapper;

    @Autowired
    private IExchangeDetailMapper exchangeDetailMapper;

    @Override
    public void save(IntegralDO integralDO) {
        integralMapper.save(integralDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        integralMapper.save(map);
    }

    @Override
    public void saveBatch(List<IntegralDO> list) {
        integralMapper.saveBatch(list);
    }

    @Override
    public int update(IntegralDO integralDO) {
        return integralMapper.update(integralDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return integralMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return integralMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return integralMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return integralMapper.deleteBatch(ids);
    }

    @Override
    public IntegralDO queryById(Object id) {
        return integralMapper.queryById(id);
    }

    @Override
    public List<IntegralDO> queryList(IntegralQueryCondition integralQueryCondition) {
        return integralMapper.queryList(integralQueryCondition);
    }

    @Override
    public int queryTotal(IntegralQueryCondition integralQueryCondition) {
        return integralMapper.queryTotal(integralQueryCondition);
    }

    @Override
    public int total() {
        return integralMapper.total();
    }

    @Transactional(rollbackFor = CustomException.class)
    @Override
    public R exchangeGoods(ExchangeDetailDO exchangeDetail) {

        // 查看用户积分是否足够兑换物品
        PersonDO person = personMapper.queryById(exchangeDetail.getPersonId());
        if (person == null) {
            return R.error("用户不存在, 无法兑换!");
        }


        IntegralDO integral = new IntegralDO();
        integral.setNumber(exchangeDetail.getPoints());
        integral.setType(2);
        integral.setSource(9);
        integral.setCreateDate(DateUtil.format(new Date()));
        integral.setCreateTime(new Date());
        integral.setPersonId(exchangeDetail.getPersonId());


        try {
            if (exchangeDetail.getId() == null) {

                if (person.getCandyPoints() < exchangeDetail.getPoints()) {
                    return R.error("用户积分不足, 无法兑换!");
                }

                person.setCandyPoints(person.getCandyPoints() - exchangeDetail.getPoints());
                personMapper.update(person);
                exchangeDetailMapper.save(exchangeDetail);

                integral.setExchangeDetailId(exchangeDetail.getId());
                integralMapper.save(integral);
            } else {

                ExchangeDetailDO detail = exchangeDetailMapper.queryById(exchangeDetail.getId());
                int r = detail.getPoints() - exchangeDetail.getPoints();

                person.setCandyPoints(person.getCandyPoints() + r);
                personMapper.update(person);

                exchangeDetailMapper.update(exchangeDetail);
                IntegralQueryCondition query = new IntegralQueryCondition();
                query.setExchangeDetailId(exchangeDetail.getId());
                List<IntegralDO> integralList = integralMapper.queryList(query);
                if (integralList.size() > 0) {
                    integral = integralList.get(0);
                    integral.setNumber(exchangeDetail.getPoints());
                    integralMapper.update(integral);
                } else {
                    logger.error("积分兑换记录查询错误! 兑换明细 id: {}", exchangeDetail.getId());
                    throw new CustomException("兑换失败, 请联系管理员");
                }

            }
        } catch (Exception e) {
            logger.error("积分兑换物品失败! {}", e.getMessage());
            throw new CustomException("兑换失败, 请联系管理员!");
        }
        return R.ok("兑换成功");
    }

    @Transactional(rollbackFor = CustomException.class)
    @Override
    public R deleteIntegralById(Long id) {

        IntegralDO integral = integralMapper.queryById(id);

        if (integral == null) {
            logger.info("查询积分明细异常! 积分明细ID: {}", id);
            return R.error("积分明细不存在!");
        }

        // 兑换商品类型积分删除后回退给用户
        if (integral.getSource() == 9) {
            ExchangeDetailDO exchangeDetail = exchangeDetailMapper.queryById(integral.getExchangeDetailId());
            if (exchangeDetail == null) {
                return R.error("积分兑换物品明细不存在!");
            }
            // 兑换商品未发货 才可以回退
            if (exchangeDetail.getStatus() != 2) {
                try {
                    PersonDO person = personMapper.queryById(exchangeDetail.getPersonId());
                    if (person == null) {
                        return R.error("积分兑换用户不存在!");
                    }
                    // 返还积分
                    person.setCandyPoints(person.getCandyPoints() + exchangeDetail.getPoints());
                    personMapper.update(person);
                    // 删除兑换明细
                    exchangeDetailMapper.deleteById(integral.getExchangeDetailId());
                    // 删除积分详情
                    deleteById(id);
                    return R.ok("删除成功");
                } catch (Exception e) {
                    logger.error("删除积分详情异常! {}", e.getMessage());
                    throw new CustomException(e.getMessage());
                }
            } else {
                return R.error("积分兑换商品已签收, 无法删除!");
            }
        } else {
            deleteById(id);
            return R.ok("删除成功");
        }
    }
}
