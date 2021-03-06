package com.example.hungerbox.dto;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;

import com.example.hungerbox.model.Vendor;

public class ItemResponseDto {
	private Long itemId;
	private String name;
	private String itemType;
	private String itemDescription;
	private Double unitPrice;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	private Vendor vendor;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	
}
