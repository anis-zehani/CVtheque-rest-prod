package com.cvtheque.org.service;

import java.util.List;

import com.cvtheque.org.model.Fichier;

public interface FichierService {
	
	public List<Fichier> getAllFichiers();
	
	public void deleteFichier(String nomFichier);
}
