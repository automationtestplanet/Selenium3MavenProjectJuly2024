package org.openmrs.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void loginToOpeneMrs(String userName, String password, String moduleName) {
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id(moduleName)).click();
		driver.findElement(By.cssSelector("input[value='Log In']")).click();
	}
}
