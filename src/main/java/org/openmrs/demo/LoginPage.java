package org.openmrs.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "username")
	private WebElement userNameElement;

	@FindBy(id = "password")
	private WebElement passwordElement;

	@FindBy(css = "input[value='Log In']")
	private WebElement loginButtonElement;

	public WebElement getUserNameElement() {
		return userNameElement;
	}

	public WebElement getPasswordElement() {
		return passwordElement;
	}

	public WebElement getLoginButtonElement() {
		return loginButtonElement;
	}

	public void setUserName(String userName) {
		getUserNameElement().sendKeys(userName);
	}

	public void setPassword(String password) {
		getPasswordElement().sendKeys(password);
	}

	public void clickModule(String moduleName) {

		try {
			WebElement moduleElement = driver.findElement(By.id(moduleName));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true)", moduleElement);
			Actions actions = new Actions(driver);
//			moduleElement.click();
			actions.moveToElement(moduleElement).click(moduleElement).build().perform();
		} catch (Exception e) {
			System.out.println("Exception Occured while clicking ModuleName: " + e.getMessage());
		}
	}

	public void clickLoginButton() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true)", getLoginButtonElement());
//			getLoginButtonElement().click();
			Actions actions = new Actions(driver);
//			moduleElement.click();
			actions.moveToElement(getLoginButtonElement()).click(getLoginButtonElement()).build().perform();
		} catch (Exception e) {
			System.out.println("Exception Occured While clicking login: " + e.getMessage());
		}
	}

	public void loginToOpenMrs(String userName, String password, String moduleName) {
//		driver.findElement(By.id("username")).sendKeys(userName);
//		driver.findElement(By.id("password")).sendKeys(password);
//		driver.findElement(By.id(moduleName)).click();
//		driver.findElement(By.cssSelector("input[value='Log In']")).click();

		setUserName(userName);
		setPassword(password);
		clickModule(moduleName);
		clickLoginButton();
	}
}
