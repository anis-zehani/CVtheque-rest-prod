package com.cvtheque.org.repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cvtheque.org.model.Priorite;
import com.cvtheque.org.model.Projet;
import com.cvtheque.org.model.Rappel;

@Repository
public interface RappelRepository extends JpaRepository<Rappel, Long> {
	
	Rappel findByDetailsRappel(@Param("detailsRappel") String detailsRappel);
	
	List<Rappel> findByProjet(@Param("projet") Projet projet);
	
	@Query("SELECT r FROM Rappel r where r.dateEcheance =:date")
	List<Rappel> findByToday(@Param("date") LocalDate date);
	
	@Query("SELECT r FROM Rappel r where r.dateEcheance > :dateDebut and r.dateEcheance < :dateFin")
	List<Rappel> findByNext7Days(@Param("dateDebut") LocalDate dateDebut, @Param("dateFin") LocalDate dateFin);
	
	List<Rappel> findByPriorite(@Param("priorite") Priorite priorite);
	
	//Native Query = true : Supprimer tous les rappels appartenant à un projet
	@Modifying
	@Transactional
	@Query(value = 
			"DELETE FROM Rappel r WHERE "
			+ "r.projet_id = ?1"
			, nativeQuery = true)
	void deleteAllRappelsByProjet(@Param("projet") Projet projet);
	
}
