package org.openmrs.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = "i[class='icon-home small']")
	WebElement homeButton;
	
	@FindBy(partialLinkText = "Logout")
	WebElement logoutButton;
	
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
	
	public WebElement getHomeButton() {
		return homeButton;
	}
	
	public void clickHomeButton() {
		getHomeButton().click();
	}
	
	public void clickLogout() {
		logoutButton.click();
	}
}
