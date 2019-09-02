package com.cvtheque.org.service;

import java.util.List;

import com.cvtheque.org.model.Projet;
import com.cvtheque.org.model.Rappel;

public interface RappelService {
	
	public List<Rappel> getAllRappels(Long idUtilisateur);
	
	public List<Rappel> getAllRappelsByToday(Long idUtilisateur);
	
	public List<Rappel> getAllRappelsByNext7Days(Long idUtilisateur);
	
	public List<Rappel> getAllRappelsByProjetAndUtilisateur(Projet projet, Long idUtilisateur);
	
	public List<Rappel> getAllRappelsByPrioriteAndUtilisateur(String valeurPriorite, Long idUtilisateur);
	
	public Rappel getRappel(Long id);
	
	public Rappel addRappel(Rappel rappel);
	
	public Rappel editRappel(Rappel rappel);
	
	public void deleteRappel(Long id);
	
	public void deleteAllRappelsByProjet(Projet projet);
	
	public Rappel addFichierToRappel(Long id, String urlFichier, String nomFichier);

}
