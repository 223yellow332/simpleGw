package com.calmdown.simpleGw.domain;

import javax.persistence.*;

import lombok.*;


@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "orders")
public class Orders {

	@Id
	@Column(name = "orders_id")
	private String id;
	
	@Column
	private String phone;
	
	@Column
	@Enumerated(EnumType.STRING)
	private MobileCarrier mobileCarrier;
	
	@Column
	private int amount;
	
	@Column
	private int limitAmount;

	@Builder
	public Orders(String id, String phone, MobileCarrier mobileCarrier, int amount, int limitAmount) {
		this.id = id;
		this.phone = phone;
		this.mobileCarrier = mobileCarrier;
		this.amount = amount;
		this.limitAmount = limitAmount;
	}
}
