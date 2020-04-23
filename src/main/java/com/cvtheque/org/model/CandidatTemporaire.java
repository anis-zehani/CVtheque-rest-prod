package com.cvtheque.org.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class CandidatTemporaire  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4470340248430361949L;

	@Id
    private @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	
	@Column
	private String identite;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
    @Column
	private String email;
    
	@Column
	private LocalDateTime dateAjout;

	public Long getId() {
		return id;
	}

	public String getIdentite() {
		return identite;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public LocalDateTime getDateAjout() {
		return dateAjout;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIdentite(String identite) {
		this.identite = identite;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDateAjout(LocalDateTime dateAjout) {
		this.dateAjout = dateAjout;
	}

}
