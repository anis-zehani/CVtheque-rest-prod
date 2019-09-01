package com.cvtheque.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cvtheque.org.model.Collaborateur;

@Repository
public interface CollaborateurRepository extends JpaRepository<Collaborateur, Long> {
	
	Collaborateur findByIdentite(@Param("identite") String identite);
	
	String findPasswordByUsername(@Param("username") String username);

}
