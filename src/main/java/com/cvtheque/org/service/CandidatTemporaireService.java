package com.cvtheque.org.service;

import com.cvtheque.org.model.CandidatTemporaire;

public interface CandidatTemporaireService {

	public CandidatTemporaire addCandidatTemporaire(CandidatTemporaire candidatTemporaire);
	
	public Boolean envoiEmailActivationCompteCandidat(String email);
	
	public Boolean activationCompteCandidatTemporaire(String email);
}
