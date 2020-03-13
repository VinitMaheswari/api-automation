package com.assigment.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class FileUtility {

	private static Logger log = Logger.getLogger(Logger.class.getName());
	
	//Get the URI/line from file
	public List<String> getURIsFromFile(String filePath) {
		List<String> URIs = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String line;
			while ((line = reader.readLine()) != null) {
				URIs.add(line);
			}
			reader.close();
		} catch (Exception e) {
			log.error("Error in get URI from file method " + e.getMessage());
		}
		return URIs;
	}
}