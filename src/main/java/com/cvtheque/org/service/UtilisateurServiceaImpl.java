package com.cvtheque.org.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cvtheque.org.model.Candidat;
import com.cvtheque.org.model.Opportunite;
import com.cvtheque.org.model.Utilisateur;
import com.cvtheque.org.repository.CandidatRepository;
import com.cvtheque.org.repository.OpportuniteRepository;
import com.cvtheque.org.repository.UtilisateurRepository;

@Service
public class UtilisateurServiceaImpl implements UtilisateurService{
	
	private final UtilisateurRepository utilisateurRepository;
	private final OpportuniteRepository opportuniteRepository;
	private final CandidatRepository candidatRepository;
	
	public UtilisateurServiceaImpl(
			UtilisateurRepository utilisateurRepository, 
			OpportuniteRepository opportuniteRepository,
			CandidatRepository candidatRepository) {
		super();
		this.utilisateurRepository = utilisateurRepository;
		this.opportuniteRepository =  opportuniteRepository;
		this.candidatRepository =  candidatRepository;
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


	public void addCandidatToFavorisUtilisateur(Long idUtilisateur, Long idCandidat) {
		
		utilisateurRepository.addCandidatToFavorisUtilisateur(idUtilisateur, idCandidat);
	}


	public void deleteCandidatFromFavorisUtilisateur(Long idUtilisateur, Long idCandidat) {
		
		utilisateurRepository.deleteCandidatFromFavorisUtilisateur(idUtilisateur, idCandidat);
	}


	public List<Candidat> getAllCandidatsFavorisForUtilisateur(Long idUtilisateur) {
		
		List<Candidat> liste = candidatRepository.getAllCandidatsFavorisForUtilisateur(idUtilisateur);
		
		return liste;
	}


	public void addOpportuniteToFavorisUtilisateur(Long idUtilisateur, Long idOpportunite) {
		
		utilisateurRepository.addOpportuniteToFavorisUtilisateur(idUtilisateur, idOpportunite);
	}


	public void deleteOpportuniteFromFavorisUtilisateur(Long idUtilisateur, Long idOpportunite) {
		
		utilisateurRepository.deleteOpportuniteFromFavorisUtilisateur(idUtilisateur, idOpportunite);
	}


	public List<Opportunite> getAllOpportunitesFavorisForUtilisateur(Long idUtilisateur) {
		
		List<Opportunite> liste = opportuniteRepository.getAllOpportunitesFavorisForUtilisateur(idUtilisateur);
		
		return liste;
	}

}
