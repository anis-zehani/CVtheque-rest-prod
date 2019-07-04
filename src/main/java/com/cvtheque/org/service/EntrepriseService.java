package com.cvtheque.org.service;

import java.util.List;
import java.util.Optional;

import com.cvtheque.org.model.Entreprise;

public interface EntrepriseService {
	
	public List<Entreprise> getAllEntreprises();
	
	public Optional<Entreprise> getEntreprise(Long id);
	
	public Entreprise addEntreprise(Entreprise entreprise);
	
	public Entreprise editEntreprise(Entreprise entreprise);
	
	public void deleteEntreprise(Long id);

}
