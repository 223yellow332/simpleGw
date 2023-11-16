package com.calmdown.simpleGw.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.calmdown.simpleGw.domain.MobileCarrier;
import com.calmdown.simpleGw.domain.Order;
import com.calmdown.simpleGw.util.EnumPattern;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CertRequest {

    //휴대전화번호
	@NotBlank(message = "전화번호 입력은 필수입니다.")
	@Pattern(regexp = "^[0-9]*$", message = "전화번호 형식이 잘못되었습니다.")
	@Size(max = 11)
    private String phone;

    //통신사
	@EnumPattern(regexp = "SK|KT|LG", message = "통신사 형식이 잘못되었습니다.")
    private MobileCarrier mobileCarrier;
    
    @Positive(message = "결제금액은 1원 이상부터 가능합니다.")
    @Max(1000000)
    private Integer payAmount;
    
    @Builder
    public CertRequest(String phone, MobileCarrier mobileCarrier, int payAmount) {
    	this.phone = phone;
    	this.mobileCarrier = mobileCarrier;
    	this.payAmount = payAmount;
    }
    
    public Order toEntity(String trxId, Long limitAmount) {
    	return Order.builder()
                .id(trxId)
                .phone(phone)
                .mobileCarrier(mobileCarrier)
                .amount(payAmount)
                .limitAmount(limitAmount)
                .build();
    }
}
