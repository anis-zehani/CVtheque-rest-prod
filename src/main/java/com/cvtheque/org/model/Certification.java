package com.cvtheque.org.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
public class Certification {

    @Id
    private @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	
    @NotEmpty(message="Odix - certification ne peut pas être vide")
    @Column(unique=true)
	private String nomCertification;

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
