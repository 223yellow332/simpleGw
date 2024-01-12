package com.calmdown.simpleGw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "shop")
public class Shop {

	@Column(name = "shop_id")
	@Id
	private String id;
	
	@Column(name = "shop_secret_key")
	private String key;

	@Column
	private String name;
}
