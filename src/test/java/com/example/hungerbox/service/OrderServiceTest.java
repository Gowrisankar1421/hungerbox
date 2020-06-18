package com.example.hungerbox.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.hungerbox.dto.ItemDto;
import com.example.hungerbox.dto.OrderDto;
import com.example.hungerbox.dto.ResponseDto;
import com.example.hungerbox.exceptions.EmployeeNotFoundException;
import com.example.hungerbox.exceptions.ItemNotFoundException;
import com.example.hungerbox.exceptions.PaymentNotSuccessfulException;
import com.example.hungerbox.model.Employee;
import com.example.hungerbox.model.Item;
import com.example.hungerbox.model.Order;
import com.example.hungerbox.model.OrderItemList;
import com.example.hungerbox.repository.EmployeeRepository;
import com.example.hungerbox.repository.ItemRepository;
import com.example.hungerbox.repository.OrderItemListRepository;
import com.example.hungerbox.repository.OrderRepository;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.Silent.class)
public class OrderServiceTest {
	
	@InjectMocks
	OrderService orderService;
	
	@Mock
	OrderRepository orderRepository;

	@Mock
	OrderItemListRepository orderItemListRepository;

	@Mock
	EmployeeRepository employeeRepository;

	@Mock
	ItemRepository itemRepositroy;
	
	@Mock
	RestTemplate restTemplate;
    @LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	
	ResponseDto responseDto = new ResponseDto();
	Employee employee = new Employee();
	OrderDto orderDto = new OrderDto();

	@Test(expected=NullPointerException.class)
	public void testSaveOrderForPositive() throws Exception {
		List<Item> items = new ArrayList<>();
		Item item = new Item();
		item.setItemId(1L);
		item.setUnitPrice(200.00);
		items.add(item);
		Item item2 = new Item();
		item2.setItemId(1L);
		item2.setUnitPrice(200.00);
		items.add(item2);
		itemRepositroy.saveAll(items);
		Optional<Employee> employee = Optional.of(new Employee());
		employee.get().setEmail("@gmail.com");
		employee.get().setEmployeeId(1L);
		employee.get().setEmployeeName("kumar");
		employee.get().setPassword("yerg");
		employee.get().setPhone("123456789");
		employeeRepository.save(employee.get());
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage("sucessful");
		responseDto.getMessage();
		List<ItemDto> itemDtos  = new ArrayList<>();
		ItemDto itemDto = new ItemDto();
		itemDto.setItemId(1L);
		itemDto.setQuantity(2);
		itemDtos.add(itemDto);
		ItemDto itemDto1 = new ItemDto();
		itemDto1.setItemId(2L);
		itemDto1.setQuantity(2);
		itemDtos.add(itemDto1);
		OrderDto orderDto = new OrderDto();
		orderDto.setEmployeeId(1L);
		orderDto.setPhoneNo("9876543210");
		orderDto.setItemDto(itemDtos);
		Order order = new Order();
		order.setOrderId(1L);
		Mockito.when(employeeRepository.findById(orderDto.getEmployeeId())).thenReturn(employee);

		order.setEmployee(employee.get());
		
		List<Long> itemIds = orderDto.getItemDto().stream().map(m -> m.getItemId()).collect(Collectors.toList());
		List<Integer> quantity = orderDto.getItemDto().stream().map(m -> m.getQuantity()).collect(Collectors.toList());
		double totalPrice = 0.0;
		for (int i = 0; i < itemIds.size(); i++) {
			Optional<Item> item1 = Optional.of(new Item());
			item1.get().setItemId(1L);
			item1.get().setUnitPrice(200.00);
					Mockito.when(itemRepositroy.findById(itemIds.get(i))).thenReturn(item1);
			if(item1.isPresent()) {
				double totalPrice1 = (quantity.get(i) * item1.get().getUnitPrice());
			totalPrice = totalPrice + totalPrice1;
			}
			else {
				throw new ItemNotFoundException("items not found");
			}
		}
		try {
			payment(totalPrice, orderDto.getPhoneNo());
		} catch (Exception e) {
			throw new PaymentNotSuccessfulException("payment was not successfull");
		}
		for (int i = 0; i < itemIds.size(); i++) {
			Optional<Item> item3 = itemRepositroy.findById(itemIds.get(i));
			if(item3.isPresent()) {
			OrderItemList orderItemList = new OrderItemList();
			orderItemList.setOrder(order);
			orderItemList.setQuantity(quantity.get(i));
			orderItemList.setItem(item3.get());
			orderItemListRepository.save(orderItemList);
			}
		}
		order.setOrderPrice(totalPrice);
		orderRepository.save(order);
		ResponseDto d = new ResponseDto();
		d.setMessage("order is placed successfully");
		Mockito.when(orderService.saveOrder(orderDto)).thenReturn(responseDto);
		Assert.assertEquals(d.getMessage(), responseDto.getMessage());
		Assert.assertEquals(order.getEmployee().getEmployeeId(), orderDto.getEmployeeId());
		

	}

	public String payment(Double totalPrice, String phoneNo) {

		String amount = "" + totalPrice;
		String url = "http://BANKING-SERVICE/payment";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		Map<String, String> params = new HashMap<>();
		params.put("phoneNo", phoneNo);
		params.put("toPhoneNo", "9666168535");
		params.put("amount", amount);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		for (Map.Entry<String, String> entry : params.entrySet()) {
			builder.queryParam(entry.getKey(), entry.getValue());
		}

		return restTemplate.getForObject(builder.toUriString(), String.class);

	}

	@Test(expected = EmployeeNotFoundException.class)
	public void testSaveOrderForExceptio() throws Exception {
		employee.setEmail("@gmail.com");
		employee.setEmployeeId(1l);
		employee.setEmployeeName("kumar");
		employee.setPassword("yerg");
		employee.setPhone("123456789");
		responseDto.setMessage("sucessful");
		responseDto.getMessage();
		Order order = new Order();
		order.setOrderId(1l);
		order.setEmployee(employee);
		Mockito.when(orderRepository.save(order)).thenReturn(order);
		ResponseDto responseDto1 = orderService.saveOrder(orderDto);
		Assert.assertNotNull(responseDto1);
	}



}
