package com.cvtheque.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cvtheque.org.model.CandidatTemporaire;

@Repository
public interface CandidatTemporaireRepository extends JpaRepository<CandidatTemporaire, Long> {

}
