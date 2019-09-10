package com.cvtheque.org.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cvtheque.org.model.OpportunitesFavoris;
import com.cvtheque.org.repository.OpportunitesFavorisRepository;

@Service
public class OpportunitesFavorisServiceImpl implements OpportunitesFavorisService {

	private final OpportunitesFavorisRepository opportunitesFavorisRepository;

	public OpportunitesFavorisServiceImpl(OpportunitesFavorisRepository opportunitesFavorisRepository) {
		super();
		this.opportunitesFavorisRepository = opportunitesFavorisRepository;
	}

    // Lister les Opportunités Favorites pour un Utilisateur
	public List<OpportunitesFavoris> getAllOpportunitesFavorisForUtilisateur(Long idUtilisateur) {

		List<OpportunitesFavoris> liste = opportunitesFavorisRepository.findByIdUtilisateur(idUtilisateur);
		
		return liste;
	}

	// Ajouter une Opportunité Favorite à un Utilisateur
	public OpportunitesFavoris addOpportuniteToFavorisToUtilisateur(OpportunitesFavoris opportunitesFavoris) {
		
		return opportunitesFavorisRepository.save(opportunitesFavoris);
	}

	// Supprimer une Opportunité Favorite pour un Utilisateur
	public void deleteOpportuniteFromFavorisToUtilisateur(Long idOpportuniteFavorie) {
		
		opportunitesFavorisRepository.deleteById(idOpportuniteFavorie);
	}
	
	// Vérifie si une Opportunité existe dèja dans la liste des favoris d'un Utilisateur
	public boolean checkIfOpportuniteExistsDansFavorisUtilisateur(Long idUtilisateur, Long idOpportunite) {
		
		if(opportunitesFavorisRepository.findByIdUtilisateurAndIdOpportunite(idUtilisateur, idOpportunite) != null)
			return true;
		return false;
	}
	
	

}
