package com.cvtheque.org.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvtheque.org.model.Entreprise;
import com.cvtheque.org.service.EntrepriseService;

@CrossOrigin
@RestController
@RequestMapping("/api/entreprise")
public class EntrepriseController {
	
	@Autowired
	private final EntrepriseService entrepriseService;
	
	EntrepriseController(EntrepriseService entrepriseService) {
		this.entrepriseService = entrepriseService;
	}

	@GetMapping()
	public List<Entreprise> getAllEntreprises() {
	    return entrepriseService.getAllEntreprises();
	}
	
	@GetMapping("{id}")
	public Entreprise getEntreprise(@PathVariable Long id) {
		return entrepriseService.getEntreprise(id);
	}
	
	// Retourne la liste des 5 premières Entreprises ORDER BY le nombre des Candidats qu'il y a pour elle
	@GetMapping("/candidatsByEntreprise")
	public List<Entreprise> getCandidatsByEntreprise() {
		return entrepriseService.candidatsByEntreprise();
	}
		
	// Retourne la liste des 5 premières Entreprises ORDER BY le nombre des Partenaires qu'il y a pour elle
	@GetMapping("/partenairesByEntreprise")
	public List<Entreprise> getPartenairesByEntreprise() {
		return entrepriseService.partenairesByEntreprise();
	}
		
	// Retourne la somme des Candidats liés et des Partenaires liés pour toutes les Entreprises
	@GetMapping("/sumCandiatsAndPartenairesByEntreprises")
	public Map<String, Integer> sumCandiatsAndPartenairesByEntreprises(){
		return entrepriseService.sumCandiatsAndPartenairesByEntreprises();
	}
	
	//Ajouter une Entreprise pour un utilisateur : (idUtilisateur existe dans l'objet Utilisateur envoyé à l'intérieur de l'objet Entreprise)
	@PostMapping()
	public Entreprise addEntreprise(@Valid @RequestBody Entreprise entreprise) {
		return entrepriseService.addEntreprise(entreprise);
	}
	
	//Modifier une Entreprise pour un utilisateur : (idUtilisateur existe dans l'objet Utilisateur envoyé à l'intérieur de l'objet Entreprise)
	@PutMapping()
	public Entreprise editEntreprise(@Valid @RequestBody Entreprise entreprise) {
		return entrepriseService.editEntreprise(entreprise);
	}
	
	@DeleteMapping("{id}")
	public boolean deleteEntreprise(@PathVariable Long id) {
		return entrepriseService.deleteEntreprise(id);
	}

}