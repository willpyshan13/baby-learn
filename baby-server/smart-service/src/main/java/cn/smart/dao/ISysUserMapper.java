package cn.smart.dao;


import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.SysUserQueryCondition;
import cn.smart.entity.SysMenuDO;
import cn.smart.entity.SysUserDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Ye
 * @date 2018-4-2 9:25:01
 */
@Repository
public interface ISysUserMapper extends IBaseDao<SysUserDO, SysUserQueryCondition> {

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Map<String, Object> map);

    /**
     * 根据用户名，查询系统用户
     */
    SysUserDO queryByUserName(String username);

    /**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<SysMenuDO> queryAllPerms(Long userId);


}
