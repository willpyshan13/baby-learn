package cn.smart.controller.mobile;

import cn.smart.common.ConfigConstant;
import cn.smart.common.annotation.AuthIgnore;
import cn.smart.common.annotation.LoginUser;
import cn.smart.common.annotation.Logs;
import cn.smart.common.api.result.ApiLoginResult;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.api.result.ApiStatus;
import cn.smart.common.utils.Validator;
import cn.smart.service.IApiPersonService;
import cn.smart.entity.PersonDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/mobile")
public class ApiLoginController {

    @Autowired
    private IApiPersonService personService;

    @AuthIgnore
    @GetMapping("/user/loginByVerifyCode")
    @Logs(value = "APP验证码登录")
    public ApiLoginResult loginByVerifyCode(
            @RequestParam(value = "username") String mobilePhone,
            @RequestParam(value = "code") String verifyCode) {

        if (Validator.isEmpty(mobilePhone)) {
            return new ApiLoginResult(ApiStatus.SC_MOBILE_PHONE_NULL, "手机号码不能为空!");
        }
        if (Validator.isEmpty(mobilePhone)) {
            return new ApiLoginResult(ApiStatus.SC_VERIFY_CODE_NULL, "验证码不能为空!");
        }

        ApiLoginResult result = personService.loginByVerifyCode(mobilePhone, verifyCode, 1);

        return result;

    }

    @AuthIgnore
    @GetMapping(value = "/user/login")
    @Logs(value = "APP用户登录")
    public ApiLoginResult loginByPassword(
            @RequestParam("username") String mobilePhone,
            @RequestParam("password") String password) {

        if (Validator.isEmpty(mobilePhone)) {
            return new ApiLoginResult(-1, ApiStatus.SC_MOBILE_PHONE_NULL, "手机号码不能为空!");
        }
        if (Validator.isEmpty(password)) {
            return new ApiLoginResult(-1, ApiStatus.SC_PASSWORD_NULL, "密码不能为空!");
        }

        return personService.login(mobilePhone, password, 1);
    }

    @PostMapping(value = "/user/logout")
    @Logs(value = "APP用户登出")
    public ApiResult logout(@LoginUser PersonDO person,
                            @RequestParam("token") String token, HttpServletRequest request) {

        if (Validator.isEmpty(person.getPersonId())) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "token无效，请重新登录！");
        }
        ApiResult result;
        try {
            result = personService.logout(person);
            request.removeAttribute(ConfigConstant.USER_KEY);
        } catch (Exception e) {
            return new ApiResult(-1, ApiStatus.SC_LOG_OUT_ERROR, "退出失败！");
        }

        return result;
    }
}
