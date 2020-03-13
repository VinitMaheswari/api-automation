package com.assignment.utils;

import org.apache.log4j.Logger;

import com.assigment.utility.FileUtility;

/**
 * Collection of all class instances. Abstract and Encapsulate the elements of
 * the respective pages. When the implementer design a test cases, don't expose
 * the objects instead offer a method with wrapped actions.
 */

public class ClassFactory {

	/**
	 * Instance of the HTTPClient class using singleton class
	 * 
	 * @return instance of HTTPClient Class
	 */

	private static Logger log = Logger.getLogger(Logger.class.getName());
	private static FileUtility instanceFileUtility;
	private static TestUtility instanceTestUtility;
	private static CompareResponse instanceCompareResponse;

	private ClassFactory() {

	}

	public static FileUtility getFileUtilityInstance() {
		try {
			if (instanceFileUtility == null) {
				instanceFileUtility = new FileUtility();
			}
		} catch (Exception e) {
			log.error("Error in get File utility instance" + e.getMessage());
			throw new RuntimeException("Exception occured in creating singleton instance");
		}
		return instanceFileUtility;
	}

	public static TestUtility getTestUtilityInstance() {
		try {
			if (instanceTestUtility == null) {
				instanceTestUtility = new TestUtility();
			}
		} catch (Exception e) {
			log.error("Error in get Test utility instance" + e.getMessage());
			throw new RuntimeException("Exception occured in creating singleton instance");
		}
		return instanceTestUtility;
	}

	public static CompareResponse getCompareResponseInstance() {
		try {
			if (instanceCompareResponse == null) {
				instanceCompareResponse = new CompareResponse();
			}
		} catch (Exception e) {
			log.error("Error in get Compare Response instance" + e.getMessage());
			throw new RuntimeException("Exception occured in creating singleton instance");
		}
		return instanceCompareResponse;
	}
}