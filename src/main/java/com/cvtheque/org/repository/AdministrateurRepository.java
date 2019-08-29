package com.cvtheque.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cvtheque.org.model.Administrateur;

@Repository
public interface AdministrateurRepository extends JpaRepository<Administrateur, Long> {

	@Query(value = "SELECT * FROM utilisateur u WHERE u.dtype like ?1 ", nativeQuery = true)
	Administrateur verifyAdmin(@Param("dtype") String dtype);
}
