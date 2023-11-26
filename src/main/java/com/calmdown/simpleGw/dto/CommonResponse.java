package com.calmdown.simpleGw.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResponse {

	public String mobileTrxid;
    //휴대전화번호
    public Long limitAmount;

    //통신사
    public String resultCode;
    public String resultMessage;
    
    @Builder(builderMethodName = "SuccessBuilder")
    CommonResponse(String mobileTrxid, Long limitAmount) {
    	this.mobileTrxid = mobileTrxid;
    	this.limitAmount = limitAmount;
    	this.resultCode = "0";
    	this.resultMessage = "성공";
    }

    @Builder(builderMethodName = "SuccessBuilder")
    CommonResponse(String mobileTrxid, Long limitAmount, String resultCode, String resultMessage) {
        this.mobileTrxid = mobileTrxid;
        this.limitAmount = limitAmount;
        this.resultCode = "0";
        this.resultMessage = "성공";
    }
}
