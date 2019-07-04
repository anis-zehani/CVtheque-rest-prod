package com.cvtheque.org.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
@DiscriminatorValue(value="Administrateur")
public class Administrateur extends Utilisateur{
	
	private static final long serialVersionUID = 1L;

	public Administrateur() {
		super();
	}
	
	

}
