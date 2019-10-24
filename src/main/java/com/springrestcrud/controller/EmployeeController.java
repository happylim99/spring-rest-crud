package com.springrestcrud.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrestcrud.dao.EmployeeDAO;
import com.springrestcrud.model.Employee;

@RestController
@RequestMapping("/company")
public class EmployeeController {
	
	@Autowired
	EmployeeDAO employeeDAO;
	
	//List employees
	@GetMapping("/employees")
	public List<Employee> listEmployee() {
		return employeeDAO.findAll();
	}
	
	//Show an employee
	@GetMapping("/employee/{id}")
	public ResponseEntity<Optional<Employee>> showEmployee(@PathVariable(value="id") Long empId) {
		
		Optional<Employee> emp = employeeDAO.findById(empId);
		
		if(emp==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(emp);
	}
	
	//Store an employee
	@PostMapping("/employee")
	public Employee createEmployee(@Valid @RequestBody Employee emp) {
		return employeeDAO.store(emp);
	}
	
	//Update an employee
	@PutMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") Long empId, @Valid @RequestBody Employee empDetails) {
		
		Employee emp = employeeDAO.findById(empId).get();
		
		if(emp==null) {
			return ResponseEntity.notFound().build();
		}
		emp.setName(empDetails.getName());
		emp.setDesignation(empDetails.getDesignation());
		emp.setExpertise(empDetails.getExpertise());
		
		Employee updateEmployee = employeeDAO.store(emp);
		return ResponseEntity.ok().body(updateEmployee);
	}
	
	//Delete an employee
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(value="id") Long empId) {
		Employee emp = employeeDAO.findById(empId).get();
		if(emp==null) {
			return ResponseEntity.notFound().build();
		}
		employeeDAO.delete(emp);
		return ResponseEntity.ok().build();
	}

}
