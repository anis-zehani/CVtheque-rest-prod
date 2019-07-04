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

import com.cvtheque.org.model.Collaborateur;
import com.cvtheque.org.service.CollaborateurService;

@CrossOrigin
@RestController
@RequestMapping("/collaborateur")
public class CollaborateurController {
	
	@Autowired
	private final CollaborateurService collaborateurService;
	
	private CollaborateurController(CollaborateurService collaborateurService) {
		this.collaborateurService = collaborateurService;
	}

	@GetMapping()
	List<Collaborateur> getAllCollaborateurs() {
	    return collaborateurService.getAllCollaborateurs();
	}
	
	@GetMapping("{id}")
	Optional<Collaborateur> getCollaborateur(@PathVariable Long id) {
		return collaborateurService.getCollaborateur(id);
	}
	
	@PostMapping()
	Collaborateur addCollaborateur(@Valid @RequestBody Collaborateur collaborateur) {
		return collaborateurService.addCollaborateur(collaborateur);
	}
	
	@PutMapping()
	Collaborateur editCollaborateur(@Valid @RequestBody Collaborateur collaborateur) {
		return collaborateurService.editCollaborateur(collaborateur);
	}
	
	@DeleteMapping("{id}")
	void deleteCollaborateur(@PathVariable Long id) {
		collaborateurService.deleteCollaborateur(id);
	}

}
