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
public class Curriculum implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3125388939469501605L;

	@Id
	private @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	
	@Column
	private String urlCvOriginal;
	
	@Column
	private String urlCvOdix;

	public Curriculum() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrlCvOriginal() {
		return urlCvOriginal;
	}

	public void setUrlCvOriginal(String urlCvOriginal) {
		this.urlCvOriginal = urlCvOriginal;
	}

	public String getUrlCvOdix() {
		return urlCvOdix;
	}

	public void setUrlCvOdix(String urlCvOdix) {
		this.urlCvOdix = urlCvOdix;
	}

}
