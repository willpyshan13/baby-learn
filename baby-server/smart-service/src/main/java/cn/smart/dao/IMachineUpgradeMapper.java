package cn.smart.dao;

import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.MachineUpgradeQueryCondition;
import cn.smart.entity.MachineUpgradeDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-7-23 17:06:06
 */
@Repository
public interface IMachineUpgradeMapper extends IBaseDao<MachineUpgradeDO, MachineUpgradeQueryCondition> {

    void updateUpgradeBatch(MachineUpgradeDO machineUpgrade);
}
