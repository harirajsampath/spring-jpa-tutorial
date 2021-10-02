package com.bita.training.data;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bita.training.model.Employee;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

	Employee findByFirstNameAndLastNameAndAge(String firstName, String lastName, int age);
	
	@Query(value = "SELECT * FROM employee emp WHERE emp.first_name = :firstName", nativeQuery = true)
	List<Employee> findByFirstName(@Param(value = "firstName") String fName);
	
	List<Employee> findAll(Specification<Employee> employeeSpec);
	
	List<Employee> findAll(Example<Employee> employeeSpec);

	@Procedure(procedureName = "sp_emp_name", outputParameterName = "fullName")
	String getEmployeeFullName(Integer id);
}
