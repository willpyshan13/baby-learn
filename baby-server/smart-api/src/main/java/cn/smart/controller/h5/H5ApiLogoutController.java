package cn.smart.controller.h5;

import cn.smart.common.ConfigConstant;
import cn.smart.common.annotation.LoginUser;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.api.result.ApiStatus;
import cn.smart.common.utils.Validator;
import cn.smart.service.IApiTokenService;
import cn.smart.entity.PersonDO;
import cn.smart.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/h5/api")
public class H5ApiLogoutController {

    private final IPersonService personService;

    private final IApiTokenService tbTokenService;

    @Autowired
    public H5ApiLogoutController(IPersonService personService, IApiTokenService tbTokenService) {
        this.personService = personService;
        this.tbTokenService = tbTokenService;
    }

    /**
     * 用户退出登录
     * @param person 用户
     * @param request request
     * @return R
     */
    @GetMapping("/logout")
    public ApiResult logout(@LoginUser PersonDO person, HttpServletRequest request) {

        if (Validator.isEmpty(person.getPersonId())) {
            return new ApiResult(-1, ApiStatus.SC_LOG_OUT_ERROR, "退出失败!");
        }

        int result = tbTokenService.deleteById(person.getPersonId());
        if (1 == result) {
            request.removeAttribute(ConfigConstant.USER_KEY);
            return new ApiResult("退出成功!");
        } else {
            return new ApiResult(-1, ApiStatus.SC_LOG_OUT_ERROR, "退出失败!");
        }
    }
}
