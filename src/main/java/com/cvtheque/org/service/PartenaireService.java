package com.cvtheque.org.service;

import java.util.List;

import com.cvtheque.org.model.Partenaire;

public interface PartenaireService {
	
	public List<Partenaire> getAllPartenaires(String etat);
	
	public List<Partenaire> getAllPartenairesByEntreprise(Long idEntreprise);
	
	public Partenaire getPartenaire(Long id);
	
	public Partenaire addPartenaire(Partenaire partenaire);
	
	public Partenaire editPartenaire(Partenaire partenaire);
	
	public Partenaire editPartenaireAutoFill(Partenaire partenaire);
	
	public Partenaire editEtatPartenaire(Partenaire partenaire);
	
	public void updateLinkPartenaireEntreprise(Long idPartenaire);
	
	public void deletePartenaire(Long id);

	public Partenaire addPhotoToPartenaire(Long id, String urlPhoto);
	
	public Partenaire addPhotoToPartenaireAutoFill(Long id, String urlPhoto);

}
