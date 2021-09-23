package cn.smart.oauth2;

import cn.smart.common.utils.DateUtils;
import cn.smart.entity.SysUserDO;
import cn.smart.entity.SysUserTokenDO;
import cn.smart.service.IShiroService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Set;

/**
 * @author Ye
 */
public class OAuth2Realm extends AuthorizingRealm {

    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private IShiroService shiroService;

    /**
     * 支持验证的 Token 类型
     * @param token token
     * @return boolean
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(验证授权时调用)
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUserDO user = (SysUserDO)principalCollection.getPrimaryPrincipal();
        Long userId = user.getUserId();

        //用户权限列表
        Set<String> permsSet = shiroService.getUserPermissions(userId);
        logger.info("本用户权限为:" + permsSet);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();

        //根据accessToken，查询用户信息
        SysUserTokenDO tokenEntity = shiroService.queryByToken(accessToken);
        //token失效
        if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()){
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        //查询用户信息
        SysUserDO user = shiroService.queryUser(tokenEntity.getUserId());
        //账号锁定
        if(user.getStatus() == 0){
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        tokenEntity.setExpireTime(DateUtils.addDay(new Date(), 240));
        shiroService.updateExpire(tokenEntity);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
        return info;
    }
}
