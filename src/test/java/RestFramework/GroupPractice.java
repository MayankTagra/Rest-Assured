package RestFramework;

import org.testng.annotations.Test;

public class GroupPractice {
	
	@Test(groups= {"windows.checkintest"})
	public void checkWindowOnly()
	{
		System.out.println("Inside Windows Check Intest");
	}
	@Test(groups= {"linux.checkintest"})
	public void checkLinuxOnly()
	{
		System.out.println("Inside Linux CheckIntest");
	}
	@Test(groups= {"windows.functest"})
	public void checkWindowsToo()
	{
		System.out.println("Inside Windows Function Test ");
	}

}



