package RestFramework;

import org.testng.annotations.Test;

public class GroupTestExample {
	
	@Test(groups= {"functest","checkintest"})
	public void testPrintMessage()
	{
		System.out.println("Test Print Message");
	}
	
	@Test(groups= {"checkintest"})
	public void testSalutationMessgae()
	{
		System.out.println("Test Salutation Message");
	}
	@Test(groups= {"functest"})
	public void testingExitMessage()
	{
		System.out.println("Testing Exit Message");
	}
	@Test(groups= {"windows"})
	public void windowsOnComputer()
	{
		System.out.println("Windows on Computer");
	}
	
	@Test(groups= {"linux"})
	public void linuxOnComputer()
	{
		System.out.println("linux on computer");
	}
	
	@Test(groups= {"windows"})
	public void windowsOnLaptop()
	{
		System.out.println("Windows on Laptop");
	}
	
	@Test(groups= {"linux"})
	public void linuxLaptop()
	{
		System.out.println("Linux on Laptop");
	}

}
