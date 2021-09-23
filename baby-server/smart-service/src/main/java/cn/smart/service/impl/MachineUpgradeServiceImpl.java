package cn.smart.service.impl;

import cn.smart.condition.MachineUpgradeQueryCondition;
import cn.smart.dao.IMachineUpgradeMapper;
import cn.smart.entity.MachineUpgradeDO;
import cn.smart.service.IMachineUpgradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-7-23 17:01:59
*/

@Service
public class MachineUpgradeServiceImpl  implements IMachineUpgradeService {

    @Autowired
    private IMachineUpgradeMapper machineUpgradeMapper;

    @Override
    public void save(MachineUpgradeDO machineUpgradeDO) {
        machineUpgradeMapper.save(machineUpgradeDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        machineUpgradeMapper.save(map);
    }

    @Override
    public void saveBatch(List<MachineUpgradeDO> list) {
        machineUpgradeMapper.saveBatch(list);
    }

    @Override
    public int update(MachineUpgradeDO machineUpgradeDO) {
        return machineUpgradeMapper.update(machineUpgradeDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return machineUpgradeMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return machineUpgradeMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return machineUpgradeMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return machineUpgradeMapper.deleteBatch(ids);
    }

    @Override
    public MachineUpgradeDO queryById(Object id) {
        return machineUpgradeMapper.queryById(id);
    }

    @Override
    public List<MachineUpgradeDO> queryList(MachineUpgradeQueryCondition machineUpgradeQueryCondition) {
        return machineUpgradeMapper.queryList(machineUpgradeQueryCondition);
    }

    @Override
    public int queryTotal(MachineUpgradeQueryCondition machineUpgradeQueryCondition) {
        return machineUpgradeMapper.queryTotal(machineUpgradeQueryCondition);
    }

    @Override
    public int total() {
        return machineUpgradeMapper.total();
    }

    @Override
    public void updateBatch(MachineUpgradeDO machineUpgrade) {
        machineUpgradeMapper.updateUpgradeBatch(machineUpgrade);
    }
}
