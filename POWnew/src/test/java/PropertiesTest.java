import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utils.configReader;

public class PropertiesTest {
	Properties prop;
	
	@BeforeTest
	public void beforeTest() {}
	
	@Test
	public void printProperties() throws Exception{
		
		configReader config = new configReader("./Config/config.properties");
				
		String baseURL = config.getValue("baseURL");
		System.out.println("URL :"+baseURL);
		
		String reportFile = config.getValue("POWtest", 2);
		System.out.println("REPORT :"+reportFile);
		
		
	}
	
	
	
}
