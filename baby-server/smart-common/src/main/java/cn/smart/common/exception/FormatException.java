package cn.smart.common.exception;

/**
 * 数据格式错误
 * @author yusheng
 *
 */

public class FormatException extends Exception {
	public FormatException() {
	}

	public FormatException(String message, Throwable cause) {
		super("数据格式错误: " + message, cause);
	}

	public FormatException(String message) {
		super("数据格式错误: " + message);
	}

	public FormatException(Throwable cause) {
		super(cause);
	}
}
