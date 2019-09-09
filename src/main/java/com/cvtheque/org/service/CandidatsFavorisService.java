package com.cvtheque.org.service;

import java.util.List;

import com.cvtheque.org.model.CandidatsFavoris;

public interface CandidatsFavorisService {
	
	public List<CandidatsFavoris> getAllCandidatsFavorisForUtilisateur(Long idUtilisateur);
	
	public CandidatsFavoris addCandidatToFavorisToUtilisateur(CandidatsFavoris candidatsFavoris);
	
	public void deleteCandidatFromFavorisToUtilisateur(Long idCandidatFavori);
}
