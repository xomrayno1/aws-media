package com.media.core.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.media.core.enums.APIStatus;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ValidateException.class)
	private ResponseEntity<ErrorDetail> handleValidateException(ValidateException exception, WebRequest request){
		ErrorDetail error = new ErrorDetail(exception.getCode(), exception.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}
	
//	@ExceptionHandler(KafkaProducerException.class)
//	private ResponseEntity<ErrorDetail> handleKafkaProducerException(KafkaProducerException exception, WebRequest request){
//		ErrorDetail error = new ErrorDetail(500, exception.getMessage(), LocalDateTime.now());
//		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	@ExceptionHandler(Exception.class)
	private ResponseEntity<ErrorDetail> handleCommonException(Exception exception, WebRequest request){
		ErrorDetail error = new ErrorDetail(APIStatus.ERR_SYSTEM_COMMON.getCode(), APIStatus.ERR_SYSTEM_COMMON.getMessage(), LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
 
	 
}
