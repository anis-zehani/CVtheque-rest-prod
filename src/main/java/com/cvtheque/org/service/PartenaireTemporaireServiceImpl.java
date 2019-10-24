package com.cvtheque.org.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvtheque.org.model.Entreprise;
import com.cvtheque.org.model.Partenaire;
import com.cvtheque.org.model.PartenaireTemporaire;
import com.cvtheque.org.model.Utilisateur;
import com.cvtheque.org.repository.PartenaireTemporaireRepository;
import com.cvtheque.org.util.Consts;
import com.cvtheque.org.util.JavaMailSenderService;

@Service
public class PartenaireTemporaireServiceImpl implements PartenaireTemporaireService{
	
	private static final String urlPlatformeLoginPage = Consts.urlPlatformeLoginPage;
	
	@Autowired
	PartenaireTemporaireRepository partenaireTemporaireRepository;
	
	@Autowired
	PartenaireService partenaireService;
	
	@Autowired
	EntrepriseService entrepriseService;
	
	@Autowired
	JavaMailSenderService mailService;
	
	@Autowired
	NotificationService notificationService;
	
	@Autowired
	UtilisateurService utilisateurService;
	
	
	// Retourne la liste des Partenaires Temporaires pas encore activés
	@Override
	public List<PartenaireTemporaire> getAllPartenairesTemporaires() {
		
		List<PartenaireTemporaire> listePartenairesDistincts = new ArrayList<PartenaireTemporaire>();
		
		//Selectionner les emails présents : Distinct
		List<String> listEmails = partenaireTemporaireRepository.getListEmailsDistinct();
		
		//Parcourir les emails Distinct et retirer le dernier By Date de chacun
		for(int i=0; i<listEmails.size(); i++) {
			PartenaireTemporaire partenaireTemporaire = partenaireTemporaireRepository.getLastAttemptedPartenaireTemporaireByDateAndEmail(listEmails.get(i));
			listePartenairesDistincts.add(partenaireTemporaire);
		}
		
		return listePartenairesDistincts;		
	}


	// Ajoute un Partenaire Temporaire à la table temporaire
	@Override
	public PartenaireTemporaire addPartenaireTemporaire(PartenaireTemporaire partenaireTemporaire) {
		
		partenaireTemporaire.setDateAjout(LocalDateTime.now());
		PartenaireTemporaire partenairePending = partenaireTemporaireRepository.save(partenaireTemporaire);
		
		if(partenairePending != null) {
			//Envoi du mail de confirmation au partenaire
			// Envoi du mail avec lien d'activation du compte
			String contenu = 
					"Bonjour, "+ partenaireTemporaire.getIdentite()
					+ "<br><br>"
					+ "Votre demande d'adhésion à notre Plateforme Odix est entrain d'être instruite."
					+ "<br>"
					+ "Vous recevrez un email de confirmation dès l'activation de votre profil."
					+ "<br><br>"
					+ "<a href=\""+ urlPlatformeLoginPage + "\" target=\"_blank\">" + urlPlatformeLoginPage + "</a>"
					+ "<br>"
					+ "Merci pour votre intérêt,"
					+ "<br>"
					+ "A très vite - Odix";
			try {
				mailService.sendSimpleHtmlMessage(partenaireTemporaire.getEmail(), "Odix : demande d'adhésion en cours", contenu);
				
				// Génération d'une Notification Destinée à l'Administrateur
				Utilisateur admin = utilisateurService.getUtilisateurByRole("ROLE_ADMINISTRATEUR");
				List<Utilisateur> listeDestinatairesNotification = new ArrayList<Utilisateur>();
				listeDestinatairesNotification.add(admin);
				
				// Notification générée par le système (ou bien disons par l'Admin) vers lui même (l'Admin)
				notificationService.
				generateSimpleNotification(Consts.objetMsgNotificationDemandeAdhesionPartenaire, 
										   Consts.corpsMsgNotificationDemandeAdhesionPartenaire, 
										   listeDestinatairesNotification, 
										   admin,
										   null,
										   partenairePending,
										   null);
				
				// Ajouter le partenaire Pending à la liste des Partenaires Inactifs
				// Puis quand on l'active à partir de "Gestion Partenaires" il recevra l'email de notification une seule fois
				// S'il existe dans la table temporaire puis il sera effacé de là bas.
				
				return partenairePending;
			} catch (MessagingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}


	
	// Activer un Partenaire Temporaire : le supprimer de la table Temporaire et l'ajouter à la table Partenaire
	@Override
	public Boolean activatePartenaireTemporaire(String email, Long idEntreprise) {
		// Cherche un Partenaire Temporaire par Email et retourne le tout nouveau dans le cas de plusieurs tentatives via le même email
		PartenaireTemporaire lastAttemptedPartenaire = partenaireTemporaireRepository.getLastAttemptedPartenaireTemporaireByDateAndEmail(email);
		
		// Créer et Sauvegarder un nouveau ojbet Partenaire
		Partenaire partenaire = new Partenaire();
		partenaire.setIdentite(lastAttemptedPartenaire.getIdentite());
		partenaire.setUsername(lastAttemptedPartenaire.getUsername());
		partenaire.setPassword(lastAttemptedPartenaire.getPassword());
		partenaire.setEmail(lastAttemptedPartenaire.getEmail());
		partenaire.setTelephone(lastAttemptedPartenaire.getTelephone());
		partenaire.setPosteOccupe(lastAttemptedPartenaire.getPosteOccupe());
		partenaire.setDescriptionDetaillee(lastAttemptedPartenaire.getDescriptionDetaillee());
		
		//Entreprise est recherchée via le paramètre entré
		if(idEntreprise!= null && idEntreprise!= 0) {
			Entreprise entreprise = entrepriseService.getEntreprise(idEntreprise);
			partenaire.setEntreprise(entreprise);
		}else {
			Entreprise entreprise = new Entreprise();
			partenaire.setEntreprise(entreprise);
		}
		
		partenaireService.addPartenaire(partenaire);
		
		
		// Supprimer tous les Partenaires Temporaires ayant l'adresse email venant d'être activée
		partenaireTemporaireRepository.deleteAllPartenairesTemporairesByEmailAdresse(email);
		
		
		// Envoi du mail de confirmation d'activation avec lien de connection (pas besoin de s'authentifier de nouveau)
		String contenu = 
				"Bonjour, "+ lastAttemptedPartenaire.getIdentite()
				+ "<br><br>"
				+ "Votre compte vient d'être activé avec succès. Vos paramètres d'accès sont les suivants : "
				+ "<br><br>"
				+ "-Nom d'utilisateur : <b>" + lastAttemptedPartenaire.getUsername() + "</b>"
				+ "<br>"
				+ "-Mot de passe : <b>" + lastAttemptedPartenaire.getPassword() + "</b>"
				+ "<br><br>"
				+ "Pour accéder à la Plateforme, veuillez suivre ce lien : "
				+ "<a href=\""+ urlPlatformeLoginPage + "\" target=\"_blank\">" + urlPlatformeLoginPage + "</a>"
				+ "<br><br>"
				+ "Cordialement - Odix";
		try {
			mailService.sendSimpleHtmlMessage(email, "Odix : votre compte a été activé", contenu);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;

	}

	
}
