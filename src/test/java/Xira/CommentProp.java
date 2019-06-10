package Xira;

import org.testng.annotations.Test;

import XiraFiles.CommentPropPayload;

import static io.restassured.RestAssured.given;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CommentProp {
	
	String sessionId;
	@Test(priority=0)
	public void createSession()
	{
		
		sessionId=Comment.CreateSession();
		System.out.println("Id for this session "+" "+sessionId);
		
	}
	
	@Test()
	public void getCommentProp()
	{	
		String commentId="10002";
		RestAssured.baseURI="http://localhost:8080";
		Response response=given().
		header("Content-Type","application/json").
		header("Application","application/json").
		header("Cookie","JSESSIONID="+sessionId+"").
		when().
		get("/rest/api/2/comment/"+commentId+"/properties").
		then().
		assertThat().statusCode(200).extract().response();
		
		String res=response.asString();
		System.out.println("getCommentProperty"+" "+res);
		
	}
	


}
