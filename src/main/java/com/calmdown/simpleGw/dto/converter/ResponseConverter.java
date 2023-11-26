package com.calmdown.simpleGw.dto.converter;

import com.calmdown.simpleGw.dto.CommonResponse;

public class ResponseConverter {

    public static CommonResponse successResponse(String mobileTrxid, Long limitAmount) {
        return CommonResponse.SuccessBuilder()
                .mobileTrxid(mobileTrxid)
                .limitAmount(limitAmount)
                .build();
    }
}
