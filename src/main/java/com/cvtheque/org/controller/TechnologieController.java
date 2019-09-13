package com.cvtheque.org.controller;

import java.util.List;

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

import com.cvtheque.org.model.Technologie;
import com.cvtheque.org.service.TechnologieService;

@CrossOrigin
@RestController
@RequestMapping("/api/technologie")
public class TechnologieController {
	
	@Autowired
	private final TechnologieService technologieService;
	
	TechnologieController(TechnologieService technologieService) {
		this.technologieService = technologieService;
	}
	
	@GetMapping()
	public List<Technologie> getAllTechnologies() {
	    return technologieService.getAllTechnologies();
	}
	
	@GetMapping("{id}")
	public Technologie getTechnologie(@PathVariable Long id) {
		return technologieService.getTechnologie(id);
	}

	@GetMapping("/candidatsByTechnologie")
	public List<Technologie> getCandidatsByTechnologie() {
	    return technologieService.candidatsByTechnologie();
	}
	
	@GetMapping("/opportunitesByTechnologie")
	public List<Technologie> getOpportunitesByTechnologie() {
	    return technologieService.opportunitesByTechnologie();
	}
	
	//Ajouter une Technologie pour un utilisateur : (idUtilisateur existe dans l'objet Utilisateur envoyé à l'intérieur de l'objet Technologie)
	@PostMapping()
	public Technologie addTechnologie(@Valid @RequestBody Technologie technologie) {
		return technologieService.addTechnologie(technologie);
	}
	
	//Modifier une Technologie pour un utilisateur : (idUtilisateur existe dans l'objet Utilisateur envoyé à l'intérieur de l'objet Technologie)
	@PutMapping()
	public Technologie editTechnologie(@Valid @RequestBody Technologie technologie) {
		return technologieService.editTechnologie(technologie);
	}
	
	@DeleteMapping("{id}")
	public boolean deleteTechnologie(@PathVariable Long id) {
		return technologieService.deleteTechnologie(id);
	}

}
