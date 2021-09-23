package cn.smart.service.impl;

import cn.smart.common.exception.CustomException;
import cn.smart.condition.SysConfigQueryCondition;
import cn.smart.dao.ISysConfigMapper;
import cn.smart.entity.SysConfigDO;
import cn.smart.service.ISysConfigService;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-5-17 10:14:25
*/

@Service("sysConfigService")
public class SysConfigServiceImpl  implements ISysConfigService {

    @Autowired
    private ISysConfigMapper sysConfigMapper;


    @Override
    public <T> T getConfigObject(String key, Class<T> clazz) {
        String value = getValue(key);
        if(StringUtils.isNotBlank(value)){
            return new Gson().fromJson(value, clazz);
        }

        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new CustomException("获取参数失败");
        }
    }

    @Override
    public String getValue(String key) {
        // 从 redis 中获取
        // SysConfigDO config = sysConfigRedis.get(key);
        SysConfigDO config = null;
        SysConfigQueryCondition sysConfigQuery = new SysConfigQueryCondition();
        sysConfigQuery.setKey(key);
        List<SysConfigDO> configList = sysConfigMapper.queryList(sysConfigQuery);
        if (configList.size() > 0) {
            // 更新 redis 中配置
            //sysConfigRedis.saveOrUpdate(config);
            config = configList.get(0);
        }
        return config == null ? null : config.getValue();
    }

    @Override
    public void save(SysConfigDO sysConfigDO) {
        sysConfigMapper.save(sysConfigDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        sysConfigMapper.save(map);
    }

    @Override
    public void saveBatch(List<SysConfigDO> list) {
        sysConfigMapper.saveBatch(list);
    }

    @Override
    public int update(SysConfigDO sysConfigDO) {
        return sysConfigMapper.update(sysConfigDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return sysConfigMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return sysConfigMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return sysConfigMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return sysConfigMapper.deleteBatch(ids);
    }

    @Override
    public SysConfigDO queryById(Object id) {
        return sysConfigMapper.queryById(id);
    }

    @Override
    public List<SysConfigDO> queryList(SysConfigQueryCondition sysConfigQueryCondition) {
        return sysConfigMapper.queryList(sysConfigQueryCondition);
    }

    @Override
    public int queryTotal(SysConfigQueryCondition sysConfigQueryCondition) {
        return sysConfigMapper.queryTotal(sysConfigQueryCondition);
    }

    @Override
    public int total() {
        return sysConfigMapper.total();
    }
}
