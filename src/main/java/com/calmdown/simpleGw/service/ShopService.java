package com.calmdown.simpleGw.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calmdown.simpleGw.domain.Shop;
import com.calmdown.simpleGw.domain.ShopRepository;
import com.calmdown.simpleGw.exception.errorCode.CommonErrorCode;
import com.calmdown.simpleGw.exception.exception.RestApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ShopService {
	
	private final ShopRepository shopRepository;
	
	public Shop findById(String id) {
		return shopRepository.findById(id).orElseThrow(() -> new RestApiException(CommonErrorCode.INVALID_PAYMENT_ID));
	}
	
	public String getShopKey(String id) {
		Shop shop = shopRepository.findById(id)
				.orElseThrow(() -> new RestApiException(CommonErrorCode.INVALID_PAYMENT_ID));
		return shop.getKey()==null ? "" : shop.getKey();
		
	}

}
