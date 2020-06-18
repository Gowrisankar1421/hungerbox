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
import org.springframework.util.Assert;

import com.example.hungerbox.dto.ItemResponseDto;
import com.example.hungerbox.model.Item;
import com.example.hungerbox.service.ItemService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ItemControllerTest {
	
	 @InjectMocks
	 ItemController itemController;
	 
	 @Mock
	 ItemService itemService;
	 
	
	 @SuppressWarnings("deprecation")
	@Test
	    public void TestGetItemByNameForPositive() {
	    	 List<ItemResponseDto> items = new ArrayList<>();
	    	 Item item=new Item();
	    	 item.setItemId(2l);
	    	 item.setName("noodles");
	    	 item.setItemDescription("spicy");
	    	 item.setUnitPrice(200.00);
	    	 item.setItemType("chineese");
	         Mockito.when(itemService.viewItemByName(Mockito.anyString())).thenReturn(items);        
			ResponseEntity<List<ItemResponseDto>> result=itemController.getItemByName("noodles");
			Assert.notNull(result.getBody());
	    }
	 
	 @SuppressWarnings("deprecation")
	@Test
	    public void TestGetItemByNameForNegative(){
	    	 List<ItemResponseDto> items = new ArrayList<>();
	    	 Item item=new Item();
	    	 item.setItemId(-2l);
	    	 item.setName("noodles");
	    	 item.setItemDescription("spicy");
	    	 item.setUnitPrice(200.00);
	    	 item.setItemType("chineese");
	         Mockito.when(itemService.viewItemByName(Mockito.anyString())).thenReturn(items);        
			ResponseEntity<List<ItemResponseDto>> result=itemController.getItemByName("noodles");
			Assert.notNull(result.getBody());
	    }

}
