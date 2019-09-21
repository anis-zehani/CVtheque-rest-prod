package com.cvtheque.org.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvtheque.org.util.LinkedInUtil;

import net.minidev.json.JSONObject;

@CrossOrigin
@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {
	
	@Autowired
	LinkedInUtil linkedInUtil;
	
	@GetMapping(value = "/linkedIn")
	public JSONObject connectWithLinkedIn() {
		
		return linkedInUtil.connectWithLinkedIn();
	}
	
	@PostMapping("/redirectLinkedIn/{code}/{state}")
	public JSONObject redirectLinkedIn(@PathVariable String code, @PathVariable String state) throws Exception {
		
		JSONObject profileLinkedIn = linkedInUtil.redirectLinkedIn(code, state);
		
		return profileLinkedIn;
	}

}
