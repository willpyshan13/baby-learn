package cn.smart.service;

import cn.smart.common.base.service.ICrudService;
import cn.smart.condition.MachineUpgradeQueryCondition;
import cn.smart.entity.MachineUpgradeDO;

/**
* @author Ye
* @date 2018-7-23 17:01:59
*/
public interface IMachineUpgradeService extends ICrudService<MachineUpgradeDO, MachineUpgradeQueryCondition> {


    void updateBatch(MachineUpgradeDO machineUpgrade);
}