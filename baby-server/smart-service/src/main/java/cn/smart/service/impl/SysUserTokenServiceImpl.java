package cn.smart.service.impl;

import cn.smart.common.utils.*;
import cn.smart.condition.SysUserTokenQueryCondition;
import cn.smart.dao.ISysUserTokenMapper;
import cn.smart.entity.SysUserTokenDO;
import cn.smart.service.ISysUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-4-12 17:58:58
*/

@Service
public class SysUserTokenServiceImpl  implements ISysUserTokenService {

    @Autowired
    private ISysUserTokenMapper sysUserTokenMapper;

    @Override
    public SysUserTokenDO queryByUserId(Long userId) {
        return sysUserTokenMapper.queryByUserId(userId);
    }

    @Override
    public SysUserTokenDO queryByToken(String token) {
        return sysUserTokenMapper.queryByToken(token);
    }

    @Override
    public R createToken(long userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = DateUtils.addDay(new Date(), 365);

        //判断是否生成过token
        SysUserTokenQueryCondition query = new SysUserTokenQueryCondition();
        query.setUserId(userId);
        List<SysUserTokenDO> list = queryList(query);
        if (Validator.isEmpty(list)) {
            SysUserTokenDO tokenEntity = new SysUserTokenDO();
                tokenEntity.setUserId(userId);
                tokenEntity.setToken(token);
                tokenEntity.setUpdateTime(now);
                tokenEntity.setExpireTime(expireTime);
                //保存token
                save(tokenEntity);
        } else {
            SysUserTokenDO tokenEntity = list.get(0);

            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            //更新token
            update(tokenEntity);
        }
        return R.ok().put("token", token).put("expire", expireTime.getTime());

    }

    @Override
    public void save(SysUserTokenDO sysUserTokenDO) {
        sysUserTokenMapper.save(sysUserTokenDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        sysUserTokenMapper.save(map);
    }

    @Override
    public void saveBatch(List<SysUserTokenDO> list) {
        sysUserTokenMapper.saveBatch(list);
    }

    @Override
    public int update(SysUserTokenDO sysUserTokenDO) {
        return sysUserTokenMapper.update(sysUserTokenDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return sysUserTokenMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return sysUserTokenMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return sysUserTokenMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return sysUserTokenMapper.deleteBatch(ids);
    }

    @Override
    public SysUserTokenDO queryById(Object id) {
        return sysUserTokenMapper.queryById(id);
    }

    @Override
    public List<SysUserTokenDO> queryList(SysUserTokenQueryCondition sysUserTokenQueryCondition) {
        return sysUserTokenMapper.queryList(sysUserTokenQueryCondition);
    }

    @Override
    public int queryTotal(SysUserTokenQueryCondition sysUserTokenQueryCondition) {
        return sysUserTokenMapper.queryTotal(sysUserTokenQueryCondition);
    }

    @Override
    public int total() {
        return sysUserTokenMapper.total();
    }
}
