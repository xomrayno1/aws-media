package com.media.core.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Aspect
@Component
public class DatabaseLoggingAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(DatabaseLoggingAspect.class);

    @Around("execution(* org.springframework.data.jpa.repository.JpaRepository+.*(..))")
    public Object logDatabaseQuery(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
  
        final StopWatch stopWatch = new StopWatch();
        try {
            logger.info("Database Query - Repository: {}, Method: {}", className, methodName);
        	
            stopWatch.start();
        	
        	Object result = joinPoint.proceed();
            
        	stopWatch.stop();

            logger.info("Database Query Completed - Repository: {}, Method: {}, Duration: {} ms", className, methodName, stopWatch.getTotalTimeMillis());

            return result;
        } catch (Throwable throwable) {
            logger.error("Exception in database query: {}", throwable.getMessage(), throwable);
            throw throwable;
        }
        
       
    }

}
