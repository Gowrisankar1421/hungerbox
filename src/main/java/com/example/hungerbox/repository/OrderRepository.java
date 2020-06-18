package com.example.hungerbox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hungerbox.model.Employee;
import com.example.hungerbox.model.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{

	List<Order> findOrderByEmployee(Employee employee);

}
