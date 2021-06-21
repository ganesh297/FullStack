package TestNG;


import java.util.NoSuchElementException;

import org.testng.annotations.Test;
public class RetryTestCase 
    
{
	
	@Test//(retryAnalyzer=RetryAnalyzer.class)
	public void testcase1(){
		System.out.println("Test Case 1");
		throw new NoSuchElementException();
		
	}
	
	@Test
	public void testcase2(){
		
		System.out.println("Test Case 2");
	}
	
	@Test
	public void testcase3(){
		
		System.out.println("Test Case 3");
	}
   
}
