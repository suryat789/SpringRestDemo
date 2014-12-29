/**
 * 
 */
package dev.surya.spring.rest.test;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import dev.surya.spring.rest.config.AppConfig;
import dev.surya.spring.rest.entities.Employee;
import dev.surya.spring.rest.services.EmployeeSEI;
import dev.surya.spring.rest.to.Status;
import dev.surya.spring.rest.to.StatusConstants;

/**
 * @author user
 *
 */
public class EmployeeSEITest {
	private static AbstractApplicationContext context;
	private static EmployeeSEI service;
	
	/**
	 * @throws java.lang.Exception
	 */
	@SuppressWarnings("resource")
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		service = (EmployeeSEI) context.getBean("employeeService");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
		service = null;
	}

	/**
	 * Test method for {@link dev.surya.spring.rest.services.EmployeeSEI#getEmpoyeeDetails(java.lang.String)}.
	 */
	@Test
	public void testGetEmpoyeeDetails() throws Exception {
		String empID = "1001";
		Employee employee = service.getEmpoyeeDetails(empID);
		Assert.assertNotNull(employee);
		Assert.assertEquals("Surya", employee.getEmployeeName());
		Assert.assertEquals("Dev", employee.getEmployeeDept());
	}

	/**
	 * Test method for {@link dev.surya.spring.rest.services.EmployeeSEI#updateEmployee(dev.surya.spring.rest.entities.Employee)}.
	 */
	@Test
	public void testUpdateEmployee() throws Exception {
		String empID = "1002";
		String deptActual = "Mgmt";
		String deptExpected = "Dev";
		
		// Update Emp details.
		Employee employeeExp = new Employee(empID, "Steve", deptExpected);
		Status status = service.updateEmployee(employeeExp);
		
		Assert.assertEquals(StatusConstants.StatusCode.SUCCESS, status.getCode());
		Assert.assertEquals(StatusConstants.StatusMessages.SUCCESS, status.getMessage());
		
		// Get Emp details and assert.
		Employee employee = service.getEmpoyeeDetails(empID);
		Assert.assertNotNull(employee);
		Assert.assertEquals("Steve", employee.getEmployeeName());
		Assert.assertNotSame(deptActual, employee.getEmployeeDept());
		Assert.assertEquals(deptExpected, employee.getEmployeeDept());
		
		// Undo the changes.
		employeeExp.setEmployeeDept(deptActual);
		status = service.updateEmployee(employeeExp);
		
		Assert.assertEquals(StatusConstants.StatusCode.SUCCESS, status.getCode());
		Assert.assertEquals(StatusConstants.StatusMessages.SUCCESS, status.getMessage());
	}

	/**
	 * Test method for {@link dev.surya.spring.rest.services.EmployeeSEI#deleteEmployeeByEmpID(java.lang.String)}.
	 */
	@Test
	public void testDeleteEmployeeByEmpID() throws Exception {
		String empID = "1002";
		
		// Declare Employee to be added back.
		Employee employeeToBeDeleted = new Employee(empID, "Steve", "Mgmt");
		
		// Delete the Employee.
		Status status = service.deleteEmployeeByEmpID(empID);
		
		// Asert the status.
		Assert.assertEquals(StatusConstants.StatusCode.SUCCESS, status.getCode());
		Assert.assertEquals(StatusConstants.StatusMessages.SUCCESS, status.getMessage());
		
		// Get the Employee details.
		Employee employee = service.getEmpoyeeDetails(empID);
		Assert.assertNull(employee);
		
		// Undo the changes.
		status = service.addEmployee(employeeToBeDeleted);
		
		// Assert the status.
		Assert.assertEquals(StatusConstants.StatusCode.SUCCESS, status.getCode());
		Assert.assertEquals(StatusConstants.StatusMessages.SUCCESS, status.getMessage());
	}
	
	@Test
	public void testAddEmployee(){
		String empID = "1003";
		
		// Declare Employee to be added.
		Employee employeeToBeAdded = new Employee(empID, "Steve", "Mgmt");
		Status status = service.addEmployee(employeeToBeAdded);
		
		// Assert the status.
		Assert.assertEquals(StatusConstants.StatusCode.SUCCESS, status.getCode());
		Assert.assertEquals(StatusConstants.StatusMessages.SUCCESS, status.getMessage());
		
		// Undo the changes.
		status = service.deleteEmployeeByEmpID(empID);
		
		// Assert the status.
		Assert.assertEquals(StatusConstants.StatusCode.SUCCESS, status.getCode());
		Assert.assertEquals(StatusConstants.StatusMessages.SUCCESS, status.getMessage());
	}
}
