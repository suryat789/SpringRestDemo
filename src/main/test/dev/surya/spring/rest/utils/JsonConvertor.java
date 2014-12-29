package dev.surya.spring.rest.utils;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.surya.spring.rest.entities.Employee;

public class JsonConvertor {

	public static Employee getEmployeeFromJson(String json){
		Employee employee = null;
		
		return employee;
	}
	
	public static String getJsonFromEmployee(Employee employee) throws JsonGenerationException, JsonMappingException, IOException{
		StringWriter writer = new StringWriter();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(writer, employee);
		
		return writer.toString();
	}
	
	public static void main(String[] args) {
		Employee employee = new Employee("1001", "Surya", "Dev");
		
		try {
			System.out.println(getJsonFromEmployee(employee));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
