package com.cvtheque.org.service;

import java.util.List;
import java.util.Map;

import com.cvtheque.org.model.Entreprise;

public interface EntrepriseService {
	
	public List<Entreprise> getAllEntreprises();
	
	public Entreprise getEntreprise(Long id);
	
	public Entreprise addEntreprise(Entreprise entreprise);
	
	public Entreprise editEntreprise(Entreprise entreprise);
	
	public boolean deleteEntreprise(Long id);
	
	public void updateNombreCandidatsAndNombrePartenairesStats(Long idTechnologie, Integer nombreCandidats, Integer nombrePartenaires);

	public List<Entreprise> candidatsByEntreprise();
	
	public List<Entreprise> partenairesByEntreprise();
	
	public Map<String, Integer> sumCandiatsAndPartenairesByEntreprises();

}
