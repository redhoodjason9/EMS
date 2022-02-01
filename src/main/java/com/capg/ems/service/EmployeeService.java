package com.capg.ems.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capg.ems.beans.Designation;
import com.capg.ems.beans.Employee;
import com.capg.ems.exception.EmployeeNotFoundException;
import com.capg.ems.mapstruct.dto.EmployeeDto;
import com.capg.ems.mapstruct.mapper.IMapper;
import com.capg.ems.repository.IEmployeeRepository;

@Component
public class EmployeeService implements IEmployeeService {
	
	private Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
	
	@Autowired
	IEmployeeRepository repository;

	@Autowired
	IMapper mapper;
	
	@Override
	public EmployeeDto addEmployee(EmployeeDto employeeDto) {
		LOGGER.info("Employee Service initialized for Add Employee");
		Employee employeeInput = mapper.employeeDtoToEmployee(employeeDto);
		Employee employeeResult = repository.save(employeeInput);
		EmployeeDto result = mapper.employeeToEmployeeDto(employeeResult);
		LOGGER.info("Employee Service executed successfully for Add Employee");
		return result;
	}

	@Override
	public EmployeeDto updateEmployee(EmployeeDto employeeDto) throws EmployeeNotFoundException {
		LOGGER.info("Employee Service initialized for Update Employee");
		repository.findById(employeeDto.getId())
				.orElseThrow(() -> new EmployeeNotFoundException("Employee with given Id doesn't exist"));
		Employee employeeInput = mapper.employeeDtoToEmployee(employeeDto);
		Employee employeeResult = repository.save(employeeInput);
		EmployeeDto result = mapper.employeeToEmployeeDto(employeeResult);
		LOGGER.info("Employee Service executed successfully for Update Employee");
		return result;
	}

	@Override
	public EmployeeDto getEmployeeById(int id) throws EmployeeNotFoundException {
		LOGGER.info("Employee Service initialized for Get Employee");
		Employee result = repository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee with given Id doesn't exist"));
		EmployeeDto employeeDto = mapper.employeeToEmployeeDto(result);
		LOGGER.info("Employee Service executed successfully for Get Employee");
		return employeeDto;
	}

	@Override
	public List<EmployeeDto> getEmployees() {
		LOGGER.info("Employee Service initialized for Get All Employees");
		List<Employee> result = repository.findAll();
		List<EmployeeDto> employeeDto = result.stream().map(employee->mapper.employeeToEmployeeDto(employee)).collect(Collectors.toList());
		LOGGER.info("Employee Service executed successfully for Get All Employees");
		return employeeDto;
	}

	@Override
	public String deleteEmployee(int id) throws EmployeeNotFoundException {		
		LOGGER.info("Employee Service initialized for Delete Employee");
		repository.findById(id)
		.orElseThrow(() -> new EmployeeNotFoundException("Employee with given Id doesn't exist"));
		repository.deleteById(id);
		LOGGER.info("Employee Service executed successfully for Delete Employees");
		return "Employee Record Deletion Successful";
	}

	@Override
	public List<EmployeeDto> getEmployeeByName(String name) {
		LOGGER.info("Employee Service initialized for Get Employees By Name");
		List<Employee> result = repository.findByName(name);
		List<EmployeeDto> employeeDto = result.stream()
				.map(employee->mapper.employeeToEmployeeDto(employee)).collect(Collectors.toList());
		LOGGER.info("Employee Service executed successfully for Get Employees By Name");
		return employeeDto;
	}

	@Override
	public int countByDesignation(Designation designation) {
		LOGGER.info("Employee Service initialized for Get Employees Count By designation");
		int result = repository.countByDesignation(designation);
		LOGGER.info("Employee Service executed successfully for Get Employees Count By designation");
		return result;
	}

}
