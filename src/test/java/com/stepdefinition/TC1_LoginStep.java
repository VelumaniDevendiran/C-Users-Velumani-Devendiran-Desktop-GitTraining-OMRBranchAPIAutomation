package com.stepdefinition;

import java.io.FileNotFoundException;
import java.io.IOException;

/* 
 * 
 * 
 * 
 */

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojoclass.Login_Output_Pojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

/**
 * 
 * 
 * @author Velumani Devendiran
 *
 * @Description Used to Maintain Login Step methods
 *
 * @Date 06/09/2022
 */
public class TC1_LoginStep extends BaseClass {

	Response response;
	static String logtoken;

	@Given("User add header")
	public void user_add_header() {
		addHeader("accept", "application/json");
	}

	@Given("User add basic authentication for login")
	public void user_add_basic_authentication_for_login() throws FileNotFoundException, IOException {
		basicAuth("username", "password");
	}

	@When("User send {string} request for login endpoint")
	public void user_send_request_for_login_endpoint(String POST) {
		response = requestMethodType(POST, Endpoints.POSTMANBASICAUTHLOGIN);

	}

	@Then("User verify the login response body firstName present as {string} and get the logtoken saved")
	public void user_verify_the_login_response_body_first_name_present_as_and_get_the_logtoken_saved(
			String expfirst_name) {

		Login_Output_Pojo login_Output_Pojo = response.as(Login_Output_Pojo.class);
		expfirst_name = login_Output_Pojo.getData().getFirst_name();

		Assert.assertEquals("Verify the firstName", "Velumani", expfirst_name);

		logtoken = login_Output_Pojo.getData().getLogtoken();
		System.out.println(logtoken);
	}

}
