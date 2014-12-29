package dev.surya.spring.rest.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import dev.surya.spring.rest.entities.Employee;
import dev.surya.spring.rest.to.Status;
import dev.surya.spring.rest.to.StatusConstants;

@SuppressWarnings(value = "unchecked")
@Repository("employeeDao")
public class EmployeeDAOImpl extends AbstractDAO implements EmployeeDAO {

	
	@Override
	public Employee getEmpoyeeDetails(String id) {
		Criteria criteria = getSession().createCriteria(Employee.class);
		criteria.add(Restrictions.eq("employeeID", id));
		List<Employee> employees = criteria.list();
		if(employees != null && !employees.isEmpty()){
			return employees.get(0);
		}
		return null;
	}
	
	public Status updateEmployee(Employee employee){
		Status status = null;
		try {
			getSession().update(employee);
			status = new Status(StatusConstants.StatusCode.SUCCESS, StatusConstants.StatusMessages.SUCCESS);
		} catch (Exception e) {
			status = new Status(StatusConstants.StatusCode.FAILURE, StatusConstants.StatusMessages.FAILURE);
			e.printStackTrace();
		}
		return status;
	}
	
	public Status deleteEmployeeByEmpID(String empID) {
		Query query = null;
		Status status = null;
		try {
			query = getSession().createSQLQuery("DELETE FROM Employee where EmployeeID = :empID");
			query.setString("empID", empID);
			query.executeUpdate();
			status = new Status(StatusConstants.StatusCode.SUCCESS, StatusConstants.StatusMessages.SUCCESS);
		} catch (Exception ex){
			status = new Status(StatusConstants.StatusCode.FAILURE, StatusConstants.StatusMessages.FAILURE);
			ex.printStackTrace();
		}
		return status;
	}

	@Override
	public Status addEmployee(Employee employee) {
		Status status = null;
		try {
			persist(employee);
			status = new Status(StatusConstants.StatusCode.SUCCESS, StatusConstants.StatusMessages.SUCCESS);
		} catch (Exception ex){
			status = new Status(StatusConstants.StatusCode.FAILURE, StatusConstants.StatusMessages.FAILURE);
			ex.printStackTrace();
		}
		return status;
	}
}
