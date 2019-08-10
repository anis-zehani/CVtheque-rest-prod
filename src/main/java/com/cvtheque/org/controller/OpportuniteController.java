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

import com.cvtheque.org.model.Opportunite;
import com.cvtheque.org.service.OpportuniteService;

@CrossOrigin
@RestController
@RequestMapping("/opportunite")
public class OpportuniteController {
	 
	@Autowired
	private final OpportuniteService opportuniteService;
	
	OpportuniteController(OpportuniteService opportuniteService) {
		this.opportuniteService = opportuniteService;
	}

	@GetMapping("/all/{etatOpportunite}")
	public List<Opportunite> getAllOpportunites(@PathVariable String etatOpportunite) {
	    return opportuniteService.getAllOpportunites(etatOpportunite);
	}
	
	//La liste des Opportunités pour un Partenaire
	@GetMapping("/allOpportunitesByPartenaire/{id}")
	public List<Opportunite> getAllOpportunitesByPartenaire(@PathVariable Long id) {
	    return opportuniteService.getAllOpportunitesByPartenaire(id);
	}
	
	//La liste des Opportunités pour une Technologie
	@GetMapping("/allOpportunitesByTechnologie/{id}")
	public List<Opportunite> getAllOpportunitesByTechnologie(@PathVariable Long id) {
		return opportuniteService.getAllOpportunitesByTechnologie(id);
	}
	
	//La liste des Opportunités pour une Certification
	@GetMapping("/allOpportunitesByCertification/{id}")
	public List<Opportunite> getAllOpportunitesByCertification(@PathVariable Long id) {
		return opportuniteService.getAllOpportunitesByCertification(id);
	}
	
	@GetMapping("{id}")
	public Optional<Opportunite> getOpportunite(@PathVariable Long id) {
		return opportuniteService.getOpportunite(id);
	}
	
	@PostMapping()
	public Opportunite addOpportunite(@Valid @RequestBody Opportunite opportunite) {
		return opportuniteService.addOpportunite(opportunite);
	}
	
	@PutMapping()
	public Opportunite editOpportunite(@Valid @RequestBody Opportunite opportunite) {
		return opportuniteService.editOpportunite(opportunite);
	}
	
	@PutMapping("/editEtat")
	public Opportunite editEtatOpportunite(@Valid @RequestBody Opportunite opportunite) {
		return opportuniteService.editEtatOpportunite(opportunite);
	}
	
	@DeleteMapping("{id}")
	public void deleteOpportunite(@PathVariable Long id) {
		opportuniteService.deleteOpportunite(id);
	}

}