package com.cvtheque.org.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cvtheque.org.model.Technologie;
import com.cvtheque.org.repository.TechnologieRepository;


@Service
public class TechnologieServiceImpl implements TechnologieService{
	
	private final TechnologieRepository technologieRepository;

	private TechnologieServiceImpl(TechnologieRepository technologieRepository) {
		super();
		this.technologieRepository = technologieRepository;
	}
	
	public List<Technologie> getAllTechnologies() {
	    return technologieRepository.findAll();
	}
	
	public Optional<Technologie> getTechnologie(Long id) {
		return technologieRepository.findById(id);
	}
	
	//Ajouter une technologie
	public Technologie addTechnologie(Technologie technologie) 
	{
		if(technologieRepository.findByNomTechnologie(technologie.getNomTechnologie()) == null)
		{
			return technologieRepository.save(technologie);
		}
		return null;
	}
	
	//Modifier une technologie
	public Technologie editTechnologie(Technologie technologie) 
	{
		if(technologieRepository.existsById(technologie.getId()))
		{
			return technologieRepository.save(technologie);
		}
		return null;
	}
	
	//Supprimer une technologie
	public void deleteTechnologie(Long id) 
	{
		if(technologieRepository.existsById(id))
		{
			technologieRepository.deleteById(id);
		}
	}

}
