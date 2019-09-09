package com.cvtheque.org.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvtheque.org.model.CandidatsFavoris;
import com.cvtheque.org.service.CandidatsFavorisService;

@CrossOrigin
@RestController
@RequestMapping("/api/candidatsfavoris")
public class CandidatsFavorisController {

	@Autowired
	CandidatsFavorisService candidatsFavorisService;
	
	// Lister les Candidats Favoris pour un Utilisateur
	@GetMapping("/getAllCandidatsFavorisForUtilisateur/{idUtilisateur}")
	public List<CandidatsFavoris> getAllCandidatsFavorisForUtilisateur(@PathVariable Long idUtilisateur){
		
		return candidatsFavorisService.getAllCandidatsFavorisForUtilisateur(idUtilisateur);
	}
	
	// Ajouter un Candidat Favoris à un Utilisateur
	@PostMapping("/addCandidatToFavorisToUtilisateur")
	public CandidatsFavoris addCandidatToFavorisToUtilisateur(@RequestBody CandidatsFavoris candidatsFavoris) {
		
		return candidatsFavorisService.addCandidatToFavorisToUtilisateur(candidatsFavoris);
	}
	
	// Supprimer un Candidat Favoris pour un Utilisateur
	@DeleteMapping("/deleteCandidatFromFavorisToUtilisateur/{idCandidatFavori}")
	public void deleteCandidatFromFavorisToUtilisateur(@PathVariable Long idCandidatFavori) {
		
		candidatsFavorisService.deleteCandidatFromFavorisToUtilisateur(idCandidatFavori);
	}
	
}
