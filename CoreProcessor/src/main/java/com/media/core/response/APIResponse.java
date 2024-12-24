package com.media.core.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.media.core.enums.APIStatus;
import com.media.core.utils.DateUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class APIResponse<T extends Object> implements Serializable {
	
	private static final long serialVersionUID = -2949637537989916289L;
	
	private T data;
	private String message;
	private int code;
	@JsonFormat(shape = Shape.STRING, pattern = DateUtils.PATTERN_DATE_TIME_FORMAT)
	private LocalDateTime time = LocalDateTime.now();
	
	public APIResponse(APIStatus apiStatus, T data) {
		if(apiStatus == null) {
			throw new IllegalArgumentException("APIStatus must not be null");
		}
		this.data = data;
		this.message = apiStatus.getMessage();
		this.code = apiStatus.getCode();
	}
	
}

