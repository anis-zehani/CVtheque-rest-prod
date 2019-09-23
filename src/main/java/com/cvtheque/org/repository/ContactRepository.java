package com.cvtheque.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cvtheque.org.model.Contact;
import com.cvtheque.org.model.Utilisateur;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	Contact findByIdentite(@Param("identite") String identite);
	
	Contact findByEmail(@Param("email") String email);
	
	List<Contact> findAllByUtilisateur(@Param("utilisateur") Utilisateur utilisateur);
	
}
