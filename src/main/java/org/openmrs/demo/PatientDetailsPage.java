package org.openmrs.demo;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.FluentWait;

public class PatientDetailsPage extends BasePage {

	public PatientDetailsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[contains(text(),'Start Visit')]//ancestor::a")
	WebElement activeVistsLinkElement;

	@FindBy(xpath = "//h3[normalize-space(text())='Start a visit']//ancestor::div[contains(@id,'quick-visit-creation-dialog')]//button[normalize-space(text())='Confirm']")
	WebElement activeVisitConfirmButtonElement;

//	@FindBys({
//		@FindBy(xpath = "//div[@class='action-section']//h3[text()='Current Visit Actions']//following-sibling::li//div[contains(text(),'End Visit')]//ancestor::a[contains(@id,'endVisit')]"),
//		@FindBy(xpath = "//div[@class='visit-actions active-visit']/a[contains(@href,'EndVisit')]")
//	})
	@FindBy(xpath = "//div[@class='action-section']//h3[text()='Current Visit Actions']//following-sibling::li//div[contains(text(),'End Visit')]//ancestor::a[contains(@id,'endVisit')]|//div[@class='visit-actions active-visit']/a[contains(@href,'EndVisit')]")
	WebElement endVisitsLinkElement;

	
//	@FindBys({
//	@FindBy(xpath = "//div[@class='action-section']//h3[text()='Current Visit Actions']//following-sibling::li//div[contains(text(),'Attachments')]//ancestor::a"),
//	@FindBy(xpath = "//div[@class='visit-actions active-visit']/a[contains(@id,'attachments')]")
//	})
	@FindBy(xpath = "//div[@class='action-section']//h3[text()='Current Visit Actions']//following-sibling::li//div[contains(text(),'Attachments')]//ancestor::a|//div[@class='visit-actions active-visit']/a[contains(@id,'attachments')]")
	WebElement attachmentsLinkElement;

	@FindBy(xpath = "//div[text()='Click or drop a file here.']")
	WebElement clickDropElement;

	@FindBy(xpath = "//textarea[@placeholder='Enter a caption']")
	WebElement captionElement;

	@FindBy(xpath = "//button[text()='Upload file']")
	WebElement uploadButtonElement;

	@FindBy(xpath = "//div[contains(text(),'Delete Patient')]//ancestor::a")
	WebElement deletePatientLink;

	@FindBy(id = "delete-reason")
	WebElement deleteReasonInputElement;

	@FindBy(xpath = "//h3[contains(text(),'Delete Patient')]//ancestor::div[contains(@id,'delete-patient-creation-dialog')]//button[normalize-space(text())='Confirm']")
	WebElement deletePatientConfirmButtonElement;

	@FindBy(xpath = "//td[text()='No matching records found']")
	WebElement errorMessageElement;

	public boolean verifyRegisteredPatientDetails(String name) {
		return driver
				.findElement(By
						.xpath("//em[text()='Given']//preceding-sibling::span[normalize-space(text())='" + name + "']"))
				.isDisplayed();
	}

	public String getPatientId() {
		return driver.findElement(By.xpath("//em[text()='Patient ID']//following-sibling::span")).getText().trim();
	}

	public WebElement getActiveVistsLinkElement() {
		return activeVistsLinkElement;
	}

	public void clickActiveVisits() {
		getActiveVistsLinkElement().click();
	}

	public WebElement getActiveVistsConfirmButtonElement() {
		return activeVisitConfirmButtonElement;
	}

	public void clickActiveVisitsConfirmButton() {
		getActiveVistsConfirmButtonElement().click();
	}

	public WebElement getEndVistsLinkElement() {
		return endVisitsLinkElement;
	}

	public boolean verifyEndVisitsLink() {
		return getEndVistsLinkElement().isDisplayed();
	}

	public WebElement getAttachmentsLinkElement() {
		return attachmentsLinkElement;
	}

	public void clickAttachmentsLink() {
		getAttachmentsLinkElement().click();
	}

	public WebElement getClickDropElement() {
		return clickDropElement;
	}

	public boolean verifyClickDropButton() {
		return getClickDropElement().isDisplayed();
	}

	public void clickDropButton() {
		getClickDropElement().click();
	}

	public void uploadFileFromWindowPopup(String filePath) {
		try {
			Robot robot = new Robot();

			StringSelection stringSeletion = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSeletion, null);
			Thread.sleep(5000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);

			Thread.sleep(5000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(5000);

		} catch (Exception e) {
			System.out.println("Exception Occureed duiring file upload: " + e.getMessage());
		}
	}

	public WebElement getCaptionElement() {
		return captionElement;
	}

	public void setCaption(String caption) {
		getCaptionElement().sendKeys(caption);
	}

	public WebElement getUploadButtoElement() {
		return uploadButtonElement;
	}

	public void clickUploadButton() {
		try {
			getUploadButtoElement().click();
			Thread.sleep(10000);
		} catch (Exception e) {
			System.out.println("Exception Occured while file Upload: " + e.getMessage());

		}
	}

	public boolean verifyUploadedFile(String fileCaption) {
		return driver
				.findElement(By.xpath("//p[text()='" + fileCaption
						+ "']//ancestor::div//preceding-sibling::div[contains(@class,'att_thumbnail-container')]"))
				.isDisplayed();
	}

	public WebElement getDeletePatientLinkElement() {
		return deletePatientLink;
	}

	public void clickDeletePatientLink() {
		getDeletePatientLinkElement().click();
	}

	public WebElement getDeleteReasonInputElement() {
		return deleteReasonInputElement;
	}

	public void setDeleteReason(String reason) {
		getDeleteReasonInputElement().sendKeys(reason);
	}

	public WebElement getDeletePatientConfirmuttonElement() {
		return deletePatientConfirmButtonElement;
	}

	public void clickDeletePatientCofirmButton() {
		getDeletePatientConfirmuttonElement().click();
	}

	public WebElement getErrorMessageElement() {
		return errorMessageElement;
	}

	public boolean verifyErrorMessage() {
		try {
			FluentWait wait = new FluentWait(driver);
			wait.withTimeout(20, TimeUnit.SECONDS).pollingEvery(5000, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);
			Thread.sleep(5000);
			WebElement searchedRecord = (WebElement) wait.until(driver -> getErrorMessageElement());
			return searchedRecord.isDisplayed();
		} catch (Exception e) {
			System.out.println("Exception Occured while verifying the error message: " + e.getMessage());
			return false;
		}
	}

}
