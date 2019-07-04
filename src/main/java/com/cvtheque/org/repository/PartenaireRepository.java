package com.cvtheque.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cvtheque.org.model.Etat;
import com.cvtheque.org.model.Partenaire;

@Repository
public interface PartenaireRepository extends JpaRepository<Partenaire, Long> {
	
	Partenaire findByIdentite(@Param("identite") String identite);
	
	List<Partenaire> findByEtatPartenaire(@Param("etatPartenaire") Etat etatPartenaire);
	
}
