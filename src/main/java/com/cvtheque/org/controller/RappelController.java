package com.cvtheque.org.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cvtheque.org.model.Rappel;
import com.cvtheque.org.service.ProjetService;
import com.cvtheque.org.service.RappelService;
import com.cvtheque.org.util.StorageService;

@CrossOrigin
@RestController
@RequestMapping("/api/rappel")
public class RappelController {
	
	@Autowired
	StorageService storageService;
	
	@Autowired
	private final RappelService rappelService;
	
	@Autowired
	private final ProjetService projetService;
	
	RappelController(RappelService rappelService, ProjetService projetService) {
		this.rappelService = rappelService;
		this.projetService = projetService;
	}

	@GetMapping()
	public List<Rappel> getAllRappels() {
	    return rappelService.getAllRappels();
	}
	
	@GetMapping("/allRappelsByToday")
	public List<Rappel> getAllRappelsByToday() {
	    return rappelService.getAllRappelsByToday();
	}
	
	@GetMapping("/allRappelsByNext7Days")
	public List<Rappel> getAllRappelsByNext7Days() {
	    return rappelService.getAllRappelsByNext7Days();
	}
	
	@GetMapping("/allRappelsByProjet/{idProjet}")
	public List<Rappel> getAllRappelsByProjet(@PathVariable Long idProjet) {
	    return rappelService.getAllRappelsByProjet(projetService.getProjet(idProjet));
	}
	
	@GetMapping("/allRappelsByPriorite/{valeurPriorite}")
	public List<Rappel> getAllRappelsByPriorite(@PathVariable String valeurPriorite) {
	    return rappelService.getAllRappelsByPriorite(valeurPriorite);
	}
	
	@GetMapping("{id}")
	public Rappel getRappel(@PathVariable Long id) {
		return rappelService.getRappel(id);
	}
	
	@PostMapping()
	public Rappel addRappel(@Valid @RequestBody Rappel rappel) {
		return rappelService.addRappel(rappel);
	}
	
	//Ajouter un Fichier à un rappel :
	@PostMapping("addFichier/{id}")
	public Rappel addFichier(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
	
	//le Fichier est placé sur le serveur : on récupére un ArrayList avec le nom original + le nom modifié
	ArrayList<String> files = storageService.addFichierRappel(file);
	String urlFichier =  files.get(0);
	String nomFichier =  files.get(1);
	
	//le Fichier est affecté au rappel via son id
	return rappelService.addFichierToRappel(id, urlFichier, nomFichier);
	}
	
	@PutMapping()
	public Rappel editRappel(@Valid @RequestBody Rappel rappel) {
		return rappelService.editRappel(rappel);
	}
	
	@DeleteMapping("{id}")
	public void deleteRappel(@PathVariable Long id) {
		rappelService.deleteRappel(id);
	}

}
