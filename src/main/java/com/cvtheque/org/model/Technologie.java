

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

	/*public List<Candidat> getListeCandidats() {
		return listeCandidats;
	}

	public void setListeCandidats(List<Candidat> listeCandidats) {
		this.listeCandidats = listeCandidats;
	}

	public List<Opportunite> getListeOpportunites() {
		return listeOpportunites;
	}

	public void setListeOpportunites(List<Opportunite> listeOpportunites) {
		this.listeOpportunites = listeOpportunites;
	}*/
	
}
