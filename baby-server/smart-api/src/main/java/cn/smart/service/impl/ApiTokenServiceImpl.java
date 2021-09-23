package cn.smart.service.impl;

import cn.smart.common.utils.DateUtils;
import cn.smart.common.utils.TokenGenerator;
import cn.smart.common.utils.Validator;
import cn.smart.service.IApiTokenService;
import cn.smart.dao.IApiTokenMapper;
import cn.smart.condition.ApiTokenQueryCondition;
import cn.smart.entity.ApiTokenDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-4-8 14:04:41
*/

@Service
public class ApiTokenServiceImpl  implements IApiTokenService {

    //240小时后过期
    private final static int EXPIRE = 3600000 * 240;

    @Autowired
    private IApiTokenMapper apiTokenMapper;

    @Override
    public void save(ApiTokenDO apiTokenDO) {
        apiTokenMapper.save(apiTokenDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        apiTokenMapper.save(map);
    }

    @Override
    public void saveBatch(List<ApiTokenDO> list) {
        apiTokenMapper.saveBatch(list);
    }

    @Override
    public int update(ApiTokenDO apiTokenDO) {
        return apiTokenMapper.update(apiTokenDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return apiTokenMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return apiTokenMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return apiTokenMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return apiTokenMapper.deleteBatch(ids);
    }

    @Override
    public ApiTokenDO queryById(Object id) {
        return apiTokenMapper.queryById(id);
    }

    @Override
    public List<ApiTokenDO> queryList(ApiTokenQueryCondition apiTokenQueryCondition) {
        return apiTokenMapper.queryList(apiTokenQueryCondition);
    }

    @Override
    public int queryTotal(ApiTokenQueryCondition apiTokenQueryCondition) {
        return apiTokenMapper.queryTotal(apiTokenQueryCondition);
    }

    @Override
    public int total() {
        return apiTokenMapper.total();
    }

    @Override
    public ApiTokenDO createToken(Long userId, Integer type) {
        // 生成一个 token
        String tokenStr = TokenGenerator.generateValue();
        // 过期时间
        Long expireTime = DateUtils.addDay(new Date(), 365).getTime();

        //判断是否生成过token
        ApiTokenQueryCondition apiTokenQueryCondition = new ApiTokenQueryCondition();
        apiTokenQueryCondition.setPersonId(userId);
        apiTokenQueryCondition.setType(type);
        List<ApiTokenDO> tokenList = apiTokenMapper.queryList(apiTokenQueryCondition);
        ApiTokenDO token;
        if (Validator.isEmpty(tokenList)) {
            token = new ApiTokenDO();
            token.setPersonId(userId);
            token.setToken(tokenStr);
            token.setType(type);
            token.setExpireTime(expireTime);
            // 保存
            apiTokenMapper.save(token);
        } else {
            token = tokenList.get(0);
            token.setToken(tokenStr);
            token.setExpireTime(expireTime);
            token.setUpdateTime(null);
            // 更新
            apiTokenMapper.update(token);
        }

        return token;
    }
}
