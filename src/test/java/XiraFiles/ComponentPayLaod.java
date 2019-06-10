package XiraFiles;

public class ComponentPayLaod {
	public static String createComponenet()
	{
		return "{\r\n" + 
				"	\"name\":\"Component 1\",\r\n" + 
				"	\"description\":\"This is a JIRA Component\",\r\n" + 
				"	\"leadUserName\":\"tester\",\r\n" + 
				"\r\n" + 
				"	\"isAssigneeTypeValid\":false,\r\n" + 
				"	\"project\":\"RES\",\r\n" + 
				"	\"projectId\":\"10000\"\r\n" + 
				"}";
	}
	public static String updateComponent()
	{
		return "{\r\n" + 
				"	\"name\":\"Component 1\",\r\n" + 
				"	\"description\":\"Component 1 created successfully\",\r\n" + 
				"	\"leadUserName\":\"tester\",\r\n" + 
				"	\"isAssigneeTypeValid\":false,\r\n" + 
				"	\"project\":\"RES\",\r\n" + 
				"	\"projectId\":\"10000\"\r\n" + 
				"}";
	}
	public static String createSession()
	{
		return "{\r\n" + 
				"	\"username\":\"tester\",\r\n" + 
				"	\"password\":\"malinga\"\r\n" + 
				"}";
	}

}
