package com.cvtheque.org.service;

import java.util.List;
import java.util.Optional;

import com.cvtheque.org.model.Collaborateur;

public interface CollaborateurService {
	
	public List<Collaborateur> getAllCollaborateurs();
	
	public Optional<Collaborateur> getCollaborateur(Long id);
	
	public Collaborateur addCollaborateur(Collaborateur collaborateur);
	
	public Collaborateur editCollaborateur(Collaborateur collaborateur);
	
	public void deleteCollaborateur(Long id);

}
