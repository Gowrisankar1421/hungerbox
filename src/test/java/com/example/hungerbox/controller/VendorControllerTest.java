package com.example.hungerbox.controller;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner.Silent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.hungerbox.dto.VendorDto;
import com.example.hungerbox.model.Item;
import com.example.hungerbox.model.Vendor;
import com.example.hungerbox.service.VendorServiceImpl;

@RunWith(Silent.class)
public class VendorControllerTest {
	@InjectMocks
	VendorController vendorController;
	@Mock
	VendorServiceImpl VendorServiceiml;
	static Vendor vendor = null;
	Item items = new Item();

	@BeforeClass
	public static void setUp() {
		vendor = new Vendor();
	}

	@Test
	public void testSearchVendorName() {
		VendorDto vendorDto = new VendorDto();
		vendorDto.setStallNumber(12);
		vendorDto.setVendorDescription("good");
		vendorDto.setVendorId(12L);
		vendorDto.setVendorName("sam");
		Mockito.when(VendorServiceiml.searchforVendorName("divya")).thenReturn(vendorDto);
		ResponseEntity<VendorDto> b1 = vendorController.searchforVendorName("divya");
		Assert.assertEquals(HttpStatus.OK, b1.getStatusCode());

	}

	@Test
	public void testSearchVendorNameNegtive() {
		VendorDto vendorDto = new VendorDto();
		vendorDto.setStallNumber(12);
		vendorDto.setVendorDescription("good");
		vendorDto.setVendorId(12L);
		vendorDto.setVendorName("sam");
		Mockito.when(VendorServiceiml.searchforVendorName("-sam")).thenReturn(vendorDto);
		ResponseEntity<VendorDto> b1 = vendorController.searchforVendorName("-sam");
		Assert.assertEquals(HttpStatus.OK, b1.getStatusCode());

	}

	public static void tearDown() {
		vendor = null;
	}

}
