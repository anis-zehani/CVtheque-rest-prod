package com.cvtheque.org.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cvtheque.org.model.Entreprise;
import com.cvtheque.org.model.Etat;
import com.cvtheque.org.model.Partenaire;

@Repository
public interface PartenaireRepository extends JpaRepository<Partenaire, Long> {
	
	Partenaire findByIdentite(@Param("identite") String identite);
	
	Partenaire findByUsername(@Param("username") String username);
	
	List<Partenaire> findByEtatPartenaire(@Param("etatPartenaire") Etat etatPartenaire);
	
	List<Partenaire> findAllByEntreprise(@Param("entreprise") Entreprise entreprise);
	

	//INNER JOIN : JPQL : La liste des Partenaires pour une Entreprise
	@Query("FROM Partenaire p INNER JOIN p.listeEntreprises p1 ON p1.idEntreprise = :idEntreprise")
	List<Partenaire> findAllPartenairesByEntreprise(@Param("idEntreprise") Long idEntreprise);
	
	//UPDATE le lien entre un partenaire et une entreprise : met entreprise à NULL
	@Modifying
	@Transactional
	@Query("UPDATE Partenaire p SET p.entreprise = null WHERE p.id = :idPartenaire")
	void updateLinkPartenaireEntreprise(@Param("idPartenaire") Long idPartenaire);

	
}
