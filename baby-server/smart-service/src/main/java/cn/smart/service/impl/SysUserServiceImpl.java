package cn.smart.service.impl;

import cn.smart.common.utils.Validator;
import cn.smart.condition.SysUserQueryCondition;
import cn.smart.dao.ISysRoleMapper;
import cn.smart.dao.ISysUserMapper;
import cn.smart.entity.SysUserDO;
import cn.smart.entity.SysUserRoleDO;
import cn.smart.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
* @author Ye
* @date 2018-4-2 9:25:01
*/

@Service
public class SysUserServiceImpl  implements ISysUserService {

    @Autowired
    private ISysUserMapper sysUserDao;

    @Autowired
    private ISysRoleMapper sysRoleMapper;

    @Override
    public SysUserDO queryByUserName(String username) {
        return sysUserDao.queryByUserName(username);
    }

    @Override
    public void saveSysUser(SysUserDO sysUserDO) {
        save(sysUserDO);
        if (Validator.isNotEmpty(sysUserDO.getRoleId())) {
            SysUserRoleDO sysUserRoleDO = new SysUserRoleDO();
            sysUserRoleDO.setRoleId(sysUserDO.getRoleId());
            sysUserRoleDO.setUserId(sysUserDO.getUserId());
            sysRoleMapper.saveSysUserRole(sysUserRoleDO);
        }
    }

    @Override
    public void save(SysUserDO sysUserDO) {
        sysUserDao.save(sysUserDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        sysUserDao.save(map);
    }

    @Override
    public void saveBatch(List<SysUserDO> list) {
        sysUserDao.saveBatch(list);
    }

    @Override
    public int update(SysUserDO sysUserDO) {

        SysUserRoleDO sysUserRoleDO =  new SysUserRoleDO();
        sysUserRoleDO.setUserId(sysUserDO.getUserId());
        sysRoleMapper.deleteSysUserRole(sysUserRoleDO);

        if (Validator.isNotEmpty(sysUserDO.getRoleId())) {
            sysUserRoleDO =  new SysUserRoleDO();
            sysUserRoleDO.setUserId(sysUserDO.getUserId());
            sysUserRoleDO.setRoleId(sysUserDO.getRoleId());
            sysRoleMapper.saveSysUserRole(sysUserRoleDO);
        }

        return sysUserDao.update(sysUserDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return sysUserDao.update(map);
    }

    @Override
    public int deleteById(Object id) {

        SysUserRoleDO sysUserRoleDO =  new SysUserRoleDO();
        sysUserRoleDO.setUserId((Long) id);
        sysRoleMapper.deleteSysUserRole(sysUserRoleDO);

        return sysUserDao.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return sysUserDao.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return sysUserDao.deleteBatch(ids);
    }

    @Override
    public SysUserDO queryById(Object id) {
        return sysUserDao.queryById(id);
    }

    @Override
    public List<SysUserDO> queryList(SysUserQueryCondition sysUserQueryCondition) {
        return sysUserDao.queryList(sysUserQueryCondition);
    }

    @Override
    public int queryTotal(SysUserQueryCondition sysUserQueryCondition) {
        return sysUserDao.queryTotal(sysUserQueryCondition);
    }

    @Override
    public int total() {
        return sysUserDao.total();
    }
}
