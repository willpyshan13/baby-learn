package cn.smart.common.exception;

import cn.smart.common.api.exception.ApiException;
import cn.smart.common.api.result.ApiResult;
import cn.smart.common.utils.R;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.AuthenticationException;

/**
 *  异常处理类
 * @author Ye
 * @Date 2018-3-27 12:50:10
 */
@ControllerAdvice
@ResponseBody
public class CustomExceptionHandler {

    private static final long serialVersionUID = 5567126415913404804L;

    private Logger logger = LogManager.getLogger(getClass());

    @ExceptionHandler(CustomException.class)
    public R handleCustomException(CustomException e) {
        R r = new R();
        r.put("msg", e.getMsg());
        r.put("code", e.getCode());
        return r;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public R handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return R.error("数据库已经存在该记录！");
    }

    @ExceptionHandler(AuthenticationException.class)
    public R handleAuthenticationException(AuthenticationException e) {
        logger.error(e.getMessage(), e);
        return R.error("暂无操作权限，请联系管理员！");
    }

    @ExceptionHandler(UnauthorizedException.class)
    public R handleUnauthorizedException(UnauthorizedException e) {
        logger.error(e.getMessage(), e);
        return R.error("暂无操作权限，请联系管理员！");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public R handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        logger.error(e.getMessage(), e);
        return R.error("请求参数错误, 请联系管理员！");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error(e.getMessage(), e);
        return R.error("请求方式错误, 请联系管理员！");
    }

    @ExceptionHandler(ApiException.class)
    public ApiResult handleApiException(ApiException e) {
        logger.error(e.getMessage(), e);
        return new ApiResult(-1, e.getStatus() == null ? HttpStatus.SC_INTERNAL_SERVER_ERROR : e.getStatus(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiResult handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return new ApiResult(-1, HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
