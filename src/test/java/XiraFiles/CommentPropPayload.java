package XiraFiles;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class CommentPropPayload {
public static String createSession()
{
	return "{\r\n" + 
			"	\"username\":\"tester\",\r\n" + 
			"	\"password\":\"malinga\"\r\n" + 
			"}";
}
}
