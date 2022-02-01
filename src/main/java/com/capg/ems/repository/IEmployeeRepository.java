package com.capg.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capg.ems.beans.Designation;
import com.capg.ems.beans.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

	
	public List<Employee> findByName(String name);
	
	@Query("select count(e.designation) from Employee e where e.designation=:designation")
	public int countByDesignation(@Param(value = "designation") Designation designation);
}
