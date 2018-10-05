package org.sww.joinfamily.cache.config ;

import java.lang.reflect.Method ;
import javax.validation.ValidationException ;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler ;
import org.springframework.context.annotation.Bean ;
import org.springframework.context.annotation.Configuration ;
import org.springframework.core.task.AsyncTaskExecutor ;
import org.springframework.scheduling.annotation.AsyncConfigurer ;
import org.springframework.scheduling.annotation.EnableAsync ;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor ;
import org.sww.framework.transfer.exception.TransferException ;
import org.sww.framework.transfer.http.dto.AsyncHttpDataTransferObject ;
import org.sww.joinfamily.cache.exception.JfcacheException ;

@EnableAsync
@Configuration
public class AsyncConfig implements AsyncConfigurer {
	
	@Bean
	public AsyncTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor() ;
		executor.setThreadNamePrefix("Anno-Executor") ;
		executor.setMaxPoolSize(10) ;
		
		return executor ;
	}
	
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new AsyncUncaughtExceptionHandler() {
			
			@Override
			public void handleUncaughtException(Throwable throwable, Method method, Object... params) {
				TransferException transferException = initTransferExcetpionByType(throwable) ;
				transferFailed(params, transferException) ;
			}
		} ;
	}
	
	private void transferFailed(Object [] params, TransferException transferException) {
		for (Object param : params) {
			if (param instanceof AsyncHttpDataTransferObject) {
				((AsyncHttpDataTransferObject) param).transferFailed(transferException) ;
			}
		}
	}
	
	private TransferException initTransferExcetpionByType(Throwable throwable) {
		JfcacheException exception = new JfcacheException(throwable) ;
		if (throwable instanceof JfcacheException) {
			exception.setCode(exception.getCode()) ;
		} else if (throwable instanceof ValidationException) {
			exception.setCode("400") ;
		} else {
			exception.setCode("500") ;
		}
		
		return TransferException.create(exception.getMessage(), exception.getCode()) ;
	}
	
}
