package com.cvtheque.org.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class Consts {
	
			//Emplacament photo sur le serveur
			public static final Path rootLocation = Paths.get("/");
		 
			//Emplacament photo sur le serveur
			public static final Path rootLocationPhoto = Paths.get("/var/tmp/uploads/img/");
		  
			//Emplacament cvodix sur le serveur
			public static final Path rootLocationCvOdix = Paths.get("/var/tmp/uploads/cvodix/");
		  
			//Emplacament cvoriginal sur le serveur
			public static final Path rootLocationCvOriginal = Paths.get("/var/tmp/uploads/cvoriginal/");
		  
			//Emplacament fichier sur le serveur
			public static final Path rootLocationFichierRappel = Paths.get("/var/tmp/uploads/files/");
			
			//URL : uploads/img/
			public static final String urlUploadsImg = "img/";
			
			//URL : uploads/cvodix/
			public static final String urlUploadsCvOdix = "cvodix/";
					
			//URL : uploads/cvoriginal/
			public static final String urlUploadsCvOriginal = "cvoriginal/";
					
			//URL : uploads/files/
			public static final String urlUploadsFiles = "files/";
			
			//URL Avatar
			public static final String urlAvatar = "assets/img/avatar.png";
			
			//URL Money Avatar
			public static final String urlMoney = "assets/img/money.png";
	  
}
