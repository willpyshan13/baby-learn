package cn.smart.service.impl;

import cn.smart.common.utils.Constant;
import cn.smart.condition.SysMenuQueryCondition;
import cn.smart.dao.ISysMenuMapper;
import cn.smart.dao.ISysUserMapper;
import cn.smart.dao.ISysUserTokenMapper;
import cn.smart.entity.SysMenuDO;
import cn.smart.entity.SysUserDO;
import cn.smart.entity.SysUserTokenDO;
import cn.smart.service.IShiroService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Ye
 */
@Service
public class ShiroServiceImpl implements IShiroService {
    @Autowired
    private ISysMenuMapper sysMenuMapper;
    @Autowired
    private ISysUserMapper sysUserDao;
    @Autowired
    private ISysUserTokenMapper sysUserTokenDao;

    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if(userId == Constant.SUPER_ADMIN){
            List<SysMenuDO> menuList = sysMenuMapper.queryList(new SysMenuQueryCondition());
            permsList = new ArrayList<>(menuList.size());
            for(SysMenuDO menu : menuList){
                permsList.add(menu.getPerms());
            }
        }else{
            List<SysMenuDO> listSysMenu = sysUserDao.queryAllPerms(userId);
            permsList = new ArrayList<>(listSysMenu.size());
            for (SysMenuDO sysMenu : listSysMenu) {
                permsList.add(sysMenu.getPerms());
            }
            // permsList = sysUserDao.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public SysUserTokenDO queryByToken(String token) {
        return sysUserTokenDao.queryByToken(token);
    }

    @Override
    public SysUserDO queryUser(Long userId) {
        return sysUserDao.queryById(userId);
    }

    @Override
    public void updateExpire(SysUserTokenDO t) {
        sysUserTokenDao.update(t);
    }
}
