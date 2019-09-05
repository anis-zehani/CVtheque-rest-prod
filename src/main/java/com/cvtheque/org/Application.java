package com.cvtheque.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cvtheque.org.service.AdministrateurService;
import com.cvtheque.org.util.AwsStorageService;

@SpringBootApplication
public class Application {
	
	@Autowired
	private final AdministrateurService administrateurService;
	
	@Autowired
	private final AwsStorageService aws;

	public Application(AdministrateurService administrateurService, AwsStorageService aws) {
		super();
		this.administrateurService = administrateurService;
		this.aws = aws;
		//Si SuperAdmin n'existe pas, je le met en place
		this.administrateurService.verifyOrAddAdmin();
		//this.aws.listAllBuckets();
		//this.aws.listAllObjects("cvtheque-smartgraphe");
	}


	
	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
        
		System.out.print("Anis Zaheni : Odix - le sourcing rendu facile sur www.odix.fr");
	}
}

