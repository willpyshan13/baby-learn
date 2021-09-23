package cn.smart.service;


import cn.smart.common.base.service.ICrudService;
import cn.smart.condition.SysRoleQueryCondition;
import cn.smart.entity.SysRoleDO;

/**
* @author Ye
* @date 2018-5-21 10:37:07
*/
public interface ISysRoleService extends ICrudService<SysRoleDO, SysRoleQueryCondition> {


    void updateRolePermission(Long userId, Long[] ids);
}