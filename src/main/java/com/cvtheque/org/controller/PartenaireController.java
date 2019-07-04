package com.cvtheque.org.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cvtheque.org.model.Partenaire;
import com.cvtheque.org.service.PartenaireService;
import com.cvtheque.org.util.StorageService;

@CrossOrigin
@RestController
@RequestMapping("/partenaire")
public class PartenaireController {
	
	@Autowired
	StorageService storageService;
	 
	List<String> files = new ArrayList<String>();
	  
	@Autowired
	private final PartenaireService partenaireService;
	
	private PartenaireController(PartenaireService partenaireService) {
		this.partenaireService = partenaireService;
	}

	@GetMapping("/all/{etatPartenaire}")
	List<Partenaire> getAllPartenaires(@PathVariable String etatPartenaire) {
	    return partenaireService.getAllPartenaires(etatPartenaire);
	}
	
	@GetMapping("{id}")
	Optional<Partenaire> getPartenaire(@PathVariable Long id) {
		return partenaireService.getPartenaire(id);
	}
	
	/*
	 * Faire le tout dans un seul Request
	@PostMapping()
	Partenaire addPartenaire(@RequestPart("partenaire") Partenaire partenaire, @RequestPart("file") MultipartFile file) {
		
		Long partenaireId =  partenaireService.addPartenaire(partenaire).getId();
		String urlPhoto =  storageService.addPhoto(file);
		return partenaireService.addPhotoToPartenaire(partenaireId, urlPhoto);
	}
	*/
	

	@PostMapping()
	Partenaire addPartenaire(@Valid @RequestBody Partenaire partenaire) {
		return partenaireService.addPartenaire(partenaire);
	}
	
	//Ajouter une photo à un partenaire :
	@PostMapping("addPhoto/{id}")
	Partenaire addPhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
	//la photo est placée sur le serveur
	String urlPhoto =  storageService.addPhoto(file);
	//la photo est affectée au partenaire via son id
	return partenaireService.addPhotoToPartenaire(id, urlPhoto);

	}

	
	@PutMapping()
	Partenaire editPartenaire(@Valid @RequestBody Partenaire partenaire) {
		return partenaireService.editPartenaire(partenaire);
	}
	
	@PutMapping("/editEtat")
	Partenaire editEtatPartenaire(@Valid @RequestBody Partenaire partenaire) {
		return partenaireService.editEtatPartenaire(partenaire);
	}
	
	@DeleteMapping("{id}")
	void deletePartenaire(@PathVariable Long id) {
		partenaireService.deletePartenaire(id);
	}

}