package TestNG;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
 
public class OptionalParameter {
	
	@Parameters({"Test1"})
	@Test
	public void Parameter1(String Test1){
	    System.out.println("Parameter is: "+Test1);
	}
	
	@Parameters({"Test2"})
	@Test
	public void Parameter2(String Test2){
	    System.out.println("Parameter is: "+Test2);
	}
	
	@Parameters({"Test3"})
	@Test
	public void Parameter3(@Optional("Optional Parameter") String Test3){
	    System.out.println("Parameter is: "+Test3);
	}
}