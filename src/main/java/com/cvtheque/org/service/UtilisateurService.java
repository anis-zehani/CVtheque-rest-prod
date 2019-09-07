package com.cvtheque.org.service;

import java.util.List;

import com.cvtheque.org.model.Candidat;
import com.cvtheque.org.model.Opportunite;
import com.cvtheque.org.model.Utilisateur;

public interface UtilisateurService {
	
	public Utilisateur getUtilisateurById(Long id);
	
	public Utilisateur getUtilisateurByUsername(String username);
	
	public String getUtilisateurRoleByUsername(String username);
	
	/*
	 * Gestion des Favoris pour un Utilisateur (Administrateur / Partenaire)
	 * Les Favoris sont : Candidat / Opportunite
	 */
	
	public void addCandidatToFavoris(Long idUtilisateur, Long idCandidat);
	
	public void deleteCandidatFromFavoris(Long idUtilisateur, Long idCandidat);
	
	public List<Candidat> getAllCandidatsFavorisForUtilisateur(Long idUtilisateur);
	
	public void addOpportuniteToFavoris(Long idUtilisateur, Long idOpportunite);
	
	public void deleteOpportuniteFromFavoris(Long idUtilisateur, Long idOpportunite);
	
	public List<Opportunite> getAllOpportunitesFavorisForUtilisateur(Long idUtilisateur);

}
