package cn.smart.service;

import cn.smart.bo.GoodsBO;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.base.service.ICrudService;
import cn.smart.condition.GoodsQueryCondition;
import cn.smart.entity.GoodsDO;

import java.util.List;

/**
* @author Ye
* @date 2018-8-8 9:59:36
*/
public interface IGoodsService extends ICrudService<GoodsDO, GoodsQueryCondition>{


    /**
     * 获取虚拟商品
     * @param personId 用户
     * @param query
     * @return
     */
    List<GoodsBO> queryVirtualGoodsList(Long personId, GoodsQueryCondition query);

    /**
     * 商品兑换
     * @param personId
     * @param goodsId
     * @return
     */
    ApiResult exchange(Long personId, Long goodsId);

    /**
     * 获取商品
     * @param query
     * @return
     */
    List<GoodsDO> queryGoodsWithStageGroup(GoodsQueryCondition query);

    /**
     * 获取商品总数
     * @param query
     * @return
     */
    int countGoodsWithStageGroup(GoodsQueryCondition query);
}