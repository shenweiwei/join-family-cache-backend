package org.sww.joinfamily.cache.exception;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1213634755871246727L;

	public BusinessException(String message) {
		super(message);
	}
	public BusinessException() {
		super();
	}
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
	public BusinessException(Throwable cause) {
		super(cause);
	}
}
