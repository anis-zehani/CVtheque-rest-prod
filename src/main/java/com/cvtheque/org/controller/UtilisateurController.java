package com.cvtheque.org.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvtheque.org.util.LinkedinUtil;

import net.minidev.json.JSONObject;

@CrossOrigin
@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {
	
	@Autowired
	LinkedinUtil linkedInUtil;
	
	@GetMapping(value = "/code-linkedin")
	public JSONObject codeLinkedin() {
		
		return linkedInUtil.codeLinkedin();
	}
	
	@PostMapping("/redirect-linkedin/{code}/{state}")
	public JSONObject redirectLinkedin(@PathVariable String code, @PathVariable String state) throws Exception {
		
		JSONObject profileLinkedIn = linkedInUtil.redirectLinkedin(code, state);
		
		return profileLinkedIn;
	}

}
