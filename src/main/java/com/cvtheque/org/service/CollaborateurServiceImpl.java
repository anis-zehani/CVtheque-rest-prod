package com.cvtheque.org.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cvtheque.org.model.Collaborateur;
import com.cvtheque.org.repository.CollaborateurRepository;

@Service
public class CollaborateurServiceImpl implements CollaborateurService{
	
	private final CollaborateurRepository collaborateurRepository;
	
	CollaborateurServiceImpl(CollaborateurRepository collaborateurRepository) {
		super();
		this.collaborateurRepository = collaborateurRepository;
	}

	public List<Collaborateur> getAllCollaborateurs() {
		return collaborateurRepository.findAll();
	}

	public Optional<Collaborateur> getCollaborateur(Long id) {
		return collaborateurRepository.findById(id);
	}

	//Ajouter un collaborateur
	public Collaborateur addCollaborateur(Collaborateur collaborateur) {
		
		if(collaborateurRepository.findByIdentite(collaborateur.getIdentite()) == null)
		{
			return collaborateurRepository.save(collaborateur);
		}
		return null;
	}

	//Modifier un collaborateur
	public Collaborateur editCollaborateur(Collaborateur collaborateur) {
		
		if(collaborateurRepository.existsById(collaborateur.getId()))
		{
			return collaborateurRepository.save(collaborateur);
		}
		return null;
	}

	//Supprimer un collaborateur
	public void deleteCollaborateur(Long id) {
		
		if(collaborateurRepository.existsById(id))
		{
			collaborateurRepository.deleteById(id);
		}
	}

}
