package com.qa.client;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;

public class RestClient {
	
	public String setURL(String url, int var) {
		String newurl = url + var;
		return newurl;
	}
	
    // GET Method With Headers
    public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
    	//create a http client
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	HttpGet httpget = new HttpGet(url); //http get request
    	
    	// for headers
    	for(Map.Entry<String,String> entry : headerMap.entrySet()) {
    		httpget.addHeader(entry.getKey(), entry.getValue());
    	}
    	
    	//Response contains: status code, JSON String and headers
    	CloseableHttpResponse closeablehttpResponse = httpClient.execute(httpget); //hit the GET url
    	return closeablehttpResponse;
    	//Status Code
    	
    }
    
    // POST Method
    public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	HttpPost httpPost = new HttpPost(url); // for post request
    	httpPost.setEntity(new StringEntity(entityString)); //for payload
    	// for headers
    	for(Map.Entry<String,String> entry : headerMap.entrySet()) {
    		httpPost.addHeader(entry.getKey(), entry.getValue());
    	}
    	
    	CloseableHttpResponse closeablehttpResponse = httpClient.execute(httpPost);
    	return closeablehttpResponse;
    }
	
    // PUT Method
    public CloseableHttpResponse put(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	HttpPut httpPut = new HttpPut(url); // for post request
    	httpPut.setEntity(new StringEntity(entityString)); //for payload
    	// for headers
    	for(Map.Entry<String,String> entry : headerMap.entrySet()) {
    		httpPut.addHeader(entry.getKey(), entry.getValue());
    	}
    	
    	CloseableHttpResponse closeablehttpResponse = httpClient.execute(httpPut);
    	return closeablehttpResponse;
    }
}
