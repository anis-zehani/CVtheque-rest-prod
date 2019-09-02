package com.cvtheque.org.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
		
		Utilisateur utilisateur = utilisateurService.getUtilisateurByUsername(username);
		
		if (utilisateur == null) {
			throw new UsernameNotFoundException("Utilisateur not found with username: " + username);
		}
		
		//Je récupére la GrantedAuthority à partir du service que j'ai crée, en fait c'est le dtype : Ex 'Administrateur'
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>(); 
		grantedAuthorities.add(new SimpleGrantedAuthority(utilisateurService.getUtilisateurRoleByUsername(username)));

		return new org.springframework.security.core.userdetails.User(
				utilisateur.getUsername(), 
				utilisateur.getPassword(), 
				grantedAuthorities);
		
	}
}
