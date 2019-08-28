package com.cvtheque.org.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	CandidatServiceImpl(CandidatRepository candidatRepository, StorageService storageService) 
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
	
	
	//INNER JOIN : retourne les candidats par Opportunité
	public List<Candidat> getAllCandidatsByOpportunite(Long idOpportunite){
		
		return candidatRepository.findAllCandidatsByOpportunite(idOpportunite);
	}
	
	//INNER JOIN : retourne les candidats par Technologie
	public List<Candidat> getAllCandidatsByTechnologie(Long idTechnologie){
			
		return candidatRepository.findAllCandidatsByTechnologie(idTechnologie);
	}
	
	//La liste des candidats qui ont une Technologie au moins dans la liste fournie
	public List<Candidat> getAllCandidatsByListTechnologies(ArrayList<Long> listTechnologies){
		
		return candidatRepository.findAllCandidatsByListTechnologies(listTechnologies);
	}
		
	//INNER JOIN : retourne les candidats par Certification
	public List<Candidat> getAllCandidatsByCertification(Long idCertification){
			
		return candidatRepository.findAllCandidatsByCertification(idCertification);
	}
	
	//retourne les candidats par Entreprise
	public List<Candidat> getAllCandidatsByEntreprise(Long idEntreprise){
			
		return candidatRepository.findAllCandidatsByEntreprise(idEntreprise);
	}
	
	//Supprimer le lien entre un candidat et une opportunité
	public void deleteLinkCandidatOpportunite(Long idCandidat, Long idOpportunite) {
		 	   candidatRepository.deleteLinkCandidatOpportunite(idCandidat, idOpportunite);
	}
	
	//Supprimer le lien entre un candidat et une technologie
	public void deleteLinkCandidatTechnologie(Long idCandidat, Long idTechnologie) {
			 	candidatRepository.deleteLinkCandidatTechnologie(idCandidat, idTechnologie);
	}
	
	//Supprimer le lien entre un candidat et une certification
	public void deleteLinkCandidatCertification(Long idCandidat, Long idCertification) {
				candidatRepository.deleteLinkCandidatCertification(idCandidat, idCertification);
	}

	public Candidat getCandidat(Long id) {
		return candidatRepository.getOne(id);
	}
	
	//Créer un lien entre des candidats et une opportunité
	public void addCandidatsToOpportunite(Long idOpportunite, ArrayList<Candidat> listeCandidats, boolean withDeletion) {
		
		if(withDeletion)
		{

			candidatRepository.deleteAllCandidatsByOpportunite(idOpportunite);
		}
		if(idOpportunite != null && !listeCandidats.isEmpty())
		{
			for(int i=0;i<listeCandidats.size();i++)
			{
				candidatRepository.addCandidatToOpportunite(listeCandidats.get(i).getId(), idOpportunite);
			}
		}
	}

	//Ajouter un candidat
	public Candidat addCandidat(Candidat candidat) {
		
		//Par défaut, le candidat est activé
		candidat.setEtatCandidat(Etat.True);
		
		//On met l'image par défaut à tout le monde : elle pourra être écrasée plus tard
		candidat.setUrlPhoto("");
		
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
		
		//Encoder le Password avant de l'insérer dans la base
		candidat.setPassword(bcryptEncoder.encode(candidat.getPassword()));
		
		return  candidatRepository.save(candidat);
	}
	
	//Affecter une photo à un candidat (fonction appelée dans Ajout + Update)
	public Candidat addPhotoToCandidat(Long id, String urlPhoto) {
		
		if(candidatRepository.existsById(id))
		{
			Candidat candidat = candidatRepository.getOne(id);
			
			//delete ancienne photo : si elle existe dans le cas d'un Update
			if(candidat.getUrlPhoto() != null)
			{
				storageService.deletePhoto(candidat.getUrlPhoto());
			}

			//update URL photo avec nouveau nom
			candidat.setUrlPhoto(urlPhoto);

			return candidatRepository.saveAndFlush(candidat);
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
			catch(Exception e) 
			{
				System.out.print("Erreur durant deleteCvOdix :"+e);
			}
			
			return candidatRepository.saveAndFlush(candidat);
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
			catch(Exception e) 
			{
				System.out.print("Erreur durant CvOriginal :"+e);
			}
			
			return candidatRepository.saveAndFlush(candidat);
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
			candidatToUpdate.setPosteOccupe(candidat.getPosteOccupe());
			candidatToUpdate.setDescriptionDetaillee(candidat.getDescriptionDetaillee());
			
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
			
			/*
			 * listeTechnologies : @ManyToMany
			 */
			if(candidat.getListeTechnologies() != null)
			{
				candidatToUpdate.setListeTechnologies(candidat.getListeTechnologies());
			}
			
			/*
			 * listeOpportunites : @ManyToMany
			 */
			if(candidat.getListeOpportunites() != null)
			{
				candidatToUpdate.setListeOpportunites(candidat.getListeOpportunites());
			}
			
			/*
			 * listeCertifications : @ManyToMany
			 */
			if(candidat.getListeCertifications() != null)
			{
				candidatToUpdate.setListeCertifications(candidat.getListeCertifications());
			}
			
			//Récupérer le password affiché sur le formulaire
			String passwordFormulaire = candidat.getPassword();
			
			// Si le Password Affiché est différent de celui qui est stocké : on change le password
			if(!passwordFormulaire.equals(candidatRepository.findPasswordByIdentite(candidat.getIdentite()).getPassword()))
			{
				candidatToUpdate.setPassword(bcryptEncoder.encode(candidat.getPassword()));
			}
			// Sinon on réinsére l'ancien password
			else
			{
				candidatToUpdate.setPassword(candidatRepository.findPasswordByIdentite(candidat.getIdentite()).getPassword());
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
	
	//Update le lien entre un candidat et une entreprise : met entreprise à NULL
	public void updateLinkCandidatEntreprise(Long idCandidat) {
		
		if(candidatRepository.existsById(idCandidat))
		{
			candidatRepository.updateLinkCandidatEntreprise(idCandidat);
		}
		
	}
	
	//Supprimer un candidat
	public void deleteCandidat(Long id) {
		
		if(candidatRepository.existsById(id))
		{
			Candidat candidat = candidatRepository.getOne(id);

			try
			{
				
				//On supprime d'abord la photo si ce n'est pas un avatar
				if(candidat.getUrlPhoto() != null)
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
			catch(Exception e) 
			{
				System.out.print("Erreur durant deleteCandidat :"+e);
			}
			
				candidatRepository.deleteById(id);
		}
	}
}