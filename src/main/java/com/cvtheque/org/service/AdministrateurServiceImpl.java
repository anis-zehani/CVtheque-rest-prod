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
		
		Administrateur admin =  administrateurRepository.verifyAdmin("ROLE_ADMINISTRATEUR");
		
		if(admin == null)
		{
			Administrateur superAdmin = new Administrateur();
			superAdmin.setUsername("odix");
			superAdmin.setPassword("$2a$10$tWZVsDODx11zOTpm/jdVU.Aw6GV0iHy12KE58boDl6.80eodnqngS");
			superAdmin.setIdentite("Odix");
			superAdmin.setEmail("contact@odix.fr");
			superAdmin.setUrlPhoto("https://media.licdn.com/dms/image/C4D03AQGvl_z8bsx2Ew/profile-displayphoto-shrink_200_200/0?e=1577318400&v=beta&t=Fx9LN6el85eyVDPHiDIBUwPDbDiDa1zVO3bRc0obNt8");
			administrateurRepository.save(superAdmin);
		}
		
	}
}
