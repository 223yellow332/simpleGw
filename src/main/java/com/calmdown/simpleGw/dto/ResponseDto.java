package com.calmdown.simpleGw.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResponseDto {

	public String mobileTrxid;
    public Long limitAmount;
    public String resultCode;
    public String resultMessage;

    @Builder(builderMethodName = "SuccessBuilder")
    ResponseDto (String mobileTrxid, Long limitAmount) {
        this.mobileTrxid = mobileTrxid;
        this.limitAmount = limitAmount;
        this.resultCode = "0";
        this.resultMessage = "성공";
    }

    @Builder
    ResponseDto (String mobileTrxid, Long limitAmount, String resultCode, String resultMessage) {
        this.mobileTrxid = mobileTrxid;
        this.limitAmount = limitAmount;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }
}
