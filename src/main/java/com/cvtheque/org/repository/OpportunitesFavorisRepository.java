package com.cvtheque.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.cvtheque.org.model.OpportunitesFavoris;

public interface OpportunitesFavorisRepository  extends JpaRepository<OpportunitesFavoris, Long> {

	List<OpportunitesFavoris> findByIdUtilisateur(@Param("idUtilisateur") Long idUtilisateur);
	
}
