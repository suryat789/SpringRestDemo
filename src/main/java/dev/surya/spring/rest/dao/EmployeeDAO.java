package dev.surya.spring.rest.dao;

import dev.surya.spring.rest.entities.Employee;
import dev.surya.spring.rest.to.Status;


public interface EmployeeDAO {
	
	public Status addEmployee(Employee employee);
	
	public Employee getEmpoyeeDetails(String id);
	
	public Status updateEmployee(Employee employee);
	
	public Status deleteEmployeeByEmpID(String empID);
		
}