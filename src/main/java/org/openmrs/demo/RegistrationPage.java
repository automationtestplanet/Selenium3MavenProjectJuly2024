package org.openmrs.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage {

	WebDriver driver;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean verifyRegisterPatientPage(String pageName) {
		return driver.findElement(By.xpath("//h2[normalize-space(text())='" + pageName + "']")).isDisplayed();
	}

	public void setPatientName(String patientName) {
		String[] patientNameArr = patientName.split(",");
		driver.findElement(By.name("givenName")).sendKeys(patientNameArr[0].trim());
		driver.findElement(By.name("familyName")).sendKeys(patientNameArr[1].trim());
	}

	public void clickNextButton() {
		driver.findElement(By.id("next-button")).click();
	}

	public void selectGenderByVisibleText(String genderText) {
		Select genderDropdown = new Select(driver.findElement(By.id("gender-field")));
		genderDropdown.selectByVisibleText("Male");

//		List<WebElement> allOptions = genderDropdown.getOptions();
//		for (WebElement eachOption : allOptions)
//			System.out.println(eachOption.getText());
//		System.out.println(genderDropdown.getFirstSelectedOption().getText());
	}

	public void selectGenderByValue(String genderText) {
		Select genderDropdown = new Select(driver.findElement(By.id("gender-field")));
		genderDropdown.selectByValue("M");
	}

	public void selectGenderByIndex(int index) {
		Select genderDropdown = new Select(driver.findElement(By.id("gender-field")));
		genderDropdown.selectByIndex(0);
	}

	public void setDateOfBirth(String dateOfBirth) {
		String[] dateOfBirthArr = dateOfBirth.split(",");

		driver.findElement(By.id("birthdateDay-field")).sendKeys(dateOfBirthArr[0].trim());
		Select monthDropdown = new Select(driver.findElement(By.id("birthdateMonth-field")));
		monthDropdown.selectByVisibleText(dateOfBirthArr[1].trim());
		driver.findElement(By.id("birthdateYear-field")).sendKeys(dateOfBirthArr[2].trim());
	}

	public void setAddress(String streetAndLocality, String city, String state, String country, String pinCode) {
		driver.findElement(By.id("address1")).sendKeys(streetAndLocality);
		driver.findElement(By.id("cityVillage")).sendKeys(city);
		driver.findElement(By.id("stateProvince")).sendKeys(state);
		driver.findElement(By.id("country")).sendKeys(country);
		driver.findElement(By.id("postalCode")).sendKeys(pinCode);
	}

	public void setPhoneNumber(String phoneNumer) {
		driver.findElement(By.name("phoneNumber")).sendKeys(phoneNumer);
	}

	public boolean verfyConfirmPage(String name, String gender, String dateOfBirth, String city, String phoneNumber) {
		String actualName = driver.findElement(By.xpath("//span[text()='Name: ']//parent::p")).getText().trim();
		String actualGender = driver.findElement(By.xpath("//span[text()='Gender: ']//parent::p")).getText().trim();
		String actualBirthDate = driver.findElement(By.xpath("//span[text()='Birthdate: ']//parent::p")).getText()
				.trim();
		String actualAddress = driver.findElement(By.xpath("//span[text()='Address: ']//parent::p")).getText().trim();
		String actualPhoneNumber = driver.findElement(By.xpath("//span[text()='Phone Number: ']//parent::p")).getText()
				.trim();

		return actualName.contains(name) && actualGender.contains(gender) && actualBirthDate.contains(dateOfBirth)
				&& actualAddress.contains(city) && actualPhoneNumber.contains(phoneNumber);
	}

	public void clickConfirm() {
		driver.findElement(By.cssSelector("input[value='Confirm']")).click();
	}

	public void clickCancel() {
		driver.findElement(By.cssSelector("input[value='Cancel']")).click();
	}
}
