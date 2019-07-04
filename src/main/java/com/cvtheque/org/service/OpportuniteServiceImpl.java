package com.cvtheque.org.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cvtheque.org.model.Etat;
import com.cvtheque.org.model.Opportunite;
import com.cvtheque.org.repository.OpportuniteRepository;
import com.cvtheque.org.util.Consts;

@Service
public class OpportuniteServiceImpl implements OpportuniteService {
	
	private final OpportuniteRepository opportuniteRepository;
	
	private OpportuniteServiceImpl(OpportuniteRepository opportuniteRepository) {
		super();
		this.opportuniteRepository = opportuniteRepository;
	}

	public List<Opportunite> getAllOpportunites(String etatOpportunite) {
		
		if(etatOpportunite.equals("True"))
		{
			return opportuniteRepository.findByEtatOpportunite(Etat.True);
		}
		else 
		{
			return opportuniteRepository.findByEtatOpportunite(Etat.False);
		}

	}

	public Optional<Opportunite> getOpportunite(Long id) {
		return opportuniteRepository.findById(id);
	}

	//Ajouter une opportunité
	public Opportunite addOpportunite(Opportunite opportunite) {
		
			opportunite.setEtatOpportunite(Etat.True);

			opportunite.setDateAjout(LocalDate.now());
			
			if(opportunite.getResponsableOpportunite().getId() == null)
			{
				opportunite.setResponsableOpportunite(null);
			}
			
			//On met l'image par défaut à toutes les opportunités : elle s'affiche si l'opportunité n'est liée à aucun partenaire
			opportunite.setUrlPhotoOpportunite(Consts.urlMoney.replace("\"", ""));

			return opportuniteRepository.save(opportunite);
	}

	//Modifier une opportunité
	public Opportunite editOpportunite(Opportunite opportunite) {
		
		if(opportuniteRepository.existsById(opportunite.getId()))
		{
			if(opportunite.getResponsableOpportunite().getId() == null)
			{
				opportunite.setResponsableOpportunite(null);
			}
			
			//On met l'image par défaut à toutes les opportunités : elle s'affiche si l'opportunité n'est liée à aucun partenaire
			opportunite.setUrlPhotoOpportunite(Consts.urlMoney.replace("\"", ""));
			
			return opportuniteRepository.save(opportunite);
		}
		
		return null;
	}
	
	//Modifier l'état d'une Opportunité : Active/Inactive
	public Opportunite editEtatOpportunite(Opportunite opportunite) {
		
		if(opportuniteRepository.existsById(opportunite.getId()))
		{
			Opportunite opportuniteToUpdate = opportuniteRepository.getOne(opportunite.getId());
			
			if(opportuniteToUpdate.getEtatOpportunite().equals(Etat.True))
			{
				opportuniteToUpdate.setEtatOpportunite(Etat.False);
			}
			else 
			{
				opportuniteToUpdate.setEtatOpportunite(Etat.True);
			}
			
			return opportuniteRepository.save(opportuniteToUpdate);
		}
		return null;
	}

	//Supprimer une opportunité
	public void deleteOpportunite(Long id) {
		
		if(opportuniteRepository.existsById(id))
		{
			opportuniteRepository.deleteById(id);
		}
	}
}
