package com.example.hungerbox.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hungerbox.dto.ItemResponseDto;
import com.example.hungerbox.exceptions.ItemNotFoundException;
import com.example.hungerbox.model.Item;
import com.example.hungerbox.repository.ItemRepository;

/**
 * 
 * @author sowjanya
 * this is class for item service
 *
 */

@Service
public class ItemService {
	
	Logger logger = LoggerFactory.getLogger(ItemService.class);
	
	@Autowired
	ItemRepository itemRepository;
	
	public List<ItemResponseDto> viewItemByName(String name) {
		List<Item> items = itemRepository.findItemByNameLike("%" + name + "%");
			 if(items.isEmpty()) {
				 throw new ItemNotFoundException("item not found with the given name");
			 } 
			 List<ItemResponseDto> itemsDto = new ArrayList<>();
			 for(int i = 0;i<items.size();i++) {
				 ItemResponseDto itemResponse = new ItemResponseDto();
				 Item item = items.get(i);
				 BeanUtils.copyProperties(item, itemResponse);
				 itemsDto.add(itemResponse);
			 }
			 
			 return itemsDto;
		}



}
