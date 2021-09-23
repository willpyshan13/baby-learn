package cn.smart.service;

import cn.smart.common.api.result.ApiResult;
import cn.smart.common.base.service.ICrudService;
import cn.smart.condition.VerifyCodeQueryCondition;
import cn.smart.entity.VerifyCodeDO;

/**
* @author Ye
* @date 2018-4-2 12:49:40
*/
public interface IVerifyCodeService extends ICrudService<VerifyCodeDO, VerifyCodeQueryCondition> {

    /**
     * 发送验证码
     * @param verifyCodeDO
     * @return
     */
    ApiResult sendVerifyCode(VerifyCodeDO verifyCodeDO);

    /**
     * 验证 发送的验证码
     * @param mobilePhone
     * @param code
     * @return
     */
    ApiResult verifyCode(String mobilePhone, String code);

    /**
     * 发送短信验证码并返回验证码
     * @param verifyCodeDO
     * @return
     */
    ApiResult sendAndReturnVerifyCode(VerifyCodeDO verifyCodeDO);
}