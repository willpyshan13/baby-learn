package cn.smart.interceptor;

import cn.smart.common.ConfigConstant;
import cn.smart.common.annotation.AuthIgnore;
import cn.smart.common.api.exception.ApiException;
import cn.smart.common.api.result.ApiStatus;
import cn.smart.common.utils.DateUtils;
import cn.smart.common.utils.Validator;
import cn.smart.condition.ApiTokenQueryCondition;
import cn.smart.entity.ApiTokenDO;
import cn.smart.service.IApiTokenService;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

import static java.lang.System.currentTimeMillis;

@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LogManager.getLogger(getClass());

    private static final String API_MOBILE = "mobile";
    private static final String API_H5 = "h5";

    @Autowired
    private IApiTokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        logger.info("==> Parameters: request url={}, parameter map={}", request.getRequestURI(), request.getParameterMap());

        AuthIgnore annotation;

        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(AuthIgnore.class);
        } else {
            return true;
        }

        // 如果有 @AuthIgnore 注解，则不验证 Token
        if (annotation != null) {
            return true;
        }
        // 从 Header 中获取 Token
        String token = request.getHeader("token");
        // 如果Header 中没有， 则从参数中获取
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("token");
        }

        if (token == null) {
            throw new ApiException("Token 不能为空", ApiStatus.SC_TOKEN_NULL);
        }

        // 查询 Token 信息
        ApiTokenQueryCondition query = new ApiTokenQueryCondition();
        query.setToken(token);

        // token 类型 1:Android app 2:H5
        if (request.getRequestURI().contains(API_MOBILE)) {
            query.setType(1);
        } else if (request.getRequestURI().contains(API_H5)) {
            query.setType(2);
        }

        List<ApiTokenDO> apiTokenList = tokenService.queryList(query);
        if (Validator.isEmpty(apiTokenList) || Validator.isEmpty(apiTokenList.get(0)) || apiTokenList.get(0).getExpireTime() < currentTimeMillis()) {
            throw new ApiException("Token 失效，请重新登录！", ApiStatus.SC_TOKEN_INVALID);
        } else {
            ApiTokenDO t = apiTokenList.get(0);
            // 更新过期时间
            Long expireTime = DateUtils.addDay(new Date(), 365).getTime();
            t.setExpireTime(expireTime);
            t.setUpdateTime(new Date());
            tokenService.update(t);

            logger.info("<==     Person: id={}", t.getPersonId());
        }

        // 设置到 request 域中，后期获取
        request.setAttribute(ConfigConstant.USER_KEY, apiTokenList.get(0).getPersonId());

        return true;
    }
}
