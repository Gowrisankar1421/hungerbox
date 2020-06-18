package com.example.hungerbox.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Vendor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long vendorId;

	private String vendorName;
	
	private int stallNumber;
	
	private String vendorDescription;

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

	/*
	 * @OneToMany(mappedBy= "category", cascade = CascadeType.ALL, fetch =
	 * FetchType.LAZY)
	 * 
	 * @JsonIgnore private List<Item> itemList;
	 */
	
}
