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

import com.cvtheque.org.model.Ecole;
import com.cvtheque.org.service.EcoleService;

@CrossOrigin
@RestController
@RequestMapping("/ecole")
public class EcoleController {
	
	@Autowired
	private final EcoleService écoleService;
	
	private EcoleController(EcoleService écoleService) {
		this.écoleService = écoleService;
	}

	@GetMapping()
	List<Ecole> getAllEcoles() {
	    return écoleService.getAllEcoles();
	}
	
	@GetMapping("{id}")
	Optional<Ecole> getEcole(@PathVariable Long id) {
		return écoleService.getEcole(id);
	}
	
	@PostMapping()
	Ecole addEcole(@Valid @RequestBody Ecole Ecole) {
		return écoleService.addEcole(Ecole);
	}
	
	@PutMapping()
	Ecole editEcole(@Valid @RequestBody Ecole Ecole) {
		return écoleService.editEcole(Ecole);
	}
	
	@DeleteMapping("{id}")
	void deleteEcole(@PathVariable Long id) {
		écoleService.deleteEcole(id);
	}

}
