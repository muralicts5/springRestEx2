package com.springdemo.springComplete.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Entity
@Table(name="employeecomplete")
public class Employee {

	public Employee(int employeeId, String name, Date date, float salary, String email, String designation) {
		this.employeeId=employeeId;
		this.name=name;
		this.dob=date;
		this.salary=salary;
		this.email=email;
		this.designation=designation;

		
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Employee() {
		
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Max(value=2000,message="please proper id")
	private int employeeId;
	
	@NotEmpty(message="please enter username")
	@Pattern(regexp="[a-z]{4,6}", message="Please enter 4-6 small case character")
	private String name;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Past(message="please enter proper date of birth")
	@NotNull(message="please enter date of birth")
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	private float salary;
	
	@Email(message="please enter proper email")
	@NotEmpty(message="please enter email")
	private String email;
	
	@NotEmpty
	private String designation;
	
}
