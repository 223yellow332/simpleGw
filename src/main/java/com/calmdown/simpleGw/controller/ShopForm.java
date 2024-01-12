package com.calmdown.simpleGw.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
@ToString
public class ShopForm {

    @NotEmpty(message = "가맹점 이름은 필수 입니다")
    private String id;
    private String name;
}
