package com.cvtheque.org.controller;

import java.util.List;
import java.util.Optional;

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
	public Optional<Entreprise> getEntreprise(@PathVariable Long id) {
		return entrepriseService.getEntreprise(id);
	}
	
	@PostMapping()
	public Entreprise addEntreprise(@Valid @RequestBody Entreprise entreprise) {
		return entrepriseService.addEntreprise(entreprise);
	}
	
	@PutMapping()
	public Entreprise editEntreprise(@Valid @RequestBody Entreprise entreprise) {
		return entrepriseService.editEntreprise(entreprise);
	}
	
	@DeleteMapping("{id}")
	public boolean deleteEntreprise(@PathVariable Long id) {
		return entrepriseService.deleteEntreprise(id);
	}

}