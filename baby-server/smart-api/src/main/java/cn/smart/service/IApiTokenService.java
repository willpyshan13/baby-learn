package cn.smart.service;

import cn.smart.common.base.service.ICrudService;
import cn.smart.condition.ApiTokenQueryCondition;
import cn.smart.entity.ApiTokenDO;

/**
* @author Ye
* @date 2018-4-8 14:04:41
*/
public interface IApiTokenService extends ICrudService<ApiTokenDO, ApiTokenQueryCondition>{

    /**
     * 创建token
     * @param userId 用户id
     * @param type 类型 1:Android 2:H5
     * @return
     */
    ApiTokenDO createToken(Long userId, Integer type);
}