package com.calmdown.simpleGw.exception.exception;

import com.calmdown.simpleGw.exception.errorCode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException{
	
	private final ErrorCode errorCode;

}
