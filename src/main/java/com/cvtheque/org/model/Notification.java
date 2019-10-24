package com.cvtheque.org.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Notification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1938031921001982088L;

	@Id
    private @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	
	@ManyToMany
	@JoinTable(name = "notification_destinataire",
	joinColumns = { @JoinColumn(name = "id_notification") },
	inverseJoinColumns = { @JoinColumn(name = "id_destinataire") })
	private List<Utilisateur> listeDestinatairesNotification;
	
	@Column
	private Utilisateur generateurNotification;
	
	@Column
	private String objetNotification;
	
	@Column(length = 1024)
	private String corpstNotification;
	
	@Column
	private LocalDateTime dateAjout;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Etat etatNotification;
	
	/*Paramètres d'informations supplémantaires pour l'affichage de la Pop-Up Notification*/
	@Column
	private Candidat candidatNotification;
	
	@Column
	private PartenaireTemporaire partenaireNotification;
	
	@Column
	private Opportunite opportuniteNotification;

	public Long getId() {
		return id;
	}

	public String getObjetNotification() {
		return objetNotification;
	}

	public String getCorpstNotification() {
		return corpstNotification;
	}

	public LocalDateTime getDateAjout() {
		return dateAjout;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setObjetNotification(String objetNotification) {
		this.objetNotification = objetNotification;
	}

	public void setCorpstNotification(String corpstNotification) {
		this.corpstNotification = corpstNotification;
	}

	public void setDateAjout(LocalDateTime dateAjout) {
		this.dateAjout = dateAjout;
	}

	public List<Utilisateur> getListeDestinatairesNotification() {
		return listeDestinatairesNotification;
	}

	public Utilisateur getGenerateurNotification() {
		return generateurNotification;
	}

	public void setListeDestinatairesNotification(List<Utilisateur> listeDestinatairesNotification) {
		this.listeDestinatairesNotification = listeDestinatairesNotification;
	}

	public void setGenerateurNotification(Utilisateur generateurNotification) {
		this.generateurNotification = generateurNotification;
	}

	public Etat getEtatNotification() {
		return etatNotification;
	}

	public void setEtatNotification(Etat etatNotification) {
		this.etatNotification = etatNotification;
	}

	public Candidat getCandidatNotification() {
		return candidatNotification;
	}

	public PartenaireTemporaire getPartenaireNotification() {
		return partenaireNotification;
	}

	public Opportunite getOpportuniteNotification() {
		return opportuniteNotification;
	}

	public void setCandidatNotification(Candidat candidatNotification) {
		this.candidatNotification = candidatNotification;
	}

	public void setPartenaireNotification(PartenaireTemporaire partenaireNotification) {
		this.partenaireNotification = partenaireNotification;
	}

	public void setOpportuniteNotification(Opportunite opportuniteNotification) {
		this.opportuniteNotification = opportuniteNotification;
	}
}
