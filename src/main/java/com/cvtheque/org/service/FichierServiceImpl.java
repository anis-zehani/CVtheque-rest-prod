package com.cvtheque.org.service;

import java.io.File;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvtheque.org.model.Fichier;
import com.cvtheque.org.util.Consts;
import com.cvtheque.org.util.LocalStorageService;

@Service
public class FichierServiceImpl implements FichierService {
	
	@Autowired
	LocalStorageService localStorageService;
	private static final Path rootLocation = Consts.rootLocation;
	
	@Override
	public List<Fichier> getAllFichiers() {
		
		List<Fichier> listeFichiers = new ArrayList<>();

		String[] files = localStorageService.listAllFilesOfRootDirectory();
		
		/* Récupère les noms des fichiers, créé un objet Fichier, modifie le paramètres nomFichier de l'object
		   puis l'insère dans la liste à retourner vers UI */
		try {
	        for (int i = 0; i < files.length; i++) { 
	        	File file = new File(rootLocation.toString()+"/"+files[i]); 
	        	LocalDateTime dateCreationFichier = LocalDateTime.ofInstant(Instant.ofEpochMilli(file.lastModified()), ZoneId.systemDefault());
	        	// Taille en Kb
	        	Long tailleFichierKB = file.length() / 1024; // Pour le MB : il faut diviser par 1024;
	        	
	        	Fichier fichier = new Fichier();
	        	fichier.setNomFichier(files[i]);
	        	fichier.setUrlFichier("uploads/"+files[i]);
	        	fichier.setDateCreationFichier(dateCreationFichier.toLocalDate().toString()+ " à "+dateCreationFichier.toLocalTime().toString());
	        	fichier.setTailleFichier(tailleFichierKB.toString()+ " Kb");
	        	listeFichiers.add(fichier);
	        } 
	        return listeFichiers;
		}catch (Exception e) {
			
			return listeFichiers;
		}

		
	}

}
