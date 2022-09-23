package com.stepdefinition;

import java.util.ArrayList;

import java.util.List;

import org.bouncycastle.asn1.DLExternal;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojoclass.AddUserAddres_Output_Pojo;
import com.pojoclass.AddUserAddress_Input_Pojo;
import com.pojoclass.DeleteUserAddress_Input_Pojo;
import com.pojoclass.DeleteUserAddress_Output_Pojo;
import com.pojoclass.GetUserAddress_Output_Pojo;
import com.pojoclass.Login_Output_Pojo;
import com.pojoclass.UpdateUserAddress_Input_Pojo;
import com.pojoclass.UpdateUserAddress_Output_Pojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import junit.framework.Assert;

/**
 * 
 * @author Velumani Devendiran
 *
 * @Description User used to AddUser address, UpdateUser adders, DeleteUser
 *              address and Finally get the UserAddress with Verification of
 *              Assertions
 *
 * @Date 06/09/2022
 *
 */

public class TC2_AddressStep extends BaseClass {

	// static String logtoken;

	static String address_id;

	@Given("User add header and bearer authrozation for accessing address end points")
	public void user_add_header_and_bearer_authrozation_for_accessing_address_end_points() {

		List<Header> h = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		h.add(h1);
		h.add(h2);
		h.add(h3);
		Headers headers = new Headers(h);
		addHeaders(headers);

	}

	@When("User add request body for Addnew address {string},{string},{string},{string},{string},{string},{string},{string},{string} and {string}")
	public void user_add_request_body_for_addnew_address(String first_name, String last_name, String mobile,
			String apartment, String state, String city, String country, String zipcode, String address,
			String address_type) {

		AddUserAddress_Input_Pojo addUserAddress_Input_Pojo = new AddUserAddress_Input_Pojo(first_name, last_name,
				mobile, apartment, Integer.parseInt(state), Integer.parseInt(city), Integer.parseInt(country), zipcode,
				address, address_type);

		addBodyObject(addUserAddress_Input_Pojo);

	}

	@When("User send {string} request for addUserAddress endpoint")
	public void user_send_request_for_add_user_address_endpoint(String POST) {
		response = requestMethodType(POST, Endpoints.POSTMANADDUSERADDRESS);

	}

	@Then("User verify the addUserAddress response message mathches {string}")
	public void user_verify_the_add_user_address_response_message_mathches(String expMessage) {

		AddUserAddres_Output_Pojo addUserAddres_Output_Pojo = response.as(AddUserAddres_Output_Pojo.class);

		int id = addUserAddres_Output_Pojo.getAddress_id();
		address_id = String.valueOf(id);
		System.out.println(address_id);

		String message = addUserAddres_Output_Pojo.getMessage();
		Assert.assertEquals("Verify Address added successfully", expMessage, message);

	}

	@When("User add reuqest body for Update existing address {string},{string},{string},{string},{string},{string},{string},{string},{string} and {string}")
	public void user_add_reuqest_body_for_update_existing_address(String first_name, String last_name, String mobile,
			String apartment, String state, String city, String country, String zipcode, String address,
			String address_type) {
		UpdateUserAddress_Input_Pojo updateUserAddress_Input_Pojo = new UpdateUserAddress_Input_Pojo(
				Integer.parseInt(address_id), first_name, last_name, mobile, apartment, Integer.parseInt(state),
				Integer.parseInt(city), Integer.parseInt(country), zipcode, address, address_type);
		addBodyObject(updateUserAddress_Input_Pojo);
	}

	@When("User send {string} request for updateUserAddress end point")
	public void user_send_request_for_update_user_address_end_point(String PUT) {
		response = requestMethodType(PUT, Endpoints.POSTMANUPDTAEUSERADDRESS);
	}

	@Then("User verify the updateUserAddress response message mathches {string}")
	public void user_verify_the_update_user_address_response_message_mathches(String expMsgAddresUpdate) {
		UpdateUserAddress_Output_Pojo updateUserAddress_Output_Pojo = response.as(UpdateUserAddress_Output_Pojo.class);
		String message = updateUserAddress_Output_Pojo.getMessage();
		Assert.assertEquals("Verify address updated sucessfully", expMsgAddresUpdate, message);

	}

	@When("User send {string} request for deleteUserAddress end point")
	public void user_send_request_for_delete_user_address_end_point(String DELETE) {
		DeleteUserAddress_Input_Pojo deleteUserAddress_Input_Pojo = new DeleteUserAddress_Input_Pojo(address_id);

		addBodyObject(deleteUserAddress_Input_Pojo);

		response = requestMethodType(DELETE, Endpoints.POSTMANDELETEUSERADDRESS);
	}

	@Then("User verify the deleteUserAddress response message mathches {string}")
	public void user_verify_the_delete_user_address_response_message_mathches(String expDeleteMsg) {

		DeleteUserAddress_Output_Pojo deleteUserAddress_Output_Pojo = response.as(DeleteUserAddress_Output_Pojo.class);
		String message = deleteUserAddress_Output_Pojo.getMessage();
		Assert.assertEquals("Verify delete address sucessfully", expDeleteMsg, message);
	}

	@When("User send {string} request for getUserAddress end point")
	public void user_send_request_for_get_user_address_end_point(String GET) {
		response = requestMethodType(GET, Endpoints.POSTMANGETUSERADDRESS);

	}

	@Then("User verify the getUserAddress response message mathches {string}")
	public void user_verify_the_get_user_address_response_message_mathches(String expOkmsg) {

		GetUserAddress_Output_Pojo getUserAddress_Output_Pojo = response.as(GetUserAddress_Output_Pojo.class);
		String message = getUserAddress_Output_Pojo.getMessage();
		Assert.assertEquals("Verify Ok", expOkmsg, message);
	}

}
