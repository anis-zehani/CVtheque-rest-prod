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
public class ChiffresCles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5761069691032544954L;
	
	@Id
    private @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	
	@Column
	private Integer totalCandidats;
	
	@Column
	private Integer totalOpportunites;
	
	@Column
	private Integer totalPartenaires;
	
	@Column
	private Integer totalContacts;
	
	@Column
	private Integer totalTechnologies;
	
	@Column
	private Integer totalEntreprises;

	public Integer getTotalCandidats() {
		return totalCandidats;
	}

	public void setTotalCandidats(Integer totalCandidats) {
		this.totalCandidats = totalCandidats;
	}

	public Integer getTotalOpportunites() {
		return totalOpportunites;
	}

	public void setTotalOpportunites(Integer totalOpportunites) {
		this.totalOpportunites = totalOpportunites;
	}

	public Integer getTotalPartenaires() {
		return totalPartenaires;
	}

	public void setTotalPartenaires(Integer totalPartenaires) {
		this.totalPartenaires = totalPartenaires;
	}

	public Integer getTotalContacts() {
		return totalContacts;
	}

	public void setTotalContacts(Integer totalContacts) {
		this.totalContacts = totalContacts;
	}

	public Integer getTotalTechnologies() {
		return totalTechnologies;
	}

	public void setTotalTechnologies(Integer totalTechnologies) {
		this.totalTechnologies = totalTechnologies;
	}

	public Integer getTotalEntreprises() {
		return totalEntreprises;
	}

	public void setTotalEntreprises(Integer totalEntreprises) {
		this.totalEntreprises = totalEntreprises;
	}
}
