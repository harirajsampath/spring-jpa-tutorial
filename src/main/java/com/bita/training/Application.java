package com.bita.training;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;

import com.bita.training.model.Address;
import com.bita.training.model.Employee;
import com.bita.training.service.EmployeeService;

public class Application {
	
	public static void main(String[] args) {

		ApplicationContext ctx = new AnnotationConfigApplicationContext();
		((AnnotationConfigApplicationContext)ctx).scan("com.bita.training");
		((AnnotationConfigApplicationContext)ctx).refresh();
		
	    EmployeeService empSvc = ctx.getBean(EmployeeService.class);
		
		createEmployee(empSvc);
//		getAllEmployees(empSvc);
//	    getEmployeeById(empSvc);
//	    getEmployee(empSvc);
//	    getEmployeesPaginated(empSvc);
//	    getEmployeeByFirstName(empSvc);
//		getSeniorEmp(empSvc);
//	    getEmpByExample(empSvc);		
		  getEmpFullName(empSvc);
		 
	    
		((AnnotationConfigApplicationContext)ctx).close();
	}

	private static void getEmpFullName(EmployeeService empSvc) {
		String empName = empSvc.getEmployeeFullName(6);
		  System.out.println("Emp full name: " + empName);
	}

	private static void getEmpByExample(EmployeeService empSvc) {
		Employee emp = new Employee();
	    emp.setLastName("Granger");
	    emp.setAge(21);
	    
	    List<Employee> emps = empSvc.getEmployeeMatching(emp);
	    emps.forEach((employee) -> System.out.println(employee.getFirstName()));
	}

	private static void getSeniorEmp(EmployeeService empSvc) {
		List<Employee> emps = empSvc.getSeniorEmployees(26);
	    emps.forEach((emp) -> System.out.println(emp.getEmail()));
	}

	private static void getEmployeeByFirstName(EmployeeService empSvc) {
		List<Employee> emps = empSvc.getEmployeeByFirstName("Draco");
	    emps.forEach((emp) -> System.out.println(emp.getEmail()));
	}

	private static void getEmployeesPaginated(EmployeeService empSvc) {
		Page<Employee> emps = empSvc.getPaginatedEmployees();
	    emps.forEach((emp) -> System.out.println(emp.getFirstName()));
	}

	private static void getEmployee(EmployeeService empSvc) {
		Employee emp = empSvc.getEmployee("Draco", "Malfoy", 32);
	    System.out.println("Email of employee:" + emp.getEmail());
	}

	private static void getEmployeeById(EmployeeService empSvc) {
		Optional<Employee> empOp = empSvc.getEmployee(6);
	    System.out.println("Employee name: " + empOp.get().getFirstName());
	}

	private static void getAllEmployees(EmployeeService empSvc) {
		Iterable<Employee> employees = empSvc.getAllEmployees();
		for (Employee employee : employees) {
			System.out.println("Employee name:" + employee.getFirstName());
		}
	}

	private static void createEmployee(EmployeeService empSvc) {
		Address address = new Address();
		address.setCity("Chennai");
		address.setStreet("GST Road");
		address.setZipCode("600145");
		
		Employee john = new Employee();
		john.setFirstName("Minerva");
		john.setLastName("Mcgonegall");
		john.setEmail("dolres.umbridge@bita.com");
		john.setAge(48);
		john.setAddress(address);
		
		Employee createdEmp = empSvc.createEmployee(john);
		System.out.println("Id of the created Employee:" + createdEmp.getId());
	}
}
