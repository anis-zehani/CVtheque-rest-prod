package com.cvtheque.org.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cvtheque.org.model.Etat;
import com.cvtheque.org.model.Opportunite;

@Repository
public interface OpportuniteRepository extends JpaRepository<Opportunite, Long> {
	
	List<Opportunite> findByEtatOpportunite(@Param("etatOpportunite") Etat etatOpportunite);
	

	//INNER JOIN : JPQL : La liste des Opportunités pour une Technologie
	@Query("FROM Opportunite o INNER JOIN o.listeTechnologies o1 ON o1.id = :idTechnologie")
	List<Opportunite> findAllOpportunitesByTechnologie(@Param("idTechnologie") Long idTechnologie);
	
	//Native Query = true : Supprimer le lien entre une opportunité et une technologie
	@Modifying
	@Transactional
	@Query(value = 
			"DELETE FROM opportunite_technologie o WHERE "
			+ "o.id_opportunite = ?1 AND o.id_technologie =?2"
			, nativeQuery = true)
	void deleteLinkOpportuniteTechnologie(@Param("idOpportunite") Long idOpportunite, @Param("idTechnologie") Long idTechnologie);
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//INNER JOIN : JPQL : La liste des Opportunités pour une Certification
	@Query("FROM Opportunite o INNER JOIN o.listeCertifications o1 ON o1.id = :idCertification")
	List<Opportunite> findAllOpportunitesByCertification(@Param("idCertification") Long idCertification);
	
	//Native Query = true : Supprimer le lien entre une opportunité et une certification
	@Modifying
	@Transactional
	@Query(value = 
			"DELETE FROM opportunite_certification o WHERE "
			+ "o.id_opportunite = ?1 AND o.id_certification =?2"
			, nativeQuery = true)
	void deleteLinkOpportuniteCertification(@Param("idOpportunite") Long idOpportunite, @Param("idCertification") Long idCertification);
		
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
	
	//INNER JOIN : JPQL : La liste des Opportunités pour un Partenaire
	@Query("FROM Opportunite o WHERE o.responsableOpportunite.id = :idPartenaire")
	List<Opportunite> findAllOpportunitesByPartenaire(@Param("idPartenaire") Long idPartenaire);
	
	//Native Query = true : UPDATE le lien entre une opportunité et un partenaire
	@Modifying
	@Transactional
	@Query(value = 
			"UPDATE Opportunite o SET "
			+ "responsable_opportunite_id = 'null' WHERE "
			+ "o.id =?1 AND o.responsable_opportunite_id =?2"
			, nativeQuery = true)
	void updateLinkOpportunitePartenaire(@Param("idOpportunite") Long idOpportunite, @Param("idPartenaire") Long idPartenaire);
}
