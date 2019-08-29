package com.cvtheque.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cvtheque.org.service.AdministrateurService;

@SpringBootApplication
public class Application {
	
	//@Resource
	//StorageService storageService;
	

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
		AdministrateurService administrateurService = applicationContext.getBean(AdministrateurService.class);
		administrateurService.verifyOrAddAdmin();
        
		System.out.print("Anis Zaheni : Odix - le sourcing rendu facile sur www.odix.fr");

	}
}

