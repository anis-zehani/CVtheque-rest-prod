package com.cvtheque.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cvtheque.org.model.Technologie;

@Repository
public interface TechnologieRepository extends JpaRepository<Technologie, Long> {
	
	Technologie findByNomTechnologie(@Param("nomTechnologie") String nomTechnologie);

}
