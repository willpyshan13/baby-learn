package cn.smart.service;

import cn.smart.common.base.service.ICrudService;
import cn.smart.common.utils.R;
import cn.smart.condition.GameInfoQueryCondition;
import cn.smart.entity.GameInfoDO;

/**
* @author Ye
* @date 2018-5-4 11:34:16
*/
public interface IGameInfoService extends ICrudService<GameInfoDO, GameInfoQueryCondition> {


    R deleteGameInfoBatch(Long[] ids);
}