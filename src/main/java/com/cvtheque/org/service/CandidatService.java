package com.cvtheque.org.service;

import java.util.ArrayList;
import java.util.List;

import com.cvtheque.org.model.Candidat;

public interface CandidatService {
	
	public List<Candidat> getAllCandidats(String etat);
	
	public List<Candidat> getAllCandidatsByOpportunite(Long idOpportunite);
	
	public List<Candidat> getAllCandidatsByTechnologie(Long idTechnologie);
	
	public List<Candidat> getAllCandidatsByCertification(Long idCertification);
	
	public Candidat getCandidat(Long id);
	
	
	public Candidat addCandidat(Candidat candidat);
	
	public void addCandidatsToOpportunite(Long idOpportunite, ArrayList<Candidat> listeCandidats);
	
	public Candidat editCandidat(Candidat candidat);
	
	public Candidat editEtatCandidat(Candidat candidat);
	
	
	public void deleteCandidat(Long id);
	
	public void deleteLinkCandidatOpportunite(Long idCandidat, Long idOpportunite);
	
	public void deleteLinkCandidatTechnologie(Long idCandidat, Long idTechnologie);
	
	public void deleteLinkCandidatCertification(Long idCandidat, Long idCertification);
	

	public Candidat addPhotoToCandidat(Long id, String urlPhoto);
	
	public Candidat addCvOdixToCandidat(Long idCandidat, String urlCvOdix);
	
	public Candidat addCvOriginalToCandidat(Long idCandidat, String urlCvOriginal);

}
