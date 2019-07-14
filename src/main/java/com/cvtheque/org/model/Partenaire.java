package com.cvtheque.org.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
@DiscriminatorValue(value="Partenaire")
public class Partenaire extends Utilisateur {
	
	private static final long serialVersionUID = 1L;
	
	/**Les champs remplis pour le partenaire sont uniquement :
	 * 
	 * Identite
	 * telephone
	 * email
	 * poste_occupe
	 * description_detaillee
	 * urlPhoto
	 * login
	 * password
	 * entreprise : @ManyToOne
	 * etatPartenaire : Actif/Inactif
	 * listeCvSauvegardes : @OneToMany
	 */
	
	@Column
	@Enumerated(EnumType.STRING)
	private Etat etatPartenaire;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Curriculum> listeCvSauvegardes = new ArrayList<Curriculum>();

	public Partenaire() {
		super();
	}

	public Etat getEtatPartenaire() {
		return etatPartenaire;
	}

	public void setEtatPartenaire(Etat etatPartenaire) {
		this.etatPartenaire = etatPartenaire;
	}
}
