package com.media.core.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.media.core.enums.APIStatus;
import com.media.core.response.APIResponse;

public class ResponseUtils {
	private ResponseUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static ResponseEntity<APIResponse> responseSuccess(Object data) {
		return buildResponse(APIStatus.OK, data, HttpStatus.OK);
	}

	public static ResponseEntity<APIResponse> buildResponse(APIStatus apiStatus, Object data, HttpStatus httpStatus) {
		return new ResponseEntity<>(new APIResponse<Object>(apiStatus, data), httpStatus);
	}

}
