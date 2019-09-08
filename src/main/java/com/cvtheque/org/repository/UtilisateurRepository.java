package com.cvtheque.org.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cvtheque.org.model.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	
	Utilisateur findUtilisateurById(@Param("id") Long id);
	
	Utilisateur findUtilisateurByUsername(@Param("username") String username);
	
	@Query(value = "SELECT dtype FROM utilisateur u WHERE u.username like ?1 ", nativeQuery = true)
	String findUtilisateurRoleByUsername(@Param("username") String username);
	
	/**
	 * Gestion de favoris
	 */
	
	@Modifying
	@Transactional
	@Query(value = 
			"INSERT INTO utilisateur_candidats_favoris (id_utilisateur, id_candidat) "
			+ "VALUES(?1 , ?2)"
			, nativeQuery = true)
	void addCandidatToFavorisUtilisateur(@Param("idUtilisateur") Long idUtilisateur, @Param("idCandidat") Long idCandidat);
	
	@Modifying
	@Transactional
	@Query(value = 
			"DELETE FROM utilisateur_candidats_favoris u WHERE "
			+ " u.id_utilisateur = ?1 AND u.id_candidat =?2"
			, nativeQuery = true)
	void deleteCandidatFromFavorisUtilisateur(@Param("idUtilisateur") Long idUtilisateur, @Param("idCandidat") Long idCandidat);

	
	@Modifying
	@Transactional
	@Query(value = 
			"INSERT INTO utilisateur_opportunites_favoris (id_utilisateur, id_opportunite) "
			+ "VALUES(?1 , ?2)"
			, nativeQuery = true)
	void addOpportuniteToFavorisUtilisateur(@Param("idUtilisateur") Long idUtilisateur, @Param("idOpportunite") Long idOpportunite);
	
	@Modifying
	@Transactional
	@Query(value = 
			"DELETE FROM utilisateur_opportunites_favoris u WHERE "
			+ " u.id_utilisateur = ?1 AND u.id_opportunite =?2"
			, nativeQuery = true)
	void deleteOpportuniteFromFavorisUtilisateur(@Param("idUtilisateur") Long idUtilisateur, @Param("idOpportunite") Long idOpportunite);
	
}
