package com.cvtheque.org.util;

import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
 
@Service
public class LocalStorageService {
	
	  //Emplacament photo sur le serveur
	  private static final Path rootLocationPhoto = Consts.rootLocationPhoto;
	  
	  //Emplacament cvodix sur le serveur
	  private static final Path rootLocationCvOdix = Consts.rootLocationCvOdix;
	  
	  //Emplacament cvoriginal sur le serveur
	  private static final Path rootLocationCvOriginal = Consts.rootLocationCvOriginal;
	  
	  //Emplacament fichier sur le serveur
	  private static final Path rootLocationFichierRappel = Consts.rootLocationFichierRappel;
	  
	  
	  //Fonction qui crypte le nom de la photo et l'insére sur le disque
	  public String addPhoto(MultipartFile file) {
		  
			String message = "";
			String filename =UUID.randomUUID().toString()+getExtensionByStringHandling(file.getOriginalFilename());
			
		    try 
		    {
		      this.savePhoto(file, filename);
		      message = LocalStorageService.rootLocationPhoto.toString().replace("\"", "")+"/"+filename;
		      return message;
		      
		    } 
		    catch (Exception e) 
		    {
		      return null;
		    }
		}
	  
	  //Fonction qui crypte le nom du CvOdix et l'insére sur le disque
	  public String addCvOdix(MultipartFile file) {
		  
			String message = "";
			String filename =UUID.randomUUID().toString()+getExtensionByStringHandling(file.getOriginalFilename());
			
		    try 
		    {
		      this.saveCvOdix(file, filename);
		      message = LocalStorageService.rootLocationCvOdix.toString().replace("\"", "")+"/"+filename;
		      return message;
		      
		    } 
		    catch (Exception e) 
		    {
		      return null;
		    }
		}
	  
	  //Fonction qui crypte le nom du CvOriginal et l'insére sur le disque
	  public String addCvOriginal(MultipartFile file) {
		  
			String message = "";
			String filename =UUID.randomUUID().toString()+getExtensionByStringHandling(file.getOriginalFilename());
			
		    try 
		    {
		      this.saveCvOriginal(file, filename);
		      message = LocalStorageService.rootLocationCvOriginal.toString().replace("\"", "")+"/"+filename;
		      return message;
		      
		    } 
		    catch (Exception e) 
		    {
		      return null;
		    }
		}
	 
	  //Fonction qui crypte le nom du fichier et l'insére sur le disque
	  public ArrayList<String> addFichierRappel(MultipartFile file) {
		  
			String urlFichier = "";
			String nomFichier = "";
			
			String filenameOriginal =file.getOriginalFilename();
			String filenameModified =UUID.randomUUID().toString()+getExtensionByStringHandling(file.getOriginalFilename());
			
		    try 
		    {
		      this.saveFichierRappel(file, filenameModified);
		      
		      urlFichier = LocalStorageService.rootLocationFichierRappel.toString().replace("\"", "")+"/"+filenameModified;
		      nomFichier = filenameOriginal;
		      
		      ArrayList<String> urls = new ArrayList<String>();
		      
		      urls.add(urlFichier);
		      urls.add(nomFichier);
		      
		      return urls;
		      
		    } 
		    catch (Exception e) 
		    {
		      return new ArrayList<String>();
		    }
		}
	  
	  //save Photo
	  public void savePhoto(MultipartFile file, String filename ) {
	    try 
	    {
	      Files.copy(file.getInputStream(), LocalStorageService.rootLocationPhoto.resolve(filename));
	    } 
	    catch (Exception e) 
	    {
	      throw new RuntimeException("Erreur de sauvegarde de la Photo - saveFile !");
	    }
	  }
	  
	  //save CvOdix
	  public void saveCvOdix(MultipartFile file, String filename ) {
		    try 
		    {
		      Files.copy(file.getInputStream(), LocalStorageService.rootLocationCvOdix.resolve(filename));
		    } 
		    catch (Exception e) 
		    {
		      throw new RuntimeException("Erreur de sauvegarde du CvOdix - saveFile !");
		    }
		  }
	  
	  //save CvOriginal
	  public void saveCvOriginal(MultipartFile file, String filename ) {
		    try 
		    {
		      Files.copy(file.getInputStream(), LocalStorageService.rootLocationCvOriginal.resolve(filename));
		    } 
		    catch (Exception e) 
		    {
		      throw new RuntimeException("Erreur de sauvegarde du CvOriginal - saveFile !");
		    }
		  }
	  
	  //save Fichier
	  public void saveFichierRappel(MultipartFile file, String filename ) {
	    try 
	    {
	      Files.copy(file.getInputStream(), LocalStorageService.rootLocationFichierRappel.resolve(filename));
	    } 
	    catch (Exception e) 
	    {
	      throw new RuntimeException("Erreur de sauvegarde du Fichier Rappel - saveFile !");
	    }
	  }
	  
	  //Supprime une seule photo via son Path
	  public void deletePhoto(String filename) {

		  File rootLocation = new File(filename);
		  try 
		  {
			  rootLocation.delete();
		  } 
		  catch (Exception e) 
		  {
			throw new RuntimeException("Erreur de suppression de l'image !");
		  }
		}
	  
	  //Supprime un CvOdix via son Path
	  public void deleteCvOdix(String filename) {

		  File rootLocation = new File(filename);
		  try 
		  {
			  rootLocation.delete();
		  } 
		  catch (Exception e) 
		  {
			throw new RuntimeException("Erreur de suppression du CvOdix !");
		  }
		}
	 
	  //Supprime un CvOriginal via son Path
	  public void deleteCvOriginal(String filename) {

		  File rootLocation = new File(filename);
		  try 
		  {
			  rootLocation.delete();
		  } 
		  catch (Exception e) 
		  {
			throw new RuntimeException("Erreur de suppression du CvOriginal !");
		  }
		}
	  
	  //Supprime un seule fichier via son Path
	  public void deleteFichier(String filename) {

		  File rootLocation = new File(filename);
		  try 
		  {
			  rootLocation.delete();
		  } 
		  catch (Exception e) 
		  {
			throw new RuntimeException("Erreur de suppression du fichier !");
		  }
		}

	  //Retourne l'extention d'un fichier : image ou autre
	  public String getExtensionByStringHandling(String filename) 
	  {
		    return "."+filename.substring(filename.lastIndexOf('.') + 1);
	  }
	  
	}