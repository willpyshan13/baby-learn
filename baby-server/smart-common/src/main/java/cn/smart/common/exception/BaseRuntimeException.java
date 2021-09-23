package cn.smart.common.exception;

/**
 * 请求超时错误
 * @author yusheng
 *
 */
public class BaseRuntimeException extends RuntimeException {
	public BaseRuntimeException() {
	}

	public BaseRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseRuntimeException(String message) {
		super(message);
	}

	public BaseRuntimeException(Throwable cause) {
		super(cause);
	}
}
