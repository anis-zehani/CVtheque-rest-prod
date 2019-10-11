package com.cvtheque.org.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvtheque.org.model.Fichier;
import com.cvtheque.org.service.FichierService;

@CrossOrigin
@RestController
@RequestMapping("/api/fichier")
public class FichierController {
	
	@Autowired
	private final FichierService fichierService;
	
	FichierController(FichierService fichierService) {
		this.fichierService = fichierService;
	}
	
	@GetMapping()
	public List<Fichier> getAllFichiers() {
	    return fichierService.getAllFichiers();
	}

}
