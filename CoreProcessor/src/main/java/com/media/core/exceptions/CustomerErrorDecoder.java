package com.media.core.exceptions;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.media.core.enums.APIStatus;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomerErrorDecoder  implements ErrorDecoder{
	private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
	
	private final ErrorDecoder defaultErrorDecoder = new Default();

	@Override
	public Exception decode(String methodKey, Response response) {
		try {
			if (response.body() != null) {
				ErrorDetail erorRespose = objectMapper.readValue(response.body().asInputStream(), ErrorDetail.class);
				return new ValidateException(erorRespose.getCode(), erorRespose.getMessage());
			}
			return defaultErrorDecoder.decode(methodKey, response);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ValidateException(APIStatus.INTERNAL_SERVER_ERROR);
		} 
	}

}
