package com.cvtheque.org.service;

import java.util.List;

import com.cvtheque.org.model.Technologie;

public interface TechnologieService {
	
	public List<Technologie> getAllTechnologies();
	
	public Technologie getTechnologie(Long id);
	
	public Technologie addTechnologie(Technologie technologie);
	
	public Technologie editTechnologie(Technologie technologie);
	
	public boolean deleteTechnologie(Long id);
	
	public void updateNombreCandidatsAndNombreOpportunitesStats(Long idTechnologie, Integer nombreCandidats, Integer nombreOpportunites);

}
