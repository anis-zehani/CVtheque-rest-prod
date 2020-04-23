package com.cvtheque.org.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Data
//@Entity
public class Fichier implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7697898031944652164L;
	
	@Id
	private @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	
    @Column(unique=true)
    private String nomFichier;
    
    @Column(unique=true)
    private String urlFichier;
    
    @Column(unique=true)
    private String dateCreationFichier;
    
    @Column(unique=true)
    private String tailleFichier;

	public Fichier() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getNomFichier() {
		return nomFichier;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}

	public String getUrlFichier() {
		return urlFichier;
	}

	public void setUrlFichier(String urlFichier) {
		this.urlFichier = urlFichier;
	}

	public String getDateCreationFichier() {
		return dateCreationFichier;
	}

	public String getTailleFichier() {
		return tailleFichier;
	}

	public void setDateCreationFichier(String dateCreationFichier) {
		this.dateCreationFichier = dateCreationFichier;
	}

	public void setTailleFichier(String tailleFichier) {
		this.tailleFichier = tailleFichier;
	}
	
}
