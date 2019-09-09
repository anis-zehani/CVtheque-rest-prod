package com.cvtheque.org.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cvtheque.org.model.CandidatsFavoris;
import com.cvtheque.org.repository.CandidatsFavorisRepository;


@Service
public class CandidatsFavorisServiceImpl implements CandidatsFavorisService {
	
	private final CandidatsFavorisRepository candidatsFavorisRepository;
	
	public CandidatsFavorisServiceImpl(CandidatsFavorisRepository candidatsFavorisRepository) {
		super();
		this.candidatsFavorisRepository = candidatsFavorisRepository;
	}

	// Lister les Candidats Favoris pour un Utilisateur
	public List<CandidatsFavoris> getAllCandidatsFavorisForUtilisateur(Long idUtilisateur) {
		
		List<CandidatsFavoris> liste = candidatsFavorisRepository.findByIdUtilisateur(idUtilisateur);
		
		return liste;
	}
	
	// Ajouter un Candidat Favoris à un Utilisateur
	public CandidatsFavoris addCandidatToFavorisToUtilisateur(CandidatsFavoris candidatsFavoris) {
		
		return candidatsFavorisRepository.save(candidatsFavoris);
	}

	// Supprimer un Candidat Favoris pour un Utilisateur
	public void deleteCandidatFromFavorisToUtilisateur(Long idCandidatFavori) {
		
		candidatsFavorisRepository.deleteById(idCandidatFavori);
	}

}
