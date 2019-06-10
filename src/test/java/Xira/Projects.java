package Xira;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import XiraFiles.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;






public class Projects {
	 String  sessionId;
	
	@Test(priority=0)
	public void createSession()
	{
		sessionId=Comment.CreateSession();
		System.out.println("Id for the session "+" "+sessionId);
	}
	
	@Test()
	public  Response getAllProjects() 
	{	
		

		RestAssured.baseURI="http://localhost:8080";
		Response response=given().
		header("Content-Type","application/json").
		header("Application","application/json").
		header("Cookie","JSESSIONID="+sessionId+"").
		when().
		get("rest/api/2/project").
		then().
		assertThat().
		statusCode(200).
		body("id",hasItems("10000")).
		body("key",hasItems("RES")).
		body("name",hasItems("Rest-Assured")).
		body("projectTypeKey",hasItems("software")).
		extract().response();
		System.out.println("Response Obtained for All get projects");
		System.out.println(response.asString());
		
	
		
		//Having the Response as meaningful json response

		
		
		
		return response;
		
		
		
		//Here above the key is inside an array so thats why it is giving an error
		//In the above manner the baove type of response could be validates

		
	}
	@Test()
	public void getProject()
	{	
		RestAssured.baseURI="http://localhost:8080";
		Response response=given().
		header("Content-Type","application/json").
		header("Application","application/json").
		header("Cookie","JSESSIONID="+sessionId+"").
		when().
		get("rest/api/2/project/RES").
		then().
		assertThat().
		body("id",equalTo("10000")).
		body("key",equalTo("RES")).
		body("projectTypeKey",equalTo("software")).
		body("lead.key",equalTo("tester")).
		body("components[0].name",equalTo("Component 1")).
		body("components[1].name",equalTo("Component 2")).
		body("components[0].description",equalTo("This is a JIRA Component")).
		body("components[1].description",equalTo("This is second  JIRA Component")).
		body("components[0].id",equalTo("10004")).
		body("components[1].id",equalTo("10001")).
		statusCode(200).extract().response();
		
		System.out.println("Response Obtained"+" "+response.asString());

	}
	@Test()
	@Parameters("projectkey")
	public void updateProject(String projectkey)
	{		
		Response rr=getAllProjects();
		//JsonNode om=new ObjectMapper().readTree(rr.asString());
		//List projectKey
		//problem in Update Project
		try
		{
		JsonNode om=new ObjectMapper().readTree(rr.asString());
		List projectKeyList=new ArrayList();
		List projectIdList=new ArrayList();
		
		projectKeyList=om.findValuesAsText("id");
		projectIdList=om.findValuesAsText("key");
		System.out.println(projectKeyList);
		System.out.println(projectIdList);

		if(projectKeyList.contains(projectkey)||(projectIdList.contains(projectkey)))
		{
		
			RestAssured.baseURI="http://localhost:8080";
		Response response=	given().
			header("Content-Type","application/json").
			header("Application","application/json").	
			header("Cookie","JSESSIONID="+sessionId+"").
			body(Payload.updateProject()).
			when().
			put("/rest/api/2/project/"+projectkey+"").
			then().
			assertThat().
			statusCode(200).
			body("name",equalTo("Rest-Assured")).
			body("key",equalTo("RES")).
			body("projectTypeKey",equalTo("software")).
			body("description",equalTo("Example project description")).
			body("components[0].id",equalTo("10004")).
			body("components[1].id",equalTo("10001")).
			extract().response();
		
		System.out.println("Response Obtained for Update Project");
		System.out.println();
		System.out.println(response.asString());
		
		}
		else
		{
			System.out.println("Project Key or Id doesn't exists on JIRA");
		}
		}
		catch(Exception e)
		{
			System.out.println("Exception Occured "+" "+e);
		}
	
	}
	@Test()
	
	public void createProject()
	{	
	
		
		Response r=getAllProjects();
		try
		{
		JsonNode om=new ObjectMapper().readTree(r.asString());
		List projectKeyList=new ArrayList();
		List projectNameList=new ArrayList();
		
		projectKeyList=om.findValuesAsText("key");
		projectNameList=om.findValuesAsText("name");
		
		System.out.println(projectKeyList);
		System.out.println(projectNameList);
		
		String payload=Payload.createProject();
		JsonPath jp=new JsonPath(payload);
		String projectKey=jp.getString("key");
		System.out.println("Project Key"+" "+projectKey);
		
		String projectName=jp.getString("name");
		System.out.println("Project Name"+" "+projectName);
	
		boolean b1=projectKeyList.contains(projectKey);
		boolean b2=projectNameList.contains(projectName);
		boolean b=(b1)||(b2);
		
		
		

		if(!b)
		{
			
			RestAssured.baseURI="http://localhost:8080";
			Response response=given().
			header("Content-Type","application/json").
			header("Application","application/json").
			header("Cookie","JSESSIONID="+sessionId+"").
			body(Payload.createProject()).
			when().
			post("/rest/api/2/project").
			then().
			assertThat().
			extract().response();
			
			System.out.println("Response Obtained");
			System.out.println();
			System.out.println(response.asString());
		}
		else
		{
			System.out.println("Either Project key or Project name is repeated");
		}
		}
		catch(Exception e)
		{	
			
			
			System.out.println(e);
		}

	
	}
	/*
	@Test()
	@Parameters("projectId")
	public void deleteProject(String projectId)
	{	
		//Validate the request by validating whether that projectKey exists in the System or not
		
		try
		{	
			Response r=getAllProjects();
			
			JsonNode om=new ObjectMapper().readTree(r.asString());
			List projectKeyList=new ArrayList();
			List projectIdList=new ArrayList();
			projectKeyList=om.findValuesAsText("key");
			projectIdList=om.findValuesAsText("id");
			
			System.out.println(projectKeyList);
			System.out.println(projectIdList);
			
			if((projectKeyList.contains(projectId))||(projectIdList.contains(projectId)))
					{
				
					RestAssured.baseURI="http://localhost:8080";
					Response response=given().
					header("Content-Type","application/json").
					header("Application","application/json").
					header("Cookie","JSESSIONID="+sessionId+"").
					when().
					delete("/rest/api/2/project/"+projectId+"").
					then().
					assertThat().
					statusCode(204).
					extract().response();
					String res=response.asString();
					
					JsonPath jp=new JsonPath(res);
					System.out.println("Response after Deleting the Project");
					System.out.println();
					System.out.println(res);
				
					}
			else
			{
				System.out.println("The project key or Id does not exist");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	
		
	
	}
	*/
	
	@Test()
	@Parameters("projectId")
	public void deleteProhject(String projectId)
	{	
		//Delete the Project
		Response r=getAllProjects();
		try
		{
			JsonNode om=new ObjectMapper().readTree(r.asString());
			List projectKeyList=new ArrayList();
			List projectIdList=new ArrayList();
			
			projectKeyList=om.findValuesAsText("id");
			projectIdList=om.findValuesAsText("key");
			
			System.out.println("Project Key List"+" "+projectKeyList);
			System.out.println("Project Id List"+" "+projectIdList);
			
			if((projectKeyList.contains(projectId))||(projectIdList.contains(projectId)))
			{
				RestAssured.baseURI="http://localhost:8080";
				Response response=given().
				header("Content-Type","application/json").
				header("Application","application/json").
				header("Cookie","JSESSIONID="+sessionId+"").
				when().
				post("/rest/api/2/project/"+projectId+"").
				then().
				assertThat().
				statusCode(204).
				extract().response();
				
				System.out.println("Response Obtained"+" "+response.asString());
				
				
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Exception Occured "+" "+e);
		}
		
	}
	@Test()
	@Parameters("projectid")
	public void projectRoles(String projectid)
	{
		Response rr=getAllProjects();
		try
		{
		JsonNode om=new ObjectMapper().readTree(rr.asString());
		
		List projectKeyList=new ArrayList();
		List projectIdList=new ArrayList();
		
		projectKeyList=om.findValuesAsText("id");
		projectIdList=om.findValuesAsText("key");
		
		System.out.println(projectKeyList);
		System.out.println(projectIdList);
		
		if(projectKeyList.contains(projectid)||(projectIdList.contains(projectid)))
		{
			RestAssured.baseURI="http://localhost:8080";
			Response response=given().
			header("Content-Type","application/json").
			header("Application","application/json").
			header("Cookie","JSESSIONID="+sessionId+"").
			when().
			get("/rest/api/2/project/"+projectid+"/role").
			then().
			assertThat().
			statusCode(200).extract().response();
			
			System.out.println("Response Obtained");
			System.out.println();
			System.out.println(response.asString());
			
			
		}
		else
		{
			System.out.println("The current project key or projectId doesn't exists on Jira");
		}
		
		}
		catch(Exception e)
		{
			System.out.println("Exception Caught"+" "+e);
		}
		
	}
	

}
