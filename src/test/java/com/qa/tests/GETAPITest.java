package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.com.restapitest.restapi.TestBase;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;


import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GETAPITest extends TestBase{
	
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
		apiUrl = prop.getProperty("serviceURLget");
		
		url = serviceUrl + apiUrl;
	}
	
	@Test
	public void GetAPITestWithHeaders() throws ClientProtocolException, IOException{
		restClient = new RestClient();
		String newurl = restClient.setURL(url, 719);		
		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");
		closeablehttpResponse = restClient.get(newurl, headerMap);
		
		// STatus Code
		int statuscode = closeablehttpResponse.getStatusLine().getStatusCode();
    	System.out.print("Status" + statuscode);
    	Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "The status code did not match");
    	
    	//JSON String
    	String resposneString = EntityUtils.toString(closeablehttpResponse.getEntity(), "UTF-8"); 
        JSONObject responseJSON = new JSONObject(resposneString);

    	Assert.assertEquals(TestUtil.getValueByJpath(responseJSON, "/data[0]/id"), "719");
    	Assert.assertEquals(TestUtil.getValueByJpath(responseJSON, "/data[0]/name"), "test");
    	Assert.assertEquals(TestUtil.getValueByJpath(responseJSON, "/data[0]/salary"), "123");
    	Assert.assertEquals(TestUtil.getValueByJpath(responseJSON, "/data[0]/age"), "23");
    	Assert.assertEquals(TestUtil.getValueByJpath(responseJSON, "/data[0]/profileimage"), "");
	}
}
