package com.cvtheque.org.service;

import java.util.ArrayList;
import java.util.List;

import com.cvtheque.org.model.Candidat;

public interface CandidatService {
	
	public List<Candidat> getAllCandidats(String etat);
	
	public List<Candidat> getAllCandidatsByOpportunite(Long idOpportunite);
	
	public List<Candidat> getAllCandidatsByTechnologie(Long idTechnologie);
	
	public List<Candidat> getAllCandidatsByListTechnologies(ArrayList<Long> listTechnologies);
	
	public List<Candidat> getAllCandidatsByCertification(Long idCertification);
	
	public List<Candidat> getAllCandidatsByEntreprise(Long idEntreprise);
	
	public Candidat getCandidat(Long id);
	
	public Candidat getCandidatByIdLinkedin(String idLinkedin);
	
	public Candidat addCandidat(Candidat candidat);
	
	public void addCandidatsToOpportunite(Long idOpportunite, ArrayList<Candidat> listeCandidats, boolean withDeletion);
	
	public Candidat editCandidat(Candidat candidat);
	
	public Candidat editCandidatAutoFill(Candidat candidat);
	
	public Candidat editEtatCandidat(Candidat candidat);
	
	public void updateLinkCandidatEntreprise(Long idCandidat);
	
	
	public void deleteCandidat(Long id);
	
	public void deleteLinkCandidatOpportunite(Long idCandidat, Long idOpportunite);
	
	public void deleteLinkCandidatTechnologie(Long idCandidat, Long idTechnologie);
	
	public void deleteLinkCandidatCertification(Long idCandidat, Long idCertification);
	

	public Candidat addPhotoToCandidat(Long id, String urlPhoto);
	
	public Candidat addPhotoToCandidatAutoFill(Long id, String urlPhoto);
	
	public Candidat addCvOdixToCandidat(Long idCandidat, String urlCvOdix);
	
	public Candidat addCvOriginalToCandidat(Long idCandidat, String urlCvOriginal);
	
	public Candidat addCvOriginalToCandidatAutoFill(Long idCandidat, String urlCvOriginal);

}
