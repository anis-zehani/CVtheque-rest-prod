package com.cvtheque.org.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cvtheque.org.model.Technologie;
import com.cvtheque.org.repository.CandidatRepository;
import com.cvtheque.org.repository.OpportuniteRepository;
import com.cvtheque.org.repository.TechnologieRepository;
import com.cvtheque.org.service.TechnologieService;

@Service
public class Statistiques {
	
	@Autowired
	TechnologieService technologieService;
	
	private final TechnologieRepository technologieRepository;
	private final OpportuniteRepository opportuniteRepository;
	private final CandidatRepository candidatRepository;
	
	public Statistiques(TechnologieRepository technologieRepository, OpportuniteRepository opportuniteRepository,
			CandidatRepository candidatRepository) {
		super();
		this.technologieRepository = technologieRepository;
		this.opportuniteRepository = opportuniteRepository;
		this.candidatRepository = candidatRepository;
	}
	
	// UPDATE le nombre des Candidats liés et des Opportunités liées à une Technologie : chaque 15 minutes
	@Scheduled(fixedRate = 900000)
	public void cronCandidatsAndOpportunitesByTechnologie() {
		
		Integer nombreCandidats = 0;
		Integer nombreOpportunites = 0;
		
		List<Technologie> listeTechnologies = technologieRepository.findAll();
		
		for(int i=0; i<listeTechnologies.size(); i++) {
			
			Long idTechnologie = listeTechnologies.get(i).getId();
			
			nombreCandidats = candidatRepository.findAllCandidatsByTechnologie(idTechnologie).size();
			nombreOpportunites = opportuniteRepository.findAllOpportunitesByTechnologie(idTechnologie).size();
			
			technologieService.updateNombreCandidatsAndNombreOpportunitesStats(idTechnologie, nombreCandidats, nombreOpportunites);	
			
			/*String newLine = System.getProperty("line.separator");
			
			System.out.print("Technologie : " + listeTechnologies.get(i).getNomTechnologie());
			System.out.println(newLine);
			System.out.print("nombreCandidats : " + nombreCandidats);
			System.out.println(newLine);
			System.out.print("nombreOpportunites : " + nombreOpportunites);
			System.out.println(newLine);*/
		}
	}
	
	

}
