package com.cvtheque.org.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cvtheque.org.model.Collaborateur;
import com.cvtheque.org.repository.CollaborateurRepository;

@Service
public class CollaborateurServiceImpl implements CollaborateurService{
	
	private final CollaborateurRepository collaborateurRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
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
			if(collaborateur.getPassword() != null)
			{
				//Encoder le Password avant de l'insérer dans la base
				collaborateur.setPassword(bcryptEncoder.encode(collaborateur.getPassword()));
			}

			return collaborateurRepository.save(collaborateur);
		}
		return null;
	}

	//Modifier un collaborateur
	public Collaborateur editCollaborateur(Collaborateur collaborateur) {
		
		if(collaborateurRepository.existsById(collaborateur.getId()))
		{
			//Récupérer le password affiché sur le formulaire
			String passwordFormulaire = collaborateur.getPassword();
			
			// Si le Password récupéré est différent de celui qui est stocké : on change le password
			if(!passwordFormulaire.equals(collaborateurRepository.findPasswordByIdentite(collaborateur.getIdentite()).getPassword()))
			{
				collaborateur.setPassword(bcryptEncoder.encode(collaborateur.getPassword()));
			}
			// Sinon on réinsére l'ancien password
			else
			{
				collaborateur.setPassword(collaborateurRepository.findPasswordByIdentite(collaborateur.getIdentite()).getPassword());
			}
			
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
