package com.cvtheque.org.service;

import com.cvtheque.org.model.Utilisateur;

public interface UtilisateurService {
	
	public Utilisateur getUtilisateurById(Long id);
	
	public Utilisateur getUtilisateurByUsername(String username);
	
	public String getUtilisateurRoleByUsername(String username);

}
