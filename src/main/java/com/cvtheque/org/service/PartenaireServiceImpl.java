package com.cvtheque.org.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

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
		
		//Encoder le Password avant de l'insérer dans la base
		partenaire.setPassword(bcryptEncoder.encode(partenaire.getPassword()));
			
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
			//Récupérer le password affiché sur le formulaire
			String passwordFormulaire = partenaire.getPassword();
			
			if(partenaire.getEntreprise().getIdEntreprise() == null)
			{
				partenaire.setEntreprise(null);
			}
			
			
			// Si le Password Affiché est différent de celui qui est stocké : on change le password
			if(passwordFormulaire.compareTo(partenaireRepository.findPasswordByIdentite(partenaire.getIdentite()).getPassword()) != 0)
			{
				partenaire.setPassword(bcryptEncoder.encode(partenaire.getPassword()));
			}
			// Sinon on réinsére l'ancien password
			else
			{
				partenaire.setPassword(partenaireRepository.findPasswordByIdentite(partenaire.getIdentite()).getPassword());
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

	//UPDATE le lien entre un partenaire et une entreprise : met entreprise à NULL
	public void updateLinkPartenaireEntreprise(Long idPartenaire) {
		if(partenaireRepository.existsById(idPartenaire))
		{
			partenaireRepository.updateLinkPartenaireEntreprise(idPartenaire);
		}
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
				System.out.print("Erreur durant deletePartenaire :"+e);
			}
			
			//On supprime la ligne de la base
			partenaireRepository.deleteById(id);
		}
	}
}
