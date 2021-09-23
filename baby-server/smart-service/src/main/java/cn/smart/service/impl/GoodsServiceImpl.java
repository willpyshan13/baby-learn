package cn.smart.service.impl;

import cn.smart.bo.GoodsBO;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.api.result.ApiStatus;
import cn.smart.common.exception.CustomException;
import cn.smart.common.utils.DateUtil;
import cn.smart.condition.ExchangeDetailQueryCondition;
import cn.smart.dao.IExchangeDetailMapper;
import cn.smart.dao.IIntegralMapper;
import cn.smart.dao.IPersonMapper;
import cn.smart.entity.ExchangeDetailDO;
import cn.smart.entity.IntegralDO;
import cn.smart.entity.PersonDO;
import cn.smart.service.IGoodsService;

import cn.smart.dao.IGoodsMapper;
import cn.smart.condition.GoodsQueryCondition;
import cn.smart.entity.GoodsDO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-8-8 9:59:36
*/

@Service
public class GoodsServiceImpl  implements IGoodsService {

    private static Logger logger = LogManager.getLogger(GoodsServiceImpl.class);

    @Autowired
    private IGoodsMapper goodsMapper;

    @Autowired
    private IExchangeDetailMapper exchangeDetailMapper;

    @Autowired
    private IPersonMapper personMapper;

    @Autowired
    private IIntegralMapper integralMapper;

    @Override
    public void save(GoodsDO goodsDO) {
        goodsMapper.save(goodsDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        goodsMapper.save(map);
    }

    @Override
    public void saveBatch(List<GoodsDO> list) {
        goodsMapper.saveBatch(list);
    }

    @Override
    public int update(GoodsDO goodsDO) {
        return goodsMapper.update(goodsDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return goodsMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return goodsMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return goodsMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return goodsMapper.deleteBatch(ids);
    }

    @Override
    public GoodsDO queryById(Object id) {
        return goodsMapper.queryById(id);
    }

    @Override
    public List<GoodsDO> queryList(GoodsQueryCondition goodsQueryCondition) {
        return goodsMapper.queryList(goodsQueryCondition);
    }

    @Override
    public int queryTotal(GoodsQueryCondition goodsQueryCondition) {
        return goodsMapper.queryTotal(goodsQueryCondition);
    }

    @Override
    public int total() {
        return goodsMapper.total();
    }

    @Override
    public List<GoodsBO> queryVirtualGoodsList(Long personId, GoodsQueryCondition query) {

        List<GoodsDO> gList = goodsMapper.queryList(query);
        List<GoodsBO> goodsList = new ArrayList<>(gList.size());

        ExchangeDetailQueryCondition exchangeQuery = new ExchangeDetailQueryCondition();
        exchangeQuery.setPersonId(personId);
        List<ExchangeDetailDO> exchangeList = exchangeDetailMapper.queryList(exchangeQuery);

        GoodsBO goods;
        for(GoodsDO g : gList) {
            goods = new GoodsBO();
            goods.setId(g.getId());
            goods.setName(g.getName());
            goods.setImageUrl(g.getImageUrl());
            goods.setPoints(g.getPoints());
            goods.setExchanged(false);
            for (ExchangeDetailDO e : exchangeList) {
                if (g.getId().equals(e.getGoodsId())) {
                    goods.setExchanged(true);
                }
            }
            goodsList.add(goods);
        }
        return goodsList;
    }

    @Transactional(rollbackFor = CustomException.class)
    @Override
    public ApiResult exchange(Long personId, Long goodsId) {

        Date date = new Date();
        GoodsDO goods = goodsMapper.queryById(goodsId);
        if (goods == null) {
            return new ApiResult(-1, ApiStatus.SC_GOODS_IS_NULL, "兑换物品不存在");
        }
        if (goods.getVirtual() == 0) {
            return new ApiResult(-1, ApiStatus.SC_GOODS_TYPE_ERROR, "商品类型错误");
        }
        int points = goods.getPoints();

        // 查看用户积分是否足够兑换物品
        PersonDO person = personMapper.queryById(personId);
        if (person == null) {
            return new ApiResult(-1, ApiStatus.SC_USER_DOSE_NOT_EXIST, "用户不存在, 无法兑换!");
        }

        if (person.getCandyPoints() == null || person.getCandyPoints() < points) {
            return new ApiResult(-1, ApiStatus.SC_LACK_OF_INTEGRAL,"用户积分不足, 无法兑换!");
        }
        try {
            // 积分扣减
            person.setCandyPoints(person.getCandyPoints() - points);
            personMapper.update(person);
           // 商品兑换明细记录
            ExchangeDetailDO exchangeDetail = new ExchangeDetailDO();
            exchangeDetail.setPersonId(personId);
            exchangeDetail.setGoodsId(goodsId);
            exchangeDetail.setNumbers(1L);
            exchangeDetail.setPoints(goods.getPoints());
            exchangeDetail.setIntegralType(2);
            exchangeDetail.setSendTime(date);
            exchangeDetail.setReceiveTime(date);
            exchangeDetail.setStatus(2);
            exchangeDetailMapper.save(exchangeDetail);
            // 积分明细登记记录
            IntegralDO integral = new IntegralDO();
            integral.setPersonId(personId);
            integral.setNumber(points);
            integral.setType(2);
            integral.setSource(9);
            integral.setCreateDate(DateUtil.format(date));
            integral.setCreateTime(date);
            integral.setExchangeDetailId(exchangeDetail.getId());
            integralMapper.save(integral);
        } catch (Exception e) {
            logger.error("兑换虚拟商品 LiPa 游戏异常 --------> {}", e.getMessage());
            throw new CustomException(e.getMessage());
        }

        return new ApiResult("兑换成功!");
    }

    @Override
    public List<GoodsDO> queryGoodsWithStageGroup(GoodsQueryCondition query) {
        return goodsMapper.queryGoodsWithStageGroup(query);
    }

    @Override
    public int countGoodsWithStageGroup(GoodsQueryCondition query) {
        return goodsMapper.countGoodsWithStageGroup(query);
    }
}
