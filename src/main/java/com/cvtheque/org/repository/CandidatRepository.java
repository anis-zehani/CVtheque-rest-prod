package com.cvtheque.org.repository;

import java.util.ArrayList;
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
	
	String findPasswordByUsername(@Param("username") String username);

	List<Candidat> findByEtatCandidat(@Param("etatCandidat") Etat etatCandidat);
	
	//INNER JOIN : JPQL : La liste des candidats pour une Opportunité
	@Query("FROM Candidat c INNER JOIN c.listeOpportunites c1 ON c1.id = :idOpportunite")
	List<Candidat> findAllCandidatsByOpportunite(@Param("idOpportunite") Long idOpportunite);
	
	//Native Query = true : Supprimer le lien entre un candidat et une opportunité
	@Modifying
	@Transactional
	@Query(value = 
			"DELETE FROM candidat_opportunite c WHERE "
			+ "c.id_candidat = ?1 AND c.id_opportunite =?2"
			, nativeQuery = true)
	void deleteLinkCandidatOpportunite(@Param("idCandidat") Long idCandidat, @Param("idOpportunite") Long idOpportunite);
	
	//Native Query = true : Supprimer tous les liens entre les candidats et une opportunité
	@Modifying
	@Transactional
	@Query(value = 
			"DELETE FROM candidat_opportunite c WHERE "
			+ "c.id_opportunite =?1"
			, nativeQuery = true)
	void deleteAllCandidatsByOpportunite(@Param("idOpportunite") Long idOpportunite);
	
	//Native Query = true : Créer un lien entre un candidat et une opportunité
	@Modifying
	@Transactional
	@Query(value = 
			"INSERT INTO candidat_opportunite (id_candidat, id_opportunite) "
			+ "VALUES(?1 , ?2)"
			, nativeQuery = true)
	void addCandidatToOpportunite(@Param("idCandidat") Long idCandidat, @Param("idOpportunite") Long idOpportunite);
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//INNER JOIN : JPQL : La liste des candidats pour une Technologie
	@Query("FROM Candidat c INNER JOIN c.listeTechnologies c1 ON c1.id = :idTechnologie")
	List<Candidat> findAllCandidatsByTechnologie(@Param("idTechnologie") Long idTechnologie);
	
	//INNER JOIN : JPQL : La liste des candidats qui ont une Technologie au moins dans la liste fournie
	//@Query("FROM Candidat c INNER JOIN c.listeTechnologies c1 ON c1.id IN :listTechnologies")
	@Query(value = 
			"FROM Candidat c WHERE "
			+ "EXISTS(FROM c.listeTechnologies c1 WHERE c1.id IN :listTechnologies)")
	List<Candidat> findAllCandidatsByListTechnologies(@Param("listTechnologies") ArrayList<Long> listTechnologies);
	
	
	//Native Query = true : Supprimer le lien entre un candidat et une technologie
	@Modifying
	@Transactional
	@Query(value = 
			"DELETE FROM candidat_technologie c WHERE "
			+ "c.id_candidat = ?1 AND c.id_technologie =?2"
			, nativeQuery = true)
	void deleteLinkCandidatTechnologie(@Param("idCandidat") Long idCandidat, @Param("idTechnologie") Long idTechnologie);
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//INNER JOIN : JPQL : La liste des candidats pour une Certification
	@Query("FROM Candidat c INNER JOIN c.listeCertifications c1 ON c1.id = :idCertification")
	List<Candidat> findAllCandidatsByCertification(@Param("idCertification") Long idCertification);
	
	//Native Query = true : Supprimer le lien entre un candidat et une certification
	@Modifying
	@Transactional
	@Query(value = 
			"DELETE FROM candidat_certification c WHERE "
			+ "c.id_candidat = ?1 AND c.id_certification =?2"
			, nativeQuery = true)
	void deleteLinkCandidatCertification(@Param("idCandidat") Long idCandidat, @Param("idCertification") Long idCertification);
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
	//La liste des candidats pour une Entreprise
	@Query("FROM Candidat c WHERE c.entreprise.idEntreprise = :idEntreprise")
	List<Candidat> findAllCandidatsByEntreprise(@Param("idEntreprise") Long idEntreprise);
	
	//UPDATE le lien entre un candiat et une entreprise : met entreprise à NULL
	@Modifying
	@Transactional
	@Query("UPDATE Candidat c SET c.entreprise = null WHERE c.id = :idCandidat")
	void updateLinkCandidatEntreprise(@Param("idCandidat") Long idCandidat);

}
