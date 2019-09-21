package com.cvtheque.org.util;

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
import org.springframework.stereotype.Service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

@Service
public class LinkedInUtil {
	
	@Autowired
	private Environment env;
	
	private LinkedInConnectionFactory lfactory;
	
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
		AccessGrant accessToken = operations.exchangeForAccess(code, "http://localhost:4200/redirectLinkedIn", null);
		
		System.out.println("AccessToken is : " + accessToken.getAccessToken());
		
		//URL pour le r_liteprofile
		String urlLiteProfile = "https://api.linkedin.com/v2/me?projection";
		
		JSONObject jsonRLiteprofile = new JSONObject();
		
		JSONObject jo = (JSONObject)callToLinkedIn(urlLiteProfile, accessToken); 
		
		@SuppressWarnings("rawtypes")
		Map profilePicture = (Map)jo.get("profilePicture");
		
		jsonRLiteprofile.put("id", (String)jo.get("id"));
		jsonRLiteprofile.put("firstName", (String)jo.get("localizedFirstName"));
		jsonRLiteprofile.put("lastName", (String)jo.get("localizedLastName"));
		jsonRLiteprofile.put("profilePicture", (String)profilePicture.get("displayImage"));
		
		//URL pour le r_emailaddress
		String urlEmail ="https://api.linkedin.com/v2/clientAwareMemberHandles?q=members&projection=(elements*(handle~))";
		
		jo = (JSONObject)callToLinkedIn(urlEmail, accessToken); 
		
		for (Object o : (JSONArray)jo.get("elements")) {
			JSONObject ca = (JSONObject) o;
			JSONObject email = (JSONObject) ca.get("handle~");
			
			jsonRLiteprofile.put("emailAddress", email.get("emailAddress"));
		}

		return jsonRLiteprofile;
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
		
		System.out.println("Response is : " + responseStrBuilder.toString());
		
		return (JSONObject) json;
	}
}

