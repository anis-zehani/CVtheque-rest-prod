package com.cvtheque.org.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Opportunite {
	
	@Id
	private @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	
	@Column
	private String titreOpportunite;
	
	@Column(length = 1024)
	private String descriptionOpportunite;

	@Column
	private LocalDate dateAjout;
	
	@Column
	private LocalDate dateDemarrageSouhaitee;
	
	@Column
	private String tjmOpportunite;
	
	@Column
	private String urlPhotoOpportunite;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Etat etatOpportunite;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	Partenaire responsableOpportunite;

	public Opportunite() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitreOpportunite() {
		return titreOpportunite;
	}

	public void setTitreOpportunite(String titreOpportunite) {
		this.titreOpportunite = titreOpportunite;
	}

	public String getDescriptionOpportunite() {
		return descriptionOpportunite;
	}

	public void setDescriptionOpportunite(String descriptionOpportunite) {
		this.descriptionOpportunite = descriptionOpportunite;
	}

	public LocalDate getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(LocalDate dateAjout) {
		this.dateAjout = dateAjout;
	}

	public LocalDate getDateDemarrageSouhaitee() {
		return dateDemarrageSouhaitee;
	}

	public void setDateDemarrageSouhaitee(LocalDate dateDemarrageSouhaitee) {
		this.dateDemarrageSouhaitee = dateDemarrageSouhaitee;
	}

	public String getTjmOpportunite() {
		return tjmOpportunite;
	}

	public void setTjmOpportunite(String tjmOpportunite) {
		this.tjmOpportunite = tjmOpportunite;
	}

	public String getUrlPhotoOpportunite() {
		return urlPhotoOpportunite;
	}

	public void setUrlPhotoOpportunite(String urlPhotoOpportunite) {
		this.urlPhotoOpportunite = urlPhotoOpportunite;
	}

	public Etat getEtatOpportunite() {
		return etatOpportunite;
	}

	public void setEtatOpportunite(Etat etatOpportunite) {
		this.etatOpportunite = etatOpportunite;
	}

	public Partenaire getResponsableOpportunite() {
		return responsableOpportunite;
	}

	public void setResponsableOpportunite(Partenaire responsableOpportunite) {
		this.responsableOpportunite = responsableOpportunite;
	}

}
