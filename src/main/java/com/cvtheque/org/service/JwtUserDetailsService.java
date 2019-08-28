package com.cvtheque.org.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("demo".equals(username)) 
		{
			return new User("demo", "$2a$10$c1ZHy6raZPAro027PWd5cuPZ9pC0rAi9NC4.o3Fu43ylffGQYV5Oa", new ArrayList<>());
		} 
		else 
		{
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
