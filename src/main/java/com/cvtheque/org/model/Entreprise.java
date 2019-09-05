package com.cvtheque.org.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
public class Entreprise implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 621044971711896472L;

	@Id
	private @GeneratedValue(strategy = GenerationType.IDENTITY) Long idEntreprise;
	
	@NotEmpty(message="Odix - entreprise ne peut pas être vide")
    @Column(unique=true)
	private String nomEntreprise;
	
	@Column(length = 4096)
	private String descriptionDetaillee;
	
	// Pour regrouper les entreprises par IdUtilisateur : qui a inséré cette entreprise 
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Utilisateur utilisateur;

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

	public String getDescriptionDetaillee() {
		return descriptionDetaillee;
	}

	public void setDescriptionDetaillee(String descriptionDetaillee) {
		this.descriptionDetaillee = descriptionDetaillee;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
}
