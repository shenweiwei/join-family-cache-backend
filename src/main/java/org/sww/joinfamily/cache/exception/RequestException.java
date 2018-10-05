package org.sww.joinfamily.cache.exception ;

public class RequestException extends JfcacheException {
	
	public RequestException(String message) {
		super(message) ;
	}

	public RequestException(String message, Exception e) {
		super(message,e) ;
	}

	private static final long	serialVersionUID	= 403408793026937592L ;
	
}
