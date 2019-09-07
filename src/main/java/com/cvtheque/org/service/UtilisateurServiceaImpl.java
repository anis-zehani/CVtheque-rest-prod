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
	public void addCandidatToFavorisUtilisateur(Long idUtilisateur, Long idCandidat) {
		
		utilisateurRepository.addCandidatToFavorisUtilisateur(idUtilisateur, idCandidat);
	}


	@Override
	public void deleteCandidatFromFavorisUtilisateur(Long idUtilisateur, Long idCandidat) {
		
		utilisateurRepository.deleteCandidatFromFavorisUtilisateur(idUtilisateur, idCandidat);
	}


	@Override
	public List<Candidat> getAllCandidatsFavorisForUtilisateur(Long idUtilisateur) {
		
		List<Candidat> liste = utilisateurRepository.getAllCandidatsFavorisForUtilisateur(idUtilisateur);
		
		return liste;
	}


	@Override
	public void addOpportuniteToFavorisUtilisateur(Long idUtilisateur, Long idOpportunite) {
		
		utilisateurRepository.addOpportuniteToFavorisUtilisateur(idUtilisateur, idOpportunite);
	}


	@Override
	public void deleteOpportuniteFromFavorisUtilisateur(Long idUtilisateur, Long idOpportunite) {
		
		utilisateurRepository.deleteOpportuniteFromFavorisUtilisateur(idUtilisateur, idOpportunite);
	}


	@Override
	public List<Opportunite> getAllOpportunitesFavorisForUtilisateur(Long idUtilisateur) {
		
		List<Opportunite> liste = utilisateurRepository.getAllOpportunitesFavorisForUtilisateur(idUtilisateur);
		
		return liste;
	}

}
