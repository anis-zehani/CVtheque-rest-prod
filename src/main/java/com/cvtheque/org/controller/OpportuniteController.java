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
	
	private OpportuniteController(OpportuniteService opportuniteService) {
		this.opportuniteService = opportuniteService;
	}

	@GetMapping("/all/{etatOpportunite}")
	List<Opportunite> getAllOpportunites(@PathVariable String etatOpportunite) {
	    return opportuniteService.getAllOpportunites(etatOpportunite);
	}
	
	@GetMapping("{id}")
	Optional<Opportunite> getOpportunite(@PathVariable Long id) {
		return opportuniteService.getOpportunite(id);
	}
	
	@PostMapping()
	Opportunite addOpportunite(@Valid @RequestBody Opportunite opportunite) {
		return opportuniteService.addOpportunite(opportunite);
	}
	
	@PutMapping()
	Opportunite editOpportunite(@Valid @RequestBody Opportunite opportunite) {
		return opportuniteService.editOpportunite(opportunite);
	}
	
	@PutMapping("/editEtat")
	Opportunite editEtatOpportunite(@Valid @RequestBody Opportunite opportunite) {
		return opportuniteService.editEtatOpportunite(opportunite);
	}
	
	@DeleteMapping("{id}")
	void deleteOpportunite(@PathVariable Long id) {
		opportuniteService.deleteOpportunite(id);
	}

}