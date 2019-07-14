package com.cvtheque.org.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Visa implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3545362140070255221L;

	@Id
	private @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	
	@Column
	@Enumerated(EnumType.STRING)
	private TypeVisa typeVisa;
	
	@Column
	private LocalDate dateDebutVisa;
	
	@Column
	private LocalDate dateFinVisa;

	public Visa() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TypeVisa getTypeVisa() {
		return typeVisa;
	}

	public void setTypeVisa(TypeVisa typeVisa) {
		this.typeVisa = typeVisa;
	}

	public LocalDate getDateDebutVisa() {
		return dateDebutVisa;
	}

	public void setDateDebutVisa(LocalDate dateDebutVisa) {
		this.dateDebutVisa = dateDebutVisa;
	}

	public LocalDate getDateFinVisa() {
		return dateFinVisa;
	}

	public void setDateFinVisa(LocalDate dateFinVisa) {
		this.dateFinVisa = dateFinVisa;
	}
	
}
