package com.example.hungerbox.dto;

import java.util.List;

import com.example.hungerbox.model.Item;

public class VendorDto {
	private Long vendorId;

	private String vendorName;
	
	private int stallNumber;
	
	private String vendorDescription;
	
	private List<Item> items;

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public int getStallNumber() {
		return stallNumber;
	}

	public void setStallNumber(int stallNumber) {
		this.stallNumber = stallNumber;
	}

	public String getVendorDescription() {
		return vendorDescription;
	}

	public void setVendorDescription(String vendorDescription) {
		this.vendorDescription = vendorDescription;
	}

	
}
