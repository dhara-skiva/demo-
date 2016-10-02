package utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.reporters.Files;


public class Common {

	public static String convert(int a, int b){
		int sum = a+b;
		String result = Integer.toString(sum);
		
		return result;
	}

	// Sum two strings and return a string
	public static String convert(String a, String b){
		
		int a1 = Integer.parseInt(a);
		int b1 = Integer.parseInt(b);
		
		int sum = a1+b1;
		
		String result = Integer.toString(sum);
		return result;
	}
	
	// Sum int, String and return a String 
	public static String convert(int a, String b){
		
		int b1 = Integer.parseInt(b);
		int sum = a+b1;

		String result = Integer.toString(sum);
		return result;
	}
	
public static String[] convertInttoString(int []a){
		
		String s[] = new String[4];
		int x;
		
		System.out.print("int a[]={");
		for(int i=0;i<a.length;i++){
			
			// add 80 to input	
			x = a[i]+80;
			if(i==3){
				System.out.print(a[i]);
			}else{
				System.out.print(a[i]+",");
			}
			// convert x to string and put in string array 
			s[i] = Integer.toString(x);
			
		}
		System.out.println("}");

		return s;	
	}
	
	public static boolean compareStrings(String s1, String s2){
		
		if(s1.equalsIgnoreCase(s2))
			return true;
		
		else
			return false;
		
	} 
	

	public static String takeScreenShot(WebDriver driver,String fileName) throws Exception{
          long dateTime = System.currentTimeMillis();
        
          String filepath = "./Screenshot/"+fileName+dateTime+".png";
          File outFile = new File(filepath);
          TakesScreenshot sshot = (TakesScreenshot)driver;
          File inputFile = sshot.getScreenshotAs(OutputType.FILE);
          FileUtils.copyFile(inputFile, outFile);
          // Files.copy(inputFile, outFile);

          return filepath;
	}
}
