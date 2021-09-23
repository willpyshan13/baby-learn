package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.GameDetailQueryCondition;
import cn.smart.entity.GameDetailDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-5-4 17:00:13
 */
@Repository
public interface IGameDetailMapper extends IBaseDao<GameDetailDO, GameDetailQueryCondition> {

}
