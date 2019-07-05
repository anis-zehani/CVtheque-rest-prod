package com.cvtheque.org.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cvtheque.org.model.Contact;
import com.cvtheque.org.repository.ContactRepository;
import com.cvtheque.org.util.Consts;
import com.cvtheque.org.util.StorageService;

@Service
public class ContactServiceImpl implements ContactService{
	
	private final ContactRepository contactRepository;
	private final StorageService storageService;
	
	private ContactServiceImpl(ContactRepository contactRepository, StorageService storageService) {
		super();
		this.contactRepository = contactRepository;
		this.storageService = storageService;
	}

	public List<Contact> getAllContacts() {
		
		List<Contact> allContacts = contactRepository.findAll();
		
		return allContacts;
	}

	public Optional<Contact> getContact(Long id) {
		return contactRepository.findById(id);
	}

	//Ajouter un contact
	public Contact addContact(Contact contact) {
		
		if(contact.getEntreprise().getIdEntreprise() == null)
		{
			//Obligatoire pour @ManyToOne
			contact.setEntreprise(null);
		}
		//On met l'image par défaut à tout le monde : elle pourra être écrasée plus tard
		contact.setUrlPhoto(Consts.urlAvatar.replace("\"", ""));
			
		return contactRepository.save(contact);
	}
	
	//Affecter une photo à un contact (fonction appelée dans Ajout + Update)
	public Contact addPhotoToContact(Long id, String urlPhoto) {
		
		if(contactRepository.existsById(id))
		{
			Contact contact = contactRepository.getOne(id);
			
			//delete ancienne photo : si elle existe dans le cas d'un Update
			if(contact.getUrlPhoto() != null && contact.getUrlPhoto().startsWith(Consts.urlAvatar.replace("\"", ""))==false)
			{
				storageService.deletePhoto(contact.getUrlPhoto());
			}
		
			//update URL photo avec nouveau nom
			contact.setUrlPhoto(urlPhoto);

			return contactRepository.save(contact);
		}
		
		return null;
	}

	//Modifier un contact
	public Contact editContact(Contact contact) {
		
		//L'Update url photo se fait en haut dans la fonction addPhotoToContact
		if(contactRepository.existsById(contact.getId()))
		{
			if(contact.getEntreprise().getIdEntreprise() == null)
			{
				contact.setEntreprise(null);
			}

			return contactRepository.save(contact);
		}
		return null;
	}

	//Supprimer un contact
	public void deleteContact(Long id) {
		
		if(contactRepository.existsById(id))
		{
			Contact contact = contactRepository.getOne(id);

			try
			{
				//On supprime d'abord la photo si ce n'est pas un avatar
				if(contact.getUrlPhoto() != null && contact.getUrlPhoto().startsWith(Consts.urlAvatar.replace("\"", ""))==false)
				{
					storageService.deletePhoto(Consts.rootLocation+contact.getUrlPhoto());
				}
			}
			catch(NoSuchElementException e) 
			{
				System.out.print("Erreur durant deleteCandidat :"+e);
			}
			
			//On supprime la ligne de la base
			contactRepository.deleteById(id);
		}
	}

}
