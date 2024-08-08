package org.openmrs.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisteredDetailsPage extends BasePage {
	
	public RegisteredDetailsPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean verifyRegisteredPatientDetails(String name) {
		return driver
				.findElement(By
						.xpath("//em[text()='Given']//preceding-sibling::span[normalize-space(text())='" + name + "']"))
				.isDisplayed();
	}

	public String getPatientId() {
		return driver.findElement(By.xpath("//em[text()='Patient ID']//following-sibling::span")).getText().trim();
	}
}
