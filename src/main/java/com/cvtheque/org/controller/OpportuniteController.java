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
import org.springframework.web.bind.annotation.RestController;

import com.cvtheque.org.model.Opportunite;
import com.cvtheque.org.service.OpportuniteService;

@CrossOrigin
@RestController
@RequestMapping("/api/opportunite")
public class OpportuniteController {
	 
	@Autowired
	private final OpportuniteService opportuniteService;
	
	OpportuniteController(OpportuniteService opportuniteService) {
		this.opportuniteService = opportuniteService;
	}

	//@Secured("ROLE_ADMINISTRATEUR")
	@GetMapping("/all/{etatOpportunite}")
	public List<Opportunite> getAllOpportunites(@PathVariable String etatOpportunite) {
	    return opportuniteService.getAllOpportunites(etatOpportunite);
	}
	
	//Liste des opportunités favories pour un Utilisateur (Administrateur/Partenaire)
	@GetMapping("/getAllOpportunitesFavorisForUtilisateur/{idUtilisateur}")
	public List<Opportunite> getAllOpportunitesFavorisForUtilisateur(@PathVariable Long idUtilisateur){
		
		return opportuniteService.getAllOpportunitesFavorisForUtilisateur(idUtilisateur);
	}
	
	// Retourne les Opportunités Publique + les Opportunités Privée du Partenaire connecté
	@GetMapping("/allOpportunitesPublicAndPrivateByPartenaire/{etatOpportunite}/{id}")
	public List<Opportunite> getAllOpportunitesPublicAndPrivateByPartenaire(@PathVariable String etatOpportunite, @PathVariable Long id) {
	    return opportuniteService.getAllOpportunitesPublicAndPrivateByPartenaire(etatOpportunite, id);
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
	
	@GetMapping("/allOpportunitesByListTechnologies/{listTechnologies}")
	//La liste des Opportunités qui ont une Technologie au moins dans la liste fournie
	public List<Opportunite> getAllOpportunitesByListTechnologies(@PathVariable ArrayList<Long> listTechnologies){
		return opportuniteService.getAllOpportunitesByListTechnologies(listTechnologies);
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
	
	//Update le lien entre une opportunité et un partenaire : met responsableOpportunite à NULL
	@PutMapping("/updateLinkOpportunitePartenaire/{idOpportunite}")
	public void updateLinkOpportunitePartenaire(@PathVariable Long idOpportunite) {
		opportuniteService.updateLinkOpportunitePartenaire(idOpportunite);
	}
	
	@DeleteMapping("{id}")
	public void deleteOpportunite(@PathVariable Long id) {
		opportuniteService.deleteOpportunite(id);
	}
	
	//Supprimer le lien entre une opportunité et une technologie
	@DeleteMapping("/deleteLinkOpportuniteTechnologie/{idOpportunite}/{idTechnologie}")
	public void deleteLinkOpportuniteTechnologie(@PathVariable Long idOpportunite, @PathVariable Long idTechnologie) {
		opportuniteService.deleteLinkOpportuniteTechnologie(idOpportunite, idTechnologie);
	}
	
	//Supprimer le lien entre une opportunité et une certification
	@DeleteMapping("/deleteLinkOpportuniteCertification/{idOpportunite}/{idCertification}")
	public void deleteLinkOpportuniteCertification(@PathVariable Long idOpportunite, @PathVariable Long idCertification) {
		opportuniteService.deleteLinkOpportuniteCertification(idOpportunite, idCertification);
	}	

}