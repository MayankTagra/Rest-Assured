package XiraFiles;

public class Payload {
	public static String  createSession()
	{
		return "{\r\n" + 
				"	\"username\":\"tester\",\r\n" + 
				"	\"password\":\"malinga\"\r\n" + 
				"}";
	}
	
	public static String createIssue()
	{
		return "{\r\n" + 
				"	\"fields\":\r\n" + 
				"	{\r\n" + 
				"		\"project\":\r\n" + 
				"		{\r\n" + 
				"			\"key\":\"RES\"\r\n" + 
				"		},\r\n" + 
				"		\"summary\":\"Debit card defect\",\r\n" +
				"		\"description\":\"create my first bug\",\r\n" + 
				"		\"issuetype\":\r\n" + 
				"		{\r\n" + 
				"			\"name\":\"Bug\"\r\n" + 
				"		}\r\n" + 
				"	}\r\n" + 
				"}";
	}

	public static String addComment()
	{
		return"{\n" +
				"\t\"visibility\":\n" +
				"\t{\n" +
				"\t\t\"type\":\"role\",\n" +
				"\t\t\"value\":\"Administrators\"\n" +
				"\t},\n" +
				"\t\"body\":\"New Issue in RES-3\"\n" +
				"}";
	}
	public static String updateComment()
    {
        return "{\n" +
                "\t\"body\":\"RES-3 Comment\",\n" +
                "\t\"visibility\":\n" +
                "\t{\n" +
                "\t\t\"type\":\"role\",\n" +
                "\t\t\"value\":\"Administrators\"\n" +
                "\t}\n" +
                "}";
    }
	
	public static String updatIssue()
	{
		return "{\r\n" + 
				"\"update\":\r\n" + 
				"{\r\n" + 
				"	\"summary\":[{\"set\":\"Bug founded on 6 th June\"}]\r\n" + 
				"}\r\n" + 
				"}";
	}
	public static String updateUser()
	{
		return "{\r\n" + 
				"	\"password\":\"malinga\",\r\n" + 
				"	\"emailAddress\":\"tagramayank203@gmail.com\",\r\n" + 
				"	\"displayName\":\"Zester Haladie\"\r\n" + 
				"}";
	}
	
	public static String changePassword()
	{
		return "{\r\n" + 
				"	\"password\":\"malinga\",\r\n" + 
				"	\"currentPassword\":\"malinga\"\r\n" + 
				"}";
	}
	public static String updateProject()
	{
		return "{\r\n" + 
				"	\"key\":\"RES\",\r\n" + 
				"	\"name\":\"Rest-Assured\",\r\n" + 
				"	\"projectTypeKey\":\"software\",\r\n" + 
				"	\"projectTemplateKey\":\"com.atlassian.jira-core-project-templates:jira-core-project-management\",\r\n" + 
				"	\"description\":\"Example project description\"\r\n" + 
				"}";
	}
	public static String createProject()
	{
		return "{\r\n" + 
				"	\"key\":\"ES\",\r\n" + 
				"	\"name\":\"Extract\",\r\n" + 
				"	\"projectTypeKey\":\"business\",\r\n" + 
				"	\"projectTemplateKey\":\"com.atlassian.jira-core-project-templates:jira-core-project-management\",\r\n" + 
				"	\"description\":\"Extract Project description\",\r\n" + 
				"	\"lead\":\"tester\",\r\n" + 
				"	\"assigneeType\": \"PROJECT_LEAD\"\r\n" + 
				"}";
	}

}
