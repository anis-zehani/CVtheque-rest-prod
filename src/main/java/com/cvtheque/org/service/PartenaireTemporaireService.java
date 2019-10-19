package com.cvtheque.org.service;

import java.util.List;

import com.cvtheque.org.model.PartenaireTemporaire;

public interface PartenaireTemporaireService {
	
	public List<PartenaireTemporaire> getAllPartenairesTemporaires();

	public PartenaireTemporaire addPartenaireTemporaire(PartenaireTemporaire partenaireTemporaire);
}
