package com.calmdown.simpleGw.dto;

import com.calmdown.simpleGw.util.EnumPattern;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CancelRequest {

    @NotBlank(message = "거래번호 입력은 필수입니다.")
	public String mobileTrxid;

    //휴대전화번호
    @NotBlank(message = "전화번호 입력은 필수입니다.")
    @Pattern(regexp = "^[0-9]*$", message = "전화번호 형식이 잘못되었습니다.")
    @Size(max = 11)
    public String phone;

    //통신사
    @EnumPattern(regexp = "SK|KT|LG", message = "통신사 형식이 잘못되었습니다.")
    public String mobileCarrier;

    @Positive(message = "취소금액은 1원 이상부터 가능합니다.")
    @Max(1000000)
    public int payAmount;

    @Builder
    public CancelRequest(String mobileTrxid, String phone, String mobileCarrier, int payAmount) {
    	this.mobileTrxid = mobileTrxid;
    	this.phone = phone;
    	this.mobileCarrier = mobileCarrier;
    	this.payAmount = payAmount;
    }

}
