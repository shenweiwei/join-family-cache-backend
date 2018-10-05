package org.sww.joinfamily.cache.exception ;

public class RequestException extends RuntimeException {
	
	private static final long	serialVersionUID	= 403408793026937592L ;
	private String						code ;
	
	public RequestException() {
		super() ;
	}
	
	public RequestException(String message) {
		super(message) ;
	}
	
	public RequestException(String message, String code) {
		super(message) ;
		setCode(code) ;
	}
	
	public RequestException(String message, Throwable cause) {
		super(message, cause) ;
	}
	
	public RequestException(String message, String code, Throwable cause) {
		super(message, cause) ;
		setCode(code) ;
	}
	
	public RequestException(Throwable cause) {
		super(cause) ;
	}
	
	public String getCode() {
		return code ;
	}
	
	public void setCode(String code) {
		this.code = code ;
	}
	
}
