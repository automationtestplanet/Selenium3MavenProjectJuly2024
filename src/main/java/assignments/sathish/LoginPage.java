package assignments.sathish;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(id = "username")
	private WebElement userNameElement;
	@FindBy(id = "password")
	private WebElement passwordElement;
	@FindBy(id = "loginButton")
	private WebElement loginButtonElement;

	public void setUserName(String enterName) {
		userNameElement.sendKeys(enterName);
	}

	public void setPassword(String password) {
		passwordElement.sendKeys(password);
	}

	public void clickModuleName(String clickModuleName) {
		driver.findElement(By.id(clickModuleName)).click();
	}

	public void setLoginButton() {
		loginButtonElement.click();
	}

	public void loginOpenMrsApplication(String enterName, String enterPassword, String enterModuleName) {
		setUserName(enterName);
		setPassword(enterPassword);
		clickModuleName(enterModuleName);
		setLoginButton();
	}

}
