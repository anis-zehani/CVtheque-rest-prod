package com.cvtheque.org.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cvtheque.org.model.Certification;
import com.cvtheque.org.repository.CertificationRepository;


@Service
public class CertificationServiceImpl implements CertificationService{
	
	private final CertificationRepository certificationRepository;

	CertificationServiceImpl(CertificationRepository certificationRepository) {
		super();
		this.certificationRepository = certificationRepository;
	}
	
	public List<Certification> getAllCertifications() {
	    return certificationRepository.findAll();
	}
	
	public Optional<Certification> getCertification(Long id) {
		return certificationRepository.findById(id);
	}
	
	//Ajouter une certification
	public Certification addCertification(Certification certification) 
	{
		if(certificationRepository.findByNomCertification(certification.getNomCertification()) == null)
		{
			return certificationRepository.save(certification);
		}
		return null;
	}
	
	//Modifier une certification
	public Certification editCertification(Certification certification) 
	{
		if(certificationRepository.existsById(certification.getId()))
		{
			return certificationRepository.save(certification);
		}
		return null;
	}
	
	//Supprimer une certification
	public void deleteCertification(Long id) 
	{
		if(certificationRepository.existsById(id))
		{
			certificationRepository.deleteById(id);
		}
	}

}