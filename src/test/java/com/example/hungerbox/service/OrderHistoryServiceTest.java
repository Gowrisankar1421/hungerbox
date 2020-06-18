package com.example.hungerbox.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.hungerbox.dto.OrderItemsListResponseDto;
import com.example.hungerbox.exceptions.EmployeeNotFoundException;
import com.example.hungerbox.model.Employee;
import com.example.hungerbox.model.Order;
import com.example.hungerbox.model.OrderItemList;
import com.example.hungerbox.repository.EmployeeRepository;
import com.example.hungerbox.repository.OrderItemListRepository;
import com.example.hungerbox.repository.OrderRepository;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.Silent.class)
public class OrderHistoryServiceTest {
	
	@InjectMocks
	OrderHistoryService orderHistoryService;
	
	@Mock
	OrderItemListRepository orderItemListRepository;
	
	@Mock
	OrderRepository orderRepository;
	
	@Mock
	EmployeeRepository employeeRepository;
	
	@Test
	public void testShowTransaction() {
		long employeeId=1L;
		List<Order> order=lastFiveTransactions(employeeId);
		List<OrderItemsListResponseDto> resultOrderList = new ArrayList<>();
		for(int i=0;i<order.size();i++) {
			
			OrderItemsListResponseDto orderDto = new OrderItemsListResponseDto();
			List<OrderItemList> orderList = new ArrayList<>();
			Mockito.when(orderItemListRepository.findOrderItemListByOrder(order.get(i))).thenReturn(orderList);
			orderDto.setOrderId(order.get(i).getOrderId());
			 orderDto.setItems(orderList); 
			 resultOrderList.add(orderDto);
			 Assert.assertNotNull(orderList);
			 resultOrderList=orderHistoryService.showTransaction(employeeId);
			 Assert.assertNotNull(resultOrderList);
		} 
	}
	
	@Test(expected=EmployeeNotFoundException.class)
	public void testShowTransactionforExcep() {
		long employeeId=1L;
		List<Order> order=lastFiveTransactions1(employeeId);
		List<OrderItemsListResponseDto> resultOrderList = new ArrayList<>();
		for(int i=0;i<order.size();i++) {
			
			OrderItemsListResponseDto orderDto = new OrderItemsListResponseDto();
			List<OrderItemList> orderList = new ArrayList<>();
			Mockito.when(orderItemListRepository.findOrderItemListByOrder(order.get(i))).thenReturn(orderList);
			orderDto.setOrderId(order.get(i).getOrderId());
			 orderDto.setItems(orderList); 
			 resultOrderList.add(orderDto);
			 Assert.assertNotNull(orderList);
			 resultOrderList=orderHistoryService.showTransaction(employeeId);
			 Assert.assertNotNull(resultOrderList);
		} 
	}
	
	private List<Order> lastFiveTransactions1(long employeeId) {
		long employeeId1=1L;
		Employee employee = new Employee();
		employee.setEmail("gs@gmail.com");
		employee.setEmployeeId(1L);
		employee.setEmployeeName("gs");
		employee.setPassword("pwd");
		employee.setPhone("9876543210");
		employeeRepository.save(employee);
		Mockito.when(employeeRepository.findById(employeeId1).orElseThrow(()->new EmployeeNotFoundException("employee with given id not found"))).thenReturn(employee);
		Assert.assertNotNull(employee);
		List<Order> orders = new ArrayList<>();
		Order order=new Order();
		order.setEmployee(employee);
		order.setOrderId(1L);
		order.setOrderPrice(20000.00);
		orders.add(order);
		Mockito.when(orderRepository.findOrderByEmployee(employee)).thenReturn(orders);
		Assert.assertNotNull(orders);
		Collections.reverse(orders);
		return orders.stream().limit(5).collect(Collectors.toList());
	}

	
	private List<Order> lastFiveTransactions(long employeeId) {
		long employeeId1=1L;
		Optional<Employee> employee = Optional.of(new Employee());
		employee.get().setEmail("gs@gmail.com");
		employee.get().setEmployeeId(1L);
		employee.get().setEmployeeName("gs");
		employee.get().setPassword("pwd");
		employee.get().setPhone("9876543210");
		employeeRepository.save(employee.get());
		Mockito.when(employeeRepository.findById(employeeId1)).thenReturn(employee);
		Assert.assertNotNull(employee);
		List<Order> orders = new ArrayList<>();
		Order order=new Order();
		order.setEmployee(employee.get());
		order.setOrderId(1L);
		order.setOrderPrice(20000.00);
		orders.add(order);
		Mockito.when(orderRepository.findOrderByEmployee(employee.get())).thenReturn(orders);
		Assert.assertNotNull(orders);
		Collections.reverse(orders);
		return orders.stream().limit(5).collect(Collectors.toList());
	}

	@Test(expected=EmployeeNotFoundException.class)
	public void testLastFiveTransactions() {
		long employeeId1=1L;
		Employee employee = new Employee();
		employee.setEmail("gs@gmail.com");
		employee.setEmployeeId(1L);
		employee.setEmployeeName("gs");
		employee.setPassword("pwd");
		employee.setPhone("9876543210");
		employeeRepository.save(employee);
		Mockito.when(employeeRepository.findById(employeeId1).orElseThrow(()->new EmployeeNotFoundException("employee with given id not found"))).thenReturn(employee);
		Assert.assertNotNull(employee);
		List<Order> order = new ArrayList<>();
		Mockito.when(orderRepository.findOrderByEmployee(employee)).thenReturn(order);
		Assert.assertNotNull(order);
		Collections.reverse(order);
		
	}

}
