package Garbage;

import org.testng.annotations.Test;

public class PriorityExample {
    @Test(priority = 3)
    public void m1()
    {
        System.out.println("Inside method m1")
;    }
    @Test(priority=2)
    public void m2()
    {
        System.out.println("Inside method m2");
    }

    @Test(priority=1)
    public void m3()
    {
        System.out.println("Inside method m3");
    }
    @Test(priority = 0)
    public void m4()
    {
        System.out.println("Inside method m4");
    }
}
