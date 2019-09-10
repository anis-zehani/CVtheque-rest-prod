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

import com.cvtheque.org.model.OpportunitesFavoris;
import com.cvtheque.org.service.OpportunitesFavorisService;

@CrossOrigin
@RestController
@RequestMapping("/api/opportunitesfavoris")
public class OpportunitesFavorisController {
	
	@Autowired
	OpportunitesFavorisService opportunitesFavorisService;
	
	// Lister les Opportunités Favorites pour un Utilisateur
	@GetMapping("/getAllOpportunitesFavorisForUtilisateur/{idUtilisateur}")
	public List<OpportunitesFavoris> getAllOpportunitesFavorisForUtilisateur(@PathVariable Long idUtilisateur){
		
		return opportunitesFavorisService.getAllOpportunitesFavorisForUtilisateur(idUtilisateur);
	}
	
	// Ajouter une Opportunité Favorite à un Utilisateur
	@PostMapping("/addOpportuniteToFavorisToUtilisateur")
	public OpportunitesFavoris addOpportuniteToFavorisToUtilisateur(@RequestBody OpportunitesFavoris opportunitesFavoris) {
		
		return opportunitesFavorisService.addOpportuniteToFavorisToUtilisateur(opportunitesFavoris);
	}
	
	// Supprimer une Opportunité Favorite pour un Utilisateur
	@DeleteMapping("/deleteOpportuniteFromFavorisToUtilisateur/{idOpportuniteFavorie}")
	public void deleteOpportuniteFromFavorisToUtilisateur(@PathVariable Long idOpportuniteFavorie) {
		
		opportunitesFavorisService.deleteOpportuniteFromFavorisToUtilisateur(idOpportuniteFavorie);
	}
	
	@GetMapping("/checkIfOpportuniteExistsDansFavorisUtilisateur/{idUtilisateur}/{idOpportunite}")
	public boolean checkIfOpportuniteExistsDansFavorisUtilisateur(Long idUtilisateur, Long idOpportunite) {
		
		return opportunitesFavorisService.checkIfOpportuniteExistsDansFavorisUtilisateur(idUtilisateur, idOpportunite);
	}

}
