package cn.smart.controller.h5;

import cn.smart.common.annotation.AuthIgnore;
import cn.smart.common.api.result.ApiLoginResult;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.api.result.ApiStatus;
import cn.smart.common.utils.Validator;
import cn.smart.entity.CasualUserDO;
import cn.smart.service.IApiPersonService;
import cn.smart.entity.PersonDO;
import cn.smart.entity.VerifyCodeDO;
import cn.smart.service.IVerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/h5/api")
public class H5ApiRegisterController {

    private static final Long EXPIRE_TIME = 60000L;

    private final IApiPersonService personService;

    private final IVerifyCodeService verifyCodeService;

    @Autowired
    public H5ApiRegisterController(IApiPersonService personService, IVerifyCodeService verifyCodeService) {
        this.personService = personService;
        this.verifyCodeService = verifyCodeService;
    }

    /**
     * 用户注册
     * @param mobilePhone 手机号码
     * @param verifyCode 验证码
     * @return ApiResult
     */
    @AuthIgnore
    @PostMapping(value = "/register")
    public ApiLoginResult userRegister(
            @RequestParam("mobilePhone") String mobilePhone,
            @RequestParam("verifyCode") String verifyCode) {

        if (Validator.isEmpty(mobilePhone)) {
            return new ApiLoginResult(-1, ApiStatus.SC_MOBILE_PHONE_NULL, "手机号码不能为空!");
        }
        if (Validator.isEmpty(verifyCode)) {
            return new ApiLoginResult(-1, ApiStatus.SC_VERIFY_CODE_NULL, "验证码不能为空!");
        }

        PersonDO personDO = new PersonDO();
        personDO.setMobileNumber(mobilePhone);
        personDO.setCreateTime(new Date());
        // h5 注册
        personDO.setChannel(2);
        // 生效
        personDO.setStatus(1);
        return personService.register(personDO, verifyCode, null, null);
    }

    /**
     * 记录临时用户
     * @param mobilePhone 手机号
     * @param age 年龄
     * @param verifyCode 验证码
     * @param userName 姓名
     * @return
     */
    @AuthIgnore
    @PostMapping("/casual/reg")
    public ApiLoginResult casualUserReg(@RequestParam("mobilePhone") String mobilePhone,
                                        @RequestParam("age") Integer age,
                                        @RequestParam("verifyCode") String verifyCode,
                                        @RequestParam("userName") String userName) {
        if (Validator.isEmpty(mobilePhone)) {
            return new ApiLoginResult(-1, ApiStatus.SC_MOBILE_PHONE_NULL, "手机号码不能为空!");
        }
        if (Validator.isEmpty(verifyCode)) {
            return new ApiLoginResult(-1, ApiStatus.SC_VERIFY_CODE_NULL, "验证码不能为空!");
        }

        CasualUserDO casualUser = new CasualUserDO();
        casualUser.setUserName(userName);
        casualUser.setMobilePhone(mobilePhone);
        casualUser.setAge(age);
        casualUser.setCreateTime(new Date());
        casualUser.setSource(1);

        return personService.register(casualUser, verifyCode);

    }

    /**
     * 记录临时用户
     * @param mobilePhone 手机号
     * @param age 年龄
     * @param userName 姓名
     * @return
     */
    @AuthIgnore
    @PostMapping("/casual/register")
    public ApiLoginResult casualUserReg(@RequestParam("mobilePhone") String mobilePhone,
                                        @RequestParam("age") Integer age,
                                        @RequestParam("userName") String userName) {
        if (Validator.isEmpty(mobilePhone)) {
            return new ApiLoginResult(-1, ApiStatus.SC_MOBILE_PHONE_NULL, "手机号码不能为空!");
        }

        CasualUserDO casualUser = new CasualUserDO();
        casualUser.setUserName(userName);
        casualUser.setMobilePhone(mobilePhone);
        casualUser.setAge(age);
        casualUser.setCreateTime(new Date());
        casualUser.setSource(2);

        return personService.register(casualUser, null);

    }

    /**
     * 验证手机号码是否已注册
     * @param mobilePhone 手机号码
     * @return R
     */
    @AuthIgnore
    @GetMapping(value = "/validate/casual/phone")
    public ApiResult validatePhone(@RequestParam("mobilePhone") String mobilePhone) {

        if (Validator.isEmpty(mobilePhone)) {
            return new ApiResult(-1, ApiStatus.SC_MOBILE_PHONE_NULL, "手机号码不能为空!");
        }

        return personService.validateCasualPhone(mobilePhone);
    }

    /**
     * 验证手机号码是否已注册
     * @param mobilePhone 手机号码
     * @return R
     */
    @AuthIgnore
    @GetMapping(value = "/validateMobilePhone")
    public ApiResult validateMobilePhone(@RequestParam("mobilePhone") String mobilePhone) {

        if (Validator.isEmpty(mobilePhone)) {
            return new ApiResult(-1, ApiStatus.SC_MOBILE_PHONE_NULL, "手机号码不能为空!");
        }

        return personService.validateMobilePhone(mobilePhone);
    }

    /**
     * 获取验证码
     * @param mobilePhone 手机号码
     * @return R
     */
    @AuthIgnore
    @GetMapping(value = "/getVerifyCode")
    public ApiResult getVerifyCode(@RequestParam("mobilePhone") String mobilePhone) {


        if (StringUtils.isEmpty(mobilePhone)) {
            return new ApiResult(-1, ApiStatus.SC_MOBILE_PHONE_NULL, "手机号码不能为空!");
        }

        VerifyCodeDO verifyCodeDO = new VerifyCodeDO();
        verifyCodeDO.setTelephone(mobilePhone);
        try {
            return verifyCodeService.sendVerifyCode(verifyCodeDO);
        } catch (Exception e) {
            return new ApiResult(-1, ApiStatus.SC_VERIFY_CODE_SEND_ERROR,"验证码发送失败,请重新发送!");
        }
    }

    /**
     * 第三方获取短信验证码并返回验证码
     */
    @AuthIgnore
    @GetMapping(value = "/getCode")
    public ApiResult getCode(@RequestParam("mobilePhone") String mobilePhone) {

        if (StringUtils.isEmpty(mobilePhone)) {
            return new ApiResult(-1, ApiStatus.SC_MOBILE_PHONE_NULL, "手机号码不能为空!");
        }

        VerifyCodeDO verifyCodeDO = new VerifyCodeDO();
        verifyCodeDO.setTelephone(mobilePhone);
        try {
            return verifyCodeService.sendAndReturnVerifyCode(verifyCodeDO);
        } catch (Exception e) {
            return new ApiResult(-1, ApiStatus.SC_VERIFY_CODE_SEND_ERROR,"验证码发送失败!");
        }
    }

}
