package com.cvtheque.org.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cvtheque.org.model.Candidat;
import com.cvtheque.org.model.Etat;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, Long> {
	
	Candidat findByIdentite(@Param("identite") String identite);

	List<Candidat> findByEtatCandidat(@Param("etatCandidat") Etat etatCandidat);
	
	//INNER JOIN : JPQL
	@Query("FROM Candidat c INNER JOIN c.listeOpportunites c1 ON c1.id = :idCandidat")
	List<Candidat> findAllCandidatsByOpportunite(@Param("idCandidat") Long idCandidat);
	
	//Native Query = true
	@Modifying
	@Transactional
	@Query(value = 
			"DELETE FROM candidat_opportunite c WHERE "
			+ "c.id_candidat = ?1 AND c.id_opportunite =?2"
			, nativeQuery = true)
	void deleteLinkCandidatOpportunite(@Param("idOpportunite") Long idOpportunite, @Param("idCandidat") Long idCandidat);
}
