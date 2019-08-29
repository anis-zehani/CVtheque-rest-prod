package com.cvtheque.org.service;

import org.springframework.stereotype.Service;

import com.cvtheque.org.model.Utilisateur;
import com.cvtheque.org.repository.UtilisateurRepository;

@Service
public class UtilisateurServiceaImpl implements UtilisateurService{
	
	private final UtilisateurRepository utilisateurRepository;
	
	public UtilisateurServiceaImpl(UtilisateurRepository utilisateurRepository) {
		super();
		this.utilisateurRepository = utilisateurRepository;
	}


	public Utilisateur findByUsername(String username) {
		return utilisateurRepository.findByUsername(username);
	}
	
	public String getUserRole(String username) {
		return utilisateurRepository.getUserRole(username);
	}

}
