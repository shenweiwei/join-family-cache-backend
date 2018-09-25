package org.sww.joinfamily.cache.exception;

public class RedisException extends RuntimeException{
	
	private static final long serialVersionUID = 1288574372982008787L;
	
	public RedisException(String message) {
		super(message);
	}
	public RedisException() {
		super();
	}
	public RedisException(String message, Throwable cause) {
		super(message, cause);
	}
	public RedisException(Throwable cause) {
		super(cause);
	}
}
