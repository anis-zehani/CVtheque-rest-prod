package com.cvtheque.org.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
public class Certification implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1998921502004317093L;

	@Id
    private @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	
    @NotEmpty(message="Odix - certification ne peut pas être vide")
    @Column(unique=true)
	private String nomCertification;
    
    @ManyToMany(mappedBy="listeCertifications")
	private List<Candidat> listeCandidats = new ArrayList<Candidat>();

    public Certification() {
		super();
	}

    public Certification(Long id, String nomCertification) {
		super();
		this.id = id;
		this.nomCertification = nomCertification;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomCertification() {
		return nomCertification;
	}

	public void setNomCertification(String nomCertification) {
		this.nomCertification = nomCertification;
	}

}
