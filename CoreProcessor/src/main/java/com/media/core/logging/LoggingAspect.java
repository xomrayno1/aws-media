package com.media.core.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class LoggingAspect {

	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	private final ObjectMapper objectMapper;

	public LoggingAspect(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	
	//@Around("@annotation(Loggable)")
	@Around("execution(* com.app.service..*.*(..)) || execution(* com.account.service..*(..))")
	public Object logService(ProceedingJoinPoint joinPoint) throws Throwable {

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String className = signature.getDeclaringType().getSimpleName();
		String methodName = signature.getName();

		logger.info("Service Running, Class: {}, Method: {}, Args: {} ", className, methodName, joinPoint.getArgs());

		final StopWatch stopWatch = new StopWatch();

		Object result;
		try {
			stopWatch.start();

			result = joinPoint.proceed();

			stopWatch.stop();

			logMethodServiceExecution(className, methodName, joinPoint.getArgs(), result, stopWatch.getTotalTimeMillis());
		} catch (Throwable throwable) {
			logException(className, methodName, throwable);
			throw throwable;
		}

		return result;
	}
	 
    private final int LOG_RESPONSE_SIZE = 1000;
  
	private void logMethodServiceExecution(String className, String methodName, Object[] args, Object result,
			long executionTime) {
		try {
			String argsJson = objectMapper.writeValueAsString(args);
			String resultJson = objectMapper.writeValueAsString(result);
			if(resultJson.length() > LOG_RESPONSE_SIZE) {
				resultJson = resultJson.substring(0, LOG_RESPONSE_SIZE) + "...";
			}
		 
			logger.info("Service Completed,  Class: {}, Method: {}, Args: {}, Result: {}, Duration: {} ms", className, methodName,
					argsJson, resultJson, executionTime);
		} catch (Exception e) {
			logger.error("Error while logging method execution", e);
		}
	}

	private void logException(String className, String methodName, Throwable throwable) {
		logger.error("Exception in {}.{}: {}", className, methodName, throwable.getMessage(), throwable);
	}

}
