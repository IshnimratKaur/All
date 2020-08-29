package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.com.restapitest.restapi.TestBase;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends TestBase{
	
	TestBase testbase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeablehttpResponse;
	
	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		testbase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURLpost");
		
		url = serviceUrl + apiUrl;		
	}
	
	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");
		
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("name1", 30000, 35); //expected 
		
		mapper.writeValue(new File("C:\\Users\\A0782393.AONNET\\Desktop\\RestAPINew\\src\\main\\java\\com\\qa\\data\\users.json"), users);
		
		String usersJsonString = mapper.writeValueAsString(users);
		closeablehttpResponse = restClient.post(url, usersJsonString, headerMap);
		
		// Status Code
		int statuscode = closeablehttpResponse.getStatusLine().getStatusCode();
    	System.out.print("Status" + statuscode);
    	Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_201, "The status code did not match");

    	// Verify expected and actual values
    	String resposneString = EntityUtils.toString(closeablehttpResponse.getEntity(), "UTF-8");
        JSONObject responseJSON = new JSONObject(resposneString); 
    	
    	Users usersobj = mapper.readValue(resposneString, Users.class); //actual
    	System.out.print(usersobj);
    	
    	Assert.assertEquals(users.getName(), usersobj.getName(), "The name did not match the expected value");
    	Assert.assertEquals(users.getSalary(), usersobj.getSalary(), "The salary did not match the expected value");
    	Assert.assertEquals(users.getAge(), usersobj.getAge(), "The age did not match the expected value");
	}
}
