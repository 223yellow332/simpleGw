package com.calmdown.simpleGw.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calmdown.simpleGw.domain.Shop;
import com.calmdown.simpleGw.repository.ShopRepository;
import com.calmdown.simpleGw.exception.errorCode.CommonErrorCode;
import com.calmdown.simpleGw.exception.exception.RestApiException;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

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

	public List<Shop> findShops() {
		return shopRepository.findAll();
	}

	@Transactional
	public String join(Shop shop) {
		validateDuplicateMember(shop);
		shopRepository.save(shop);
		return shop.getId();

	}

	private void validateDuplicateMember(Shop shop) {
		Optional<Shop> findShops = shopRepository.findById(shop.getId());
		if(!findShops.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 가맹점입니다");
		}
	}

}
