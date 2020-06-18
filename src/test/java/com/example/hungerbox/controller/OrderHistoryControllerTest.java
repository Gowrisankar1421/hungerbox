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

import com.example.hungerbox.dto.OrderItemsListResponseDto;
import com.example.hungerbox.service.OrderHistoryService;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.Silent.class)
public class OrderHistoryControllerTest {
	
	@InjectMocks
	OrderHistoryController orderHistoryController;
	
	@Mock
	OrderHistoryService orderHistoryService;
	
	@Test
	public void testShowHistory() {
		List<OrderItemsListResponseDto> responseOrders = new ArrayList<>();
		Mockito.when(orderHistoryService.showTransaction(1L)).thenReturn(responseOrders);
		Assert.assertNotNull(responseOrders);
		ResponseEntity<List<OrderItemsListResponseDto>> response = orderHistoryController.showHistory(1L);
		Assert.assertNotNull(response.getBody());
	}

}
