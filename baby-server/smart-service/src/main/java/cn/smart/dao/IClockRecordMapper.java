package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.ClockRecordQueryCondition;
import cn.smart.entity.ClockRecordDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-6-29 17:26:56
 */
@Repository
public interface IClockRecordMapper extends IBaseDao<ClockRecordDO, ClockRecordQueryCondition> {

    ClockRecordDO queryByPersonId(Long personId);
}
