package cn.smart.service;

import cn.smart.common.base.service.ICrudService;
import cn.smart.condition.SysConfigQueryCondition;
import cn.smart.entity.SysConfigDO;

/**
* @author Ye
* @date 2018-5-17 10:14:25
*/
public interface ISysConfigService extends ICrudService<SysConfigDO, SysConfigQueryCondition> {

    /**
     * 根据key，获取配置的value值
     *
     * @param key           key
     */
    public String getValue(String key);

    /**
     * 根据key，获取value的Object对象
     * @param key    key
     * @param clazz  Object对象
     */
    public <T> T getConfigObject(String key, Class<T> clazz);


}