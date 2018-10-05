package org.sww.joinfamily.cache.exception ;

public class RedisException extends io.lettuce.core.RedisException {
	
	private static final long	serialVersionUID	= 1288574372982008787L ;
	private String						code ;
	
	public RedisException(String message) {
		super(message) ;
	}
	
	public RedisException(String message, String code) {
		super(message) ;
		setCode(code) ;
	}
	
	public RedisException(String message, Throwable cause) {
		super(message, cause) ;
	}
	
	public RedisException(String message, String code, Throwable cause) {
		super(message, cause) ;
		setCode(code) ;
	}
	
	public RedisException(Throwable cause) {
		super(cause) ;
	}
	
	public String getCode() {
		return code ;
	}
	
	public void setCode(String code) {
		this.code = code ;
	}
	
}
