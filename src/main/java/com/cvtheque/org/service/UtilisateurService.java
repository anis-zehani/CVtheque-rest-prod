package com.cvtheque.org.service;

import java.util.List;

import com.cvtheque.org.model.Candidat;
import com.cvtheque.org.model.Utilisateur;

public interface UtilisateurService {
	
	public Utilisateur getUtilisateurById(Long id);
	
	public Utilisateur getUtilisateurByUsername(String username);
	
	public String getUtilisateurRoleByUsername(String username);
	
	/*
	 * Gestion des Favoris pour un Utilisateur (Administrateur / Partenaire)
	 * Les Favoris sont : Candidat / Opportunite
	 */
	
	public void addCandidatToFavorisUtilisateur(Long idUtilisateur, Long idCandidat);
	
	public void deleteCandidatFromFavorisUtilisateur(Long idUtilisateur, Long idCandidat);
	
	public List<Candidat> getAllCandidatsFavorisForUtilisateur(Long idUtilisateur);
	
	public void addOpportuniteToFavorisUtilisateur(Long idUtilisateur, Long idOpportunite);
	
	public void deleteOpportuniteFromFavorisUtilisateur(Long idUtilisateur, Long idOpportunite);

}
