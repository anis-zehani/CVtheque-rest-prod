package com.cvtheque.org.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cvtheque.org.model.Ecole;
import com.cvtheque.org.repository.EcoleRepository;

@Service
public class EcoleServiceImpl implements EcoleService{
	
	private final EcoleRepository ecoleRepository;

	private EcoleServiceImpl(EcoleRepository ecoleRepository) {
		super();
		this.ecoleRepository = ecoleRepository;
	}
	
	public List<Ecole> getAllEcoles(){
		return ecoleRepository.findAll();
	}
	
	public Optional<Ecole> getEcole(Long id){
		return ecoleRepository.findById(id);
	}
	
	//Ajouter une école
	public Ecole addEcole(Ecole ecole)
	{
		if(ecoleRepository.findByNomEcole(ecole.getNomEcole()) == null)
		{
			return ecoleRepository.save(ecole);
		}
		return null;
	}
	
	//Modifier une école
	public Ecole editEcole(Ecole ecole)
	{
		if(ecoleRepository.existsById(ecole.getIdEcole()))
		{
			return ecoleRepository.save(ecole);
		}
		return null;
	}
	
	//Supprimer une école
	public void deleteEcole(Long id)
	{
		if(ecoleRepository.existsById(id))
		{
			ecoleRepository.deleteById(id);
		}
	}
}
