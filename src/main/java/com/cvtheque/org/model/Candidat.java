package com.cvtheque.org.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;


import lombok.Data;

@Data
@Entity
@DiscriminatorValue(value="Candidat")
public class Candidat extends Utilisateur implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1004341819482868284L;

	/**Les champs du candidat, hérités de la classe utilisateur :
	 * 
	 * id
	 * Identite
	 * telephone
	 * email
	 * poste_occupe
	 * description_detaillee
	 * urlPhoto
	 * entreprise : @ManyToOne
	 * 
	 ***************
	 */

	@Column
	private LocalDate dateDeNaissance;
	
	@Column
	private String adresse;
	
	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	private SituationFamiliale situationFamiliale;
	
	@Column
	private String nombreEnfants;
	
	@Column
	private String salaireActuel;
	
	@Column
	private String pretentionSalariale;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Note niveauEnFrancais;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Note niveauEnAnglais;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Note noteGlobale;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Disponibilite disponibilite;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Etat etatCandidat;
	
	@Column
	private Date dateDemarrageCarriere;
	
	@Column
	private Date dateEpuisementPasseport;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional=true)
	private Diplome diplome;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional=true)
	private Visa visa;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional=true)
	private Curriculum curriculum;
	
	@ManyToMany
	@JoinTable(name = "candidat_opportunite",
	joinColumns = { @JoinColumn(name = "fk_candidat") },
	inverseJoinColumns = { @JoinColumn(name = "fk_opportunite") })
	private List<Opportunite> listeOpportunites = new ArrayList<Opportunite>();
	
	@ManyToMany
	@JoinTable(name = "candidat_technologie",
	joinColumns = { @JoinColumn(name = "fk_candidat") },
	inverseJoinColumns = { @JoinColumn(name = "fk_technologie") })
	private List<Technologie> listeTechnologies = new ArrayList<Technologie>();
	
	@ManyToMany
	@JoinTable(name = "candidat_certification",
	joinColumns = { @JoinColumn(name = "fk_candidat") },
	inverseJoinColumns = { @JoinColumn(name = "fk_certification") })
	private List<Certification> listeCertifications = new ArrayList<Certification>();

	public Candidat() {
		super();
	}

	public LocalDate getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(LocalDate dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public SituationFamiliale getSituationFamiliale() {
		return situationFamiliale;
	}

	public void setSituationFamiliale(SituationFamiliale situationFamiliale) {
		this.situationFamiliale = situationFamiliale;
	}

	public String getNombreEnfants() {
		return nombreEnfants;
	}

	public void setNombreEnfants(String nombreEnfants) {
		this.nombreEnfants = nombreEnfants;
	}

	public String getSalaireActuel() {
		return salaireActuel;
	}

	public void setSalaireActuel(String salaireActuel) {
		this.salaireActuel = salaireActuel;
	}

	public String getPretentionSalariale() {
		return pretentionSalariale;
	}

	public void setPretentionSalariale(String pretentionSalariale) {
		this.pretentionSalariale = pretentionSalariale;
	}

	public Note getNiveauEnFrancais() {
		return niveauEnFrancais;
	}

	public void setNiveauEnFrancais(Note niveauEnFrancais) {
		this.niveauEnFrancais = niveauEnFrancais;
	}

	public Note getNiveauEnAnglais() {
		return niveauEnAnglais;
	}

	public void setNiveauEnAnglais(Note niveauEnAnglais) {
		this.niveauEnAnglais = niveauEnAnglais;
	}

	public Note getNoteGlobale() {
		return noteGlobale;
	}

	public void setNoteGlobale(Note noteGlobale) {
		this.noteGlobale = noteGlobale;
	}

	public Disponibilite getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(Disponibilite disponibilite) {
		this.disponibilite = disponibilite;
	}

	public Etat getEtatCandidat() {
		return etatCandidat;
	}

	public void setEtatCandidat(Etat etatCandidat) {
		this.etatCandidat = etatCandidat;
	}

	public Date getDateDemarrageCarriere() {
		return dateDemarrageCarriere;
	}

	public void setDateDemarrageCarriere(Date dateDemarrageCarriere) {
		this.dateDemarrageCarriere = dateDemarrageCarriere;
	}

	public Date getDateEpuisementPasseport() {
		return dateEpuisementPasseport;
	}

	public void setDateEpuisementPasseport(Date dateEpuisementPasseport) {
		this.dateEpuisementPasseport = dateEpuisementPasseport;
	}

	public Diplome getDiplome() {
		return diplome;
	}

	public void setDiplome(Diplome diplome) {
		this.diplome = diplome;
	}

	public Visa getVisa() {
		return visa;
	}

	public void setVisa(Visa visa) {
		this.visa = visa;
	}

	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	public List<Opportunite> getListeOpportunites() {
		return listeOpportunites;
	}

	public void setListeOpportunites(List<Opportunite> listeOpportunites) {
		this.listeOpportunites = listeOpportunites;
	}

	public List<Technologie> getListeTechnologies() {
		return listeTechnologies;
	}

	public void setListeTechnologies(List<Technologie> listeTechnologies) {
		this.listeTechnologies = listeTechnologies;
	}

	public List<Certification> getListeCertifications() {
		return listeCertifications;
	}

	public void setListeCertifications(List<Certification> listeCertifications) {
		this.listeCertifications = listeCertifications;
	}

}
