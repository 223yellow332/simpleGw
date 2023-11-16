package com.calmdown.simpleGw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {

	@Id
	private String id;
	
	@Column
	private String phone;
	
	@Column
	private MobileCarrier mobileCarrier;
	
	@Column
	private int amount;
	
	@Column
	private Long limitAmount;
}
