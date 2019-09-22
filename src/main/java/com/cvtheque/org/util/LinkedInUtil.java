package com.cvtheque.org.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

@Service
public class LinkedInUtil {
	
	@Autowired
	private Environment env;
	
	private LinkedInConnectionFactory lfactory;
	
	String id;
	String firstName;
	String lastName;
	String emailAddress;
	String profilePicture;
	
	public JSONObject connectWithLinkedIn() {
		
        lfactory = new LinkedInConnectionFactory(env.getProperty("linkedin.consumerKey"), env.getProperty("linkedin.consumerSecret"));
		OAuth2Operations operations = lfactory.getOAuthOperations();
		OAuth2Parameters params = new OAuth2Parameters();

	    params.setRedirectUri(env.getProperty("linkedin.redirectUri"));
		params.setScope(env.getProperty("linkedin.scope"));
		params.setState(env.getProperty("linkedin.state"));

		String url = operations.buildAuthenticateUrl(params);
		
		JSONObject urlJson = new JSONObject();
		urlJson.put("0", url);
		return urlJson;
	}

	public JSONObject redirectLinkedIn(String code, String state) throws Exception {
		
        lfactory = new LinkedInConnectionFactory(env.getProperty("linkedin.consumerKey"), env.getProperty("linkedin.consumerSecret"));
		OAuth2Operations operations = lfactory.getOAuthOperations();
		AccessGrant accessToken = operations.exchangeForAccess(code, env.getProperty("linkedin.redirectUri"), null);
		
		//System.out.println("AccessToken is : " + accessToken.getAccessToken());
		
		//### r_liteprofile
		JSONObject jsonObject = (JSONObject)callToLinkedIn(env.getProperty("linkedin.urlLiteProfile"), accessToken); 
		id = (String)jsonObject.get("id");
		firstName = (String)jsonObject.get("localizedFirstName");
		lastName = (String)jsonObject.get("localizedLastName");
		
		//### r_emailaddress
		jsonObject = (JSONObject)callToLinkedIn(env.getProperty("linkedin.urlEmailaddress"), accessToken); 
		
		for (Object object : (JSONArray)jsonObject.get("elements")) {
			JSONObject objectCasted = (JSONObject) object;
			JSONObject email = (JSONObject) objectCasted.get("handle~");
			emailAddress = (String)email.get("emailAddress");
		}
		
		//### ProfilePicture
		jsonObject = (JSONObject)callToLinkedIn(env.getProperty("linkedin.urlProfilePicture"), accessToken); 
		
		JSONObject mapProfilePicture = (JSONObject)jsonObject.get("profilePicture");
		JSONObject displayImage = (JSONObject) mapProfilePicture.get("displayImage~");

		for (Object object : (JSONArray)displayImage.get("elements")) {
			JSONObject objectCasted = (JSONObject) object;
			JSONArray identifiers = (JSONArray) objectCasted.get("identifiers");
			JSONObject identifiers0 = (JSONObject) identifiers.get(0);
			profilePicture = (String)identifiers0.get("identifier");
		}
		
		//Remlissage du JSONObject Final
		JSONObject jsonProfile = new JSONObject();
		jsonProfile.put("id", id);
		jsonProfile.put("firstName", firstName);
		jsonProfile.put("lastName", lastName);
		jsonProfile.put("emailAddress", emailAddress);
		jsonProfile.put("profilePicture", profilePicture);
		
		return jsonProfile;
	}
	
	public JSONObject callToLinkedIn(String url, AccessGrant accessToken) throws Exception {
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("GET");
		con.setRequestProperty("Host", "api.linkedin.com");
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Authorization", "Bearer "+ accessToken.getAccessToken());
		
		BufferedReader bR = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String line = "";
		StringBuilder responseStrBuilder = new StringBuilder();
		
		while((line =  bR.readLine()) != null){

		    responseStrBuilder.append(line);
		}
		con.getInputStream().close();
		
		@SuppressWarnings("deprecation")
		Object json = new JSONParser().parse(responseStrBuilder.toString()); 
		
		//System.out.println("Response is : " + responseStrBuilder.toString());
		
		return (JSONObject) json;
	}
}

