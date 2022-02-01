package com.capg.ems.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capg.ems.beans.User;
import com.capg.ems.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

//	@Override
//	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//		
//		return new User("foo","foo",new ArrayList<>());
//	}
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		User user = userRepository.findById(userName).orElse(null);
		return new MyUserDetails(user);
	}

}
