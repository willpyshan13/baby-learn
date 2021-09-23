package cn.smart.oauth2;

import cn.smart.common.utils.R;
import cn.smart.common.utils.Validator;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ye
 */
public class OAuth2Filter extends AuthenticatingFilter {

    private static final Logger logger = LogManager.getLogger(AuthenticationFilter.class);

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token
        String token = getRequestToken((HttpServletRequest) request);

        if(Validator.isEmpty(token)){
            return null;
        }

        return new OAuth2Token(token);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token，如果token不存在，直接返回401
        HttpServletRequest req = (HttpServletRequest) request;
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            return true;
        }
        String token = getRequestToken(req);
        if(Validator.isEmpty(token)){
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            Object json = JSONObject.toJSON(R.error(HttpStatus.SC_UNAUTHORIZED, "invalid token"));
            httpResponse.getWriter().print(json);
            return false;
        }

        return executeLogin(request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        try {
            //处理登录失败的异常
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            R r = R.error(HttpStatus.SC_UNAUTHORIZED, throwable.getMessage());

            String json = new Gson().toJson(r);
            httpResponse.getWriter().print(json);
            httpResponse.setStatus(HttpStatus.SC_UNAUTHORIZED);
            httpResponse.setContentType(throwable.getMessage());
        } catch (IOException e1) {
            e.printStackTrace();
            logger.error(e1.getMessage());
        }

        return false;
    }

    /**
     * 获取请求的token
     */
    private String getRequestToken(HttpServletRequest httpRequest){

        //从header中获取token
        String token = httpRequest.getHeader("token");

        //如果header中不存在token，则从参数中获取token
        if(Validator.isEmpty(token)){
            token = httpRequest.getParameter("token");
        }

        return token;
    }
}
