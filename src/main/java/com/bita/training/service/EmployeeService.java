package com.bita.training.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bita.training.data.EmployeeRepository;
import com.bita.training.data.EmployeeSpecs;
import com.bita.training.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;
	
	
	public Employee createEmployee(Employee emp) {
		return empRepo.save(emp);
	}
	
	public Iterable<Employee> getAllEmployees() {
		return empRepo.findAll();
	}
	
	public Optional<Employee> getEmployee(Integer id) {
		return empRepo.findById(id);
	}
	
	public Employee getEmployee(String firstName, String lastName, int age) {
		return empRepo.findByFirstNameAndLastNameAndAge(firstName, lastName, age);
	}
	
	public Page<Employee> getPaginatedEmployees() {
		
		Pageable pageable = PageRequest.of(1, 2, Direction.DESC, new String[] {"firstName", "lastName"});
		return empRepo.findAll(pageable);
	}
	
	public List<Employee> getEmployeeByFirstName(String firstName) {
		return empRepo.findByFirstName(firstName);
	}
	
	public String getEmployeeFullName(Integer id) {
		return empRepo.getEmployeeFullName(id);
	}
	
	public List<Employee> getSeniorEmployees(int age) {
		return empRepo.findAll(EmployeeSpecs.isSenior(age));
	}
	
	public List<Employee> getEmployeeMatching(Employee emp) {
		
		ExampleMatcher exMatcher = ExampleMatcher.matching().withIgnoreCase("lastName");
		Example<Employee> exEmp = Example.of(emp, exMatcher);
		return empRepo.findAll(exEmp);
	}
}
