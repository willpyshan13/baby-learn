package cn.smart.common.api.exception;

import cn.smart.common.exception.CustomException;

/**
 * @author Ye
 */
public class ApiException extends CustomException {

    private static final long serialVersionUID = -5622403997098809267L;

    private Integer status;

    public ApiException(String msg) {
        super(msg);
    }

    public ApiException(String msg, Throwable e) {
        super(msg, e);
    }

    public ApiException(String msg, int code) {
        super(msg, code);
        this.status = code;
    }

    public ApiException(String msg, int status, Throwable e) {
        super(msg, status, e);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
