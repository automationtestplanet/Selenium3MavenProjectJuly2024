package assignments.sathish;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
	public HomePage(WebDriver driver) {
		super(driver);
	}

	public boolean verifyLoginPage(String pageTitle) {
		return driver.getTitle().equalsIgnoreCase(pageTitle)
				&& driver.findElement(By.partialLinkText("Logout")).isDisplayed();
	}

	public void selectModule(String enterModuleName) {
		driver.findElement(By.partialLinkText(enterModuleName)).click();
	}
}
