package com.cvtheque.org.service;

import java.util.List;
import java.util.Optional;

import com.cvtheque.org.model.Certification;

public interface CertificationService {
	
	public List<Certification> getAllCertifications();
	
	public Optional<Certification> getCertification(Long id);
	
	public Certification addCertification(Certification certification);
	
	public Certification editCertification(Certification certification);
	
	public boolean deleteCertification(Long id);

}
