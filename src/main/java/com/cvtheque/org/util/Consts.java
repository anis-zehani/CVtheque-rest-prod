package com.cvtheque.org.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class Consts {
			
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
*/

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
			

}
