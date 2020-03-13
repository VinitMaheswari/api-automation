package com.assignment.httpClients;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import org.apache.http.HttpEntity;
import org.apache.log4j.Logger;

public class HTTPClient extends Thread {

	private static Logger log = Logger.getLogger(Logger.class.getName());

	static PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
	CloseableHttpResponse response;
	CloseableHttpClient httpClient;
	int id;
	String url;
	String httpMethod;
	int httpCode;
	String responseBody;

	public HTTPClient(int id, String url, String httpMethod) {
		this.url = url;
		this.id = id;
		this.httpMethod = httpMethod;
	}

	@Override
	public void run() {
		try {
			// Creating the connection
			response = createConnection();
			// Displaying the status of the request.
			log.info("status of thread " + id + " : " + getHttpCode());
			responseBody = getResponse();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	//Set maximum thread connection
	public static void setMaximumThreadConnection(int threadCount) {
		connectionManager.setMaxTotal(threadCount);
	}
	
	//Set client builder object
	public HttpClientBuilder setClientBuilderObject() {
		HttpClientBuilder clientbuilder = null;
		try {
			clientbuilder = HttpClients.custom().setConnectionManager(connectionManager);
		} catch (Exception e) {
			log.error("Error in set client builder object method" + e.getMessage());
		}
		return clientbuilder;
	}
	
	//Creating the connection with the http method type. As of now only supported GET method
	public CloseableHttpResponse createConnection() {
		try {
			switch (httpMethod.toUpperCase()) {
			case "GET":
				HttpGet get = new HttpGet(url);
				httpClient = setClientBuilderObject().build();
				response = httpClient.execute(get);
				log.info("Established connection with the GET method");
				break;
			default:
				log.warn("Please provide a proper http method (Only GET Supported)");
			}
		} catch (Exception e) {
			log.error("Error in create Connection method " + e.getMessage());
		}
		return response;
	}
	
	//Get the http status code
	public int getHttpCode() {
		try {
			log.debug("Getting the response");
			httpCode = response.getStatusLine().getStatusCode();
		} catch (Exception e) {
			log.error("Error in get http status code" + e.getMessage());
		}
		return httpCode;
	}
	
	//Get the string response of an API
	public String getResponse() {
		log.debug("Enter get response method");
		try {
			if (httpCode == 200) {
				log.info("Response status is 200");
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					responseBody = EntityUtils.toString(entity);
					log.info("Response Body is not null for status code 200 " + responseBody);
				} else {
					log.error("Response Body is null for status code 200");
				}
			} else {
				log.info("Response status is not 200");
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					responseBody = EntityUtils.toString(entity);
					log.info("Response Body is not null for status code not 200 " + responseBody);
				} else {
					log.error("Response Body is null for status code not 200");
				}
			}
		} catch (Exception e) {
			log.error("Error in get response method" + e.getMessage());
		}
		return responseBody;
	}
	
	//closing the http connection
	public void closeConnection() {
		try {
			if (httpClient != null && response != null) {
				httpClient.close();
				response.close();
			}
		} catch (Exception e) {
			log.error("Error in close Connection method" + e.getMessage());
		}
	}
}
