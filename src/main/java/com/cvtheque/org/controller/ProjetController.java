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

import com.cvtheque.org.model.Projet;
import com.cvtheque.org.service.ProjetService;

@CrossOrigin
@RestController
@RequestMapping("/projet")
public class ProjetController {
	
	@Autowired
	private final ProjetService projetService;
	
	ProjetController(ProjetService projetService) {
		this.projetService = projetService;
	}

	@GetMapping()
	public List<Projet> getAllProjets() {
	    return projetService.getAllProjets();
	}
	
	@GetMapping("{id}")
	public Projet getProjet(@PathVariable Long id) {
		return projetService.getProjet(id);
	}
	
	@PostMapping()
	public Projet addProjet(@Valid @RequestBody Projet projet) {
		return projetService.addProjet(projet);
	}
	
	@PutMapping()
	public Projet editProjet(@Valid @RequestBody Projet projet) {
		return projetService.editProjet(projet);
	}
	
	@DeleteMapping("{id}")
	public void deleteProjet(@PathVariable Long id) {
		projetService.deleteProjet(id);
	}

}
