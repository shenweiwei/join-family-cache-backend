package org.sww.joinfamily.cache.exception ;

public class RedisException extends JfcacheException {
	
	private static final long serialVersionUID = 1288574372982008787L ;
	
	public RedisException(String message) {
		super(message) ;
	}
	
	public RedisException(String message, io.lettuce.core.RedisException e) {
		super(message, e) ;
	}
	
}
