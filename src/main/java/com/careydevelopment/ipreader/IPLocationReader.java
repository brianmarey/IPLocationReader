package com.careydevelopment.ipreader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.careydevelopment.jsonparser.JSONException;
import com.careydevelopment.jsonparser.JSONObject;
import com.careydevelopment.jsonparser.JSONParser;

public class IPLocationReader {
	private static final Logger LOGGER = LoggerFactory.getLogger(IPLocationReader.class);
	
	private static final String JSON_URL = "http://ipinfo.io/json";
	
	public static IPLocation getIPLocation() throws IPLocationException {
		IPLocation ipLocation = null;
		
		try {
			JSONObject json = JSONParser.readJsonFromUrl(JSON_URL);
			ipLocation = getIPLocationFromJson(json);
		} catch (JSONException je) {
			LOGGER.error("Problem parsing JSON!",je);
			throw new IPLocationException(je);
		} 		
		
		return ipLocation;
	}

	
	private static IPLocation getIPLocationFromJson(JSONObject json) throws JSONException{
		IPLocation ipLocation = new IPLocation();
		
		ipLocation.setCity(json.getString("city"));
		ipLocation.setCountry(json.getString("country"));
		ipLocation.setHostname(json.getString("hostname"));
		ipLocation.setIp(json.getString("ip"));
		ipLocation.setLoc(json.getString("loc"));
		ipLocation.setOrg(json.getString("org"));
		ipLocation.setPostal(json.getString("postal"));
		ipLocation.setRegion(json.getString("region"));
		
		return ipLocation;
	}
	
	
	public static void main(String[] args) {
		try {
			IPLocation ipl = getIPLocation();
			LOGGER.info("IP is " + ipl.getIp());
		} catch (IPLocationException ip) {
			ip.printStackTrace();
		}
	}

}
