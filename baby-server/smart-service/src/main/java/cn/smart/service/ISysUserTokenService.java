package cn.smart.service;


import cn.smart.common.base.service.ICrudService;
import cn.smart.common.utils.R;
import cn.smart.condition.SysUserTokenQueryCondition;
import cn.smart.entity.SysUserTokenDO;

/**
* @author Ye
* @date 2018-4-12 17:58:58
*/
public interface ISysUserTokenService extends ICrudService<SysUserTokenDO, SysUserTokenQueryCondition> {

    SysUserTokenDO queryByUserId(Long userId);

    SysUserTokenDO queryByToken(String token);

    /**
     * 生成token
     * @param userId  用户ID
     */
    R createToken(long userId);
}