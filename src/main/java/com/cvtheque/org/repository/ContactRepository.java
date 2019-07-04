package com.cvtheque.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cvtheque.org.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	Contact findByIdentite(@Param("identite") String identite);

}
