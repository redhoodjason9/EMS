package com.capg.ems.mapstruct.mapper;

import org.mapstruct.Mapper;

import com.capg.ems.beans.Employee;
import com.capg.ems.mapstruct.dto.EmployeeDto;

@Mapper
public interface IMapper {
	
	EmployeeDto employeeToEmployeeDto(Employee employee);
	
	Employee employeeDtoToEmployee(EmployeeDto employeeDto);
}
