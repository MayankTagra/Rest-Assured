/*package RestFramework;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Files.PayLoad;
import Files.Resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;



public class Typicoderclass {
	
	@BeforeTest(enabled=false)
	public void getHost() throws IOException
	{
		Properties p=new Properties();
		
	
		FileInputStream fs=new FileInputStream("C:\\Users\\mtagra\\Reat-Assured\\src\\test\\java\\Files\\env.Properties");
		p.load(fs);
		String s=p.getProperty("HOST");
		System.out.println(s);

		System.out.println("Hello");
		

	}
	
	
	
	
	
	@Test(enabled=false)
	public void GetwithParam()
	{
		RestAssured.baseURI="https://api.openweathermap.org";
		
		given().
			param("zip","95050").
			param("units","imperial").
			param("appid","fd4698c940c6d1da602a70ac34f0b147").
		when().
			get("/data/2.5/weather").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("base",equalTo("stations")).and().
		body("weather[0].main",equalTo("Clouds")).and().
		body("weather[0].id",equalTo(804)).and().
		body("clouds.all",equalTo(90)).and().
		body("sys.type",equalTo(1)).and().
		
		//To Check the server name we match the header and the header value
		header("Server","openresty");	
		
	}
	@Test(enabled=false)
	public void PostReqExample()
	{
		RestAssured.baseURI="http://jsonplaceholder.typicode.com";
		
		given().
			queryParam("key","a1b2c3d4e5f6g7h8i9o0").
			body(PayLoad.getPostData()).
		when().
			post(Resources.placePostData()).
		then().
			assertThat().statusCode(201).body("id",equalTo(101)).contentType(ContentType.JSON);
		
		
	}
	
	@Test(enabled=false)
	public void CreateAndDeleteaPlcae()
	{	
		
		String b=PayLoad.CreateDeletePayLoad();
		RestAssured.baseURI="http://216.10.245.166";
			Response response=given().
			queryParam("key","qaclick123").
			body(b).
			when().
			post("/maps/api/place/add/json").
			then().
			assertThat().statusCode(200).contentType(ContentType.JSON).and().
			body("status",equalTo("OK")).and().
			extract().response();
			
			String resString=response.asString();
			System.out.println(resString);
			
			//How to get the Response and store it as String
			//Grabing the Response and Reciving the id
			
			JsonPath js=new JsonPath(resString);
			String placeid=js.get("place_id");
			
			System.out.println("Value of Placeid"+" "+placeid);
			
			String body="{\"place_id\":\""+placeid+"\"}";
			
			Response response2 =given().
			queryParam("key","qaclick123").
			body(body).
			when().
			delete("/maps/api/place/delete/json").
			then().
			assertThat().contentType(ContentType.JSON).statusCode(200).
			extract().response();
			
			System.out.println("Response 2");
			String dd=response2.asString();
			System.out.println(dd);
	
	}
	@Test(enabled=false)
	public void centralStageRequest()
	{
		RestAssured.baseURI="http://centralservices.stg.pch.com/RFApi_v9/Svc/membermanagement.svc/json/api/registration/setmember";
		Response response=given().
		header("Content-Type","Application/json").
		header("Api-Consumer-Name","RFApiTestPage").
		body(PayLoad.CentralServicePayload()).
		when().
		post().
		then().extract().response();
		
		System.out.println("Response Obtained"+response.asString());
		int statusCode=response.getStatusCode();
		System.out.println("Status Code"+statusCode);

	}
	@Test(groups= {"first"})
	public String  centralStageRequestWithDynamicJSON() throws IOException
	{	
		String jsondata=null;
		String line=null;
		RestAssured.baseURI="http://centralservices.stg.pch.com/RFApi_v9/Svc/membermanagement.svc/json/api/registration/setmember";
		InputStream input=new FileInputStream("C:\\Users\\mtagra\\Reat-Assured\\src\\test\\java\\Files\\setMember.json");
		InputStreamReader reader=new InputStreamReader(input);
		BufferedReader bf=new BufferedReader(reader);
		
		
		while((line=bf.readLine())!=null)
		{
			jsondata+=line+"\n";
		}
		
		System.out.println(jsondata);
		HashMap<String,String> hm=new HashMap<String,String>();
		hm.put("firstname","\"Scott\"");
		
		
		StrSubstitutor st=new StrSubstitutor(hm);
		jsondata=st.replace(jsondata);
		System.out.println(jsondata);
		
	Response  response=	given().
		header("Conetnt-Type","application/json").
		header("Api-Consumer-Name","RFApiTestPage").
		body(jsondata).
		when().
		post().
		then().extract().response();
	
	System.out.println(response.asString());
	return null;
	
	//return "This method from typicoderClass has been tested";
	
		
	}
	@Test(groups= {"second"})
	public void LibraryPostTest() throws IOException
	{	
		
			
		String jsondata=null;
		String line=null;
		RestAssured.baseURI="http://216.10.245.166";
		InputStream input=new FileInputStream("C:\\Users\\mtagra\\Reat-Assured\\src\\test\\java\\Files\\isbn.json");
		InputStreamReader reader=new InputStreamReader(input);
		BufferedReader bf=new BufferedReader(reader);
		while((line=bf.readLine())!=null)
		{
			jsondata+=line+"\n";
		}
		HashMap<String,String> hm=new HashMap<String,String>();
		hm.put("Isbn", "mckkkeede");
		StrSubstitutor str=new StrSubstitutor(hm);
		jsondata=str.replace(jsondata);
		System.out.println("Request Payload");
		System.out.println(jsondata);
		
		Response response=	given().
			header("Content-Type","application/json").
			body(jsondata).
			when().
			post("/Library/Addbook.php").
			then().
			assertThat().statusCode(200).
			extract().response();
		
		System.out.println("Response"+response.asString());

		
	}
	
	
	


}
*/