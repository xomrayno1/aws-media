package com.media.core.config;

import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.media.core.exceptions.CustomerErrorDecoder;
import com.media.core.utils.ConstantManager;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;

@Configuration
public class FeignConfig {

	@Bean
	ErrorDecoder errorDecoder() {
		return new CustomerErrorDecoder();
	}

	/** configure logging for specific profiles (stg, prod) */
	@Bean
	//@Profile("!dev")
	public feign.Logger logger() {
		return new FeignLogger();
	}

	/**
	 * The interceptors can perform a variety of implicit tasks, from authentication
	 * to logging, for every HTTP request/response. Ex: Here I added request id into
	 * header of every request for tracing between services
	 */
	@Bean
	public RequestInterceptor requestInterceptor() {
		return template -> template.header(ConstantManager.REQUEST_ID_KEY, MDC.get(ConstantManager.REQUEST_ID_KEY));
	}
}
