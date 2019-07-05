package com.cvtheque.org.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.cvtheque.org.model.Etat;
import com.cvtheque.org.model.Candidat;
import com.cvtheque.org.model.Curriculum;
import com.cvtheque.org.repository.CandidatRepository;
import com.cvtheque.org.util.Consts;
import com.cvtheque.org.util.StorageService;

@Service
public class CandidatServiceImpl implements CandidatService{
	
	private final CandidatRepository candidatRepository;
	private final StorageService storageService;
	
	private CandidatServiceImpl(CandidatRepository candidatRepository, StorageService storageService) 
	{
		super();
		this.candidatRepository = candidatRepository;
		this.storageService = storageService;
	}

	public List<Candidat> getAllCandidats(String etatCandidat) {
		
		//On filtre selon l'état : Actif / Inactif
		if(etatCandidat.equals("True"))
		{
			return candidatRepository.findByEtatCandidat(Etat.True);
		}
		else 
		{
			return candidatRepository.findByEtatCandidat(Etat.False);
		}

	}

	public Candidat getCandidat(Long id) {
		return candidatRepository.getOne(id);
	}

	//Ajouter un candidat
	public Candidat addCandidat(Candidat candidat) {
		
		//Par défaut, le candidat est activé
		candidat.setEtatCandidat(Etat.True);
		
		//On met l'image par défaut à tout le monde : elle pourra être écrasée plus tard
		candidat.setUrlPhoto(Consts.urlAvatar.replace("\"", ""));
		
		//Indispensable afin de créer un Objet CV au démarrage : util pour l'ajout des PJs
		Curriculum cv = new Curriculum();
		candidat.setCurriculum(cv);
			
		//Entreprise : Si le user n'a pas ajouté une Entreprise
		if(candidat.getEntreprise().getIdEntreprise() == null)
		{
			//Obligatoire pour @ManyToOne
			candidat.setEntreprise(null);
		}
		
		//Ecole : Si le user n'a pas ajouté une Ecole pour un Diplome
		if(candidat.getDiplome().getEcole().getIdEcole() == null)
		{
			//Obligatoire pour @ManyToOne
			candidat.getDiplome().setEcole(null);
		}
		
		return  candidatRepository.save(candidat);
	}
	
	//Affecter une photo à un candidat (fonction appelée dans Ajout + Update)
	public Candidat addPhotoToCandidat(Long id, String urlPhoto) {
		
		if(candidatRepository.existsById(id))
		{
			Candidat candidat = candidatRepository.getOne(id);
			
			//delete ancienne photo : si elle existe dans le cas d'un Update
			if(candidat.getUrlPhoto() != null && candidat.getUrlPhoto().startsWith(Consts.urlAvatar.replace("\"", ""))==false)
			{
				storageService.deletePhoto(candidat.getUrlPhoto());
			}

			//update URL photo avec nouveau nom
			candidat.setUrlPhoto(urlPhoto);

			return candidatRepository.save(candidat);
		}
		
		return null;
	}
	
	//Ajout du CV Odix
	public Candidat addCvOdixToCandidat(Long idCandidat, String urlCvOdix) {
		
		if(candidatRepository.existsById(idCandidat))
		{
			Candidat candidat = candidatRepository.getOne(idCandidat);
			
			try 
			{
				//delete ancien CvOdix : s'il existe dans le cas d'un Update
				if(candidat.getCurriculum() != null && candidat.getCurriculum().getUrlCvOdix()!= null)
				{
					storageService.deleteCvOdix(candidat.getCurriculum().getUrlCvOdix());
				}
				
				candidat.getCurriculum().setUrlCvOdix(urlCvOdix);
			}
			catch(NoSuchElementException e) 
			{
				System.out.print("Erreur durant deleteCvOdix :"+e);
			}
			
			return candidatRepository.save(candidat);
		}
		
			return null;
		}
		
	//Ajout du CV Original
	public Candidat addCvOriginalToCandidat(Long idCandidat, String urlCvOriginal) {
			
		if(candidatRepository.existsById(idCandidat))
		{
			Candidat candidat = candidatRepository.getOne(idCandidat);
			
			try 
			{
				//delete ancien CvOriginal : s'il existe dans le cas d'un Update
				if(candidat.getCurriculum() != null && candidat.getCurriculum().getUrlCvOriginal()!= null)
				{
					storageService.deleteCvOriginal(candidat.getCurriculum().getUrlCvOriginal());
					
				}
				
				candidat.getCurriculum().setUrlCvOriginal(urlCvOriginal);
			}
			catch(NoSuchElementException e) 
			{
				System.out.print("Erreur durant CvOriginal :"+e);
			}
			
			return candidatRepository.save(candidat);
		}
		
			return null;
		}
	
	//Modifier un candidat
	public Candidat editCandidat(Candidat candidat) {
		
		//L'Update url photo se fait en haut dans la fonction addPhotoToCandidat
		if(candidatRepository.existsById(candidat.getId()))
		{
			Candidat candidatToUpdate = candidatRepository.getOne(candidat.getId());
			
			candidatToUpdate.setIdentite(candidat.getIdentite());
			candidatToUpdate.setTelephone(candidat.getTelephone());
			candidatToUpdate.setEmail(candidat.getEmail());
			candidatToUpdate.setPoste_occupe(candidat.getPoste_occupe());
			candidatToUpdate.setDescription_detaillee(candidat.getDescription_detaillee());
			
			/*
			 * entreprise
			 */
			if(candidat.getEntreprise().getIdEntreprise() != null)
			{
				candidatToUpdate.setEntreprise(candidat.getEntreprise());
			}

			candidatToUpdate.setDateDeNaissance(candidat.getDateDeNaissance());
			candidatToUpdate.setAdresse(candidat.getAdresse());
			candidatToUpdate.setSituationFamiliale(candidat.getSituationFamiliale());
			candidatToUpdate.setNombreEnfants(candidat.getNombreEnfants());
			candidatToUpdate.setSalaireActuel(candidat.getSalaireActuel());
			candidatToUpdate.setPretentionSalariale(candidat.getPretentionSalariale());
			candidatToUpdate.setNiveauEnFrancais(candidat.getNiveauEnFrancais());
			candidatToUpdate.setNiveauEnAnglais(candidat.getNiveauEnAnglais());
			candidatToUpdate.setNoteGlobale(candidat.getNoteGlobale());
			candidatToUpdate.setDisponibilite(candidat.getDisponibilite());
			candidatToUpdate.setEtatCandidat(candidat.getEtatCandidat());
			candidatToUpdate.setDateDemarrageCarriere(candidat.getDateDemarrageCarriere());
			candidatToUpdate.setDateEpuisementPasseport(candidat.getDateEpuisementPasseport());
			
			/*
			 * diplome : @OneToOne
			 */
			if(candidat.getDiplome() != null)
			{
				candidatToUpdate.setDiplome(candidat.getDiplome());
			}
			
			/*
			 * visa : @OneToOne
			 */
			if(candidat.getVisa() != null)
			{
				candidatToUpdate.setVisa(candidat.getVisa());
			}

			return candidatRepository.save(candidatToUpdate);
		}
		
			return null;
	}
	
	//Modifier l'état d'un Candidat : Actif/Inactif
	public Candidat editEtatCandidat(Candidat candidat) {
		
		if(candidatRepository.existsById(candidat.getId()))
		{
			Candidat candidatToUpdate = candidatRepository.getOne(candidat.getId());

			if(candidatToUpdate.getEtatCandidat().equals(Etat.True))
			{
				candidatToUpdate.setEtatCandidat(Etat.False);
			}
			else 
			{
				candidatToUpdate.setEtatCandidat(Etat.True);
			}
			
			return candidatRepository.save(candidatToUpdate);
		}
			
			return null;
	}

	//Supprimer un candidat
	public void deleteCandidat(Long id) {
		
		if(candidatRepository.existsById(id))
		{
			Candidat candidat = candidatRepository.getOne(id);

			try
			{
				
				//On supprime d'abord la photo si ce n'est pas un avatar
				if(candidat.getUrlPhoto() != null && candidat.getUrlPhoto().startsWith(Consts.urlAvatar.replace("\"", ""))==false)
				{
					storageService.deletePhoto(Consts.rootLocation+candidat.getUrlPhoto());
				}
				
				//On supprime le CV Odix du Disque
				if(candidat.getCurriculum() != null && candidat.getCurriculum().getUrlCvOdix() != null)
				{
					storageService.deleteCvOdix(Consts.rootLocation+candidat.getCurriculum().getUrlCvOdix());
				}
				
				//On supprime le CV Original du Disque
				if(candidat.getCurriculum() != null && candidat.getCurriculum().getUrlCvOriginal() != null)
				{
					storageService.deleteCvOriginal(Consts.rootLocation+candidat.getCurriculum().getUrlCvOriginal());
				}
				
			}
			catch(NoSuchElementException e) 
			{
				System.out.print("Erreur durant deleteCandidat :"+e);
			}
			
				candidatRepository.deleteById(id);
		}
	}
}