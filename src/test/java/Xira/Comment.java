package Xira;




	import XiraFiles.Payload;
	import io.restassured.RestAssured;
	import io.restassured.path.json.JsonPath;
	import io.restassured.response.Response;
	import org.json.JSONArray;
	import org.json.JSONObject;
	import org.testng.annotations.Ignore;
	import org.testng.annotations.Test;


	import java.util.ArrayList;
	import java.util.List;

	import static io.restassured.RestAssured.given;

	public class Comment {
	    static String sessionId;
	    List<String> al=new ArrayList();


	    @Test(priority = 0)
	    public static String CreateSession()
	    {
	        RestAssured.baseURI="http://localhost:8080";
	      Response response=  given().
	        header("Content-Type","application/json").
	        body(Payload.createSession()).
	        when().
	        post("/rest/auth/1/session").
	        then().
	        assertThat().

	        extract().response();

	      String res=response.asString();
	      System.out.println("Response Obtained"+res);

	        JsonPath jp=new JsonPath(res);
	        sessionId=(String)jp.get("session.value");
	        String id=sessionId;

	        System.out.println("Value of Session id"+id);
	        return sessionId;


	        }

	    //This method will add a comment in your issue

	    @Test()
	    public void addComment()
	    {
	        String id=sessionId;
	        String issueKey="RES-3";
	        RestAssured.baseURI="http://localhost:8080";
	      Response response=  given().
	        header("Content-Type","application/json").
	        header("Cookie","JSESSIONID="+sessionId+"").
	        body(Payload.addComment()).
	        when().
	        post("/rest/api/2/issue/"+issueKey+"/comment").
	        then().
	        assertThat().
	        statusCode(201).
	        extract().response();

	        System.out.println("Response Obatained after add Comment"+response.asString());

	    }
	    //This method will get Comment
	    @Test()
	    public void getIssueComments()
	    {
	        String issueKey="RES-3";
	        RestAssured.baseURI="http://localhost:8080";
	        Response response=given().
	        header("Content-Type","application/json").
	        header("Cookie","JSESSIONID="+sessionId+"").
	        when().
	        get("/rest/api/2/issue/RES-3/comment").
	        then().
	        assertThat().

	        extract().response();
	      String res=response.asString();

	      JSONObject jo=new JSONObject(res);
	      JSONArray ja=new JSONArray(jo.get("comments").toString());
	      int length=ja.length();
	      System.out.println("Number of Comments for this issue"+" "+length);



	      JsonPath jp=new JsonPath(res);
	      for(int i=0;i<length;i++)
	      {
	          String s=jp.getString("comments["+i+"].id");
	          al.add(s);
	      }
	      System.out.println();
	      for(String ss:al)
	      {
	         System.out.println("Comment id"+" "+ss);
	      }


	    }



	    //This method will delete a comment

	    @Test()
	    public void deleteComment()
	    {
	        String id=sessionId;
	        String key="RES-3";
	        String idOfComment="10100";
	        RestAssured.baseURI="http://localhost:8080";
	      Response response=  given().
	        header("Content-Type","application/json").
	        header("Application","application/json").
	        header("Cookie","JSESSIONID="+id+"").
	        when().
	        delete("/rest/api/2/issue/"+key+"/comment/"+idOfComment).
	        then().
	        statusCode(204).
	        extract().response();

	      System.out.println("Response after Deleting"+response.asString());
	      System.out.println();




	    }
	    //This method will update comment in your respective Issue

	    @Test()
	    public void updateComment()
	    {   String id=sessionId;
	        String key="RES-3";
	        String commentid="10002";

	        RestAssured.baseURI="http://localhost:8080";
	        Response response=given().
	        header("Content-Type","application/json").
	        header("Application","application/json").
	        header("Cookie","JSESSIONID="+id+"").
	        body(Payload.updateComment()).
	        when().
	        put("/rest/api/2/issue/"+key+"/comment/"+commentid+"").
	        then().
	        assertThat().
	        statusCode(200).
	        extract().response();
	        System.out.println("Response obtained after Updation "+" "+response.asString());

	    }



	}



