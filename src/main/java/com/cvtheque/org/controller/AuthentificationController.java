package com.cvtheque.org.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvtheque.org.model.Utilisateur;
import com.cvtheque.org.service.UtilisateurService;

@CrossOrigin
@RestController
@RequestMapping("/api/utilisateur")
public class AuthentificationController {
	
	//@Autowired
	//private final UtilisateurService utilisateurService;

	/*public AuthentificationController(UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}*/
	
	/*@GetMapping("/authenticate")
	public void authenticate(@RequestBody Utilisateur utilisateur) {
		utilisateurService.authenticate(utilisateur);
	}*/
	
	@GetMapping("/validateLogin")
	public Utilisateur validateLogin() {
		return new Utilisateur();
	}

}
