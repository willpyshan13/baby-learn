package cn.smart.controller.h5;

import cn.smart.common.annotation.AuthIgnore;
import cn.smart.common.annotation.LoginUser;
import cn.smart.common.annotation.Logs;
import cn.smart.common.api.result.ApiLoginResult;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.api.result.ApiStatus;
import cn.smart.common.utils.Validator;
import cn.smart.service.IApiPersonService;
import cn.smart.service.IApiTokenService;
import cn.smart.entity.PersonDO;
import cn.smart.service.IVerifyCodeService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/h5/api")
public class H5ApiLoginController {

    private final IApiPersonService personService;

    @Autowired
    public H5ApiLoginController(IApiPersonService personService, IVerifyCodeService verifyCodeService, IApiTokenService tokenService) {
        this.personService = personService;
    }

    /**
     * H5 用户验证码登录(忘记密码接口)
     * @param mobilePhone 手机号码
     * @param verifyCode  密码
     * @return R
     */
    @AuthIgnore
    @GetMapping(value = "/loginByVerifyCode")
    public ApiLoginResult loginByVerifyCode(
            @RequestParam("mobilePhone") String mobilePhone,
            @RequestParam("verifyCode") String verifyCode) {

        if (Validator.isEmpty(mobilePhone)) {
            return new ApiLoginResult(-1, ApiStatus.SC_MOBILE_PHONE_NULL, "手机号码不能为空!");
        }
        if (Validator.isEmpty(verifyCode)) {
            return new ApiLoginResult(-1, ApiStatus.SC_PASSWORD_NULL, "验证码不能为空!");
        }

        ApiLoginResult result = personService.loginByVerifyCode(mobilePhone, verifyCode, 2);
        result.setData(null);
        return result;

    }

    /**
     * H5 用户登录接口
     * @param mobilePhone 手机号码
     * @param pwd  密码
     * @return R
     */
    @AuthIgnore
    @GetMapping("/login")
    public ApiLoginResult login(@RequestParam("mobilePhone") String mobilePhone, @RequestParam("pwd") String pwd) {

        if (Validator.isEmpty(mobilePhone)) {
            return new ApiLoginResult(-1, ApiStatus.SC_MOBILE_PHONE_NULL, "手机号码不能为空!");
        }
        if (Validator.isEmpty(pwd)) {
            return new ApiLoginResult(-1, ApiStatus.SC_PASSWORD_NULL, "密码不能为空!");
        }

        pwd = new String(Base64.decodeBase64(pwd));

        ApiLoginResult result = personService.login(mobilePhone, pwd, 2);
        result.setData(null);
        return result;

    }


    @PostMapping("/password")
    @Logs(value = "H5用户修改密码")
    public ApiResult updatePassword(@LoginUser PersonDO person, @RequestParam("password") String password) {

        if (Validator.isEmpty(person.getPersonId())) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "请重新登录!");
        }
        if (Validator.isEmpty(password)) {
            return new ApiResult(-1, ApiStatus.SC_PASSWORD_NULL, "密码不能为空!");
        }
        password = new String(Base64.decodeBase64(password));

        return personService.updatePassword(person.getPersonId(), password);

    }
}
