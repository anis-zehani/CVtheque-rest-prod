package com.cvtheque.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cvtheque.org.model.Projet;
import com.cvtheque.org.model.Utilisateur;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long> {
	
	Projet findByNomProjet(@Param("nomProjet") String nomProjet);
	
	List<Projet> findAllByUtilisateur(@Param("utilisateur") Utilisateur utilisateur);
}
