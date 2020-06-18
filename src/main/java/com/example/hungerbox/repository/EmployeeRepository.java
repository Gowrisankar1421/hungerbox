package com.example.hungerbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hungerbox.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
