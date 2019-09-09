package com.cvtheque.org.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class OpportunitesFavoris implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3044733846110538628L;

	@Id
    private @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	
	@Column
	private Long idUtilisateur;
	
	@Column
	private Long idOpportunite;
	
	@Column
	private String titreOpportunite;

	public Long getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public Long getIdOpportunite() {
		return idOpportunite;
	}

	public void setIdOpportunite(Long idOpportunite) {
		this.idOpportunite = idOpportunite;
	}

	public String getTitreOpportunite() {
		return titreOpportunite;
	}

	public void setTitreOpportunite(String titreOpportunite) {
		this.titreOpportunite = titreOpportunite;
	}
}
