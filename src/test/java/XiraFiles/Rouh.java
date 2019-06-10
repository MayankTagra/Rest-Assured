package XiraFiles;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public  class   Rouh {
	public static void main(String[] args)
	{
		String json="[\r\n" + 
				"    {\r\n" + 
				"        \"expand\": \"description,lead,url,projectKeys\",\r\n" + 
				"        \"self\": \"http://localhost:8080/rest/api/2/project/10100\",\r\n" + 
				"        \"id\": \"10100\",\r\n" + 
				"        \"key\": \"EX\",\r\n" + 
				"        \"name\": \"Example\",\r\n" + 
				"        \"avatarUrls\": {\r\n" + 
				"            \"48x48\": \"http://localhost:8080/secure/projectavatar?avatarId=10324\",\r\n" + 
				"            \"24x24\": \"http://localhost:8080/secure/projectavatar?size=small&avatarId=10324\",\r\n" + 
				"            \"16x16\": \"http://localhost:8080/secure/projectavatar?size=xsmall&avatarId=10324\",\r\n" + 
				"            \"32x32\": \"http://localhost:8080/secure/projectavatar?size=medium&avatarId=10324\"\r\n" + 
				"        },\r\n" + 
				"        \"projectTypeKey\": \"business\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"        \"expand\": \"description,lead,url,projectKeys\",\r\n" + 
				"        \"self\": \"http://localhost:8080/rest/api/2/project/10000\",\r\n" + 
				"        \"id\": \"10000\",\r\n" + 
				"        \"key\": \"RES\",\r\n" + 
				"        \"name\": \"Rest-Assured\",\r\n" + 
				"        \"avatarUrls\": {\r\n" + 
				"            \"48x48\": \"http://localhost:8080/secure/projectavatar?avatarId=10324\",\r\n" + 
				"            \"24x24\": \"http://localhost:8080/secure/projectavatar?size=small&avatarId=10324\",\r\n" + 
				"            \"16x16\": \"http://localhost:8080/secure/projectavatar?size=xsmall&avatarId=10324\",\r\n" + 
				"            \"32x32\": \"http://localhost:8080/secure/projectavatar?size=medium&avatarId=10324\"\r\n" + 
				"        },\r\n" + 
				"        \"projectTypeKey\": \"software\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"        \"expand\": \"description,lead,url,projectKeys\",\r\n" + 
				"        \"self\": \"http://localhost:8080/rest/api/2/project/10101\",\r\n" + 
				"        \"id\": \"10101\",\r\n" + 
				"        \"key\": \"SA\",\r\n" + 
				"        \"name\": \"Sample\",\r\n" + 
				"        \"avatarUrls\": {\r\n" + 
				"            \"48x48\": \"http://localhost:8080/secure/projectavatar?avatarId=10324\",\r\n" + 
				"            \"24x24\": \"http://localhost:8080/secure/projectavatar?size=small&avatarId=10324\",\r\n" + 
				"            \"16x16\": \"http://localhost:8080/secure/projectavatar?size=xsmall&avatarId=10324\",\r\n" + 
				"            \"32x32\": \"http://localhost:8080/secure/projectavatar?size=medium&avatarId=10324\"\r\n" + 
				"        },\r\n" + 
				"        \"projectTypeKey\": \"business\"\r\n" + 
				"    }\r\n" + 
				"]";
		
	try
	{	
		
		List al=new ArrayList();
		al.add("mayank");
		al.add("feder");
		al.add("daswe");
		if(al.contains("mayank"))
		{
			System.out.println("Element Found");
		}
		else
		{
			System.out.println("Element not found");
		}
		
		List all=new ArrayList();
			
		JsonNode om=new ObjectMapper().readTree(json);
		all=om.findValuesAsText("key");
		System.out.println(all);
		
		String projectkey="RES";
		if(all.contains(projectkey))
		{
			System.out.println("Element found");
		}
		else
		{
			System.out.println("Elment not found");
		}
		
		
		
		
	}
	catch(Exception e)
	{
		System.out.println("Exception caught");
	}
	
	
		
			
		
		
		

		
		
			
			
			
			
			
	}

}
