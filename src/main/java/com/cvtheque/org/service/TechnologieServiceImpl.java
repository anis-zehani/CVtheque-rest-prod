package com.cvtheque.org.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cvtheque.org.model.Technologie;
import com.cvtheque.org.repository.TechnologieRepository;

@Service
public class TechnologieServiceImpl implements TechnologieService{
	
	private final TechnologieRepository technologieRepository;

	TechnologieServiceImpl(TechnologieRepository technologieRepository) {
		super();
		this.technologieRepository = technologieRepository;
	}
	
	public List<Technologie> getAllTechnologies() {
		
		List<Technologie> listeTechnologies = technologieRepository.findAll();

		return listeTechnologies;
	}
	
	public Technologie getTechnologie(Long id) {
		
		return technologieRepository.getOne(id);
	}
	
	//Ajouter une technologie
	public Technologie addTechnologie(Technologie technologie) 
	{
		if(technologieRepository.findByNomTechnologie(technologie.getNomTechnologie()) == null)
		{
			return technologieRepository.save(technologie);
		}
		return null;
	}
	
	//Modifier une technologie
	public Technologie editTechnologie(Technologie technologie) 
	{
		if(technologieRepository.existsById(technologie.getId()))
		{
			return technologieRepository.save(technologie);
		}
		return null;
	}
	
	//Supprimer une technologie
	public boolean deleteTechnologie(Long id) 
	{
		if(technologieRepository.existsById(id))
		{
			try 
			{
			technologieRepository.deleteById(id);
			return true;
			}
			catch(Exception e) 
			{
				System.out.print("Erreur durant deleteTechnologie :"+e);
			}
		}
		return false;
	}
	
	// Statistiques : UPDATE le nombre des Candidats liés et des Opportunités liées à une Technologie
	@Override
	public void updateNombreCandidatsAndNombreOpportunitesStats(Long idTechnologie, Integer nombreCandidats, Integer nombreOpportunites) {
		
		technologieRepository.updateNombreCandidatsAndNombreOpportunitesStats(idTechnologie, nombreCandidats, nombreOpportunites);
	}
	
	// Retourne la liste des 5 premières technologies ORDER BY le nombre des candidats qu'il y a pour elle
	@Override
	public List<Technologie> candidatsByTechnologie(){
		
		List<Technologie> listeCandidatsByTechnologie = technologieRepository.candidatsByTechnologie();
		
		return listeCandidatsByTechnologie;
	}
	
	// Retourne la liste des 5 premières technologies ORDER BY le nombre des opportunités qu'il y a pour elle
	@Override
	public List<Technologie> opportunitesByTechnologie(){
		
		List<Technologie> listeOpportunitesByTechnologie = technologieRepository.opportunitesByTechnologie();
		
		return listeOpportunitesByTechnologie;
	}
	
	// Retourne la somme des Candidats liés et des opportunités liées pour toutes les technologies
	@Override
	public Map<String, Integer> sumCandiatsAndOpportunitesByTechnologies(){
		
		List<Integer> listeSumsCandidats = technologieRepository.sumCandiatsByTechnologies();
		List<Integer> listeSumsOpportunites = technologieRepository.sumOpportunitesByTechnologies();
		
		Map<String, Integer> map = new HashMap<>();
		
		map.put("sumCandidatsLies", listeSumsCandidats.get(0));
		map.put("sumOpportunitesLiees", listeSumsOpportunites.get(0));
		
		return map;
	}

}
