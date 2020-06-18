package com.example.hungerbox.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.example.hungerbox.dto.ItemDto;
import com.example.hungerbox.dto.OrderDto;
import com.example.hungerbox.dto.ResponseDto;
import com.example.hungerbox.service.OrderService;

import junit.framework.Assert;


@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.Silent.class)
public class OrderControllerTest {
	
	
	
	    @InjectMocks
	    OrderController orderController;
	    @Mock
	    OrderService orderService;
	    
	    ResponseDto responseDto = new ResponseDto();
	    OrderDto orderDto=new OrderDto();
	    ItemDto itemDto=new ItemDto();
	    List<ItemDto> itemDtoList=new ArrayList<ItemDto>();
		@Test
	    public void testCreateUserForNegative() throws Exception {
	    	itemDto.setItemId(1l);
	    	itemDto.setQuantity(2);
	    	itemDtoList.add(itemDto);
	    	itemDto.setItemId(2l);
	    	itemDto.setQuantity(3);
	    	itemDtoList.add(itemDto);
	    	orderDto.setEmployeeId(1l);
	    	responseDto.setMessage("sucessful");
	    	responseDto.getMessage();
	        Mockito.when(orderService.saveOrder(orderDto)).thenReturn(responseDto);
	        ResponseEntity<ResponseDto> responseDto1 = orderController.placeOrder(orderDto);
	        Assert.assertNotNull(responseDto1);
	    }
	    
		@Test
	    public void testCreateUserForPositive() throws Exception {
	    	itemDto.setItemId(1l);
	    	itemDto.setQuantity(2);
	    	itemDtoList.add(itemDto);
	    	itemDto.setItemId(2l);
	    	itemDto.setQuantity(3);
	    	itemDtoList.add(itemDto);
	    	orderDto.setEmployeeId(1l);
	    	responseDto.setMessage("sucessful");
	    	responseDto.getMessage();
	        Mockito.when(orderService.saveOrder(Mockito.any(OrderDto.class))).thenReturn(responseDto);
	        ResponseEntity<ResponseDto> responseDto1 = orderController.placeOrder(orderDto);
	        Assert.assertNotNull(responseDto1);
	    }
	    
		@Test(expected = NullPointerException.class)
	    public void testCreateUserForException() throws Exception {
	    	
	        Mockito.when(orderService.saveOrder(Mockito.any(OrderDto.class))).thenThrow(NullPointerException.class);
	        ResponseEntity<ResponseDto> responseDto1 = orderController.placeOrder(orderDto);
	        Assert.assertNotNull(responseDto1);
	    }
}
