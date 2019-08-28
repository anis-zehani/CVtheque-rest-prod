package com.cvtheque.org.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cvtheque.org.model.Utilisateur;


@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	UtilisateurService utilisateurService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Utilisateur utilisateur = utilisateurService.findByUsername(username);
		
		if (utilisateur == null) {
			throw new UsernameNotFoundException("Utilisateur not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(utilisateur.getUsername(), utilisateur.getPassword(), new ArrayList<>());
		
		/*if ("demo".equals(username)) 
		{
			return new User("demo", "$2a$10$c1ZHy6raZPAro027PWd5cuPZ9pC0rAi9NC4.o3Fu43ylffGQYV5Oa", new ArrayList<>());
		} 
		else 
		{
			throw new UsernameNotFoundException("User not found with username: " + username);
		}*/
	}
}
