package com.cvtheque.org.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvtheque.org.model.CandidatTemporaire;
import com.cvtheque.org.repository.CandidatTemporaireRepository;

@Service
public class CandidatTemporaireServiceImpl implements CandidatTemporaireService {
	
	@Autowired
	CandidatTemporaireRepository candidatTemporaireRepository;

	@Override
	public CandidatTemporaire addCandidatTemporaire(CandidatTemporaire candidatTemporaire) {
		
		CandidatTemporaire candidat = candidatTemporaireRepository.save(candidatTemporaire);
		
		return candidat;
	}

	
}
