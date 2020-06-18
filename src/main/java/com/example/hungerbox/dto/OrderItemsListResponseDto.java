package com.example.hungerbox.dto;

import java.util.List;

import com.example.hungerbox.model.OrderItemList;

public class OrderItemsListResponseDto {
	
	private Long orderId;
	
	private List<OrderItemList> items;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public List<OrderItemList> getItems() {
		return items;
	}

	public void setItems(List<OrderItemList> items) {
		this.items = items;
	}
	
	

}
