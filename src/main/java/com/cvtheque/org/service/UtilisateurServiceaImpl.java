package com.cvtheque.org.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cvtheque.org.model.Candidat;
import com.cvtheque.org.model.Opportunite;
import com.cvtheque.org.model.Utilisateur;
import com.cvtheque.org.repository.UtilisateurRepository;

@Service
public class UtilisateurServiceaImpl implements UtilisateurService{
	
	private final UtilisateurRepository utilisateurRepository;
	
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
	
	public String getUtilisateurRoleByUsername(String username) {
		return utilisateurRepository.findUtilisateurRoleByUsername(username);
	}


	@Override
	public void addCandidatToFavoris(Long idUtilisateur, Long idCandidat) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteCandidatFromFavoris(Long idUtilisateur, Long idCandidat) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Candidat> getAllCandidatsFavorisForUtilisateur(Long idUtilisateur) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void addOpportuniteToFavoris(Long idUtilisateur, Long idOpportunite) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteOpportuniteFromFavoris(Long idUtilisateur, Long idOpportunite) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Opportunite> getAllOpportunitesFavorisForUtilisateur(Long idUtilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

}
