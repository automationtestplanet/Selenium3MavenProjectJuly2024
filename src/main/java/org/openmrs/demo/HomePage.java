package org.openmrs.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean verfyLogin(String pageName) {
		return driver.getTitle().trim().equalsIgnoreCase("Home")
				&& driver.findElement(By.partialLinkText("Logout")).isDisplayed();
	}

	public boolean verifyTile(String tileName) {
		return driver.findElement(By.partialLinkText(tileName)).isDisplayed();
	}

	public void clickTile(String tileName) {
		driver.findElement(By.partialLinkText(tileName)).click();
	}
}
