package Xira;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import XiraFiles.Payload;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Issue {
	String sessionId;
	
	@Test(priority=0)
	public void createSession()
	{
		sessionId=Comment.CreateSession();
		System.out.println("Value of Session id "+" "+sessionId);
		
			
	}
	
	@Test()
	@Parameters("issuekey")
	public void getIssue(String issuekey)
	{	
		
		RestAssured.baseURI="http://localhost:8080";
		Response response=given().
		header("Content-Type","application/json").
		header("Application","application/json").
		header("Cookie","JSESSIONID="+sessionId+"").
		when().
		get("rest/api/2/issue/"+issuekey+"").
		then().
		assertThat().
		statusCode(200).extract().response();
		
		System.out.println("Response Recieved for getIssue"+" "+response.asString());
		
	
	
	}
	//Validating the Request body 
	@Test()
	public void createIssue()
	{
		RestAssured.baseURI="http://localhsot:8080";
		Response response=given().
		header("Content-Type","application/json").
		header("Application","application/json").
		header("Cookie","JSESSIONID="+sessionId+"").
		body(Payload.createIssue()).
		when().post("/rest/api/2/issue").
		then().
		assertThat().extract().response();
		
		System.out.println("Response Obtained after creating issue"+response.asString());
	
	}
	@Test()
	@Parameters("issuekey")
	public void deleteIssue(String issuekey)
	{
		RestAssured.baseURI="http://localhost:8080";
		Response response=given().
		header("Content-Type","application/json").
		header("Application","application/json").
		header("Cookie","JSESSIONID="+sessionId+"").
		when().
		delete("/rest/api/2/issue/"+issuekey+"").
		then().
		assertThat().
		statusCode(204).
		extract().response();
		
		System.out.println("Response Recieved"+" "+response.asString());
		
	}
	@Test()
	@Parameters("issuekey")
	public void updateIssue(String issuekey)
	{	
		RestAssured.baseURI="http://localhost:8080";
		Response response=given().
		header("Content-Type","application/json").
		header("Application","application/json").
		header("Cookie","JSESSIONID="+sessionId+"").
		body(Payload.updatIssue()).
		when().	
		put("/rest/api/2/issue/"+issuekey+"").
		then().
		assertThat().
		statusCode(204).
		extract().response();
		
		System.out.println("Response Obtained "+" "+response);

	}

}
