
import org.apache.commons.exec.ExecuteException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utils.DataReader;
import utils.LoginPage;
import utils.configReader;
import utils.Common;

public class AdminMenu {
	WebDriver driver;
	DataReader datafile;
	ExtentReports extent = null;
	ExtentTest test = null;
	String reportFile = null;
	LoginPage loginPage;
	
	@BeforeClass
	public void init() throws Exception{
	
		configReader config = new configReader("./Config/config.properties");
		
		/*String adminURL = config.getValue("adminURL");
		System.out.println("URL :"+adminURL);
		*/
		String reportRoot = config.getValue("reportsRoot");
		String reportFile = config.getValue("POWtest", 2);
		System.out.println("REPORT :"+reportRoot+"/"+reportFile);
		
				
		System.setProperty("webdriver.chrome.driver", config.getValue("chromeDriver"));
		driver = new ChromeDriver();
		driver.get(config.getValue("adminURL"));
	
		driver.manage().window().maximize();
		
		loginPage = new LoginPage(driver);
		//reportFile = "./Reports/Current/Result1.html";
		extent = new ExtentReports(reportRoot+"/"+reportFile);
		
		readFile();
	}
	
	public void readFile() throws Exception{
		// get the xlsx file
		datafile = new DataReader("./src/test/resources/pownewlinks.xlsx");
	}
	
	@Test(priority=0)
	public void verifyAdminLogin(){
		
		// Login to Admin
	//	driver.findElement(By.id("email")).sendKeys("pow_a32@yahoo.com");
	//	driver.findElement(By.id("password")).sendKeys("powadmin");
	//	driver.findElement(By.id("loginbutton")).click();
				
		loginPage.enterUsername("pow_a32@yahoo.com");
		loginPage.enterPassword("powadmin");
		loginPage.clickLogin();
	}
	
	@DataProvider(name="AdminMenu")
	public Object[][] buildAdminMenuData(){
		// get the sheet
		datafile.getSheet("AdminMenu(no Report)");
				
		int rows = datafile.getRows("AdminMenu(no Report)");
		int cols = 3;
		
		Object data[][] = new Object[rows-1][cols];
		String str[][] = new String[rows-1][cols];
		
		for(int i=0; i<rows-1;i++){
			for(int j=0; j<cols; j++){
				str[i][j] = datafile.getCellData(0, i+1, j);			
			}
		}
		
		data = str;
		return data;
	}
	
	@DataProvider(name="AdminMenuReport")
	public Object[][] buildAdminMenuReportData(){
		//System.out.println("-----------------------------------------");
		datafile.getSheet("ReportsOnly");
		int rows = datafile.getRows("ReportsOnly");
		int cols = 4;
		
		
		Object data[][] = new Object[rows-2][cols];
		String str[][] = new String[rows-2][cols];
		
		for(int i=0; i<rows-2;i++){
			for(int j=0; j<cols; j++){
					
				str[i][j] = datafile.getCellData(1, i+2, j);			
			}
		}
		
		data = str;
		return data;
	}
	

	@Test(dataProvider="AdminMenu",priority=1)
	public void verifyAdminMenuLinks(String testCase,String link, String title){
	/*	System.out.println(testCase);
		System.out.print(","+link);
		System.out.print(","+title);
		System.out.println("---------------------------------------------------");
	 */
		test = extent.startTest(testCase);
		test.log(LogStatus.INFO, "Test Started....");
		
		driver.findElement(By.linkText(link)).click();
		String actualTitle = driver.getTitle();
		String finalTitle = actualTitle.substring(12);
		//System.out.println(testCase+" link --"+link+" title --"+title+" Actual --"+finalTitle);
		Assert.assertTrue(Common.compareStrings(title, finalTitle),"Title matches");
		
		
	}

	//@Test(dataProvider="AdminMenuReport")
	public void verifyAdminMenuReportLinks(String testCase,String link, String title, String pageSource){
		/*System.out.println(testCase);
		System.out.print(","+link);
		System.out.print(","+title);
		System.out.print(","+pageSource);*/
		
		extent.startTest(link);
		test.log(LogStatus.INFO, "Test Started....");
	
		
	}
	
	@AfterMethod
	public void afterMethodTest(ITestResult result) throws Exception{
		
		/*System.out.println(result.getName());
		System.out.println(result.getStatus());
		System.out.println(result.getEndMillis());
*/
		if(result.isSuccess()){
			//System.out.println("Test Passed");
		}else{
			System.out.println("Test Failed");
			//String errMsg = result.getThrowable().getMessage();
			//System.out.println("Error Message :"+errMsg);

			System.out.println(result.getName());
			System.out.println(result.getStatus());
			
			test.log(LogStatus.FAIL, "Test Case Failed");
			String filepath = Common.takeScreenShot(driver, result.getName());
		//	System.out.println("-------->>>>> FILEPATH :"+filepath);
			String sshot = 	test.addScreenCapture("../../"+filepath);
			test.log(LogStatus.FAIL,"Attach",sshot);
			
		}
		
	extent.endTest(test);
	extent.flush();
			
	}

	
        
	@AfterClass
	public void cleanUp(){
		driver.close();
	}
}
