package com.cvtheque.org.model;

import java.io.Serializable;
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
public class Diplome implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7405930119584792399L;

	@Id
    private @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    
    @Column
	@Enumerated(EnumType.STRING)
	private TypeDiplome typeDiplome;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
	Ecole ecole;

    @Column
	private LocalDate dateObtentionDiplome;

	public Diplome() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TypeDiplome getTypeDiplome() {
		return typeDiplome;
	}

	public void setTypeDiplome(TypeDiplome typeDiplome) {
		this.typeDiplome = typeDiplome;
	}

	public Ecole getEcole() {
		return ecole;
	}

	public void setEcole(Ecole ecole) {
		this.ecole = ecole;
	}

	public LocalDate getDateObtentionDiplome() {
		return dateObtentionDiplome;
	}

	public void setDateObtentionDiplome(LocalDate dateObtentionDiplome) {
		this.dateObtentionDiplome = dateObtentionDiplome;
	}

}
