package com.cvtheque.org.service;

import java.util.List;

import com.cvtheque.org.model.Notification;
import com.cvtheque.org.model.Utilisateur;

public interface NotificationService {
	
	// Lister les Notifications Actives = True et par ID consommateur de Notif
	public List<Notification> getAllNotifications(Long idDestinataire, String etatNotification); 
	
	// Desactiver une Notification
	public Boolean deactivateNotification(Long idNotification);
	
	// Générer une Notification Simple
	public void generateSimpleNotification(String objetNotification, 
									 	   String corpsNotification, 
									       List<Utilisateur> listeDestinatairesNotification, 
									       Utilisateur generateurNotification);

}
