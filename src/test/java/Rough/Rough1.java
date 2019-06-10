package Rough;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class Rough1 {
	
	String sessionid;
	
	@Test(priority=0)
	public void createSession()
	{	
		String s ="{\r\n" + 
				"	\"username\":\"tester\",\r\n" + 
				"	\"password\":\"malinga\"\r\n" + 
				"}";
		RestAssured.baseURI="http://localhost:8080";
		Response response=given().
		header("Content-Type","application/json").
		header("Application","appplication/json").
		body(s).
		when().
		post("/rest/auth/1/session").
		then().
		assertThat().statusCode(200).extract().response();
		
		System.out.println("Response Obtained"+" "+response.asString());
		try
		{
		JsonNode om=new ObjectMapper().readTree(response.asString());
		sessionid=om.findValue("value").toString();
		System.out.println("SessionId"+" "+sessionid);
		System.out.println();
		
		}
		catch(Exception e)
		{
			System.out.println("Exception caught"+" "+e);
		}
		
	}
	
	@Test(enabled=true)
	public Response getAllProjects()
	{
		RestAssured.baseURI="http://localhost:8080";
		Response response=given().
		header("Content-Type","application/json").
		header("Application","application/json").
		header("Cookie","JSESSIONID="+sessionid+"").
		when().
		get("/rest/api/2/project").
		then().
		assertThat().
		statusCode(200).extract().response();
		System.out.println("Response obtained in get All Proects");
		
		System.out.println(response.asString());
		return response;
		
		
	}
	
	
	@Test(enabled=false)
	public void createProject()
	{
		String s="";
		try
		{	
			Response r=getAllProjects();
			JsonNode om=new ObjectMapper().readTree(r.asString());
			
			
			
		}
		catch(Exception e)
		{
			System.out.println("Exception"+" "+e);
		}
		
	}

}
