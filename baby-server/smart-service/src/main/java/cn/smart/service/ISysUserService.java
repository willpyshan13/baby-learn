package cn.smart.service;

import cn.smart.common.base.service.ICrudService;
import cn.smart.condition.SysUserQueryCondition;
import cn.smart.entity.SysUserDO;

/**
* @author Ye
* @date 2018-4-2 9:25:01
*/
public interface ISysUserService extends ICrudService<SysUserDO, SysUserQueryCondition> {

    /**
     * 根据用户名，查询系统用户
     */
    SysUserDO queryByUserName(String username);

    void saveSysUser(SysUserDO sysUserDO);

}