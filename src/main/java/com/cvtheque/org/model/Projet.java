package com.cvtheque.org.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Projet {
	
	@Id
	private @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	
	@Column
	private String nomProjet;
	
	@Column(length = 1024)
	private String detailsProjet;

	public Projet() {
		super();
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

	public Long getId() {
		return id;
	}
}
