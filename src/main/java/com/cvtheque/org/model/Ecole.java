package com.cvtheque.org.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
public class Ecole implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -858562105980175964L;

	@Id
    private @GeneratedValue(strategy = GenerationType.IDENTITY) Long idEcole;
	
    @NotEmpty(message="Odix - école ne peut pas être vide")
    @Column(unique=true)
	private String nomEcole;

    public Ecole() {
		super();
	}

    public Ecole(Long idEcole, String nomEcole) {
		super();
		this.idEcole = idEcole;
		this.nomEcole = nomEcole;
	}

	public Long getIdEcole() {
		return idEcole;
	}

	public void setIdEcole(Long idEcole) {
		this.idEcole = idEcole;
	}

	public String getNomEcole() {
		return nomEcole;
	}

	public void setNomEcole(String nomEcole) {
		this.nomEcole = nomEcole;
	}
    
}
