package org.sww.joinfamily.cache.advice ;

import javax.servlet.http.HttpServletResponse ;
import org.springframework.web.bind.annotation.ControllerAdvice ;
import org.springframework.web.bind.annotation.ExceptionHandler ;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler ;
import org.sww.framework.transfer.http.dto.HttpResposneDTO ;
import org.sww.joinfamily.cache.exception.BusinessException ;
import org.sww.joinfamily.cache.exception.RedisException ;
import org.sww.joinfamily.cache.exception.RequestException ;

@ControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(RequestException.class)
	public HttpResposneDTO handleException(RequestException requestException, HttpServletResponse response) {
		return HttpResposneDTO.create().setCode(requestException.getCode()).setMessage(requestException.getMessage()) ;
	}
	
	@ExceptionHandler(BusinessException.class)
	public HttpResposneDTO handleException(BusinessException businessException, HttpServletResponse response) {
		return HttpResposneDTO.create().setCode(businessException.getCode()).setMessage(businessException.getMessage()) ;
	}
	
	@ExceptionHandler(RedisException.class)
	public HttpResposneDTO handleException(RedisException redisException, HttpServletResponse response) {
		return HttpResposneDTO.create().setCode(redisException.getCode()).setMessage(redisException.getMessage()) ;
	}
	
}
