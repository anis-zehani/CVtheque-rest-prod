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
	
	CandidatController(CandidatService candidatService) {
		this.candidatService = candidatService;
	}

	@GetMapping("/all/{etatCandidat}")
	public List<Candidat> getAllCandidats(@PathVariable String etatCandidat) {
	    return candidatService.getAllCandidats(etatCandidat);
	}
	
	//Lister les candidats par ID Opportunité
	@GetMapping("/allCandidatsByOpportunite/{id}")
	public List<Candidat> getAllCandidatsByOpportunite(@PathVariable Long id) {
	    return candidatService.getAllCandidatsByOpportunite(id);
	}
	
	//Lister les candidats par ID Technologie
	@GetMapping("/allCandidatsByTechnologie/{id}")
	public List<Candidat> getAllCandidatsByTechnologie(@PathVariable Long id) {
	    return candidatService.getAllCandidatsByTechnologie(id);
	}
	
	//Lister les candidats par ID Certification
	@GetMapping("/allCandidatsByCertification/{id}")
	public List<Candidat> getAllCandidatsByCertification(@PathVariable Long id) {
	    return candidatService.getAllCandidatsByCertification(id);
	}
	
	//Lister les candidats par ID Entreprise
	@GetMapping("/allCandidatsByEntreprise/{id}")
	public List<Candidat> getAllCandidatsByEntreprise(@PathVariable Long id) {
	    return candidatService.getAllCandidatsByEntreprise(id);
	}
	
	@GetMapping("{id}")
	public Candidat getCandidat(@PathVariable Long id) {
		return candidatService.getCandidat(id);
	}
	
	//Ajouter un candidat
	@PostMapping()
	public Candidat addCandidat(@Valid @RequestBody Candidat candidat) {
		return candidatService.addCandidat(candidat);
	}
	
	@PostMapping("addPhoto/{id}")
	public Candidat addPhoto(@PathVariable Long id, @RequestParam("photo") MultipartFile photo) {
		//la photo est placée sur le serveur
	    String urlPhoto =  storageService.addPhoto(photo);
	    //la photo est affectée au candidat via son id
	    return candidatService.addPhotoToCandidat(id, urlPhoto);
	}
	
	@PostMapping("addCvOdix/{id}")
	public Candidat addCvOdix(@PathVariable Long id, @RequestParam("cvOdix") MultipartFile cvOdix) {	
		//le CvOdix est placé sur le serveur
	    String urlCvOdix =  storageService.addCvOdix(cvOdix);
	    //le CvOdix est affecté au candidat via son id
	    return candidatService.addCvOdixToCandidat(id, urlCvOdix);
	}
	
	@PostMapping("addCvOriginal/{id}")
	public Candidat addCvOriginal(@PathVariable Long id, @RequestParam("cvOriginal") MultipartFile cvOriginal) {
		//le CvOriginal est placé sur le serveur
	    String urlCvOriginal =  storageService.addCvOriginal(cvOriginal);
	    //le CvOriginal est affecté au candidat via son id
	    return candidatService.addCvOriginalToCandidat(id, urlCvOriginal);
	}
	
	//Permet de lier des candidats à une opportunité récement créée
	@PostMapping("addCandidatsToOpportunite/{idOpportunite}/{withDeletion}")
	public void addCandidatsToOpportunite(@PathVariable Long idOpportunite, @RequestBody ArrayList<Candidat> listeCandidats, @PathVariable boolean withDeletion) {

		candidatService.addCandidatsToOpportunite(idOpportunite, listeCandidats, withDeletion);
	}
	
	
	@PutMapping()
	public Candidat editCandidat(@Valid @RequestBody Candidat candidat) {
		return candidatService.editCandidat(candidat);
	}
	
	@PutMapping("/editEtat")
	public Candidat editEtatCandidat(@Valid @RequestBody Candidat candidat) {
		return candidatService.editEtatCandidat(candidat);
	}
	
	//Update le lien entre un candidat et une entreprise : met entreprise à NULL
	@PutMapping("/updateLinkCandidatEntreprise")
	public void updateLinkCandidatEntreprise(@Valid @RequestBody Long idCandidat) {
			   candidatService.updateLinkCandidatEntreprise(idCandidat);
	}
	
	@DeleteMapping("{id}")
	public void deleteCandidat(@PathVariable Long id) {
			   candidatService.deleteCandidat(id);
	}
	
	//Supprimer le lien entre un candidat et une opportunité
	@DeleteMapping("/deleteLinkCandidatOpportunite/{idCandidat}/{idOpportunite}")
	public void deleteLinkCandidatOpportunite(@PathVariable Long idCandidat, @PathVariable Long idOpportunite) {
			   candidatService.deleteLinkCandidatOpportunite(idCandidat, idOpportunite);
	}
	
	//Supprimer le lien entre un candidat et une technologie
	@DeleteMapping("/deleteLinkCandidatTechnologie/{idCandidat}/{idTechnologie}")
	public void deleteLinkCandidatTechnologie(@PathVariable Long idCandidat, @PathVariable Long idTechnologie) {
			   candidatService.deleteLinkCandidatTechnologie(idCandidat, idTechnologie);
	}
	
	//Supprimer le lien entre un candidat et une certification
	@DeleteMapping("/deleteLinkCandidatCertification/{idCandidat}/{idCertification}")
	public void deleteLinkCandidatCertification(@PathVariable Long idCandidat, @PathVariable Long idCertification) {
			   candidatService.deleteLinkCandidatCertification(idCandidat, idCertification);
	}

}