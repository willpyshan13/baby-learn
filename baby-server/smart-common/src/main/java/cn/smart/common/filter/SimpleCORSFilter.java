package cn.smart.common.filter;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ajax 跨域配置过滤器
 */
public class SimpleCORSFilter implements Filter{

    private String allowOrigin;
    private String allowMethods;
    private String allowCredentials;
    private String allowHeaders;
    private String exposeHeaders;


    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));

//        if (StringUtils.isNotEmpty(allowOrigin)) {
//            //允许访问的客户端域名，例如：http://web.xxx.com，若为*，则表示从任意域都能访问，即不做任何限制；
//            response.setHeader("Access-Control-Allow-Origin", allowOrigin);
//        }
        if (StringUtils.isNotEmpty(allowMethods)) {
            //允许访问的请求方式，多个用逗号分割，例如：GET,POST,PUT,DELETE,OPTIONS；
            response.setHeader("Access-Control-Allow-Methods", allowMethods);
        }
        if (StringUtils.isNotEmpty(allowCredentials)) {
            //是否允许请求带有验证信息，若要获取客户端域下的cookie时，需要将其设置为true；
            response.setHeader("Access-Control-Allow-Credentials", allowCredentials);
        }
        if (StringUtils.isNotEmpty(allowHeaders)) {
            //允许服务端访问的客户端请求头，多个请求头用逗号分割，例如：Content-Type,Access-Token,timestamp
            response.setHeader("Access-Control-Allow-Headers", allowHeaders);
        }
        if (StringUtils.isNotEmpty(exposeHeaders)) {
            //允许客户端访问的服务端响应头，多个响应头用逗号分割。
            response.setHeader("Access-Control-Expose-Headers", exposeHeaders);
        }


//
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, token, Content-Type");
//
//        if(request.getHeader("Access-Control-Request-Method")!=null&&"OPTIONS".equals(request.getMethod())) {
//            response.addHeader("Access-Control-Allow-Methods", "POST,GET,TRACE,OPTIONS");
//            response.addHeader("Access-Control-Allow-Headers", "Content-Type,Origin,Accept");
//            response.addHeader("Access-Control-Max-age", "120");
//
//        }
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setContentType("textml;charset=UTF-8");

//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "0");
//        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
//        response.setHeader("XDomainRequestAllowed","1");
//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Credential", "true");
//        response.setHeader("P3P", "CP=CAO PSA OUR");


        chain.doFilter(req, res);

    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        allowOrigin = config.getInitParameter("allowOrigin");
        allowMethods = config.getInitParameter("allowMethods");
        allowHeaders = config.getInitParameter("allowHeaders");
        allowCredentials = config.getInitParameter("allowCredentials");
    }

    public void setAllowOrigin(String allowOrigin) {
        this.allowOrigin = allowOrigin;
    }

    public void setAllowMethods(String allowMethods) {
        this.allowMethods = allowMethods;
    }

    public void setAllowCredentials(String allowCredentials) {
        this.allowCredentials = allowCredentials;
    }

    public void setAllowHeaders(String allowHeaders) {
        this.allowHeaders = allowHeaders;
    }

    public void setExposeHeaders(String exposeHeaders) {
        this.exposeHeaders = exposeHeaders;
    }
}
