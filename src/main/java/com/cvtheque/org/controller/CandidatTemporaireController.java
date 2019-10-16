package com.cvtheque.org.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvtheque.org.model.CandidatTemporaire;
import com.cvtheque.org.service.CandidatTemporaireService;

@CrossOrigin
@RestController
@RequestMapping("/api/candidat-temporaire-controller")
public class CandidatTemporaireController {
	
	@Autowired
	private final CandidatTemporaireService candidatTemporaireService;
	
	public CandidatTemporaireController(CandidatTemporaireService candidatTemporaireService) {
		super();
		this.candidatTemporaireService = candidatTemporaireService;
	}

	//Ajouter un Candidat Temporaire
	@PostMapping()
	public CandidatTemporaire addCandidatTemporaire(@Valid @RequestBody CandidatTemporaire candidatTemporaire) {
		return candidatTemporaireService.addCandidatTemporaire(candidatTemporaire);
	}
}
