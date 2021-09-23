package cn.smart.resolver;

import cn.smart.common.ConfigConstant;
import cn.smart.common.annotation.LoginUser;
import cn.smart.common.utils.Validator;
import cn.smart.condition.ApiTokenQueryCondition;
import cn.smart.entity.ApiTokenDO;
import cn.smart.service.IApiTokenService;
import cn.smart.entity.PersonDO;
import cn.smart.service.IPersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.lang.System.currentTimeMillis;

@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver{

    private static final String API_MOBILE = "mobile";
    private static final String API_H5 = "h5";

    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private IPersonService personService;

    @Autowired
    private IApiTokenService tokenService;

    /**
     *
     * @param methodParameter 请求方法参数
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {

        return methodParameter.getParameterType().isAssignableFrom(PersonDO.class) && methodParameter.hasParameterAnnotation(LoginUser.class);
    }


    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest request, WebDataBinderFactory webDataBinderFactory) throws Exception {

        Object id;

        // 从 Header 中获取 Token
        String token = request.getHeader("token");
        // 如果Header 中没有， 则从参数中获取
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("token");
        }
        // 查询 Token 信息
        ApiTokenQueryCondition query = new ApiTokenQueryCondition();
        query.setToken(token);
        // token 类型 1:Android app 2:H5
        HttpServletRequest req = request.getNativeRequest(HttpServletRequest.class);
        String url = req.getRequestURI();
        if (url.contains(API_MOBILE)) {
            query.setType(1);
        } else if (url.contains(API_H5)) {
            query.setType(2);
        }
        List<ApiTokenDO> apiTokenList = tokenService.queryList(query);
        // 获取用户Id

        if (Validator.isNotEmpty(apiTokenList) && Validator.isNotEmpty(apiTokenList.get(0)) && apiTokenList.get(0).getExpireTime() > currentTimeMillis()) {
             id = apiTokenList.get(0).getPersonId();
        } else {
            id = request.getAttribute(ConfigConstant.USER_KEY, RequestAttributes.SCOPE_REQUEST);
        }
        if (id == null) {
            return null;
        }
        // 获取用户信息
        return personService.queryById(id);
    }
}
