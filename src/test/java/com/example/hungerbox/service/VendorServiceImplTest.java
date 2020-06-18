package com.example.hungerbox.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoJUnitRunner.Silent;
import org.springframework.beans.BeanUtils;

import com.example.hungerbox.dto.VendorDto;
import com.example.hungerbox.exceptions.VendorNotFoundException;
import com.example.hungerbox.model.Item;
import com.example.hungerbox.model.Vendor;
import com.example.hungerbox.repository.ItemRepository;
import com.example.hungerbox.repository.VendorRepository;
@RunWith(MockitoJUnitRunner.Silent.class)
public class VendorServiceImplTest {
	@InjectMocks
	VendorServiceImpl vendorServiceImpl;
	@Mock
	VendorRepository vendorRepositroy;
	@Mock
	ItemRepository itemRepositroy;
	static Vendor vendor = null;
	Item items = new Item();

	@BeforeClass
	public static void setUp() {
		vendor = new Vendor();
	}
	@Test(expected = VendorNotFoundException.class)
	public void testSearchforVendorName() {
		VendorDto vendorDto = new VendorDto();
		
		vendorDto.setStallNumber(12);
		vendorDto.setVendorDescription("good");
		vendorDto.setVendorId(12L);
		vendorDto.setVendorName("divya");
		Vendor vendor1 = new Vendor();
		List<Item> items=itemRepositroy.findItemByVendor(vendor1);
		vendorDto.setItems(items);
		Optional<Vendor> vendordto1=vendorRepositroy.findByVendorName("divya");
		Mockito.when(vendorRepositroy.findByVendorName("divya")).thenReturn(vendordto1);
		vendorServiceImpl.searchforVendorName("divya");
		Assert.assertEquals("divya", vendorDto.getVendorName());

		
	}
	@Test
	public void testSearchforVendorNamePov() {
		VendorDto vendordto = new VendorDto();
		Vendor vendor1 = new Vendor();
		Optional<Vendor> vendor = Optional.of(new Vendor());
				Mockito.when(vendorRepositroy.findByVendorName("divya")).thenReturn(vendor);
		if (vendor.isPresent()) {
			BeanUtils.copyProperties(vendor1, vendordto);
			vendor1 = vendor.get();
			vendordto.setStallNumber(vendor1.getStallNumber());
			vendordto.setVendorDescription(vendor1.getVendorDescription());
			vendordto.setVendorId(vendor1.getVendorId());
			vendordto.setVendorName(vendor1.getVendorName());
			List<Item> items= new ArrayList<>();
					Mockito.when(itemRepositroy.findItemByVendor(vendor1)).thenReturn(items);
			vendordto.setItems(items);
			VendorDto vendordto1 = vendorServiceImpl.searchforVendorName("divya");
			Assert.assertEquals(vendordto.getVendorName(), vendordto1.getVendorName());
		}
	}
}
