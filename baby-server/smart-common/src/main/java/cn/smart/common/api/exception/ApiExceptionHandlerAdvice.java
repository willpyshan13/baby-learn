package cn.smart.common.api.exception;

import cn.smart.common.api.result.ApiResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ApiExceptionHandlerAdvice {

    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public ApiResult exception(ApiException e) {
       return new ApiResult(-1, e.getCode(), e.getMsg());
    }

    /*@ExceptionHandler(ApiException.class)
    public ApiResult exception(ApiException e) {
        return new ApiResult(e.getResult(), e.getStatus(), e.getMessage());
    }*/
}
