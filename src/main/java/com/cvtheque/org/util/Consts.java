package com.cvtheque.org.util;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public final class Consts {
	
			@Autowired
			private Environment env;
			private String environnement = env.getProperty("environnement");
			
			public static String urlPlatformeResetPassword = "";
			public static String urlPlatformeActivateCandidat = "";
			public static String urlPlatformeLoginPage = "";
			//Emplacament photo sur le serveur physique : container Rest
			public static Path rootLocation = null;
			//Emplacament photo sur le serveur physique : container Rest
			public static Path rootLocationPhoto = null;
			//Emplacament cvodix sur le serveur physique : container Rest
			public static Path rootLocationCvOdix = null;
			//Emplacament cvoriginal sur le serveur physique : container Rest
			public static Path rootLocationCvOriginal = null;
			//Emplacament fichier sur le serveur physique : container Rest
			public static Path rootLocationFichierRappel = null;
			
		
			public Consts() {
				super();
				if (environnement.contains("PROD")) {
					urlPlatformeResetPassword = "http://plateforme.odix.fr/reset-password/";
					urlPlatformeActivateCandidat = "http://plateforme.odix.fr/activation-compte/";
					urlPlatformeLoginPage = "http://plateforme.odix.fr/";
					//Emplacament photo sur le serveur physique : container Rest
					rootLocation = Paths.get("/uploads");
					//Emplacament photo sur le serveur physique : container Rest
					rootLocationPhoto = Paths.get("/uploads");
					//Emplacament cvodix sur le serveur physique : container Rest
					rootLocationCvOdix = Paths.get("/uploads");
					//Emplacament cvoriginal sur le serveur physique : container Rest
					rootLocationCvOriginal = Paths.get("/uploads");
					//Emplacament fichier sur le serveur physique : container Rest
					rootLocationFichierRappel = Paths.get("/uploads");
				}else {
					urlPlatformeResetPassword = "http://localhost:4200/reset-password/";
					urlPlatformeActivateCandidat = "http://localhost:4200/activation-compte/";
					urlPlatformeLoginPage = "http://localhost:4200/";
					//Emplacament photo sur le serveur physique : container Rest
					rootLocation = Paths.get("/var/tmp/uploads/");
					//Emplacament photo sur le serveur physique : container Rest
					rootLocationPhoto = Paths.get("/var/tmp/uploads/");
					//Emplacament cvodix sur le serveur physique : container Rest
					rootLocationCvOdix = Paths.get("/var/tmp/uploads/");
					//Emplacament cvoriginal sur le serveur physique : container Rest
					rootLocationCvOriginal = Paths.get("/var/tmp/uploads/");
					//Emplacament fichier sur le serveur physique : container Rest
					rootLocationFichierRappel = Paths.get("/var/tmp/uploads/");
				}
			}
			
			
			/*
			
			public static final String urlPlatformeResetPassword = "http://plateforme.odix.fr/reset-password/";
			public static final String urlPlatformeActivateCandidat = "http://plateforme.odix.fr/activation-compte/";
			public static final String urlPlatformeLoginPage = "http://plateforme.odix.fr/";
			//Emplacament photo sur le serveur physique : container Rest
			public static final Path rootLocation = Paths.get("/uploads");
			//Emplacament photo sur le serveur physique : container Rest
			public static final Path rootLocationPhoto = Paths.get("/uploads");
			//Emplacament cvodix sur le serveur physique : container Rest
			public static final Path rootLocationCvOdix = Paths.get("/uploads");
			//Emplacament cvoriginal sur le serveur physique : container Rest
			public static final Path rootLocationCvOriginal = Paths.get("/uploads");
			//Emplacament fichier sur le serveur physique : container Rest
			public static final Path rootLocationFichierRappel = Paths.get("/uploads");


			public static final String urlPlatformeResetPassword = "http://localhost:4200/reset-password/";
			public static final String urlPlatformeActivateCandidat = "http://localhost:4200/activation-compte/";
			public static final String urlPlatformeLoginPage = "http://localhost:4200/";
			//Emplacament photo sur le serveur physique : container Rest
			public static final Path rootLocation = Paths.get("/var/tmp/uploads/");
			//Emplacament photo sur le serveur physique : container Rest
			public static final Path rootLocationPhoto = Paths.get("/var/tmp/uploads/");
			//Emplacament cvodix sur le serveur physique : container Rest
			public static final Path rootLocationCvOdix = Paths.get("/var/tmp/uploads/");
			//Emplacament cvoriginal sur le serveur physique : container Rest
			public static final Path rootLocationCvOriginal = Paths.get("/var/tmp/uploads/");
			//Emplacament fichier sur le serveur physique : container Rest
			public static final Path rootLocationFichierRappel = Paths.get("/var/tmp/uploads/");
			
			*/
}
