package com.cvtheque.org.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cvtheque.org.model.Utilisateur;
import com.cvtheque.org.repository.UtilisateurRepository;

@Service
public class UtilisateurServiceaImpl implements UtilisateurService{
	
	private final UtilisateurRepository utilisateurRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	public UtilisateurServiceaImpl(UtilisateurRepository utilisateurRepository) {
		super();
		this.utilisateurRepository = utilisateurRepository;
	}

	
	public Utilisateur getUtilisateurById(Long id) {
		return utilisateurRepository.findUtilisateurById(id);
	}

	public Utilisateur getUtilisateurByUsername(String username) {
		return utilisateurRepository.findUtilisateurByUsername(username);
	}
	
	public Utilisateur getUtilisateurByEmail(String email) {
		return utilisateurRepository.findUtilisateurByEmail(email);
	}
	
	public Utilisateur resetPasswordUtilisateur(String email, String password) {
		
		Utilisateur utilisateur = utilisateurRepository.findUtilisateurByEmail(email);
		utilisateur.setPassword(bcryptEncoder.encode(password));
		
		return utilisateurRepository.save(utilisateur);
	}
	
	public String getUtilisateurRoleByUsername(String username) {
		return utilisateurRepository.findUtilisateurRoleByUsername(username);
	}
}
