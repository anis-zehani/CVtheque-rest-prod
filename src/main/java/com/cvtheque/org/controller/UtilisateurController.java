package com.cvtheque.org.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

@CrossOrigin
@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {
	
	@Autowired
	private Environment env;
	
	private LinkedInConnectionFactory lfactory;
	
	@GetMapping(value = "/linkedIn")
	public JSONObject producer() {
		
        lfactory = new LinkedInConnectionFactory(
        env.getProperty("linkedin.consumerKey"), 
        env.getProperty("linkedin.consumerSecret"));
        //lfactory.setScope(env.getProperty("linkedin.scope"));

		OAuth2Operations operations = lfactory.getOAuthOperations();
		OAuth2Parameters params = new OAuth2Parameters();

	    params.setRedirectUri("http://localhost:4200/redirectLinkedIn");
		params.setScope("r_liteprofile,r_emailaddress");
		params.setState("fsdf-34jkk558-gjl57o");

		String url = operations.buildAuthenticateUrl(params);
		//System.out.println("The URL is : " + url);
		
		JSONObject urlJson = new JSONObject();
		urlJson.put("0", url);
		return urlJson;
	}
	
	@PostMapping("/redirectLinkedIn/{code}/{state}")
	public String producer(@PathVariable String code, @PathVariable String state) throws Exception {
		
        lfactory = new LinkedInConnectionFactory(
        env.getProperty("linkedin.consumerKey"), 
        env.getProperty("linkedin.consumerSecret"));
        //lfactory.setScope(env.getProperty("linkedin.scope"));
        		
		OAuth2Operations operations = lfactory.getOAuthOperations();
		AccessGrant accessToken = operations.exchangeForAccess(code, "http://localhost:4200/redirectLinkedIn", null);
		
		//System.out.println("AccessToken is : " + accessToken.getAccessToken());
		
		String url = "https://api.linkedin.com/v2/me?projection";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("GET");
		con.setRequestProperty("Host", "api.linkedin.com");
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Authorization", "Bearer "+ accessToken.getAccessToken());
		
		//int responseCode = con.getResponseCode();
		//System.out.println("ResponseCode is : " + responseCode);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		while((inputLine = in.readLine())!= null) {
			response.append(inputLine);
		}
		in.close();

		System.out.println("Response is : " + response.toString());
		
		Object json = new JSONParser().parse(response.toString()); 
		JSONObject jo = (JSONObject) json; 
		String localizedFirstName = (String) jo.get("localizedFirstName");
		String localizedLastName = (String) jo.get("localizedLastName");
		String id = (String) jo.get("id");
		Map profilePicture = (Map) jo.get("profilePicture");

		return response.toString();
		
	}

}
