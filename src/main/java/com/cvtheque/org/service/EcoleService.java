package com.cvtheque.org.service;

import java.util.List;
import java.util.Optional;

import com.cvtheque.org.model.Ecole;

public interface EcoleService {
	
	public List<Ecole> getAllEcoles();
	
	public Optional<Ecole> getEcole(Long id);
	
	public Ecole addEcole(Ecole école);
	
	public Ecole editEcole(Ecole école);
	
	public void deleteEcole(Long id);

}
