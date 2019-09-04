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
import com.cvtheque.org.util.LocalStorageService;

@CrossOrigin
@RestController
@RequestMapping("/api/partenaire")
public class PartenaireController {
	
	@Autowired
	LocalStorageService storageService;
	 
	List<String> files = new ArrayList<String>();
	  
	@Autowired
	private final PartenaireService partenaireService;
	
	PartenaireController(PartenaireService partenaireService) {
		this.partenaireService = partenaireService;
	}

	@GetMapping("/all/{etatPartenaire}")
	public List<Partenaire> getAllPartenaires(@PathVariable String etatPartenaire) {
	    return partenaireService.getAllPartenaires(etatPartenaire);
	}
	
	@GetMapping("{id}")
	public Optional<Partenaire> getPartenaire(@PathVariable Long id) {
		return partenaireService.getPartenaire(id);
	}
	
	//Liste des Partenaires Par Entreprise
	@GetMapping("/allPartenairesByEntreprise/{idEntreprise}")
	public List<Partenaire> getAllPartenairesByEntreprise(@PathVariable Long idEntreprise) {
	    return partenaireService.getAllPartenairesByEntreprise(idEntreprise);
	}

	@PostMapping()
	public Partenaire addPartenaire(@Valid @RequestBody Partenaire partenaire) {
		return partenaireService.addPartenaire(partenaire);
	}
	
	//Ajouter une photo à un partenaire :
	@PostMapping("addPhoto/{id}")
	public Partenaire addPhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
	//la photo est placée sur le serveur
	String urlPhoto =  storageService.addPhoto(file);
	//la photo est affectée au partenaire via son id
	return partenaireService.addPhotoToPartenaire(id, urlPhoto);

	}

	@PutMapping()
	public Partenaire editPartenaire(@Valid @RequestBody Partenaire partenaire) {
		return partenaireService.editPartenaire(partenaire);
	}
	
	@PutMapping("/editEtat")
	public Partenaire editEtatPartenaire(@Valid @RequestBody Partenaire partenaire) {
		return partenaireService.editEtatPartenaire(partenaire);
	}
	
	//UPDATE le lien entre un partenaire et une entreprise : met entreprise à NULL
	@PutMapping("/updateLinkPartenaireEntreprise/{idPartenaire}")
	public void updateLinkPartenaireEntreprise(@PathVariable Long idPartenaire) {
		partenaireService.updateLinkPartenaireEntreprise(idPartenaire);
	}
	
	@DeleteMapping("{id}")
	public void deletePartenaire(@PathVariable Long id) {
		partenaireService.deletePartenaire(id);
	}

}