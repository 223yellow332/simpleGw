package com.calmdown.simpleGw.controller;

import com.calmdown.simpleGw.domain.MobileCarrier;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentForm {

    private String phone;
    private MobileCarrier mobileCarrier;
    private Integer payAmount ;
}
