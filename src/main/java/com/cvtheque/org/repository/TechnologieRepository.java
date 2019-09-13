package com.cvtheque.org.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cvtheque.org.model.Technologie;

@Repository
public interface TechnologieRepository extends JpaRepository<Technologie, Long> {
	
	Technologie findByNomTechnologie(@Param("nomTechnologie") String nomTechnologie);
	
	// UPDATE le nombre des Candidats liés et des Opportunités liées à une Technologie
	@Modifying
	@Transactional
	@Query("UPDATE Technologie t SET t.statNombreCandidatsLies = :nombreCandidats, t.statNombreOpportunitesLiees = :nombreOpportunites WHERE t.id = :idTechnologie")
	void updateNombreCandidatsAndNombreOpportunitesStats(@Param("idTechnologie") Long idTechnologie, @Param("nombreCandidats") Integer nombreCandidats, @Param("nombreOpportunites") Integer nombreOpportunites);

	
	// Retourne la liste des 5 premières technologies ORDER BY le nombre des candidats qu'il y a pour elle
	@Query(value="SELECT * FROM Technologie t"
			+ " ORDER BY t.stat_nombre_candidats_lies DESC"
			+ " LIMIT 5", nativeQuery = true)
	List<Technologie> candidatsByTechnologie();
	
	
	// Retourne la liste des 5 premières technologies ORDER BY le nombre des opportunités qu'il y a pour elle
	@Query(value="SELECT * FROM Technologie t"
			+ " ORDER BY t.stat_nombre_opportunites_liees DESC"
			+ " LIMIT 5", nativeQuery = true)
	List<Technologie> opportunitesByTechnologie();
}
