package cn.smart.controller.mobile;

import cn.smart.common.annotation.AuthIgnore;
import cn.smart.common.annotation.LoginUser;
import cn.smart.common.annotation.Logs;
import cn.smart.common.api.exception.ApiException;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.api.result.ApiStatus;
import cn.smart.common.utils.Validator;
import cn.smart.condition.ApiTokenQueryCondition;
import cn.smart.entity.ApiTokenDO;
import cn.smart.service.IApiPersonService;
import cn.smart.service.IApiTokenService;
import cn.smart.entity.PersonDO;
import cn.smart.entity.VerifyCodeDO;
import cn.smart.service.IVerifyCodeService;
import cn.smart.utils.MD5Utils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/mobile")
public class ApiRegisterController {

    private static final Long EXPIRE_TIME = 120000L;

    @Autowired
    private IVerifyCodeService verifyCodeService;

    @Autowired
    private IApiPersonService personService;

    @Autowired
    private IApiTokenService tokenService;

    @AuthIgnore
    @PostMapping("/user/register")
    @Logs(value = "APP用户注册")
    public ApiResult register(
            @RequestParam("username") String mobilePhone,
            @RequestParam("password") String password,
            @RequestParam("verifyCode") String verifyCode,
            @RequestParam(value = "nickname", required = false) String nickname,
            @RequestParam(value = "mobileSerial", required = false) String mobileSerial,
            @RequestParam(value = "headImage", required = false) MultipartFile image,
            @RequestParam(value = "tag", required = false) String tag,
            HttpServletRequest request) {

        String uploadDir = request.getSession().getServletContext()
                .getRealPath("/")
                + "/";

        if (Validator.isEmpty(mobilePhone)) {
            return new ApiResult(-1, ApiStatus.SC_MOBILE_PHONE_NULL, "手机号码不能为空!");
        }
        if (Validator.isEmpty(password)) {
            return new ApiResult(-1, ApiStatus.SC_PASSWORD_NULL, "密码不能为空!");
        }
        if (Validator.isEmpty(verifyCode)) {
            return new ApiResult(-1, ApiStatus.SC_VERIFY_CODE_NULL, "验证码不能为空!");
        }
        if (image != null && image.getSize() > 1024 * 1024 * 1) {
            return new ApiResult(-1, ApiStatus.SC_IMAGE_SIZE_ERROR, "请上传小于1M的图片文件！");
        }
//        if (Validator.isEmpty(tag)) {
//            return new ApiResult(-1, ApiStatus.SC_PUSH_MARK_NULL, "推送标记不能为空!");
//        }

        PersonDO person = new PersonDO();
        person.setMobileNumber(mobilePhone);
        // 将密码转换成md5值
        person.setPassword(MD5Utils.string2MD5(password));
        person.setUserName(mobilePhone);
        person.setNickName(nickname);
        person.setMobileSerial(mobileSerial);
        // app 注册
        person.setChannel(1);
        person.setCreateTime(new Date());
        person.setStatus(1);
        // 推送标记
        person.setPushAlias(mobilePhone);

        return personService.register(person, verifyCode, image, uploadDir);
    }

    @AuthIgnore
    @PostMapping(value = "/user/token")
    public ApiResult checkToken(@LoginUser PersonDO personDO, @RequestParam("token") String token) {

        if (token == null) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "token无效,请重新登录");
        }
        // 查询 Token 信息
        ApiTokenQueryCondition query = new ApiTokenQueryCondition();
        query.setToken(token);
        // token 类型 1:Android app 2:H5
        query.setType(1);
        List<ApiTokenDO> apiTokenList = tokenService.queryList(query);
        if (Validator.isEmpty(apiTokenList) || Validator.isEmpty(apiTokenList.get(0)) || apiTokenList.get(0).getExpireTime() < System.currentTimeMillis()) {
            return new ApiResult(-1, ApiStatus.SC_TOKEN_INVALID, "token无效,请重新登录");
        }

        return personService.queryUserDetail(apiTokenList.get(0).getPersonId());
    }

    /**
     * 验证手机号码是否已注册
     * @param mobilePhone 手机号码
     * @return R
     */
    @AuthIgnore
    @GetMapping(value = "/v1/validateMobilePhone")
    public ApiResult validateMobilePhone(@RequestParam("mobilePhone") String mobilePhone) {

        if (Validator.isEmpty(mobilePhone)) {
            return new ApiResult(-1,ApiStatus.SC_MOBILE_PHONE_NULL, "手机号码不能为空!");
        }
        return personService.validateMobilePhone(mobilePhone);
    }

    /**
     * 发送验证码
     * @param mobilePhone
     * @param type
     * @return
     */
    @AuthIgnore
    @GetMapping("/user/verify")
    @Logs(value = "APP获取验证码")
    public ApiResult getVerifyCode(@RequestParam("telephone") String mobilePhone, Integer type) {

        if (Validator.isEmpty(mobilePhone)) {
            return new ApiResult(-1, ApiStatus.SC_MOBILE_PHONE_NULL, "手机号码不能为空!");
        }

        // 生成验证码
        String verifyCode = (int) ((Math.random() * 9 + 1) * 100000) + "";
        VerifyCodeDO verifyCodeDO = new VerifyCodeDO();
        verifyCodeDO.setCode(verifyCode);
        verifyCodeDO.setTelephone(mobilePhone);
        verifyCodeDO.setExpireTime(System.currentTimeMillis() + EXPIRE_TIME);
        try {
            return verifyCodeService.sendVerifyCode(verifyCodeDO);
        } catch (Exception e) {
            return new ApiResult(-1, ApiStatus.SC_VERIFY_CODE_SEND_ERROR,"验证码发送失败,请重新发送!");
        }
    }

    @AuthIgnore
    @GetMapping("/verify/code")
    public ApiResult verifyCode(@RequestParam("mobilePhone") String mobilePhone,
                                @RequestParam("code") String code) {
        ApiResult result;
        try {
            result = verifyCodeService.verifyCode(mobilePhone, code);
        } catch (ApiException e) {
            return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, "操作失败");
        }
        return result;
    }


}
