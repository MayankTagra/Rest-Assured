package Xira;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

import XiraFiles.Payload;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class User {
	String sessionId;
	
	@Test(priority=0)
	public void createSession()
	{
		sessionId=Comment.CreateSession();
		System.out.println("Id for Session"+" "+sessionId);
	}
	
	@Test()
	public void getUser()
	{
		RestAssured.baseURI="http://localhost:8080";
	Response response=	given().
		header("Content-Type","application/json").
		header("Application","application/json").
		header("Cookie","JSESSIONID="+sessionId+"").
		when().
		get("/rest/api/2/myself").
		then().	
		assertThat().
		statusCode(200).extract().response();
	
	System.out.println("Response Obtained for get Use"+" "+response.asString());
	
		
	}
	@Test()
	public void updateUser()
	{
		RestAssured.baseURI="http://localhost:8080";
	Response response=	given().
		header("Content-Type","application/json").
		header("Application","application/json").
		header("Cookie","JSESSIONID="+sessionId+"").
		body(Payload.updateUser()).
		
		when().
		put("/rest/api/2/myself").
		then().
		assertThat().
		statusCode(200).body("displayName",equalTo("Zester Haladie")).
		body("key",equalTo("tester")).
		body("name",equalTo("tester")).
		body("emailAddress",equalTo("tagramayank203@gmail.com")).
		extract().response();
		
		System.out.println("Reponse Recieved for Updated User"+response.asString());
		
	
	}
	
	//Api for changing your password on Jira
	@Test()
	public void changePassword()
	{
		RestAssured.baseURI="http://localhost:8080";
		Response response=given().
		header("Content-Type","application/json").
		header("Application","application/json").
		header("Cookie","JSESSIONID="+sessionId+"").
		body(Payload.changePassword()).
		when().
		put("/rest/api/2/myself/password").
		then().
		assertThat().
		statusCode(204).
		extract().response();
		System.out.println("Response recieved for change Password"+" "+response.asString());
		
		}
	
	

}
