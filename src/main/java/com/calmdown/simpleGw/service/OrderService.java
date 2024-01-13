package com.calmdown.simpleGw.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calmdown.simpleGw.domain.Orders;
import com.calmdown.simpleGw.repository.OrderRepository;
import com.calmdown.simpleGw.exception.errorCode.CommonErrorCode;
import com.calmdown.simpleGw.exception.exception.RestApiException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderService {
	
	private final OrderRepository orderRepository;
	
	@Transactional
	public Orders save(Orders orders) {
		return orderRepository.save(orders);
	}
	
	public Orders findById(String id) {
		return orderRepository.findById(id).orElseThrow(() -> new RestApiException(CommonErrorCode.INVALID_PAYMENT_ID));
	}

	public List<Orders> findOrdersByPhone(String phone) {
		return orderRepository.findByPhone(phone);
	}

}
