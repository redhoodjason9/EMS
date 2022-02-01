package com.capg.ems.mapstruct.dto;

import java.math.BigDecimal;

import com.capg.ems.beans.Designation;

public class EmployeeDto {

	private int id;

	private String name;

	private Designation designation;

	private BigDecimal salary;

	public EmployeeDto() {
		super();
	}

	public EmployeeDto(int id, String name, Designation designation, BigDecimal salary) {
		super();
		this.id = id;
		this.name = name;
		this.designation = designation;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

}
