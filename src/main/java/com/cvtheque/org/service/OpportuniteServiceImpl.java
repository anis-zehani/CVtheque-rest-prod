package com.cvtheque.org.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cvtheque.org.model.Etat;
import com.cvtheque.org.model.Opportunite;
import com.cvtheque.org.repository.OpportuniteRepository;

@Service
public class OpportuniteServiceImpl implements OpportuniteService {
	
	private final OpportuniteRepository opportuniteRepository;
	
	OpportuniteServiceImpl(OpportuniteRepository opportuniteRepository) {
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
			opportunite.setUrlPhotoOpportunite("");

			return opportuniteRepository.save(opportunite);
	}

	//Modifier une opportunité
	public Opportunite editOpportunite(Opportunite opportunite) {
		
		if(opportuniteRepository.existsById(opportunite.getId()))
		{
			
			Opportunite opportuniteToUpdate = opportuniteRepository.getOne(opportunite.getId());
			
			opportuniteToUpdate.setTitreOpportunite(opportunite.getTitreOpportunite());
			opportuniteToUpdate.setDescriptionOpportunite(opportunite.getDescriptionOpportunite());
			opportuniteToUpdate.setDateAjout(opportunite.getDateAjout());
			opportuniteToUpdate.setDateDemarrageSouhaitee(opportunite.getDateDemarrageSouhaitee());
			opportuniteToUpdate.setTjmOpportunite(opportunite.getTjmOpportunite());
			
			
			//On met l'image par défaut à toutes les opportunités : elle s'affiche si l'opportunité n'est liée à aucun partenaire
			opportuniteToUpdate.setUrlPhotoOpportunite("");
			
			if(opportunite.getResponsableOpportunite().getId() == null)
			{
				opportuniteToUpdate.setResponsableOpportunite(null);
			}
			else
			{
				opportuniteToUpdate.setResponsableOpportunite(opportunite.getResponsableOpportunite());
			}

			/*
			 * listeTechnologies : @ManyToMany 
			 */
			if(opportunite.getListeTechnologies() != null)
			{
				opportuniteToUpdate.setListeTechnologies(opportunite.getListeTechnologies());
			}
			
			return opportuniteRepository.save(opportuniteToUpdate);
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
