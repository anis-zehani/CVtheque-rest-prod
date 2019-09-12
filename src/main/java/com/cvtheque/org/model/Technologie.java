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
public class Technologie implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3422261716301379660L;

	@Id
	private @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	
    @NotEmpty(message="Odix - technologie ne peut pas être vide")
    @Column(unique=true)
    private String nomTechnologie;
    
	@Column(length = 4096)
	private String descriptionDetaillee;
	
	@Column
	private Integer statNombreCandidatsLies;
	
	@Column
	private Integer statNombreOpportuniteLiees;
	
	// Pour regrouper les technologies par IdUtilisateur : qui a inséré cette technologie
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Utilisateur utilisateur;
    
	public Technologie() {
		super();
	}
	
	public Technologie(Long id, String nomTechnologie) {
		super();
		this.id = id;
		this.nomTechnologie = nomTechnologie;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomTechnologie() {
		return nomTechnologie;
	}

	public void setNomTechnologie(String nomTechnologie) {
		this.nomTechnologie = nomTechnologie;
	}

	public String getDescriptionDetaillee() {
		return descriptionDetaillee;
	}

	public void setDescriptionDetaillee(String descriptionDetaillee) {
		this.descriptionDetaillee = descriptionDetaillee;
	}

	public Integer getStatNombreCandidatsLies() {
		return statNombreCandidatsLies;
	}

	public void setStatNombreCandidatsLies(Integer statNombreCandidatsLies) {
		this.statNombreCandidatsLies = statNombreCandidatsLies;
	}

	public Integer getStatNombreOpportuniteLiees() {
		return statNombreOpportuniteLiees;
	}

	public void setStatNombreOpportuniteLiees(Integer statNombreOpportuniteLiees) {
		this.statNombreOpportuniteLiees = statNombreOpportuniteLiees;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
}
