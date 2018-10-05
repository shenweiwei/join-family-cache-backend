package org.sww.joinfamily.cache.exception ;

public class JfcacheException extends RuntimeException {
	
	private static final long	serialVersionUID	= -268693255844895883L ;
	
	private String						code ;
	
	public JfcacheException(String message) {
		super(message) ;
	}
	
	public JfcacheException(String message, String code) {
		super(message) ;
		setCode(code) ;
	}
	
	public JfcacheException(String message, Throwable cause) {
		super(message, cause) ;
	}
	
	public JfcacheException(String message, String code, Throwable cause) {
		super(message, cause) ;
		setCode(code) ;
	}
	
	public JfcacheException(Throwable cause) {
		super(cause) ;
	}
	
	public String getCode() {
		return code ;
	}
	
	public void setCode(String code) {
		this.code = code ;
	}
	
}
