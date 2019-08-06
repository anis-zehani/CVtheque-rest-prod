package com.cvtheque.org.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIdentityReference
@JsonIgnoreProperties
@Data
@Entity
public class Technologie implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3422261716301379660L;

	@Id
	private @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	
    @NotEmpty(message="Odix - technologie ne peut pas être vide")
    @Column(unique=true)
    private String nomTechnologie;
    
    //Attention : ne pas faire de Getter pour ce champs, il génére une erreur Jackson
    @ManyToMany(mappedBy="listeTechnologies")
    private List<Opportunite> listeOpportunites = new ArrayList<Opportunite>();
    
	public Technologie() {
		super();
	}
	
	public Technologie(Long id, String nomTechnologie) {
		super();
		this.id = id;
		this.nomTechnologie = nomTechnologie;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomTechnologie() {
		return nomTechnologie;
	}

	public void setNomTechnologie(String nomTechnologie) {
		this.nomTechnologie = nomTechnologie;
	}
	
}
