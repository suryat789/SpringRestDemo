package dev.surya.spring.rest.services;

import dev.surya.spring.rest.entities.Employee;
import dev.surya.spring.rest.to.Status;


public interface EmployeeSEI {
	
	public Employee getEmpoyeeDetails(String id);
	
	public Status addEmployee(Employee employee);
	
	public Status updateEmployee(Employee employee);
	
	public Status deleteEmployeeByEmpID(String empID);

}