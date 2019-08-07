package com.cvtheque.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cvtheque.org.model.Candidat;
import com.cvtheque.org.model.Etat;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, Long> {
	
	Candidat findByIdentite(@Param("identite") String identite);

	List<Candidat> findByEtatCandidat(@Param("etatCandidat") Etat etatCandidat);
	
	//INNER JOIN
	@Query("FROM Candidat c INNER JOIN c.listeOpportunites c1 ON c1.id = :id")
	List<Candidat> findAllCandidatsByOpportunite(@Param("id") Long id);
}
