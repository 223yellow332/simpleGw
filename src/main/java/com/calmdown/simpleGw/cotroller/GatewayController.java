package com.calmdown.simpleGw.cotroller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.calmdown.simpleGw.dto.CancelRequest;
import com.calmdown.simpleGw.exception.errorCode.CommonErrorCode;
import com.calmdown.simpleGw.exception.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calmdown.simpleGw.domain.Order;
import com.calmdown.simpleGw.dto.AuthRequest;
import com.calmdown.simpleGw.dto.CertRequest;
import com.calmdown.simpleGw.dto.ResponseDto;
import com.calmdown.simpleGw.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/pay")
public class GatewayController {
	
	static final int LIMIT_AMOUNT = 300000;
	
	private final OrderService orderService;
	
	@PostMapping("/cert")
	public ResponseEntity<ResponseDto> cert (@Validated @RequestBody CertRequest request) throws Exception {
		log.info("request.toString = {" + request.toString() + "}");
		
		String trxid = getTrxId(request.getMobileCarrier().toString());

		long limitAmount = Long.valueOf(LIMIT_AMOUNT - request.getPayAmount());
		if(limitAmount < 0)
			throw new UserException(CommonErrorCode.LIMIT_AMOUNT_ERROR);

		Order order = request.toEntity(trxid, limitAmount);
		
		orderService.save(order);
		
		ResponseDto respMsg = ResponseDto.SuccessBuilder()
				.mobileTrxid(trxid)
				.limitAmount(Long.valueOf(LIMIT_AMOUNT - request.getPayAmount()))
				.build();
		
		return new ResponseEntity<ResponseDto>(respMsg, HttpStatus.OK);
	}
	
	@PostMapping("/auth")
	public ResponseEntity<ResponseDto> auth (@RequestBody AuthRequest request) {
		log.info("request.toString = {" + request.toString() + "}");
		Order order = orderService.findById(request.getMobileTrxid());

		if(order.getLimitAmount() < 0)
			throw new UserException(CommonErrorCode.LIMIT_AMOUNT_ERROR);

		ResponseDto respMsg = getSuccessResponse(request.getMobileTrxid(), order.getLimitAmount());
		
		return new ResponseEntity<ResponseDto>(respMsg, HttpStatus.OK);
	}
	
	@PostMapping("/cancel")
	public ResponseEntity<ResponseDto> cancel (@RequestBody CancelRequest request) {
		log.info("request.toString = {" + request.toString() + "}");
		Order order = orderService.findById(request.getMobileTrxid());

		if(order.getLimitAmount() < 0)
			throw new UserException(CommonErrorCode.LIMIT_AMOUNT_ERROR);
		
		ResponseDto respMsg = getSuccessResponse(request.getMobileTrxid(), order.getLimitAmount());
		
		return new ResponseEntity<ResponseDto>(respMsg, HttpStatus.OK);
	}

	private ResponseDto getSuccessResponse(String trxid, long limitAmount) {
		return ResponseDto.SuccessBuilder()
				.mobileTrxid(trxid)
				.limitAmount(limitAmount)
				.build();
	}
	
	public String getTrxId(String mobile){
		String dateStr = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
		return "TEST_" + mobile + dateStr + getRanStr();
	}
	
	public String getRanStr() {
		int ranNum = (int) (Math.random() * 1000000);
		String tmpNum = Integer.toString(ranNum);
		int len = tmpNum.length();
		
		for(int i = len; i < 6; i++) {
			tmpNum += "0";
		}
		
		return tmpNum;
	}

}
