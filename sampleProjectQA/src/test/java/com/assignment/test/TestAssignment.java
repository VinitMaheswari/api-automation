package com.assignment.test;

import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.assignment.utils.ClassFactory;
import com.assignment.utils.DataProvider;

public class TestAssignment {

	private static Logger log = Logger.getLogger(Logger.class.getName());

	@Test
	public void test() {
		log.debug("Inside test method class");
		try {
			HashMap<Integer, String> apiResponse1 = new HashMap<Integer, String>();
			HashMap<Integer, String> apiResponse2 = new HashMap<Integer, String>();
			//Store the API response from file1
			apiResponse1 = ClassFactory.getTestUtilityInstance().getAPIIdResponse(DataProvider.FILE_PATH_OF_FILE1,
					DataProvider.HTTP_METHOD_GET);
			//Store the API response from file2
			apiResponse2 = ClassFactory.getTestUtilityInstance().getAPIIdResponse(DataProvider.FILE_PATH_OF_FILE2,
					DataProvider.HTTP_METHOD_GET);
			//Get the file 1 urls and store in a list
			List<String> file1URIs = ClassFactory.getFileUtilityInstance()
					.getURIsFromFile(DataProvider.FILE_PATH_OF_FILE1);
			//Get the file 2 urls and store in a list
			List<String> file2URIs = ClassFactory.getFileUtilityInstance()
					.getURIsFromFile(DataProvider.FILE_PATH_OF_FILE2);
			SoftAssert sa = new SoftAssert();
			String message;
			Boolean result;
			if (file1URIs.size() == file2URIs.size()) {
				log.info("Both file contains the same number of URLS");
				for (int i = 0; i < file1URIs.size(); i++) {
					int id = i + 1;
					//Get the file urls for the corresponding id to show as a message 
					String fileDetails1 = ClassFactory.getTestUtilityInstance()
							.getIdURLFromFile(DataProvider.FILE_PATH_OF_FILE1, id);
					String fileDetails2 = ClassFactory.getTestUtilityInstance()
							.getIdURLFromFile(DataProvider.FILE_PATH_OF_FILE2, id);
					result = ClassFactory.getCompareResponseInstance().verifyResponse(apiResponse1.get(id),
							apiResponse2.get(id));
					//Compare the result and show the details in testng Reporter log segment
					message = ClassFactory.getCompareResponseInstance().getMessage(fileDetails1, fileDetails2, result);
					//do a soft assert, As not to stop the test for incorrect data
					sa.assertTrue(result, message);
				}
			} else {
				log.info(
						"Both file doesn't contains the same number of URLS. Please add the same number of URLS to proceed test");
			}
		} catch (Exception e) {
			log.error("Error in test method class");
		}
	}
}