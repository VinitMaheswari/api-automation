# Sample QA Project

This is a maven with TestNG framework project to run GET APIs using Apache HTTPClient and compare the 2 APIs response. 

# Install & Run

### IDE

Preferred IDE to use here is **Eclipse**.  
[Download Eclipse from here](https://www.eclipse.org/downloads/)

### Extensions 

Eclipse has rich ecosystem extension, which additions and extends functionality of the IDE. Install below:

1. TestNG
	TestNG is a testing framework inspired from JUnit and NUnit but introducing some new functionalities that make it more powerful and easier to use. Read more about it on - https://testng.org/doc/
	Link to install TesTNG - https://www.guru99.com/install-testng-in-eclipse.html

### Dependency used

Below are the list of maven dependency used -

1. Apache HTTP Client 
   Although the java.net package provides basic functionality for accessing resources via HTTP, it doesn't provide the full flexibility or functionality needed by many applications. HttpClient seeks to fill this void by providing an efficient, up-to-date, and feature-rich package implementing the client side of the most recent HTTP standards and recommendations.

2. Log4j
   log4j is a reliable, fast and flexible logging framework (APIs) written in Java, which is distributed under the Apache Software License.

3. Gson
	Gson is an open-source Java library to serialize and deserialize Java objects to JSON.

### Prerequisite

1. Install Java in you system.
2. Install TestNG in your system to run the project on CI/CD or CMD line.

### How to Run

1. Run using Eclipse -
 	Go to testng.xml and Run As TestNG Suite.

2. If you are running on CI/CD or CMD line, execute below command(s):
	1. Using TestNG 
		java org.testng.TestNG %projectLocation%\testng.xml
		
### Output/Result of the test
1. Once after the test is executed. Go to test-output and find the result.
2. Test Output on Reporter output -
 	https://reqres.in/api/users/1 equals https://reqres.in/api/users/1 
 	https://reqres.in/api/users/2 equals https://reqres.in/api/users/2 
 	https://reqres.in/api/users/3 not equals https://reqres.in/api/users/5 
 	https://reqres.in/api/users/4 equals https://reqres.in/api/users/4 
 	https://reqres.in/api/users/5 not equals https://reqres.in/api/users/6 
