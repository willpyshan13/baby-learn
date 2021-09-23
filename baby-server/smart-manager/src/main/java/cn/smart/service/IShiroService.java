package cn.smart.service;

import cn.smart.entity.SysUserDO;
import cn.smart.entity.SysUserTokenDO;

import java.util.Set;

/**
 * shiro相关接口
 * @author Ye
 */
public interface IShiroService {
    /**
     * 获取用户权限列表
     * @param userId 用户d
     * @return Set<String>
     */
    Set<String> getUserPermissions(long userId);

    /**
     * 根据用户 token 查询用户
     * @param token token
     * @return 用户信息
     */
    SysUserTokenDO queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     * @param userId 用户id
     * @return SysUserDO SysUserDO
     */
    SysUserDO queryUser(Long userId);

    /**
     * 更新token时间
     * @param t t
     */
    void updateExpire(SysUserTokenDO t);
}
