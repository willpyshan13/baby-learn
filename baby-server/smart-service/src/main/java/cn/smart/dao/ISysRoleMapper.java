package cn.smart.dao;


import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.SysRoleQueryCondition;
import cn.smart.entity.SysRoleDO;
import cn.smart.entity.SysRoleMenuDO;
import cn.smart.entity.SysUserRoleDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ye
 * @date 2018-5-21 10:37:07
 */
@Repository
public interface ISysRoleMapper extends IBaseDao<SysRoleDO, SysRoleQueryCondition> {

    void saveSysUserRole(SysUserRoleDO sysUserRoleDO);

    void deleteSysUserRole(SysUserRoleDO sysUserRoleDO);

    void deleteSysRoleMenu(SysRoleMenuDO sysRoleMenuDO);

    void saveRoleMenuBatch(List<SysRoleMenuDO> listSysRoleMenu);
}
