package com.cvtheque.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cvtheque.org.model.Etat;
import com.cvtheque.org.model.Opportunite;

@Repository
public interface OpportuniteRepository extends JpaRepository<Opportunite, Long> {
	
	List<Opportunite> findByEtatOpportunite(@Param("etatOpportunite") Etat etatOpportunite);

}
