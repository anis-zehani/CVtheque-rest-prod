package com.cvtheque.org.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
@DiscriminatorValue(value="Collaborateur")
public class Collaborateur extends Utilisateur {
	
	private static final long serialVersionUID = 1L;
	
	/**Les champs remplis pour le collaborateur sont uniquement :
	 * 
	 * Identite
	 * email
	 * login
	 * password
	 */

	public Collaborateur() {
		super();
	}	
}
