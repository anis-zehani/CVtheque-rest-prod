package com.cvtheque.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cvtheque.org.model.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	
	Utilisateur findUtilisateurById(@Param("id") Long id);
	
	Utilisateur findUtilisateurByUsername(@Param("username") String username);
	
	@Query(value = "SELECT dtype FROM utilisateur u WHERE u.username like ?1 ", nativeQuery = true)
	String findUtilisateurRoleByUsername(@Param("username") String username);
}
