package com.calmdown.simpleGw.domain;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String phone;

    @Column
    @Enumerated(EnumType.STRING)
    private MobileCarrier mobileCarrier;

    @Column
    private String socialNumber;

    @Column
    private String gender;

    @Column
    private Long limitAmount;

    @Builder
    public Member(Long id, String name, String phone, MobileCarrier mobileCarrier, String socialNumber, String gender, Long limitAmount) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.mobileCarrier = mobileCarrier;
        this.socialNumber = socialNumber;
        this.gender = gender;
        this.limitAmount = limitAmount;
    }
}
