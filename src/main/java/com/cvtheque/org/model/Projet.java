package com.cvtheque.org.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Projet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2987976139475982418L;

	@Id
	private @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	
	@Column
	private String nomProjet;
	
	@Column(length = 1024)
	private String detailsProjet;
	
	// Pour regrouper les projets par IdUtilisateur : qui a inséré ce projet
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Utilisateur utilisateur;

	public Projet() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getNomProjet() {
		return nomProjet;
	}

	public void setNomProjet(String nomProjet) {
		this.nomProjet = nomProjet;
	}

	public String getDetailsProjet() {
		return detailsProjet;
	}

	public void setDetailsProjet(String detailsProjet) {
		this.detailsProjet = detailsProjet;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
}
