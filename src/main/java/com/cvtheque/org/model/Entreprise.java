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
public class Entreprise {
	
	@Id
	private @GeneratedValue(strategy = GenerationType.IDENTITY) Long idEntreprise;
	
	@NotEmpty(message="Odix - entreprise ne peut pas être vide")
    @Column(unique=true)
	private String nomEntreprise;

	public Entreprise() {
		super();
	}

	public Entreprise(Long idEntreprise, String nomEntreprise) {
		super();
		this.idEntreprise = idEntreprise;
		this.nomEntreprise = nomEntreprise;
	}

	public Long getIdEntreprise() {
		return idEntreprise;
	}

	public void setIdEntreprise(Long idEntreprise) {
		this.idEntreprise = idEntreprise;
	}

	public String getNomEntreprise() {
		return nomEntreprise;
	}

	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}

}
