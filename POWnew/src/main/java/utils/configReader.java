package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class configReader {

	Properties prop;
	FileInputStream inputFile = null;
	
	public configReader(String filepath) throws Exception
	{
		try{
			inputFile = new FileInputStream(filepath);	
		
			// load the property file
			prop = new Properties();
			prop.load(inputFile);
		}catch(FileNotFoundException fe){}
	}
	
	public String getValue(String key){
		return prop.getProperty(key);
	}
	
	public String getValue(String key, int index){

		String newKey = prop.getProperty(key);
		String strArr[] = newKey.split(":");
		
		if(index > strArr.length){
			return "Incorrect index value. Please try again !";
		}else{
			return strArr[index-1];
		}
	}
}
