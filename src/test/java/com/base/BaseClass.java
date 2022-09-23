package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.http.auth.AUTH;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * 
 * @author Velumani Devendiran
 * 
 * @Description Used to Maintain all Reusable Methods
 *
 * @date 06/09/2022
 *
 */
public class BaseClass {

	RequestSpecification reqSpec;
	public static Response response;

	/**
	 * @Description Used to maintain Image Load on config.properties file with help
	 *              of reuable method
	 * @param key
	 * @return value
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String getPropertyFileValue(String key) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(System.getProperty("user.dir") + "\\config\\config.properties"));
		String value = (String) properties.get(key);
		return value;
	}

	/**
	 * @Description Used to maintain Form data key and File
	 * @param key
	 * @param file
	 */
	public void formData(String key, File file) {

		reqSpec = reqSpec.multiPart(key, file);
	}

	/**
	 * @Description Used to Add headers in RestAssurd
	 * @param headers
	 */
	public void addHeaders(Headers headers) {
		reqSpec = RestAssured.given().headers(headers);

	}

	/**
	 * @Description Used to Add header in RestAssurd
	 * @param Key
	 * @param Value
	 */
	public void addHeader(String Key, String Value) {

		reqSpec = RestAssured.given().header(Key, Value);
	}

	/**
	 * @Description Used to Pass QueryPharm
	 * @param Key
	 * @param Value
	 */
	public void addQueryPharm(String Key, String Value) {
		reqSpec.queryParam(Key, Value);
	}

	/**
	 * @Description Used to Pass PharameterPharm
	 * @param Key
	 * @param Value
	 */
	public void addPharParm(String Key, String Value) {
		reqSpec.pathParam(Key, Value);
	}

	/**
	 * @Description Used to Add body Values
	 * @param body
	 */
	public void addBody(String body) {
		reqSpec = reqSpec.body(body);
	}

	/**
	 * @Description Used to Add body object
	 * @param body
	 */
	public void addBodyObject(Object body) {
		reqSpec = reqSpec.body(body);
	}

	/**
	 * @Description Used to pass Username and Password with help of
	 *              Config.properties file
	 * @param userName
	 * @param Password
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void basicAuth(String userName, String Password) throws FileNotFoundException, IOException {

		// reqSpec=reqSpec.auth().preemptive().basic(userName, Password);
		reqSpec = reqSpec.auth().preemptive().basic(getPropertyFileValue(userName), getPropertyFileValue(Password));
	}

	/**
	 * @Description Used to pass endpoints
	 * @param Type
	 * @param endpoint
	 * @return
	 */
	public Response requestMethodType(String Type, String endpoint) {

		switch (Type) {
		case "GET":
			response = reqSpec.log().all().get(endpoint);
			break;
		case "POST":
			response = reqSpec.log().all().post(endpoint);
			break;
		case "PUT":
			response = reqSpec.log().all().put(endpoint);
			break;
		case "DELETE":
			response = reqSpec.log().all().delete(endpoint);
			break;

		default:
			break;
		}
		return response;
	}

	/**
	 * @Description Used to get Response code
	 * @param response
	 * @return
	 */
	public int getStatusCode(Response response) {
		int statusCode = response.getStatusCode();
		return statusCode;
	}

	/**
	 * @Description Used to get Response body asString format
	 * @param response
	 * @return asString
	 */
	public String getResponseBodyAsString(Response response) {
		String asString = response.asString();
		return asString;
	}

	/**
	 * @Description Used to get Response body asPreetySting format
	 * @param response
	 * @return
	 */
	public String getResponseBodyAsPreetyString(Response response) {
		String asPrettyString = response.asPrettyString();
		return asPrettyString;

	}

}
