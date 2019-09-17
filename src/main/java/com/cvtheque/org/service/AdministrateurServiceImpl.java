package com.cvtheque.org.service;

import org.springframework.stereotype.Service;

import com.cvtheque.org.model.Administrateur;
import com.cvtheque.org.repository.AdministrateurRepository;

@Service
public class AdministrateurServiceImpl implements AdministrateurService{
	
	public final AdministrateurRepository administrateurRepository;
	
	public AdministrateurServiceImpl(AdministrateurRepository administrateurRepository) {
		super();
		this.administrateurRepository = administrateurRepository;
	}

	/*
	 * On vérifie s'il y a un Administrateur dans la base
	 * Si y a pas : on ajout un Super Admin avec un mot de passe dèja Bcrypte que seul Anis Zaheni connait
	 * 
	 */
	public void verifyOrAddAdmin(){
		
		Administrateur admin =  administrateurRepository.verifyAdmin("Administrateur");
		
		if(admin == null)
		{
			Administrateur superAdmin = new Administrateur();
			superAdmin.setUsername("anis");
			superAdmin.setPassword("$2a$10$tWZVsDODx11zOTpm/jdVU.Aw6GV0iHy12KE58boDl6.80eodnqngS");
			superAdmin.setIdentite("Anis Zaheni");
			superAdmin.setEmail("azaheni@smartgraphe.com");
			administrateurRepository.save(superAdmin);
		}
		
	}
}
