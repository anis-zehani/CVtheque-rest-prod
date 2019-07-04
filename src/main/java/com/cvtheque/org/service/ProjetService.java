package com.cvtheque.org.service;

import java.util.List;

import com.cvtheque.org.model.Projet;

public interface ProjetService {
	
	public List<Projet> getAllProjets();
	
	public Projet getProjet(Long id);
	
	public Projet addProjet(Projet projet);
	
	public Projet editProjet(Projet projet);
	
	public void deleteProjet(Long id);

}
