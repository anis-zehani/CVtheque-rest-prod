package com.cvtheque.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cvtheque.org.model.Etat;
import com.cvtheque.org.model.Opportunite;

@Repository
public interface OpportuniteRepository extends JpaRepository<Opportunite, Long> {
	
	List<Opportunite> findByEtatOpportunite(@Param("etatOpportunite") Etat etatOpportunite);
	
	//INNER JOIN : JPQL : La liste des Opportunités pour un Partenaire
	@Query("FROM Opportunite o WHERE o.responsableOpportunite.id = :idPartenaire")
	List<Opportunite> findAllOpportunitesByPartenaire(@Param("idPartenaire") Long idPartenaire);

	//INNER JOIN : JPQL : La liste des Opportunités pour une Technologie
	@Query("FROM Opportunite o INNER JOIN o.listeTechnologies o1 ON o1.id = :idTechnologie")
	List<Opportunite> findAllOpportunitesByTechnologie(@Param("idTechnologie") Long idTechnologie);
	
	//INNER JOIN : JPQL : La liste des Opportunités pour une Certification
	@Query("FROM Opportunite o INNER JOIN o.listeCertifications o1 ON o1.id = :idCertification")
	List<Opportunite> findAllOpportunitesByCertification(@Param("idCertification") Long idCertification);
}
