package org.sww.joinfamily.cache.exception ;

public class BusinessException extends RuntimeException {
	
	private static final long	serialVersionUID	= 1213634755871246727L ;
	private String						code ;
	
	public BusinessException() {
		super() ;
	}
	
	public BusinessException(String message) {
		super(message) ;
	}
	
	public BusinessException(String message, String code) {
		super(message) ;
		setCode(code) ;
	}
	
	public BusinessException(String message, Throwable cause) {
		super(message, cause) ;
	}
	
	public BusinessException(String message, String code, Throwable cause) {
		super(message, cause) ;
		setCode(code) ;
	}
	
	public BusinessException(Throwable cause) {
		super(cause) ;
	}
	
	public String getCode() {
		return code ;
	}
	
	public void setCode(String code) {
		this.code = code ;
	}
	
}
