package com.calmdown.simpleGw.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.calmdown.simpleGw.dto.CancelRequest;
import com.calmdown.simpleGw.dto.converter.ResponseConverter;
import com.calmdown.simpleGw.util.CommonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calmdown.simpleGw.domain.Orders;
import com.calmdown.simpleGw.dto.AuthRequest;
import com.calmdown.simpleGw.dto.CertRequest;
import com.calmdown.simpleGw.dto.CommonResponse;
import com.calmdown.simpleGw.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/pay")
public class GatewayController {
	
	static final int LIMIT_AMOUNT = 300000;
	
	private final OrderService orderService;

	/*
	 * 인증
	 */
	@PostMapping("/cert")
	public ResponseEntity<CommonResponse> cert (@Validated @RequestBody CertRequest request) throws Exception {

		String trxid = CommonUtil.getTrxId(request.getMobileCarrier().toString());
		Long limitAmount = (long) (LIMIT_AMOUNT - request.getPayAmount());

		Orders orders = orderService.save(request.toEntity(trxid, limitAmount));
		log.info("save order = {}", orders.toString());

		CommonResponse respMsg = ResponseConverter.successResponse(orders.getId(), limitAmount);
		
		return new ResponseEntity<CommonResponse>(respMsg, HttpStatus.OK);
	}

	/*
	 * 결제
	 */
	@PostMapping("/auth")
	public ResponseEntity<CommonResponse> auth (@Validated @RequestBody AuthRequest request) {

		Orders orders = orderService.findById(request.getMobileTrxid());
		log.info("find order = {}", orders.toString());

		CommonResponse respMsg = ResponseConverter.successResponse(orders.getId(), Long.valueOf(orders.getLimitAmount()));
		
		return new ResponseEntity<CommonResponse>(respMsg, HttpStatus.OK);
	}

	/*
	 * 취소
	 */
	@PostMapping("/cancel")
	public ResponseEntity<CommonResponse> cancel (@Validated @RequestBody CancelRequest requestDto) {
		
		Orders orders = orderService.findById(requestDto.getMobileTrxid());
		log.info("find order = {}", orders.toString());

		CommonResponse respMsg = ResponseConverter.successResponse(orders.getId(), Long.valueOf(orders.getLimitAmount()));
		
		return new ResponseEntity<CommonResponse>(respMsg, HttpStatus.OK);
	}

}
