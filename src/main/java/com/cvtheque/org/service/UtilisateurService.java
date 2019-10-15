package com.cvtheque.org.service;

import com.cvtheque.org.model.Utilisateur;

public interface UtilisateurService {
	
	public Utilisateur getUtilisateurById(Long id);
	
	public Utilisateur getUtilisateurByUsername(String username);
	
	public Utilisateur getUtilisateurByEmail(String email);
	
	public Utilisateur resetPasswordUtilisateur(String email, String password);
	
	public String getUtilisateurRoleByUsername(String username);
	
}
