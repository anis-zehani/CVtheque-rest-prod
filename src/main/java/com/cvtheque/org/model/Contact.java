package com.cvtheque.org.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
@DiscriminatorValue(value="Contact")
public class Contact extends Utilisateur{
	
	private static final long serialVersionUID = 1L;
	
	/**Les champs remplis pour le contact sont uniquement :
	 * 
	 * Identite
	 * telephone
	 * email
	 * poste_occupe
	 * description_detaillee
	 * urlPhoto
	 * entreprise : @ManyToOne
	 */

	public Contact() {
		super();
	}
}
