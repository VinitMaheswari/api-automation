package com.assignment.utils;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.assignment.httpClients.HTTPClient;

public class TestUtility {

	private static Logger log = Logger.getLogger(Logger.class.getName());
	
	//Get the API Response and Id in a hashmap
	public HashMap<Integer, String> getAPIIdResponse(String filePath, String httpMethod) {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		try {
			HTTPClient.setMaximumThreadConnection(DataProvider.SET_MAXIMUM_THREAD_CONNECTION);
			List<String> file1URIs = ClassFactory.getFileUtilityInstance().getURIsFromFile(filePath);
			// create a thread for each URI
			for (int i = 0; i < file1URIs.size(); i++) {
				int id = i + 1;
				HTTPClient client = new HTTPClient(id, file1URIs.get(i), httpMethod);
				client.start();
				client.join();
				map.put(id, client.getResponse());
			}
		} catch (Exception e) {
			log.error("Error in getting API ID and Response Value" + e.getMessage());
		}
		return map;
	}
	
	//Get the Url and Id from the file
	public String getIdURLFromFile(String filePath, int key) {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		try {
			List<String> file1URIs = ClassFactory.getFileUtilityInstance().getURIsFromFile(filePath);
			// create a thread for each URI
			for (int i = 0; i < file1URIs.size(); i++) {
				int id = i + 1;
				map.put(id, file1URIs.get(i));
			}
		} catch (Exception e) {
			log.error("Error in getting File ID and URL Value" + e.getMessage());
		}
		return map.get(key);
	}
}
