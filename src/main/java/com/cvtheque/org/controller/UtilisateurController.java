package com.cvtheque.org.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvtheque.org.model.Candidat;
import com.cvtheque.org.model.Opportunite;
import com.cvtheque.org.service.UtilisateurService;

@CrossOrigin
@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {
	
	@Autowired
	private final UtilisateurService utilisateurService;

	public UtilisateurController(UtilisateurService utilisateurService) {
		super();
		this.utilisateurService = utilisateurService;
	}

	@PostMapping("/addCandidatToFavorisUtilisateur/{idUtilisateur}/{idCandidat}")
	public void addCandidatToFavorisUtilisateur(@PathVariable Long idUtilisateur, @PathVariable Long idCandidat) {
		
		utilisateurService.addCandidatToFavorisUtilisateur(idUtilisateur, idCandidat);
	}
	
	@DeleteMapping("/deleteCandidatFromFavorisUtilisateur/{idUtilisateur}/{idCandidat}")
	public void deleteCandidatFromFavorisUtilisateur(@PathVariable Long idUtilisateur, @PathVariable Long idCandidat) {
		
		utilisateurService.deleteCandidatFromFavorisUtilisateur(idUtilisateur, idCandidat);
	}
	
	@GetMapping("/getAllCandidatsFavorisForUtilisateur/{idUtilisateur}")
	public List<Candidat> getAllCandidatsFavorisForUtilisateur(@PathVariable Long idUtilisateur){
		
		return utilisateurService.getAllCandidatsFavorisForUtilisateur(idUtilisateur);
	}
	
	@PostMapping("/addOpportuniteToFavorisUtilisateur/{idUtilisateur}/{idOpportunite}")
	public void addOpportuniteToFavorisUtilisateur(@PathVariable Long idUtilisateur, @PathVariable Long idOpportunite) {
		
		utilisateurService.addOpportuniteToFavorisUtilisateur(idUtilisateur, idOpportunite);
	}
	
	@DeleteMapping("/deleteOpportuniteFromFavorisUtilisateur/{idUtilisateur}/{idOpportunite}")
	public void deleteOpportuniteFromFavorisUtilisateur(@PathVariable Long idUtilisateur, @PathVariable Long idOpportunite) {
		
		utilisateurService.deleteOpportuniteFromFavorisUtilisateur(idUtilisateur, idOpportunite);
	}
	
	@GetMapping("/getAllOpportunitesFavorisForUtilisateur/{idUtilisateur}")
	public List<Opportunite> getAllOpportunitesFavorisForUtilisateur(@PathVariable Long idUtilisateur){
		
		return utilisateurService.getAllOpportunitesFavorisForUtilisateur(idUtilisateur);
	}
}
