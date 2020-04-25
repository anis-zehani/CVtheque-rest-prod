package com.cvtheque.org.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvtheque.org.service.StatistiquesService;

@CrossOrigin
@RestController
@RequestMapping("/api/statistiques")
public class StatistiquesController {
	
	@Autowired
	StatistiquesService statistiquesService;
	
	//Retourne les 6 chiffres clés de base
	@GetMapping("/all")
	public Map<String, Long> getStatistiques() {
	    return statistiquesService.getChiffreCles();
	}
}
