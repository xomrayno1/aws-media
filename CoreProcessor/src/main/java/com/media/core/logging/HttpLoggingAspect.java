package com.media.core.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpLoggingAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpLoggingAspect.class);

    @Around("@within(org.springframework.web.bind.annotation.RestController)")
    public Object logHttpMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        
        String httpMethod = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
      
        final StopWatch stopWatch = new StopWatch();
        try {
            logger.info("HTTP Request - Method: {}, URI: {}, Query: {} ", httpMethod, uri, queryString);

        	
            stopWatch.start();
        	
        	Object result = joinPoint.proceed();
            
        	stopWatch.stop();

            logger.info("HTTP Response - Method: {}, URI: {}, Duration: {} ms", httpMethod, uri, stopWatch.getTotalTimeMillis());
            return result;
        } catch (Throwable throwable) {
            logger.error("Exception in HTTP request: {}", throwable.getMessage(), throwable);
            throw throwable;
        }

    }

}
