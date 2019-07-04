package com.cvtheque.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cvtheque.org.model.Ecole;

@Repository
public interface EcoleRepository extends JpaRepository<Ecole, Long> {
	
	Ecole findByNomEcole(@Param("nomEcole") String nomEcole);

}
