package com.cvtheque.org;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cvtheque.org.util.StorageService;

@SpringBootApplication
public class Application {
	
	@Resource
	StorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		System.out.print("Anis Zaheni : Odix - le sourcing rendu facile sur www.odix.fr");

	}
}

