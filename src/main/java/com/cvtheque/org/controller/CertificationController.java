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
@RequestMapping("/certification")
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
	
	@PostMapping()
	public Certification addCertification(@Valid @RequestBody Certification certification) {
		return certificationService.addCertification(certification);
	}
	
	@PutMapping()
	public Certification editCertification(@Valid @RequestBody Certification certification) {
		return certificationService.editCertification(certification);
	}
	
	@DeleteMapping("{id}")
	public boolean deleteCertification(@PathVariable Long id) {
		return certificationService.deleteCertification(id);
	}

}
