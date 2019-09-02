package com.cvtheque.org.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cvtheque.org.model.Contact;
import com.cvtheque.org.service.ContactService;
import com.cvtheque.org.util.StorageService;

@CrossOrigin
@RestController
@RequestMapping("/api/contact")
public class ContactController {
	
	@Autowired
	StorageService storageService;
	 
	List<String> files = new ArrayList<String>();
	  
	@Autowired
	private final ContactService contactService;
	
	ContactController(ContactService contactService) {
		this.contactService = contactService;
	}

	// Tous les contacts par idUtilisateur
	@GetMapping("/allContactsByIdUtilisateur/{idUtilisateur}")
	public List<Contact> getAllContacts(@PathVariable Long idUtilisateur) {
		
		List<Contact> contacts = contactService.getAllContacts(idUtilisateur);
	    return contacts;
	}
	
	@GetMapping("{id}")
	public Optional<Contact> getContact(@PathVariable Long id) {
		return contactService.getContact(id);
	}
	
	//Ajouter un Contact pour un utilisateur : (idUtilisateur existe dans l'objet Utilisateur envoyé à l'intérieur de l'objet Contact)
	@PostMapping()
	public Contact addContact(@Valid @RequestBody Contact contact) {
		return contactService.addContact(contact);
	}
	
	//Ajouter une photo à un Contact
	@PostMapping("addPhoto/{id}")
	public Contact addPhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
		//la photo est placée sur le serveur
	    String urlPhoto =  storageService.addPhoto(file);
	    //la photo est affectée au contact via son id
	    return contactService.addPhotoToContact(id, urlPhoto);
	}
	
	// Modifier un Contact pour un utilisateur : (idUtilisateur existe dans l'objet Utilisateur envoyé à l'intérieur de l'objet Contact)
	@PutMapping()
	public Contact editContact(@Valid @RequestBody Contact contact) {
		return contactService.editContact(contact);
	}
	
	@DeleteMapping("{id}")
	public void deleteContact(@PathVariable Long id) {
		contactService.deleteContact(id);
	}

}
