package com.cvtheque.org.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;


@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name="DTYPE",
    discriminatorType=DiscriminatorType.STRING
    )
public class Utilisateur implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3635172837730319055L;

	@Id
    private @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	
    @Column
	private String identite;
	
    @Column
	private String telephone;
	
    @Column
	private String email;
	
	@Column
	private String posteOccupe;
	
	@Column(length = 4096)
	private String descriptionDetaillee;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private String urlPhoto;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	Entreprise entreprise;
	
	// Liste des candidats favoris pour un Utilisateur (Partenaire / Administrateur)
	@OneToMany
	@JoinTable(name = "utilisateur_candidats_favoris",
	joinColumns = { @JoinColumn(name = "id_utilisateur") },
	inverseJoinColumns = { @JoinColumn(name = "id_candidat") })
	private List<Candidat> candidatsFavoris;
		
	// Liste des opportunités favories pour un partenaire (Partenaire / Administrateur)
	@OneToMany
	@JoinTable(name = "utilisateur_opportunites_favoris",
	joinColumns = { @JoinColumn(name = "id_utilisateur") },
	inverseJoinColumns = { @JoinColumn(name = "id_opportunite") })
	private List<Opportunite> opportunitesFavoris;	

	public Utilisateur() {
		super();
	}

	public String getIdentite() {
		return identite;
	}

	public void setIdentite(String identite) {
		this.identite = identite;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosteOccupe() {
		return posteOccupe;
	}

	public void setPosteOccupe(String posteOccupe) {
		this.posteOccupe = posteOccupe;
	}

	public String getDescriptionDetaillee() {
		return descriptionDetaillee;
	}

	public void setDescriptionDetaillee(String descriptionDetaillee) {
		this.descriptionDetaillee = descriptionDetaillee;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}
	
	public String getUrlPhoto() {
		return urlPhoto;
	}

	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public List<Candidat> getCandidatsFavoris() {
		return candidatsFavoris;
	}

	public void setCandidatsFavoris(List<Candidat> candidatsFavoris) {
		this.candidatsFavoris = candidatsFavoris;
	}

	public List<Opportunite> getOpportunitesFavoris() {
		return opportunitesFavoris;
	}

	public void setOpportunitesFavoris(List<Opportunite> opportunitesFavoris) {
		this.opportunitesFavoris = opportunitesFavoris;
	}

}
