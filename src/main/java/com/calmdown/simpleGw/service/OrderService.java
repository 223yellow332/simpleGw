package com.calmdown.simpleGw.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calmdown.simpleGw.domain.Order;
import com.calmdown.simpleGw.domain.OrderRepository;
import com.calmdown.simpleGw.exception.errorCode.CommonErrorCode;
import com.calmdown.simpleGw.exception.exception.UserException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderService {
	
	private final OrderRepository orderRepository;
	
	@Transactional
	public void save(Order order) {
		log.info("Insert into Orders => " + order.toString());
		orderRepository.save(order);
	}
	
	public Order findById(String id) {
		return orderRepository.findById(id).orElseThrow(() -> new UserException(CommonErrorCode.INVALID_PAYMENT_ID));
	}

}
