package cn.smart.service.impl;

import cn.smart.common.exception.CustomException;
import cn.smart.condition.SysRoleQueryCondition;
import cn.smart.dao.ISysRoleMapper;
import cn.smart.entity.SysRoleDO;
import cn.smart.entity.SysRoleMenuDO;
import cn.smart.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-5-21 10:37:07
*/

@Service
public class SysRoleServiceImpl  implements ISysRoleService {

    @Autowired
    private ISysRoleMapper sysRoleMapper;

    @Override
    public void save(SysRoleDO sysRoleDO) {
        sysRoleMapper.save(sysRoleDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        sysRoleMapper.save(map);
    }

    @Override
    public void saveBatch(List<SysRoleDO> list) {
        sysRoleMapper.saveBatch(list);
    }

    @Override
    public int update(SysRoleDO sysRoleDO) {
        return sysRoleMapper.update(sysRoleDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return sysRoleMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return sysRoleMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return sysRoleMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return sysRoleMapper.deleteBatch(ids);
    }

    @Override
    public SysRoleDO queryById(Object id) {
        return sysRoleMapper.queryById(id);
    }

    @Override
    public List<SysRoleDO> queryList(SysRoleQueryCondition sysRoleQueryCondition) {
        return sysRoleMapper.queryList(sysRoleQueryCondition);
    }

    @Override
    public int queryTotal(SysRoleQueryCondition sysRoleQueryCondition) {
        return sysRoleMapper.queryTotal(sysRoleQueryCondition);
    }

    @Override
    public int total() {
        return sysRoleMapper.total();
    }

    @Transactional(rollbackFor = CustomException.class)
    @Override
    public void updateRolePermission(Long roleId, Long[] ids) {
        try{
            SysRoleMenuDO sysRoleMenuDO = new SysRoleMenuDO();
            sysRoleMenuDO.setRoleId(roleId);
            sysRoleMapper.deleteSysRoleMenu(sysRoleMenuDO);
            List<SysRoleMenuDO> listSysRoleMenu = new ArrayList<>(16);
            SysRoleMenuDO sysRoleMenu;
            for (Long id : ids) {
                sysRoleMenu = new SysRoleMenuDO();
                sysRoleMenu.setMenuId(id);
                sysRoleMenu.setRoleId(roleId);
                listSysRoleMenu.add(sysRoleMenu);
            }
            sysRoleMapper.saveRoleMenuBatch(listSysRoleMenu);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }
}
