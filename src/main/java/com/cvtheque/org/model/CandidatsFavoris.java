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
public class CandidatsFavoris implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6140276321360588560L;

	@Id
    private @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	
	@Column
	private Long idUtilisateur;
	
	@Column
	private Long idCandidat;
	
	@Column
	private String identiteCandidat;

	public Long getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public Long getIdCandidat() {
		return idCandidat;
	}

	public void setIdCandidat(Long idCandidat) {
		this.idCandidat = idCandidat;
	}

	public String getIdentiteCandidat() {
		return identiteCandidat;
	}

	public void setIdentiteCandidat(String identiteCandidat) {
		this.identiteCandidat = identiteCandidat;
	}
}
