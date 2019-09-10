package com.cvtheque.org.service;

import java.util.List;

import com.cvtheque.org.model.OpportunitesFavoris;

public interface OpportunitesFavorisService {

	public List<OpportunitesFavoris> getAllOpportunitesFavorisForUtilisateur(Long idUtilisateur);
	
	public OpportunitesFavoris addOpportuniteToFavorisToUtilisateur(OpportunitesFavoris opportunitesFavoris);
	
	public void deleteOpportuniteFromFavorisToUtilisateur(Long idOpportuniteFavorie);
	
	public boolean checkIfOpportuniteExistsDansFavorisUtilisateur(Long idUtilisateur, Long idOpportunite);
}
