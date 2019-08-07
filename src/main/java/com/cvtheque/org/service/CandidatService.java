package com.cvtheque.org.service;

import java.util.List;

import com.cvtheque.org.model.Candidat;

public interface CandidatService {
	
	public List<Candidat> getAllCandidats(String etat);
	
	public List<Candidat> getAllCandidatsByOpportunite(Long id);
	
	public Candidat getCandidat(Long id);
	
	public Candidat addCandidat(Candidat candidat);
	
	public Candidat editCandidat(Candidat candidat);
	
	public Candidat editEtatCandidat(Candidat candidat);
	
	public void deleteCandidat(Long id);

	public Candidat addPhotoToCandidat(Long id, String urlPhoto);
	
	public Candidat addCvOdixToCandidat(Long idCandidat, String urlCvOdix);
	
	public Candidat addCvOriginalToCandidat(Long idCandidat, String urlCvOriginal);

}
