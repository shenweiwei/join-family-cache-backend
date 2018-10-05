package org.sww.joinfamily.cache.exception ;

public class BusinessException extends JfcacheException {
	
	private static final long serialVersionUID = 1213634755871246727L ;
	
	public BusinessException(String message) {
		super(message) ;
	}
	
	public BusinessException(String message, Exception e) {
		super(message, e) ;
	}
}
