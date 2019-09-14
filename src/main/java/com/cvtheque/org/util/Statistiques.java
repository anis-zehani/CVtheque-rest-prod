package com.cvtheque.org.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cvtheque.org.model.Entreprise;
import com.cvtheque.org.model.Technologie;
import com.cvtheque.org.repository.CandidatRepository;
import com.cvtheque.org.repository.EntrepriseRepository;
import com.cvtheque.org.repository.OpportuniteRepository;
import com.cvtheque.org.repository.PartenaireRepository;
import com.cvtheque.org.repository.TechnologieRepository;
import com.cvtheque.org.service.EntrepriseService;
import com.cvtheque.org.service.TechnologieService;

@Service
public class Statistiques {
	
	@Autowired
	TechnologieService technologieService;
	
	@Autowired
	EntrepriseService entrepriseService;
	
	private final TechnologieRepository technologieRepository;
	private final OpportuniteRepository opportuniteRepository;
	private final CandidatRepository candidatRepository;
	private final EntrepriseRepository entrepriseRepository;
	private final PartenaireRepository partenaireRepository;
	
	public Statistiques(TechnologieRepository technologieRepository, OpportuniteRepository opportuniteRepository,
			CandidatRepository candidatRepository, EntrepriseRepository entrepriseRepository, PartenaireRepository partenaireRepository) {
		super();
		this.technologieRepository = technologieRepository;
		this.opportuniteRepository = opportuniteRepository;
		this.candidatRepository = candidatRepository;
		this.entrepriseRepository = entrepriseRepository;
		this.partenaireRepository = partenaireRepository;
	}
	
	// UPDATE le nombre des Candidats liés et des Opportunités liées à une Technologie : chaque 15 minutes
	@Scheduled(fixedRate = 300000)
	public void cronCandidatsAndOpportunitesByTechnologie() {
		
		Integer nombreCandidats = 0;
		Integer nombreOpportunites = 0;
		
		List<Technologie> listeTechnologies = technologieRepository.findAll();
		
		for(int i=0; i<listeTechnologies.size(); i++) {
			
			Long idTechnologie = listeTechnologies.get(i).getId();
			
			nombreCandidats = candidatRepository.findAllCandidatsByTechnologie(idTechnologie).size();
			nombreOpportunites = opportuniteRepository.findAllOpportunitesByTechnologie(idTechnologie).size();
			
			technologieService.updateNombreCandidatsAndNombreOpportunitesStats(idTechnologie, nombreCandidats, nombreOpportunites);	
		}
	}
	
	// UPDATE le nombre des Candidats liés et des Partenaires liés à une Entreprise : chaque 15 minutes
	@Scheduled(fixedRate = 300000)
	public void cronCandidatsAndPartenairesByEntreprise() {
		
		Integer nombreCandidats = 0;
		Integer nombrePartenaires = 0;
		
		List<Entreprise> listeEntreprises = entrepriseRepository.findAll();
		
		for(int i=0; i<listeEntreprises.size(); i++) {
			
			Long idEntreprise = listeEntreprises.get(i).getIdEntreprise();
			
			nombreCandidats = candidatRepository.findAllCandidatsByEntreprise(idEntreprise).size();
			nombrePartenaires = partenaireRepository.findAllByEntreprise(listeEntreprises.get(i)).size();
			
			entrepriseService.updateNombreCandidatsAndNombrePartenairesStats(idEntreprise, nombreCandidats, nombrePartenaires);	
		}
	}
	
	

}
