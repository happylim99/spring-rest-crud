package com.springrestcrud.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrestcrud.model.Employee;
import com.springrestcrud.repository.EmployeeRepository;

@Service
public class EmployeeDAO {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	//List employees
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}
	
	//Show an employee by id
	public Optional<Employee> findById(Long empId) {
		return employeeRepository.findById(empId);
		       	//.orElseThrow(() -> new EntityNotFoundException(id));;
	}
	
	//Store employee
	public Employee store(Employee emp) {
		return employeeRepository.save(emp);
	}
	
	//Update employee
	
	//Delete an employee
	public void delete(Employee emp) {
		employeeRepository.delete(emp);
	}

}
