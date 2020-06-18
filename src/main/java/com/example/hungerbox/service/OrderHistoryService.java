package com.example.hungerbox.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hungerbox.controller.OrderController;
import com.example.hungerbox.dto.OrderItemsListResponseDto;
import com.example.hungerbox.exceptions.EmployeeNotFoundException;
import com.example.hungerbox.model.Employee;
import com.example.hungerbox.model.Order;
import com.example.hungerbox.model.OrderItemList;
import com.example.hungerbox.repository.EmployeeRepository;
import com.example.hungerbox.repository.OrderItemListRepository;
import com.example.hungerbox.repository.OrderRepository;


/**
 * 
 * @author Gowri Sankar
 *@version 1.4
 * this is  service class for order history 
 */
@Service
public class OrderHistoryService {
	
	Logger logger = LoggerFactory.getLogger(OrderHistoryService.class);
	
	@Autowired
	OrderItemListRepository orderItemListRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	/**
	 * 
	 * @param employeeId
	 * @return List<OrderItemsListResponseDto>
	 */
	
	public List<OrderItemsListResponseDto> showTransaction(long employeeId){
		
		logger.info("<------------------<inside show transaction service method>----------------------->");
		List<Order> order=lastFiveTransactions(employeeId);
		List<OrderItemsListResponseDto> resultOrderList = new ArrayList<>();
		for(int i=0;i<order.size();i++) {
			
			OrderItemsListResponseDto orderDto = new OrderItemsListResponseDto();
			logger.debug("<----------<getting list of OrderItemList>------------->");
			List<OrderItemList> orderList = orderItemListRepository.findOrderItemListByOrder(order.get(i));
			orderDto.setOrderId(order.get(i).getOrderId());
			 orderDto.setItems(orderList); 
			 resultOrderList.add(orderDto);
			 logger.info("<--------<show transaction service method completed>-------->");
		} 
		return resultOrderList;
	}
	
	public List<Order> lastFiveTransactions(long employeeId){
		logger.debug("<----------------<find employee by id or else throw EmployeeNotFoundException>----------------->");
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new EmployeeNotFoundException("employee with given id not found"));
		List<Order> order = orderRepository.findOrderByEmployee(employee);
		Collections.reverse(order);
		return order.stream().limit(5).collect(Collectors.toList());
		
	}
}
