package com.cvtheque.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cvtheque.org.model.CandidatsFavoris;

@Repository
public interface CandidatsFavorisRepository  extends JpaRepository<CandidatsFavoris, Long> {
	
	List<CandidatsFavoris> findByIdUtilisateur(@Param("idUtilisateur") Long idUtilisateur);
	
	// Vérifie si un Candidat existe dèja dans la liste des favoris d'un Utilisateur
	CandidatsFavoris findByIdUtilisateurAndIdCandidat(
			@Param("idUtilisateur") Long idUtilisateur, 
			@Param("idCandidat") Long idCandidat);
}
