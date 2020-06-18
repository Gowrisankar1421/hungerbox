	package com.example.hungerbox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hungerbox.model.Item;
import com.example.hungerbox.model.Vendor;

public interface ItemRepository extends JpaRepository<Item, Long> {
	List<Item> findItemByNameLike(String string);
	List<Item> findItemByVendor(Vendor vendor1);
}
