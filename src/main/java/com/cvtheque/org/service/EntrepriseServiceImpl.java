package com.cvtheque.org.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cvtheque.org.model.Entreprise;
import com.cvtheque.org.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements EntrepriseService{
	
	private final EntrepriseRepository entrepriseRepository;

	private EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
		super();
		this.entrepriseRepository = entrepriseRepository;
	}
	
	public List<Entreprise> getAllEntreprises(){
		return entrepriseRepository.findAll();
	}
	
	public Optional<Entreprise> getEntreprise(Long id){
		return entrepriseRepository.findById(id);
	}
	
	//Ajouter une entreprise
	public Entreprise addEntreprise(Entreprise entreprise)
	{
		if(entrepriseRepository.findByNomEntreprise(entreprise.getNomEntreprise()) == null)
		{
			return entrepriseRepository.save(entreprise);
		}
		return null;
	}
	
	//Modifier une entreprise
	public Entreprise editEntreprise(Entreprise entreprise)
	{
		if(entrepriseRepository.existsById(entreprise.getIdEntreprise()))
		{
			return entrepriseRepository.save(entreprise);
		}
		return null;
	}
	
	//Supprimer une entreprise
	public void deleteEntreprise(Long id)
	{
		if(entrepriseRepository.existsById(id))
		{
			entrepriseRepository.deleteById(id);
		}
	}

}