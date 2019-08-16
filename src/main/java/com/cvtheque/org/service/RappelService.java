package com.cvtheque.org.service;

import java.util.List;

import com.cvtheque.org.model.Projet;
import com.cvtheque.org.model.Rappel;

public interface RappelService {
	
	public List<Rappel> getAllRappels();
	
	public List<Rappel> getAllRappelsByToday();
	
	public List<Rappel> getAllRappelsByNext7Days();
	
	public List<Rappel> getAllRappelsByProjet(Projet projet);
	
	public List<Rappel> getAllRappelsByPriorite(String valeurPriorite);
	
	public Rappel getRappel(Long id);
	
	public Rappel addRappel(Rappel rappel);
	
	public Rappel editRappel(Rappel rappel);
	
	public void deleteRappel(Long id);
	
	public void deleteAllRappelsByProjet(Projet projet);
	
	public Rappel addFichierToRappel(Long id, String urlFichier, String nomFichier);

}
