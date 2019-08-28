package com.cvtheque.org.service;

import com.cvtheque.org.model.Utilisateur;

public interface UtilisateurService {
	
	public Utilisateur findByUsername(String username);

}
