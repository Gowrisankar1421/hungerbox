package com.example.hungerbox.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.hungerbox.dto.ItemResponseDto;
import com.example.hungerbox.exceptions.ItemNotFoundException;
import com.example.hungerbox.model.Item;
import com.example.hungerbox.repository.ItemRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ItemServiceTest {
	
	@InjectMocks
	ItemService itemService;
	
	@Mock
	ItemRepository itemRepository;
	
	@Test
    public void TestviewItemByNameForPositive() throws ItemNotFoundException {
        
		List<Item> items =new ArrayList<>();
		Item item = new Item();
		item.setItemId(1l);
		item.setItemType("southIndian");
		item.setName("chicken");
        item.setUnitPrice(200.00);
        item.setItemDescription("spicy");
    
		items.add(item); 
        
        Mockito.when(itemRepository.findItemByNameLike(Mockito.anyString())).thenReturn(items);
        List<ItemResponseDto> result=itemService.viewItemByName(item.getName());
        assertNotNull(result);
        
    }

	@Test
    public void TestviewItemByNameForNegative() throws ItemNotFoundException  {
        
		List<Item> items =new ArrayList<>();
		Item item = new Item();
		item.setItemId(-1l);
		item.setItemType("southIndian");
		item.setName("chicken");
        item.setUnitPrice(200.00);
        item.setItemDescription("spicy");
    
		items.add(item); 
        
        Mockito.when(itemRepository.findItemByNameLike(Mockito.anyString())).thenReturn(items);
        List<ItemResponseDto> result=itemService.viewItemByName(item.getName());
        assertNotNull(result);
        
    }

}
