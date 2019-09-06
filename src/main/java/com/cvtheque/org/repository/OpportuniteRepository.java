package com.cvtheque.org.repository;

import java.util.ArrayList;
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
	
	/**Datagrid Opportunité avec un Profil Partenaire
	 * Retourne les Opportunités Publique + les Opportunités Privée du Partenaire connecté
	 * @param idPartenaire
	 */
	@Query(value = 
			"SELECT * FROM Opportunite o WHERE"
			+ " o.visibilite_opportunite like 'Public' OR (o.visibilite_opportunite like 'Private' AND o.utilisateur_id = ?1)"
			, nativeQuery = true)
	List<Opportunite> findAllOpportunitesPublicAndPrivateByPartenaire(@Param("idPartenaire") Long idPartenaire);
	

	//INNER JOIN : JPQL : La liste des Opportunités pour une Technologie
	@Query("FROM Opportunite o INNER JOIN o.listeTechnologies o1 ON o1.id = :idTechnologie")
	List<Opportunite> findAllOpportunitesByTechnologie(@Param("idTechnologie") Long idTechnologie);
	
	//INNER JOIN : JPQL : La liste des opportunites qui ont une Technologie au moins dans la liste fournie
	//@Query("FROM Opportunite c INNER JOIN c.listeTechnologies c1 ON c1.id IN :listTechnologies")
	@Query(value = 
			"FROM Opportunite o WHERE "
			+ "EXISTS(FROM o.listeTechnologies o1 WHERE o1.id IN :listTechnologies)")
	List<Opportunite> findAllOpportunitesByListTechnologies(@Param("listTechnologies") ArrayList<Long> listTechnologies);
	
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
	
	//Native Query = true : Supprimer tous les liens entre les opportunités et une certification
	@Modifying
	@Transactional
	@Query(value = 
			"DELETE FROM opportunite_certification o WHERE "
			+ "o.id_certification =?1"
			, nativeQuery = true)
	void deleteAllOpportunitesByCertification(@Param("idCertification") Long idCertification);
		
	//Native Query = true : Créer un lien entre une opportunité et une certification
	@Modifying
	@Transactional
	@Query(value = 
			"INSERT INTO opportunite_certification (id_opportunite, id_certification) "
			+ "VALUES(?1 , ?2)"
			, nativeQuery = true)
	void addOpportuniteToCertification(@Param("idOpportunite") Long idOpportunite, @Param("idCertification") Long idCertification);
		
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
	
	//INNER JOIN : JPQL : La liste des Opportunités pour un Partenaire
	@Query("FROM Opportunite o WHERE o.responsableOpportunite.id = :idPartenaire")
	List<Opportunite> findAllOpportunitesByPartenaire(@Param("idPartenaire") Long idPartenaire);
	
	//Native Query = true : UPDATE le lien entre une opportunité et un partenaire : met responsableOpportunite à NULL
	@Modifying
	@Transactional
	@Query("UPDATE Opportunite o SET o.responsableOpportunite = null WHERE o.id = :idOpportunite")
	void updateLinkOpportunitePartenaire(@Param("idOpportunite") Long idOpportunite);
}
