package com.cvtheque.org.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvtheque.org.model.Fichier;
import com.cvtheque.org.util.LocalStorageService;

@Service
public class FichierServiceImpl implements FichierService {
	
	@Autowired
	LocalStorageService localStorageService;

	@Override
	public List<Fichier> getAllFichiers() {
		
		List<Fichier> listeFichiers = new ArrayList<>();
		
		String[] files = localStorageService.listAllFilesOfRootDirectory();
		
		// Récupère les noms des fichiers, créé un objet Fichier, modifie le paramètres nomFichier de l'object
		// puis l'insère dans la liste à retourner vers UI
		try {
	        for (int i = 0; i < files.length; i++) { 
	        	Fichier fichier = new Fichier();
	        	fichier.setNomFichier(files[i]);
	        	fichier.setUrlFichier("uploads/"+files[i]);
	        	listeFichiers.add(fichier);
	        } 
	        return listeFichiers;
		}catch (Exception e) {
			
			return listeFichiers;
		}

		
	}

}
