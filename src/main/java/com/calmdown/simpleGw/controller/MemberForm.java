package com.calmdown.simpleGw.controller;

import com.calmdown.simpleGw.domain.MobileCarrier;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
@ToString
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String name;
    private String phone;
    private MobileCarrier mobileCarrier;
    private String socialNumber;
    private String gender;
}
