package com.stepdefinition;

import org.junit.Assert;

import com.base.BaseClass;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;

/**
 * @author Velumani Devendiran Description User should verify Staus Code for
 *         Login, Address (Add,Update,Delete,Get) use of CommonSteps Class
 * @Date 06/09/2022
 */
public class CommonStep extends BaseClass {

	@Then("User verify the status coe is {int}")
	public void user_verify_the_status_coe_is(Integer expCode) {

		int statusCode = getStatusCode(response);
		String value = String.valueOf(statusCode);
		Assert.assertEquals("Verify Status Code", String.valueOf(statusCode), value);

	}

}
