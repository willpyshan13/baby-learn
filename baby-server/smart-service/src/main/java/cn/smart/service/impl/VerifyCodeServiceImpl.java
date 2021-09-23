package cn.smart.service.impl;

import cn.smart.common.api.result.ApiLoginResult;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.api.result.ApiStatus;
import cn.smart.common.utils.Validator;
import cn.smart.condition.VerifyCodeQueryCondition;
import cn.smart.dao.IPersonMapper;
import cn.smart.dao.IVerifyCodeMapper;
import cn.smart.entity.VerifyCodeDO;
import cn.smart.service.IVerifyCodeService;
import cn.smart.utils.JuheUtils;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
* @author Ye
* @date 2018-4-2 12:49:40
*/

@Service
public class VerifyCodeServiceImpl  implements IVerifyCodeService {

    private static Logger logger = LogManager.getLogger(VerifyCodeServiceImpl.class);

    private static final Long EXPIRE_TIME = 60000L;

    @Autowired
    private IVerifyCodeMapper verifyCodeMapper;

    @Autowired
    private IPersonMapper personMapper;

    @Override
    public void save(VerifyCodeDO verifyCodeDO) {
        verifyCodeMapper.save(verifyCodeDO);
    }

    @Override
    public void save(Map<String, Object> map) {
        verifyCodeMapper.save(map);
    }

    @Override
    public void saveBatch(List<VerifyCodeDO> list) {
        verifyCodeMapper.saveBatch(list);
    }

    @Override
    public int update(VerifyCodeDO verifyCodeDO) {
        return verifyCodeMapper.update(verifyCodeDO);
    }

    @Override
    public int update(Map<String, Object> map) {
        return verifyCodeMapper.update(map);
    }

    @Override
    public int deleteById(Object id) {
        return verifyCodeMapper.deleteById(id);
    }

    @Override
    public int delete(Map<String, Object> map) {
        return verifyCodeMapper.delete(map);
    }

    @Override
    public int deleteBatch(Object[] ids) {
        return verifyCodeMapper.deleteBatch(ids);
    }

    @Override
    public VerifyCodeDO queryById(Object id) {
        return verifyCodeMapper.queryById(id);
    }

    @Override
    public List<VerifyCodeDO> queryList(VerifyCodeQueryCondition verifyCodeQueryCondition) {
        return verifyCodeMapper.queryList(verifyCodeQueryCondition);
    }

    @Override
    public int queryTotal(VerifyCodeQueryCondition verifyCodeQueryCondition) {
        return verifyCodeMapper.queryTotal(verifyCodeQueryCondition);
    }

    @Override
    public int total() { return verifyCodeMapper.total(); }

    @Transactional
    @Override
    public ApiResult sendVerifyCode(VerifyCodeDO verifyCodeDO) {

        // 检测手机号码一分钟内是否已经发送验证码
        VerifyCodeQueryCondition verifyCodeQueryCondition = new VerifyCodeQueryCondition();
        verifyCodeQueryCondition.setTelephone(verifyCodeDO.getTelephone());
        List<VerifyCodeDO> verifyCodeDOs = verifyCodeMapper.queryList(verifyCodeQueryCondition);
        boolean sent = false;
        if (verifyCodeDOs != null && verifyCodeDOs.size() > 0) {
            verifyCodeDO = verifyCodeDOs.get(0);
            sent = System.currentTimeMillis() < verifyCodeDOs.get(0).getExpireTime();
        }
        if (sent) {
            return new ApiResult(-1, ApiStatus.SC_VERIFY_CODE_SENT,"验证码已经发送!");
        }
        try {
            // 生成验证码
            String verifyCode = (int) ((Math.random() * 9 + 1) * 100000) + "";
            verifyCodeDO.setCode(verifyCode);
            // 发送验证码
             Boolean send = JuheUtils.sendVerifyCode(verifyCodeDO.getTelephone(), verifyCodeDO.getCode());
            if (send) {
                verifyCodeDO.setExpireTime(System.currentTimeMillis() + EXPIRE_TIME);
                if (null == verifyCodeDO.getVerifyId()) {
                    verifyCodeMapper.save(verifyCodeDO);
                } else {
                    verifyCodeMapper.update(verifyCodeDO);
                }
                return new ApiResult("发送成功");
            }
        } catch (Exception e) {
            logger.error("验证码发送异常,", e.getMessage());
            throw new RuntimeException("验证码发送异常!",e);
        }
        return new ApiResult("发送失败");
    }

    @Override
    public ApiResult verifyCode(String mobilePhone, String code) {

        VerifyCodeQueryCondition verifyQuery = new VerifyCodeQueryCondition();
        verifyQuery.setTelephone(mobilePhone);
        verifyQuery.setCode(code);
        List<VerifyCodeDO> verifyCodeDOS = verifyCodeMapper.queryList(verifyQuery);
        if (Validator.isEmpty(verifyCodeDOS)) {
            return new ApiResult(-1, ApiStatus.SC_VERIFY_CODE_ERROR, "验证码错误");
        }

        VerifyCodeDO verify = verifyCodeDOS.get(0);
        if (verify.getExpireTime() < System.currentTimeMillis()) {
            return new ApiResult(-1, ApiStatus.SC_VERIFY_CODE_INVALID, "验证码已失效,请重新获取!");
        }
        if (!code.equalsIgnoreCase(verify.getCode())) {
            return new ApiLoginResult(-1, ApiStatus.SC_VERIFY_CODE_ERROR, "验证码错误, 请重新输入!");
        }

        return new ApiResult("验证成功!");
    }

    @Override
    public ApiResult sendAndReturnVerifyCode(VerifyCodeDO verifyCodeDO) {
        // 检测手机号码一分钟内是否已经发送验证码
        VerifyCodeQueryCondition verifyCodeQueryCondition = new VerifyCodeQueryCondition();
        verifyCodeQueryCondition.setTelephone(verifyCodeDO.getTelephone());
        List<VerifyCodeDO> verifyCodeDOs = verifyCodeMapper.queryList(verifyCodeQueryCondition);
        boolean sent = false;
        if (verifyCodeDOs != null && verifyCodeDOs.size() > 0) {
            verifyCodeDO = verifyCodeDOs.get(0);
            sent = System.currentTimeMillis() < verifyCodeDOs.get(0).getExpireTime();
        }
        if (sent) {
            return new ApiResult("发送成功" , verifyCodeDO.getCode());
        } else {
            try {
                // 生成验证码
                String verifyCode = (int) ((Math.random() * 9 + 1) * 100000) + "";
                verifyCodeDO.setCode(verifyCode);
                // 发送验证码
                Boolean send = JuheUtils.sendVerifyCode(verifyCodeDO.getTelephone(), verifyCodeDO.getCode());
                if (send) {
                    verifyCodeDO.setExpireTime(System.currentTimeMillis() + EXPIRE_TIME);
                    if (null == verifyCodeDO.getVerifyId()) {
                        verifyCodeMapper.save(verifyCodeDO);
                    } else {
                        verifyCodeMapper.update(verifyCodeDO);
                    }
                    return new ApiResult("发送成功", verifyCodeDO.getCode());
                } else {
                    return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "发送失败");
                }
            } catch (Exception e) {
                logger.error("验证码发送异常,", e.getMessage());
                throw new RuntimeException("验证码发送异常!",e);
            }
        }
    }
}
