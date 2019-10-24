package com.cvtheque.org.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvtheque.org.model.Notification;
import com.cvtheque.org.service.NotificationService;

@CrossOrigin
@RestController
@RequestMapping("/api/notification")
public class NotificationController {
	
	@Autowired
	NotificationService notificationService;
	
	@GetMapping("/all/{idDestinataire}/{etatNotification}")
	public List<Notification> getAllNotifications(@PathVariable Long idDestinataire, @PathVariable String etatNotification) {
	    return notificationService.getAllNotificationsByUtilisateur(idDestinataire, etatNotification);
	}
	
	@DeleteMapping("/{idNotification}")
	public Boolean deactivateNotification(@PathVariable Long idNotification) {
	    return notificationService.deactivateNotification(idNotification);
	}

}
