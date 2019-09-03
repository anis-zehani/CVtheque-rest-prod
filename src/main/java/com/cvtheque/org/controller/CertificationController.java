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

import com.cvtheque.org.model.Certification;
import com.cvtheque.org.service.CertificationService;

@CrossOrigin
@RestController
@RequestMapping("/api/certification")
public class CertificationController {
	
	@Autowired
	private final CertificationService certificationService;
	
	CertificationController(CertificationService certificationService) {
		this.certificationService = certificationService;
	}

	@GetMapping()
	public List<Certification> getAllCertifications() {
	    return certificationService.getAllCertifications();
	}
	
	@GetMapping("{id}")
	public Optional<Certification> getCertification(@PathVariable Long id) {
		return certificationService.getCertification(id);
	}
	
	//Ajouter une Certification pour un utilisateur : (idUtilisateur existe dans l'objet Utilisateur envoyé à l'intérieur de l'objet Certification)
	@PostMapping()
	public Certification addCertification(@Valid @RequestBody Certification certification) {
		return certificationService.addCertification(certification);
	}
	
	//Modifier une Certification pour un utilisateur : (idUtilisateur existe dans l'objet Utilisateur envoyé à l'intérieur de l'objet Certification)
	@PutMapping()
	public Certification editCertification(@Valid @RequestBody Certification certification) {
		return certificationService.editCertification(certification);
	}
	
	@DeleteMapping("{id}")
	public boolean deleteCertification(@PathVariable Long id) {
		return certificationService.deleteCertification(id);
	}

}
