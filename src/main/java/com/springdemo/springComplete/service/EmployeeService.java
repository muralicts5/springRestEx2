package com.springdemo.springComplete.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.springdemo.springComplete.dao.EmployeeDao;
import com.springdemo.springComplete.entity.Employee;
import com.springdemo.springComplete.exceptions.EmployeeNotFoundException;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	public List<Employee> getEmploees(){
		return (List<Employee>) employeeDao.findAll();
	}
	
	public Employee insertEmployee(Employee employee){
		return employeeDao.save(employee);
	}

	
	public Employee updateEmployee(Employee employee){
		return employeeDao.save(employee);
	}
	
	public void deleteEmployee(int employeeId){
		 employeeDao.deleteById(employeeId);
	}
	
	public Employee getEmployeeById(int id){
		Optional option=employeeDao.findById(id);
		if(option.isPresent()){
			return (Employee) option.get();
		}else
			throw new EmployeeNotFoundException("id is not found");
	}
	
	public List<Employee> getEmployeeByName(String name){
		Optional option=employeeDao.findByName(name);
		if(option.isPresent()){
			return (List<Employee>) option.get();
		}else
			throw new EmployeeNotFoundException("id is not found");
	}
	
	
}
