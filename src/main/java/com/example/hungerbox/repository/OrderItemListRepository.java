package com.example.hungerbox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hungerbox.model.Order;
import com.example.hungerbox.model.OrderItemList;

public interface OrderItemListRepository extends JpaRepository<OrderItemList, Long> {

	List<OrderItemList> findOrderItemListByOrder(Order order);

}
