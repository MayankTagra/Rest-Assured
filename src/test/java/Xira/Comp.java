package Xira;


import static io.restassured.RestAssured.given;


import org.testng.annotations.Test;

import XiraFiles.ComponentPayLaod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class Comp {
	//create Component
	String sessionId;
	
	@Test(priority=0)
	public void createSession()
	{
		
	
	sessionId=Comment.CreateSession();
	System.out.println("Id for this session "+" "+sessionId);
	
		
	}
	
	@Test()
	public  void createComponent()
	{	
		
		String id=sessionId;
		RestAssured.baseURI="http://localhost:8080";
	Response response=	given().
		header("Content-Type","application/json").
		header("Application","application/json").
		header("Cookie","JSESSIONID="+id+"").
		body(ComponentPayLaod.createComponenet()).
		when().
		post("/rest/api/2/component").
		then().
		assertThat().
		statusCode(201).and().
		extract().response();
	String res=response.asString();
	System.out.println("Response recieved for create Component"+" "+response.asString());
	JsonPath jp=new JsonPath(res);
	String componentId=jp.getString("id");
	System.out.println("Component ID"+" "+componentId);
	
	}
	//update Component
	
	@Test()
	public void updateComponent()
	{		
		String id=sessionId;
		String key="10000";
		RestAssured.baseURI="http://localhost:8080";
	Response response=	given().
		header("Content-Type","application/json").
		header("Application","application/json").
		header("Cookie","JSESSIONID="+id+"").
		body(ComponentPayLaod.updateComponent()).
		when().
		put("/rest/api/2/component/"+key+"").
		then().
		assertThat().
		statusCode(200).
		extract().response();
	
	System.out.println("Response recieved "+" "+response.asString());
	}
	
	//Getting the Details of a Component
	@Test()
	public void getComponent()
	{		
		String key="1003";
		RestAssured.baseURI="http://localhost:8080";
		Response response=given().
		header("Content-Type","application/json").
		header("Application","application/json").
		header("Cookie","JSESSIONID="+sessionId+"").
		when().
		get("/rest/api/2/component/"+key+"").
		then().
		assertThat().
		statusCode(200).
		extract().response();
		
		System.out.println("Response Recieved"+response.asString());
	}
	
	@Test()
	public void deleteComponent()
	{
		String key="10003";
		RestAssured.baseURI="http://localhost:8080/";
	Response response=	given().
		header("Content-Type","application/json").
		header("Application","application/json").
		header("Cookie","JSESSIONID="+sessionId+"").
		when().
		delete("rest/api/2/component/"+key+"").
		then().
		assertThat().
		statusCode(204).
		extract().response();
	System.out.println("Response Obtained after deletion "+" "+response.asString());
		
		
	}

}
