package com.stepdefinition;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojoclass.ChangeProfilePic_Output_Pojo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;


/**
 * 
 * @author Velumani Devendiran
 *
 *@Description Used to Maintain ChangeProfileStep Pic with help of Configuration properties 
 *
 *@Date 06/09/2022
 */
public class TC3_ChangeProfilePic extends BaseClass{
	

	
	@Given("User add header and bearer authrozation for accessing ProfilePic end points")
	public void user_add_header_and_bearer_authrozation_for_accessing_profile_pic_end_points() {
		List<Header>h=new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " +TC1_LoginStep.logtoken);
		Header h3 = new Header("Content-Type", "multipart/form-data");

		h.add(h1);
		h.add(h2);
		h.add(h3);
		Headers headers = new Headers(h);
		addHeaders(headers);
		
	}
	@When("User add request and bearer for {string}")
	public void user_add_request_and_bearer_for(String Picture) throws FileNotFoundException, IOException {
		formData(Picture, new File(System.getProperty("user.dir")+getPropertyFileValue("Profilepic")));
	
	}
	@When("User send {string} request for Changeprofile picture end point")
	public void user_send_request_for_changeprofile_picture_end_point(String POST) {
	
		response = requestMethodType(POST, Endpoints.POSTMANCHANGEPROFILEPIC);
	
	}
	@Then("User verify the profile picture Change message {string}")
	public void user_verify_the_profile_picture_change_message(String expUpdatedMsg) {
	
		ChangeProfilePic_Output_Pojo changeProfilePic_Output_Pojo = response.as(ChangeProfilePic_Output_Pojo.class);
		
		String message = changeProfilePic_Output_Pojo.getMessage();
		
		Assert.assertEquals("Verify Updated profile pic", expUpdatedMsg, message);
	}




}
