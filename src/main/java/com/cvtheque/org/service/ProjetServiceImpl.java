package com.cvtheque.org.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cvtheque.org.model.Projet;
import com.cvtheque.org.model.Utilisateur;
import com.cvtheque.org.repository.ProjetRepository;


@Service
public class ProjetServiceImpl implements ProjetService{
	
	private final ProjetRepository projetRepository;
	private final RappelService rappelService;
	private final UtilisateurService utilisateurService;

	ProjetServiceImpl(ProjetRepository projetRepository, RappelService rappelService, UtilisateurService utilisateurService) {
		super();
		this.projetRepository = projetRepository;
		this.rappelService = rappelService;
		this.utilisateurService = utilisateurService;
	}
	
	public List<Projet> getAllProjets(Long idUtilisateur) {
		
		Utilisateur utilisateur = utilisateurService.getUtilisateurById(idUtilisateur);
	    
		return projetRepository.findAllByUtilisateur(utilisateur);
	}
	
	public Projet getProjet(Long id) {
		return projetRepository.getOne(id);
	}
	
	//Ajouter un projet
	public Projet addProjet(Projet projet) 
	{
		if(projetRepository.findByNomProjet(projet.getNomProjet()) == null)
		{
			return projetRepository.save(projet);
		}
		return null;
	}
	
	//Modifier un projet
	public Projet editProjet(Projet projet) 
	{
		if(projetRepository.existsById(projet.getId()))
		{
			return projetRepository.save(projet);
		}
		return null;
	}
	
	//Supprimer un projet
	public void deleteProjet(Long id) 
	{
		if(projetRepository.existsById(id))
		{
			//On supprime d'abord les Rappels liés à ce projet
			Projet projet = this.getProjet(id);
			
			rappelService.deleteAllRappelsByProjet(projet);
			
			//Finalement on supprime le projet lui même
			projetRepository.deleteById(id);
		}
	}

}
