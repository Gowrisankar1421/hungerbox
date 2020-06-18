package com.example.hungerbox.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hungerbox.dto.OrderDto;
import com.example.hungerbox.dto.ResponseDto;
import com.example.hungerbox.exceptions.EmployeeNotFoundException;
import com.example.hungerbox.exceptions.ItemNotFoundException;
import com.example.hungerbox.exceptions.PaymentNotSuccessfulException;
import com.example.hungerbox.service.OrderService;

/**
 * 
 * @author Sai Kumar
 * @version 1.3
 * this is class for placing order
 *
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
	Logger logger = LoggerFactory.getLogger(OrderController.class);
	 
	@Autowired
	OrderService orderService;
	
	
	/**
	 * 
	 * @param orderDto
	 * @return ResponseDto
	 * @throws EmployeeNotFoundException
	 * @throws ItemNotFoundException
	 * @throws PaymentNotSuccessfulException
	 */
	@PostMapping("/order")
	public ResponseEntity<ResponseDto> placeOrder(@RequestBody OrderDto orderDto) {
		ResponseDto message =orderService.saveOrder(orderDto);
		logger.info("place order in OrderController");
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

}
