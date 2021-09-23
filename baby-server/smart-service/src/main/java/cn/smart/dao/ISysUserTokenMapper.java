package cn.smart.dao;


import cn.smart.common.base.dao.IBaseDao;
import cn.smart.condition.SysUserTokenQueryCondition;
import cn.smart.entity.SysUserTokenDO;
import org.springframework.stereotype.Repository;

/**
 * @author Ye
 * @date 2018-4-12 17:58:58
 */
@Repository
public interface ISysUserTokenMapper extends IBaseDao<SysUserTokenDO, SysUserTokenQueryCondition> {

    SysUserTokenDO queryByUserId(Long userId);

    SysUserTokenDO queryByToken(String token);

}
