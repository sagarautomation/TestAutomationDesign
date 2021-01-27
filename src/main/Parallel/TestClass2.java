package Parallel;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestClass2
{
    @BeforeClass
    public void beforeClass() {
        System.out.println("Class 2 - Before Class with Thread Id:- "+ Thread.currentThread().getId());
    }

    @Test
    public void testA()
    {
        System.out.println("Class 2 - Test Case A with Thread Id:- " + Thread.currentThread().getId());
    }

    @Test
    public void testB() {
        System.out.println("Class 2 - Test Case B with Thread Id:- " + Thread.currentThread().getId());
    }


}
