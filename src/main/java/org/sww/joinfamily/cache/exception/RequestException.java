package org.sww.joinfamily.cache.exception;

public class RequestException extends RuntimeException {
	private static final long serialVersionUID = 403408793026937592L;

	public RequestException(String message) {
		super(message);
	}
	public RequestException() {
		super();
	}
	public RequestException(String message, Throwable cause) {
		super(message, cause);
	}
	public RequestException(Throwable cause) {
		super(cause);
	}
}
