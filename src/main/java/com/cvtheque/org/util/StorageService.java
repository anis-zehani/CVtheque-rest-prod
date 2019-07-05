package com.cvtheque.org.util;

import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
 
@Service
public class StorageService {
	
	  //Emplacament photo sur le serveur
	  private final Path rootLocationPhoto = Consts.rootLocationPhoto;
	  
	  //Emplacament cvodix sur le serveur
	  private final Path rootLocationCvOdix = Consts.rootLocationCvOdix;
	  
	  //Emplacament cvoriginal sur le serveur
	  private final Path rootLocationCvOriginal = Consts.rootLocationCvOriginal;
	  
	  //Emplacament fichier sur le serveur
	  private final Path rootLocationFichierRappel = Consts.rootLocationFichierRappel;
	  
	  List<String> files = new ArrayList<String>();
	  
	  //Fonction qui crypte le nom de la photo et l'insére sur le disque
	  public String addPhoto(MultipartFile file) {
		  
			String message = "";
			String filename =UUID.randomUUID().toString()+getExtensionByStringHandling(file.getOriginalFilename());
			
		    try 
		    {
		      this.savePhoto(file, filename);
		      files.add(file.getOriginalFilename());
		      message = Consts.urlUploadsImg.replace("\"", "")+filename;
		      return message;
		      
		    } 
		    catch (Exception e) 
		    {
		      message = "Erreur de chargement de l'image " + file.getOriginalFilename() + "!";
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
		      files.add(file.getOriginalFilename());
		      message = Consts.urlUploadsCvOdix.replace("\"", "")+filename;
		      return message;
		      
		    } 
		    catch (Exception e) 
		    {
		      message = "Erreur de chargement du CvOdix " + file.getOriginalFilename() + "!";
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
		      files.add(file.getOriginalFilename());
		      message = Consts.urlUploadsCvOriginal.replace("\"", "")+filename;
		      return message;
		      
		    } 
		    catch (Exception e) 
		    {
		      message = "Erreur de chargement du CvOriginal " + file.getOriginalFilename() + "!";
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
		      files.add(file.getOriginalFilename());
		      
		      urlFichier = Consts.urlUploadsFiles.replace("\"", "")+filenameModified;
		      nomFichier = filenameOriginal;
		      
		      ArrayList<String> urls = new ArrayList<String>();
		      
		      urls.add(urlFichier);
		      urls.add(nomFichier);
		      
		      return urls;
		      
		    } 
		    catch (Exception e) 
		    {
		      //message = "Erreur de chargement du fichier " + file.getOriginalFilename() + "!";
		      return null;
		    }
		}
	  
	  //save Photo
	  public void savePhoto(MultipartFile file, String filename ) {
	    try 
	    {
	      Files.copy(file.getInputStream(), this.rootLocationPhoto.resolve(filename));
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
		      Files.copy(file.getInputStream(), this.rootLocationCvOdix.resolve(filename));
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
		      Files.copy(file.getInputStream(), this.rootLocationCvOriginal.resolve(filename));
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
	      Files.copy(file.getInputStream(), this.rootLocationFichierRappel.resolve(filename));
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
		    return "."+filename.substring(filename.lastIndexOf(".") + 1);
	  }
	  
	}