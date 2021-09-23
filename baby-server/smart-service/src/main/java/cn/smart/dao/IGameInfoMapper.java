package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.GameInfoQueryCondition;
import cn.smart.entity.GameInfoDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-5-4 11:34:16
 */
@Repository
public interface IGameInfoMapper extends IBaseDao<GameInfoDO, GameInfoQueryCondition> {

}
