package com.calmdown.simpleGw.domain;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


public enum MobileCarrier {
	SK ("SK"), 
	KT ("KT"), 
	LGU ("LGU")
	;
	
    private final String value;
    
    @JsonValue
    public String getValue() {
        return value;
    }
	
	MobileCarrier(String value) {
		this.value = value;
	}
	
	// Json으로 변환할때 사용되는 함수
	// 대소문자 구분을 하지 않고 값을 받기위한 함수 추가
	@JsonCreator
    public static MobileCarrier from(String value) {

		return Stream.of(MobileCarrier.values())
		.filter(code -> code.value.equals(value.toUpperCase()))
		.findFirst()
		.orElse(null)
//		.orElseThrow(() -> new RestApiException(CommonErrorCode.INVALID_MOBILE_CARRIER_CODE))
		;
    }
	
	public static String getPattern() {
		return Stream.of(MobileCarrier.values())
		.map(MobileCarrier::getValue)
		.collect(Collectors.joining("|"))
		;
		
	}
    

}
