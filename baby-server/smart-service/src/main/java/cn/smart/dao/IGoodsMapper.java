package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.GoodsQueryCondition;
import cn.smart.entity.GoodsDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ye
 * @date 2018-8-13 10:23:47
 */
@Repository
public interface IGoodsMapper extends IBaseDao<GoodsDO, GoodsQueryCondition> {

    List<GoodsDO> queryGoodsWithStageGroup(GoodsQueryCondition query);

    int countGoodsWithStageGroup(GoodsQueryCondition query);
}
