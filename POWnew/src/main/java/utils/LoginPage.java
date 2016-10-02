package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	WebDriver driver;
	
	By titleLogin = By.className("form-title font-green");
	By inputEmail = By.id("email");
	By inputPassword = By.id("password");
	By btnLogin = By.id("loginbutton");
	By checkRemeber = By.id("remember");
	By linkForgotPassword = By.id("forget-password");
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void enterUsername(String uname){
		driver.findElement(inputEmail).sendKeys(uname);  
	}
	
	public void enterPassword(String pass){
		driver.findElement(inputPassword).sendKeys(pass);
	}
	
	public void clickLogin(){
		driver.findElement(btnLogin).click();
	}

	public void checkRemember(String checkStatus){
		if(checkStatus.equalsIgnoreCase("Yes") && !driver.findElement(checkRemeber).isSelected())
			driver.findElement(checkRemeber).click();
	}
	
	public void clickForgotPassword(){
		driver.findElement(linkForgotPassword).click();
	}
	
	public void logintoAdmin(){
	
		
		
	}
	
	}
