package dev.surya.spring.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.surya.spring.rest.dao.EmployeeDAO;
import dev.surya.spring.rest.entities.Employee;
import dev.surya.spring.rest.to.Status;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeSEI {
	
	@Autowired
	private EmployeeDAO empDAO; 
	
	@Override
	public Employee getEmpoyeeDetails(String id) {
		return empDAO.getEmpoyeeDetails(id);
	}

	@Override
	public Status updateEmployee(Employee employee) {
		return empDAO.updateEmployee(employee);
	}

	@Override
	public Status deleteEmployeeByEmpID(String empID) {
		return empDAO.deleteEmployeeByEmpID(empID);
	}

	@Override
	public Status addEmployee(Employee employee) {
		return empDAO.addEmployee(employee);
	}

}
