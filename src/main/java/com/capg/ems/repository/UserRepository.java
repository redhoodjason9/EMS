package com.capg.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.ems.beans.User;

public interface UserRepository extends JpaRepository<User, String> {

}
