package com.capg.ems.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capg.ems.beans.Designation;
import com.capg.ems.exception.EmployeeNotFoundException;
import com.capg.ems.mapstruct.dto.EmployeeDto;

@Service
public interface IEmployeeService {

	EmployeeDto addEmployee(EmployeeDto employeeDto);
	
	EmployeeDto updateEmployee(EmployeeDto employeeDto) throws EmployeeNotFoundException;
	
	EmployeeDto getEmployeeById(int id) throws EmployeeNotFoundException;
	
	List<EmployeeDto> getEmployees();
	
	String deleteEmployee(int id) throws EmployeeNotFoundException;
	
	List<EmployeeDto> getEmployeeByName(String name);
	
	int countByDesignation(Designation designation);
}
