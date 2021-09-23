package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.PushLogQueryCondition;
import cn.smart.entity.PushLogDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ye
 * @date 2018-7-10 17:56:54
 */
@Repository
public interface IPushLogMapper extends IBaseDao<PushLogDO, PushLogQueryCondition> {

    List<PushLogDO> queryPersonMessage(PushLogQueryCondition query);

    int queryTotalPersonMessage(PushLogQueryCondition query);
}
