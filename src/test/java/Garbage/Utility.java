package Garbage;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

//@Ignore
public class Utility {
	
	//"lat":-38.383494  "lng":33.427362
	
	String b ="{\r\n" + 
			"	\r\n" + 
			"\"location\":\r\n" + 
			"{\r\n" + 
			"	\"lat\":-38.383494,\r\n" + 
			"	\"lng\":33.427362\r\n" + 
			"},\r\n" + 
			"\"accuracy\":50,\r\n" + 
			"\"name\":\"Frontline House\",\r\n" + 
			"\"phone number\":\"(+91) 983 893 3937\",\r\n" + 
			"\"address\":\"55,side layout,cohne 09\",\r\n" + 
			"\"types\":[\"shoe park\",\"parking\"],\r\n" + 
			"\"website\":\"http://google.com\",\r\n" + 
			"\"language\":\"French-IN\"\r\n" + 
			"\r\n" + 
			"	\r\n" + 
			"}";
	
	
	@Test(enabled=true,groups= {"one"})
	public void createAndDelete()
	{	
		
		System.out.println("Inside Create And Delete");

		
		RestAssured.baseURI="http://216.10.245.166/";
		Response response =given().
		header("Content-Type","application/json").
		queryParam("key","qaclick123").
		body(b).
		when().
		post("maps/api/place/add/json").
		then().
		assertThat().
		extract().response();
		
		String res=response.asString();
		JsonPath jp=new JsonPath(res);
	
		String s=(String)jp.get("place_id");
		System.out.println("value of placeid");
		System.out.println(s);
		
		String body="{"+
				"\"place_id\":\" "+ s+" \""+
				"}";
		System.out.println("PayLoad for Delete is as under");
		System.out.println(body);
		
	Response delResponse=given().
						header("Content-Type","application/json").
						body(body).
						when().
						delete("maps/api/place/delete/json").
						then().
						assertThat().extract().response();
	
	System.out.println("Response Recieved"+delResponse.asString());
	
	}
	
	@Test(enabled=true,groups= {"two"})
	public void getWithParam()
	{	
		System.out.println("Inside getWith Param");
		RestAssured.baseURI="https://api.openweathermap.org/";
		Response response=given().
		queryParam("zip","95050").
		queryParam("units","imperial").
		queryParam("appid","fd4698c940c6d1da602a70ac34f0b147").
		when().
		get("data/2.5/weather").
		then().
		assertThat().and().
		extract().response();
		
		System.out.println("Response Obtaned "+response.asString());
		
	}
	
	@Test(groups= {"one","two"})
	public void simplePostExample()
	{
		
		String body="\r\n" + 
				"{\r\n" + 
				"	\"Fields\":[\r\n" + 
				"		\r\n" + 
				"		{\r\n" + 
				"			\"fieldcode\":\"A\",\r\n" + 
				"			\"fieldvalue\":1\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"fieldcode\":\"B\",\r\n" + 
				"			\"fieldvalue\":2\r\n" + 
				"		},\r\n" + 
				"		{	\r\n" + 
				"			\"fieldcode\":\"c\",\r\n" + 
				"			\"fieldvalue\":3\r\n" + 
				"			\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"fieldcode\":\"D\",\r\n" + 
				"			\"fieldvalue\":4\r\n" + 
				"		}\r\n" + 
				"		]\r\n" + 
				"}";
		
		System.out.println("Inside Simple Post Example");
		RestAssured.baseURI="https://jsonplaceholder.typicode.com";
		Response response=given().
		header("Content-Type","application/json").
		body(body).
		when().
		post("/posts").
		then().
		assertThat().extract().response();
		
		System.out.println("Response Obtained");
		System.out.println(response.asString());
		
	}
	@Test()
	public void hold()
	{
		
	}

}
