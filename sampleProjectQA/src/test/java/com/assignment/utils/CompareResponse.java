package com.assignment.utils;

import org.apache.log4j.Logger;
import org.testng.Reporter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CompareResponse {

	private static Logger log = Logger.getLogger(Logger.class.getName());
	
	//Convert a string to JSON object
	public JsonObject convertStringToJson(String response) {
		JsonObject jsonObject = null;
		try {
			jsonObject = new JsonParser().parse(response).getAsJsonObject();
		} catch (Exception e) {
			log.error("Error in converting string to json object" + e.getMessage());
		}
		return jsonObject;
	}
	
	//Verify the 2 json response from file1 and file2
	public boolean verifyResponse(String response1, String response2) {
		boolean result = false;
		try {
			JsonObject object1 = convertStringToJson(response1);
			JsonObject object2 = convertStringToJson(response2);
			if (object1.equals(object2))
				return result = true;
		} catch (Exception e) {
			log.error("Error in comparing response" + e.getMessage());
		}
		return result;
	}
	
	//Get the message/URL for each result after verifying the 2 json response
	public String getMessage(String fileUrl1, String fileUrl2, Boolean result) {
		String message = null;
		try {
			if (result) {
				log.info("Both response of Line1 in file1 and file2 is equal");
				message = fileUrl1 + " equals " + fileUrl2;
				Reporter.log(message);
			} else {
				log.info("Both response of Line1 in file1 and file2 is not equal");
				message = fileUrl1 + " not equals " + fileUrl2;
				Reporter.log(message);
			}

		} catch (Exception e) {
			log.error("Error in get message" + e.getMessage());
		}
		return message;
	}
}
