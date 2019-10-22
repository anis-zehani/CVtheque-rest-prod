package com.cvtheque.org.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

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
	
	@OneToMany
	@JoinTable(name = "notification_destinataire",
	joinColumns = { @JoinColumn(name = "id_notification") },
	inverseJoinColumns = { @JoinColumn(name = "id_destinataire") })
	private List<Utilisateur> listeDestinatairesNotification;
	
	@Column
	private Utilisateur generateurNotification;
	
	@Column
	private String objectNotification;
	
	@Column(length = 1024)
	private String corpstNotification;
	
	@Column
	private LocalDateTime dateAjout;

	public Long getId() {
		return id;
	}

	public String getObjectNotification() {
		return objectNotification;
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

	public void setObjectNotification(String objectNotification) {
		this.objectNotification = objectNotification;
	}

	public void setCorpstNotification(String corpstNotification) {
		this.corpstNotification = corpstNotification;
	}

	public void setDateAjout(LocalDateTime dateAjout) {
		this.dateAjout = dateAjout;
	}
}
