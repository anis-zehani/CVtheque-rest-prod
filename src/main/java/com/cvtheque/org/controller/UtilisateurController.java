package com.cvtheque.org.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvtheque.org.model.Candidat;
import com.cvtheque.org.model.Diplome;
import com.cvtheque.org.model.Disponibilite;
import com.cvtheque.org.model.Ecole;
import com.cvtheque.org.model.Entreprise;
import com.cvtheque.org.model.Etat;
import com.cvtheque.org.model.Note;
import com.cvtheque.org.model.SituationFamiliale;
import com.cvtheque.org.model.TypeDiplome;
import com.cvtheque.org.model.TypeVisa;
import com.cvtheque.org.model.Visa;
import com.cvtheque.org.service.CandidatService;
import com.cvtheque.org.util.LinkedinUtil;

import net.minidev.json.JSONObject;

@CrossOrigin
@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {
	
	@Autowired
	LinkedinUtil linkedInUtil;
	
	@Autowired
	CandidatService candidatService;
	
	@GetMapping(value = "/code-linkedin")
	public JSONObject codeLinkedin() {
		
		return linkedInUtil.codeLinkedin();
	}
	
	@PostMapping("/redirect-linkedin/{code}/{state}")
	public ResponseEntity<?> redirectLinkedin(@PathVariable String code, @PathVariable String state) throws Exception {
		
		JSONObject profileLinkedIn = linkedInUtil.redirectLinkedin(code, state);
		
		//Si idLinkedin existe : donc profil a été bien reçu de la part de  Linkedin
		if(profileLinkedIn.get("idLinkedin") != "") {
			
			String idLinkedin = profileLinkedIn.get("idLinkedin").toString();
			
			// On cherche si ce candidat existe --> on le retourne à la UI
			if(candidatService.getCandidatByIdLinkedin(idLinkedin)!= null) {
				
				Candidat ancienCandidat = candidatService.getCandidatByIdLinkedin(idLinkedin);
				
				ResponseEntity<?> response =  linkedInUtil.createAuthenticationToken(ancienCandidat.getUsername(), ancienCandidat.getPassword());
				
				return response;
				
			}
			// idLinkedin n'a pas été trouvé -> candidat n'existe pas --> on le créé et on le retourne à la UI
			else 
			{
				Candidat nouveauCandidat = new Candidat();
				nouveauCandidat.setIdLinkedin(profileLinkedIn.get("idLinkedin").toString());
				nouveauCandidat.setIdentite(profileLinkedIn.get("firstName").toString() + " " + profileLinkedIn.get("lastName").toString());
				nouveauCandidat.setUsername(profileLinkedIn.get("idLinkedin").toString());
				nouveauCandidat.setPassword(profileLinkedIn.get("idLinkedin").toString());
				nouveauCandidat.setEmail(profileLinkedIn.get("emailAddress").toString());
				nouveauCandidat.setEntreprise(new Entreprise());
				Diplome diplome = new Diplome();
				diplome.setTypeDiplome(TypeDiplome.Non_Mentionee);
				diplome.setEcole(new Ecole());
				Visa visa = new Visa();
				visa.setTypeVisa(TypeVisa.Non_Mentionee);
				nouveauCandidat.setDiplome(diplome);
				nouveauCandidat.setVisa(visa);
				nouveauCandidat.setDisponibilite(Disponibilite.Non_Mentionee);
				nouveauCandidat.setEtatCandidat(Etat.True);
				nouveauCandidat.setNiveauEnAnglais(Note.Non_Mentionee);
				nouveauCandidat.setNiveauEnFrancais(Note.Non_Mentionee);
				nouveauCandidat.setNoteGlobale(Note.Non_Mentionee);
				nouveauCandidat.setSituationFamiliale(SituationFamiliale.Non_Mentionee);
				
				//Télécharger la photo de profil
				//nouveauCandidat.setUrlPhoto(profileLinkedIn.get("profilePicture").toString()); 
				
				Candidat persistedCandidat = candidatService.addCandidat(nouveauCandidat);
				
				ResponseEntity<?> response = linkedInUtil.createAuthenticationToken(persistedCandidat.getUsername(), persistedCandidat.getPassword());
				
				return response;
			}
		}
		
		return null;
	}

}
