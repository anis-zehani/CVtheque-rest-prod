package com.cvtheque.org.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cvtheque.org.model.Administrateur;
import com.cvtheque.org.repository.AdministrateurRepository;

@Service
public class AdministrateurServiceImpl implements AdministrateurService{
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	public final AdministrateurRepository administrateurRepository;
	
	public AdministrateurServiceImpl(AdministrateurRepository administrateurRepository) {
		super();
		this.administrateurRepository = administrateurRepository;
	}

	public void verifyOrAddAdmin(){
		
		Administrateur admin =  administrateurRepository.verifyAdmin("Administrateur");
		
		if(admin == null)
		{
			Administrateur anis = new Administrateur();
			anis.setUsername("anis");
			anis.setPassword(bcryptEncoder.encode("Az@zel84"));
			anis.setIdentite("Anis Zaheni");
			administrateurRepository.save(anis);
		}
		
	}
}
