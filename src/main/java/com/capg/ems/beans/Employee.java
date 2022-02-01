package com.capg.ems.beans;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull(message = "Name cannot be null.")
	@Pattern(regexp = "^[A-Za-z ]{3,20}$", message = "Name should be 3 to 20 characters long.")
	private String name;

	@Enumerated(EnumType.STRING)
	private Designation designation;

	@Min(value = 20000, message = "Minimum salary is 20000.")
	@Max(value = 500000, message = "Maximum salary is 500000.")
	private BigDecimal salary;

	public Employee() {
		super();
	}

	public Employee(int id,
			@NotNull(message = "Name cannot be null.") @Pattern(regexp = "^[A-Za-z ]{3,20}$", message = "Name should be 3 to 20 characters long.") String name,
			Designation designation,
			@Min(value = 20000, message = "Minimum salary is 20000.") @Max(value = 500000, message = "Maximum salary is 500000.") BigDecimal salary) {
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

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", designation=" + designation.toString() + ", salary="
				+ salary + "]";
	}

}
