package org.sww.joinfamily.cache.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspect {
	protected static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

	@Pointcut("execution(public * org.sww.joinfamily.cache..*.*(...)) && "
			+ "@within(org.springframework.web.bind.annotation.RestController)")
	public void processApi() {
		throw new UnsupportedOperationException();
	}
	@Before("processApi()")
	public void recordStartMarker(JoinPoint jp) {
		logger.info(getMarkerName(jp).concat(" start"));
	}
	
	@AfterReturning("processApi()")
	public void recordSucessEndMarker(JoinPoint jp) {
		logger.info(getMarkerName(jp).concat(" sucess end"));
	}
	
	@AfterReturning("processApi()")
	public void recordFailEndMarker(JoinPoint jp) {
		logger.info(getMarkerName(jp).concat(" fail end"));
	}
	private String getMarkerName(JoinPoint jp) {
		Signature signature = jp.getSignature();
		String typeName = signature.getDeclaringTypeName();
		return typeName.substring(typeName.indexOf(".") + 1).concat(".").concat(signature.getName());
	}
}
