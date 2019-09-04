package com.cvtheque.org.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.cvtheque.org.model.Priorite;
import com.cvtheque.org.model.Projet;
import com.cvtheque.org.model.Rappel;
import com.cvtheque.org.model.Utilisateur;
import com.cvtheque.org.repository.RappelRepository;
import com.cvtheque.org.util.Consts;
import com.cvtheque.org.util.LocalStorageService;


@Service
public class RappelServiceImpl implements RappelService{
	
	private final RappelRepository rappelRepository;
	private final LocalStorageService storageService;
	private final UtilisateurService utilisateurService;

	RappelServiceImpl(RappelRepository rappelRepository, LocalStorageService storageService, UtilisateurService utilisateurService) {
		super();
		this.rappelRepository = rappelRepository;
		this.storageService = storageService;
		this.utilisateurService = utilisateurService;
	}
	
	//Retourne tous les rappels sans filtre
	public List<Rappel> getAllRappels(Long idUtilisateur) {
		
		Utilisateur utilisateur = utilisateurService.getUtilisateurById(idUtilisateur);
		
	    return rappelRepository.findAllByUtilisateur(utilisateur);
	}
	
	//Retourne les rappels de Today
	public List<Rappel> getAllRappelsByToday(Long idUtilisateur) {
		
		Utilisateur utilisateur = utilisateurService.getUtilisateurById(idUtilisateur);
		LocalDate dateToday = LocalDate.now(); 
		
	    return rappelRepository.findByToday(dateToday, utilisateur);
	}
	
	//Retourne les rappels des Next 7 Days
	public List<Rappel> getAllRappelsByNext7Days(Long idUtilisateur) {
		
		Utilisateur utilisateur = utilisateurService.getUtilisateurById(idUtilisateur);
		LocalDate dateDebut = LocalDate.now(); 
		LocalDate dateFin = dateDebut.plus(Period.ofDays(7));
		
	    return rappelRepository.findByNext7Days(dateDebut, dateFin, utilisateur);
	}
	
	public List<Rappel> getAllRappelsByProjetAndUtilisateur(Projet projet, Long idUtilisateur) {
		
		Utilisateur utilisateur = utilisateurService.getUtilisateurById(idUtilisateur);
		
	    return rappelRepository.findByProjetAndUtilisateur(projet, utilisateur);
	}
	
	public List<Rappel> getAllRappelsByPrioriteAndUtilisateur(String valeurPriorite, Long idUtilisateur){
		
		Utilisateur utilisateur = utilisateurService.getUtilisateurById(idUtilisateur);
		
		//Il faut convertir le String en Enum via valueOf
		return rappelRepository.findByPrioriteAndUtilisateur(Priorite.valueOf(valeurPriorite), utilisateur);
	}
	
	public Rappel getRappel(Long id) {
		return rappelRepository.getOne(id);
	}
	
	//Ajouter un rappel
	public Rappel addRappel(Rappel rappel) 
	{
		if(rappel.getPriorite().toString().equals("Non_Mentionee"))
		{
			rappel.setPriorite(Priorite.Basse);
		}
		
		if(rappel.getProjet().getId() == null)
		{
			//Obligatoire pour @ManyToOne
			rappel.setProjet(null);
		}
	
		if(rappelRepository.findByDetailsRappel(rappel.getDetailsRappel()) == null)
		{
			return rappelRepository.save(rappel);
		}
		return null;
	}
	
	//Affecter un fichier à un rappel (fonction appelée dans Ajout + Update)
	public Rappel addFichierToRappel(Long id, String urlFichier, String nomFichier) {
			
		if(rappelRepository.existsById(id))
		{
			Rappel rappel = rappelRepository.getOne(id);
				
		//delete ancien fichier : s'il existe dans le cas d'un Update
		if(rappel.getUrlFichier() != null)
		{
			storageService.deleteFichier(rappel.getUrlFichier());
		}

		//update URL fichier avec nouveau nom s'il n'est pas vide
		if(urlFichier.isEmpty() == false)
		{
			rappel.setUrlFichier(urlFichier);
			rappel.setNomFichier(nomFichier);
		}
		
		return rappelRepository.save(rappel);
		
		}
		return null;
		}

	
	//Modifier un rappel
	public Rappel editRappel(Rappel rappel) 
	{
		if(rappel.getProjet().getId() == null)
		{
			//Obligatoire pour @ManyToOne
			rappel.setProjet(null);
		}
		
		if(rappelRepository.existsById(rappel.getId()))
		{
			Rappel rappelToEdit = rappelRepository.getOne(rappel.getId());
			
			rappelToEdit.setDateEcheance(rappel.getDateEcheance());
			rappelToEdit.setDetailsRappel(rappel.getDetailsRappel());
			rappelToEdit.setPriorite(rappel.getPriorite());
			rappelToEdit.setProjet(rappel.getProjet());
			rappelToEdit.setRemindMe(rappel.getRemindMe());
			
			//On modifie tout sauf urlFichier : il est géré ailleurs afin d'éviter les erreurs
			return rappelRepository.save(rappelToEdit);
		}
		return null;
	}
	
	//Supprimer un rappel
	public void deleteRappel(Long id) 
	{
		if(rappelRepository.existsById(id))
		{
			Rappel rappel = rappelRepository.getOne(id);
			
			try
			{
				//On supprime la pièce jointe si elle existe
				if(rappel.getUrlFichier()!=null && rappel.getUrlFichier().startsWith(Consts.rootLocationFichierRappel.toString()+"/".replace("\"", ""))==true)
				{
					storageService.deleteFichier(Consts.rootLocation+rappel.getUrlFichier());
				}
			}
			catch(NoSuchElementException e) 
			{
				System.out.print("Erreur durant deleteRappel :"+e);
			}
			
			rappelRepository.deleteById(id);
		}
	}
	
	public void deleteAllRappelsByProjet(Projet projet) {
			
			rappelRepository.deleteAllRappelsByProjet(projet);
	}

}
