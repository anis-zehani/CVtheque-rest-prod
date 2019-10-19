package com.cvtheque.org.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cvtheque.org.model.PartenaireTemporaire;
import com.cvtheque.org.repository.PartenaireTemporaireRepository;
import com.cvtheque.org.util.Consts;
import com.cvtheque.org.util.JavaMailSenderService;

@Service
public class PartenaireTemporaireServiceImpl implements PartenaireTemporaireService{
	
	private static final String urlPlatformeLoginPage = Consts.urlPlatformeLoginPage;
	
	@Autowired
	PartenaireTemporaireRepository partenaireTemporaireRepository;
	
	@Autowired
	JavaMailSenderService mailService;
	
	

	@Override
	public List<PartenaireTemporaire> getAllPartenairesTemporaires() {
		// A Refaire : il faut retourner la dernière tentative pour chaque email 
		List<PartenaireTemporaire> partenairesTemporaires = partenaireTemporaireRepository.findAll();
		
		return partenairesTemporaires;		
	}


	@Override
	public PartenaireTemporaire addPartenaireTemporaire(PartenaireTemporaire partenaireTemporaire) {
		
		partenaireTemporaire.setDateAjout(LocalDateTime.now());
		PartenaireTemporaire partenairePending = partenaireTemporaireRepository.save(partenaireTemporaire);
		
		if(partenairePending != null) {
			//Envoi du mail de confirmation au partenaire
			// Envoi du mail avec lien d'activation du compte
			String contenu = 
					"Bonjour,"
					+ "<br><br>"
					+ "Votre demande d'adhésion à notre Plateforme Odix est entrain d'être instruite par le service commercial."
					+ "<br>"
					+ "Vous recevrez un email de confirmation dès activation de votre profil."
					+ "<br><br>"
					+ "<a href=\""+ urlPlatformeLoginPage + "\" target=\"_blank\">" + urlPlatformeLoginPage + "</a>"
					+ "<br><br>"
					+ "A très vite - Odix";
			try {
				mailService.sendSimpleHtmlMessage(partenaireTemporaire.getEmail(), "Odix : demande d'adhésion en cours", contenu);
				
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

	
}
