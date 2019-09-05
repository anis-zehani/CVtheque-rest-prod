package com.cvtheque.org.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
@DiscriminatorValue(value="Partenaire")
public class Partenaire extends Utilisateur {
	
	private static final long serialVersionUID = 1L;
	
	/**Les champs remplis pour le partenaire sont uniquement :
	 * 
	 * Identite
	 * telephone
	 * email
	 * poste_occupe
	 * description_detaillee
	 * urlPhoto
	 * login
	 * password
	 * entreprise : @ManyToOne
	 * etatPartenaire : Actif/Inactif
	 * utilisateur : @ManyToOne
	 */
	
	@Column
	@Enumerated(EnumType.STRING)
	private Etat etatPartenaire;
	
	// Pour regrouper les partenaires par IdUtilisateur : qui a inséré ce partenaire (pour le moment c'est l'Administrateur)
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Utilisateur utilisateur;
	
	// Liste des candidats favoris pour un partenaire
	@OneToMany
	@JoinTable(name = "partenaire_candidats_favoris",
	joinColumns = { @JoinColumn(name = "id_partenaire") },
	inverseJoinColumns = { @JoinColumn(name = "id_candidat") })
	private List<Candidat> candidatsFavoris;
	
	public Partenaire() {
		super();
	}

	public Etat getEtatPartenaire() {
		return etatPartenaire;
	}

	public void setEtatPartenaire(Etat etatPartenaire) {
		this.etatPartenaire = etatPartenaire;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<Candidat> getCandidatsFavoris() {
		return candidatsFavoris;
	}

	public void setCandidatsFavoris(List<Candidat> candidatsFavoris) {
		this.candidatsFavoris = candidatsFavoris;
	}
}
