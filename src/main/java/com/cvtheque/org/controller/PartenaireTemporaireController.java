package com.cvtheque.org.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvtheque.org.model.PartenaireTemporaire;
import com.cvtheque.org.service.PartenaireTemporaireService;

@CrossOrigin
@RestController
@RequestMapping("/api/partenaire-temporaire-controller")
public class PartenaireTemporaireController {
	
	@Autowired
	private final PartenaireTemporaireService partenaireTemporaireService;
	
	public PartenaireTemporaireController(PartenaireTemporaireService partenaireTemporaireService) {
		super();
		this.partenaireTemporaireService = partenaireTemporaireService;
	}

	//Ajouter un Partenaire Temporaire
	@PostMapping()
	public PartenaireTemporaire addPartenaireTemporaire(@Valid @RequestBody PartenaireTemporaire partenaireTemporaire) {
		return partenaireTemporaireService.addPartenaireTemporaire(partenaireTemporaire);
	}
}
