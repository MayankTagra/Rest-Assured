package Xira;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import XiraFiles.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;



public class Basic1 {
	//Creating a session
	
	String sessionId;
	@Test()
	public void createSession()
	{
		RestAssured.baseURI="http://localhost:8080";
	Response response=	given().
		body(Payload.createSession()).
		header("Content-Type","application/json").
		when().
		post("/rest/auth/1/session").
		then().
		assertThat().
		statusCode(200).extract().response();
		String res=response.asString();
		JsonPath jp=new JsonPath(res);
		
		sessionId=jp.getString("session.value");
		System.out.println("Id for the session"+" "+sessionId);

	}
	
	//creating an issue
	@Test(enabled=false)
	public void  createIssue()
	{	
		
		String id=sessionId;
		System.out.println("value of id"+id);
		RestAssured.baseURI="http://localhost:8080";
	Response response=	given().
		header("Content-Type","application/json").
		header("Cookie","JSESSIONID="+id+"").	
		body(Payload.createIssue()).
		when().
		post("/rest/api/2/issue").
		then().
		assertThat().
		extract().response();
	System.out.println("Response Obtained");
	System.out.println(response.asString());


	}
	//Delete an Issue
	@Test(enabled=true)
	public void DeleteIssue()
	{
	
	}
	

}
