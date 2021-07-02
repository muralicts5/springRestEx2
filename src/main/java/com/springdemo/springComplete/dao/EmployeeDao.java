package com.springdemo.springComplete.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.springdemo.springComplete.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {

	Optional<List<Employee>> findByName(String name);
	
}
