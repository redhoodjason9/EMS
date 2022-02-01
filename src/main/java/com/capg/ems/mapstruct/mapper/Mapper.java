package com.capg.ems.mapstruct.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.capg.ems.beans.Employee;
import com.capg.ems.mapstruct.dto.EmployeeDto;

@Component
public class Mapper implements IMapper {
	
	private Logger LOGGER = LoggerFactory.getLogger(Mapper.class);

	@Override
	public EmployeeDto employeeToEmployeeDto(Employee employee) {
		LOGGER.info("Mapper initialized for conversion Employee To EmployeeDto");
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId(employee.getId());
		employeeDto.setDesignation(employee.getDesignation());
		employeeDto.setName(employee.getName());
		employeeDto.setSalary(employee.getSalary());
		LOGGER.info("Mapper executed successfully for conversion Employee To EmployeeDto");
		return employeeDto;
	}

	@Override
	public Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
		LOGGER.info("Mapper initialized for conversion EmployeeDto To Employee");
		Employee employee = new Employee();
		employee.setId(employeeDto.getId());
		employee.setDesignation(employeeDto.getDesignation());
		employee.setName(employeeDto.getName());
		employee.setSalary(employeeDto.getSalary());
		LOGGER.info("Mapper executed successfully for conversion EmployeeDto To Employee");
		return employee;
	}

}
