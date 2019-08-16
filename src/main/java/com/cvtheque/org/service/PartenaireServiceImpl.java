package com.cvtheque.org.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cvtheque.org.model.Entreprise;
import com.cvtheque.org.model.Etat;
import com.cvtheque.org.model.Partenaire;
import com.cvtheque.org.repository.PartenaireRepository;
import com.cvtheque.org.util.Consts;
import com.cvtheque.org.util.StorageService;

@Service
public class PartenaireServiceImpl implements PartenaireService{

	private final PartenaireRepository partenaireRepository;
	private final StorageService storageService;

	PartenaireServiceImpl(PartenaireRepository partenaireRepository, StorageService storageService) {
		super();
		this.partenaireRepository = partenaireRepository;
		this.storageService = storageService;
	}

	public List<Partenaire> getAllPartenaires(String etatPartenaire) {
		
		//On filtre selon l'état : Actif / Inactif
		if(etatPartenaire.equals("True"))
		{
			return partenaireRepository.findByEtatPartenaire(Etat.True);
		}
		else 
		{
			return partenaireRepository.findByEtatPartenaire(Etat.False);
		}

	}

	public List<Partenaire> getAllPartenairesByEntreprise(Long idEntreprise){
		
			Entreprise entreprise = new Entreprise();
			entreprise.setIdEntreprise(idEntreprise);
			
			return partenaireRepository.findAllByEntreprise(entreprise);
	}
	public Optional<Partenaire> getPartenaire(Long id) {
		return partenaireRepository.findById(id);
	}

	//Ajouter un partenaire
	public Partenaire addPartenaire(Partenaire partenaire) {
		
		//Par défaut, le partenaire est activé
		partenaire.setEtatPartenaire(Etat.True);
			
		if(partenaire.getEntreprise().getIdEntreprise() == null)
		{
			partenaire.setEntreprise(null);
		}
		
		//On met l'image par défaut à tout le monde : elle pourra être écrasée plus tard
		partenaire.setUrlPhoto("");
			
		return partenaireRepository.save(partenaire);

	}
	
	//Affecter une photo à un partenaire (fonction appelée dans Ajout + Update)
	public Partenaire addPhotoToPartenaire(Long id, String urlPhoto) {
		
		if(partenaireRepository.existsById(id))
		{
			Partenaire partenaire = partenaireRepository.getOne(id);
			
			//delete ancienne photo : si elle existe dans le cas d'un Update
			if(partenaire.getUrlPhoto() != null)
			{
				storageService.deletePhoto(partenaire.getUrlPhoto());
			}

			//update URL photo avec nouveau nom
			partenaire.setUrlPhoto(urlPhoto);

			return partenaireRepository.save(partenaire);
		}
		return null;
	}

	//Modifier un partenaire
	public Partenaire editPartenaire(Partenaire partenaire) {
		
		//L'Update url photo se fait en haut dans la fonction addPhotoToPartenaire
		if(partenaireRepository.existsById(partenaire.getId()))
		{
			if(partenaire.getEntreprise().getIdEntreprise() == null)
			{
				partenaire.setEntreprise(null);
			}

			return partenaireRepository.save(partenaire);
		}
		return null;
	}
	
	//Modifier l'état d'un Partenaire : Actif/Inactif
	public Partenaire editEtatPartenaire(Partenaire partenaire) {
		
		if(partenaireRepository.existsById(partenaire.getId()))
		{
			Partenaire partenaireToUpdate = partenaireRepository.getOne(partenaire.getId());

			if(partenaireToUpdate.getEtatPartenaire().equals(Etat.True))
			{
				partenaireToUpdate.setEtatPartenaire(Etat.False);
			}
			else 
			{
				partenaireToUpdate.setEtatPartenaire(Etat.True);
			}
			
			return partenaireRepository.save(partenaireToUpdate);
		}
		return null;
	}

	//Supprimer un partenaire
	public void deletePartenaire(Long id) {
		
		if(partenaireRepository.existsById(id))
		{
			Partenaire partenaire = partenaireRepository.getOne(id);
			
			try
			{
				//On supprime d'abord la photo si ce n'est pas un avatar
				if(partenaire.getUrlPhoto() != null)
				{
					storageService.deletePhoto(Consts.rootLocation+partenaire.getUrlPhoto());
				}
			}
			catch(NoSuchElementException e) 
			{
				System.out.print("Erreur durant deleteCandidat :"+e);
			}
			
			//On supprime la ligne de la base
			partenaireRepository.deleteById(id);
		}
	}
}
