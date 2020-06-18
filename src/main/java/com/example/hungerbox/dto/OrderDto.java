package com.example.hungerbox.dto;

import java.util.List;

public class OrderDto {
	private String phoneNo;
	private  Long employeeId;
	private List<ItemDto> itemDto;
	
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public List<ItemDto> getItemDto() {
		return itemDto;
	}
	public void setItemDto(List<ItemDto> itemDto) {
		this.itemDto = itemDto;
	}
	
}
