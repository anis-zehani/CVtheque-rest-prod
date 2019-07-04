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

import com.cvtheque.org.model.Candidat;
import com.cvtheque.org.service.CandidatService;
import com.cvtheque.org.util.StorageService;

@CrossOrigin
@RestController
@RequestMapping("/candidat")
public class CandidatController {
	
	@Autowired
	StorageService storageService;
	 
	List<String> files = new ArrayList<String>();
	  
	@Autowired
	private final CandidatService candidatService;
	
	private CandidatController(CandidatService candidatService) {
		this.candidatService = candidatService;
	}

	@GetMapping("/all/{etatCandidat}")
	List<Candidat> getAllCandidats(@PathVariable String etatCandidat) {
	    return candidatService.getAllCandidats(etatCandidat);
	}
	
	@GetMapping("{id}")
	Candidat getCandidat(@PathVariable Long id) {
		return candidatService.getCandidat(id);
	}
	
	//Ajouter un candidat
	@PostMapping()
	Candidat addCandidat(@Valid @RequestBody Candidat candidat) {
		return candidatService.addCandidat(candidat);
	}
	
	@PostMapping("addPhoto/{id}")
	Candidat addPhoto(@PathVariable Long id, @RequestParam("photo") MultipartFile photo) {
		//la photo est placée sur le serveur
	    String urlPhoto =  storageService.addPhoto(photo);
	    //la photo est affectée au candidat via son id
	    return candidatService.addPhotoToCandidat(id, urlPhoto);
	}
	
	@PostMapping("addCvOdix/{id}")
	Candidat addCvOdix(@PathVariable Long id, @RequestParam("cvOdix") MultipartFile cvOdix) {	
		//le CvOdix est placé sur le serveur
	    String urlCvOdix =  storageService.addCvOdix(cvOdix);
	    //le CvOdix est affecté au candidat via son id
	    return candidatService.addCvOdixToCandidat(id, urlCvOdix);
	}
	
	@PostMapping("addCvOriginal/{id}")
	Candidat addCvOriginal(@PathVariable Long id, @RequestParam("cvOriginal") MultipartFile cvOriginal) {
		//le CvOriginal est placé sur le serveur
	    String urlCvOriginal =  storageService.addCvOriginal(cvOriginal);
	    //le CvOriginal est affecté au candidat via son id
	    return candidatService.addCvOriginalToCandidat(id, urlCvOriginal);
	}
	
	
	@PutMapping()
	Candidat editCandidat(@Valid @RequestBody Candidat candidat) {
		return candidatService.editCandidat(candidat);
	}
	
	@PutMapping("/editEtat")
	Candidat editEtatCandidat(@Valid @RequestBody Candidat candidat) {
		return candidatService.editEtatCandidat(candidat);
	}
	
	@DeleteMapping("{id}")
	void deleteCandidat(@PathVariable Long id) {
		candidatService.deleteCandidat(id);
	}

}