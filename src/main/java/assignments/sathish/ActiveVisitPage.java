package assignments.sathish;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ActiveVisitPage extends BasePage {

	public ActiveVisitPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	public boolean verifyActiveVisitPage() {
		return driver.findElement(By.xpath("//h3[contains(text(),'Active Visits')]")).isDisplayed();
	}

	@FindBy(css = "input[type='search']")
	private WebElement enterPatientDetailsElement;

	public void setSearchPatientDetails(String enterPatientNameID) {
		enterPatientDetailsElement.sendKeys(enterPatientNameID);
	}

	public void clickPatientProfileDetails(String name) {
		driver.findElement(By.xpath("//a[contains(text(),'" + name + "')]")).click();
	}

	public boolean verifypatientdetailsInActiveVisitPage() {
		return driver.findElement(By.xpath("//em[contains(text(),'Given')]//preceding-sibling::span")).isDisplayed();
	}

	public void searchPatientDetails(String enterPatientNameID) {
		setSearchPatientDetails(enterPatientNameID);

	}

}
