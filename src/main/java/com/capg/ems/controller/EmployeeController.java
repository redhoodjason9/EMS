package com.capg.ems.controller;

import static com.capg.ems.util.Constants.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.ems.beans.Designation;
import com.capg.ems.beans.User;
import com.capg.ems.exception.EmployeeNotFoundException;
import com.capg.ems.mapstruct.dto.EmployeeDto;
import com.capg.ems.repository.UserRepository;
import com.capg.ems.security.AuthenticationRequest;
import com.capg.ems.security.AuthenticationResponse;
import com.capg.ems.security.JwtUtil;
import com.capg.ems.security.MyUserDetailsService;
import com.capg.ems.service.EmployeeService;
import com.capg.ems.service.IEmployeeService;

@CrossOrigin
@RestController
@RequestMapping(path = API)
public class EmployeeController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping(path = "/login")
	public ResponseEntity<User> createAuthenticationToken(
			@RequestBody User user) {
		
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						user.getUsername(),user.getPassword()
				)
		);
		
		UserDetails userDetails = myUserDetailsService.loadUserByUsername(user.getUsername());

		final String jwt = jwtUtil.generateToken(userDetails);
		
		User response = userRepository.getById(user.getUsername());
		
		response.setJwt(jwt);

		return ResponseEntity.ok(response);
	}
	
	@PostMapping(path = "/signup")
	public ResponseEntity<User> signup(@RequestBody User user){
		User response = userRepository.save(user);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping(path = "/getuser/{username}")
	public ResponseEntity<User> getUser(@PathVariable(name = "username") String username) throws Exception{
		User response = userRepository.findById(username).orElseThrow(()->new Exception("user not found"));
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/test")
	public String test(){
		return "hello";
	}

	private Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	IEmployeeService service;

	@PostMapping(path = ADD)
	public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {
		LOGGER.info("Employee Controller initialized for Add Employee");
		EmployeeDto result = service.addEmployee(employeeDto);
		LOGGER.info("Employee Controller executed successfully for Add Employee");
		return new ResponseEntity<EmployeeDto>(result, HttpStatus.OK);
	}

	@PutMapping(path = UPDATE)
	public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto)
			throws EmployeeNotFoundException {
		LOGGER.info("Employee Controller initialized for Update Employee");
		EmployeeDto result = service.updateEmployee(employeeDto);
		LOGGER.info("Employee Controller executed successfully for Update Employee");
		return new ResponseEntity<EmployeeDto>(result, HttpStatus.OK);
	}

	@GetMapping(path = GETBYID)
	public ResponseEntity<EmployeeDto> getEmployee(@PathVariable(name = "id") int id) throws EmployeeNotFoundException {
		LOGGER.info("Employee Controller initialized for Get Employee");
		EmployeeDto result = service.getEmployeeById(id);
		LOGGER.info("Employee Controller executed successfully for Get Employee");
		return new ResponseEntity<EmployeeDto>(result, HttpStatus.OK);
	}

	@GetMapping(path = GETALL)
	public ResponseEntity<List<EmployeeDto>> getEmployees() {
		LOGGER.info("Employee Controller initialized for Get All Employees");
		List<EmployeeDto> result = service.getEmployees();
		LOGGER.info("Employee Controller executed successfully for Get All Employees");
		return new ResponseEntity<List<EmployeeDto>>(result, HttpStatus.OK);
	}

	@DeleteMapping(path = DELETE)
	public ResponseEntity<String> deleteEmployee(@PathVariable(name = "id") int id) throws EmployeeNotFoundException {
		LOGGER.info("Employee Controller initialized for Delete Employee");
		String result = service.deleteEmployee(id);
		LOGGER.info("Employee Controller executed successfully for Delete Employees");
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@GetMapping(path = GETBYNAME)
	public ResponseEntity<List<EmployeeDto>> getEmployeesByName(@PathVariable String name) {
		LOGGER.info("Employee Controller initialized for Get All Employees by Name");
		List<EmployeeDto> result = service.getEmployeeByName(name);
		LOGGER.info("Employee Controller executed successfully for Get All Employees by Name");
		return new ResponseEntity<List<EmployeeDto>>(result, HttpStatus.OK);
	}

	@GetMapping(path = GETCOUNTBYDESIGNATION)
	public ResponseEntity<Integer> getCountByDesignation(@PathVariable Designation designation) {
		LOGGER.info("Employee Controller initialized for Get Count by Designation");
		int result = service.countByDesignation(designation);
		LOGGER.info("Employee Controller executed successfully for Get Count by Designation");
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

}
