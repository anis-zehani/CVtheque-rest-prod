package com.cvtheque.org.service;

import java.util.List;
import java.util.Optional;

import com.cvtheque.org.model.Opportunite;

public interface OpportuniteService {
	
	public List<Opportunite> getAllOpportunites(String etat);
	
	public List<Opportunite> getAllOpportunitesByPartenaire(Long idPartenaire);
	
	public List<Opportunite> getAllOpportunitesByTechnologie(Long idTechnologie);
	
	public List<Opportunite> getAllOpportunitesByCertification(Long idCertification);
	
	public Optional<Opportunite> getOpportunite(Long id);
	
	
	public Opportunite addOpportunite(Opportunite opportunite);
	
	public Opportunite editOpportunite(Opportunite opportunite);
	
	public Opportunite editEtatOpportunite(Opportunite opportunite);
	
	
	public void deleteOpportunite(Long idOpportunite);
	
	public void deleteLinkOpportuniteTechnologie(Long idOpportunite, Long idTechnologie);
	
	public void deleteLinkOpportuniteCertification(Long idOpportunite, Long idCertification);
	
	public void updateLinkOpportunitePartenaire(Long idOpportunite);

}
