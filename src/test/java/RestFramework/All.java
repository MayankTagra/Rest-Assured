package RestFramework;

import org.testng.annotations.Test;

@Test(groups= {"checkin-test"})
public class All {
	
	@Test(groups= {"func-test"})
	public void method1()
	{
		System.out.println("You are inside method 1");
	}
	
	public void method2()
	{
		System.out.println("You are inside method 2");
	}

}
