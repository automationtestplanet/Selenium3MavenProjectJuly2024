package org.openmrs.demo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class OpenMrsSelenium3Test {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//src//main//resources//DriverFiles//chrome//127//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get("https://demo.openmrs.org/openmrs/login.htm");
		driver.findElement(By.id("username")).sendKeys("Admin");
		driver.findElement(By.id("password")).sendKeys("Admin123");
		driver.findElement(By.id("Registration Desk")).click();
		driver.findElement(By.cssSelector("input[value='Log In']")).click();

		if (driver.getTitle().trim().equalsIgnoreCase("Home")
				&& driver.findElement(By.partialLinkText("Logout")).isDisplayed()) {
			System.out.println("Login Success");

			System.out.println("--------------------------------Register Patient--------------------------");

			if (driver.findElement(By.partialLinkText("Register a patient")).isDisplayed()) {
				driver.findElement(By.partialLinkText("Register a patient")).click();

				if (driver.findElement(By.xpath("//h2[normalize-space(text())='Register a patient']")).isDisplayed()) {
					driver.findElement(By.name("givenName")).sendKeys("Ganesh");
					driver.findElement(By.name("familyName")).sendKeys("M");
					driver.findElement(By.id("next-button")).click();

					Select genderDropdown = new Select(driver.findElement(By.id("gender-field")));

					genderDropdown.selectByVisibleText("Male");
					genderDropdown.selectByValue("M");
					genderDropdown.selectByIndex(0);

					List<WebElement> allOptions = genderDropdown.getOptions();

					for (WebElement eachOption : allOptions)
						System.out.println(eachOption.getText());

					System.out.println(genderDropdown.getFirstSelectedOption().getText());
					driver.findElement(By.id("next-button")).click();

					driver.findElement(By.id("birthdateDay-field")).sendKeys("1");

					Select monthDropdown = new Select(driver.findElement(By.id("birthdateMonth-field")));
					monthDropdown.selectByVisibleText("January");
					driver.findElement(By.id("birthdateYear-field")).sendKeys("1990");
					driver.findElement(By.id("next-button")).click();

					driver.findElement(By.id("address1")).sendKeys("Do.No - 8/168, S R Nagar");
					driver.findElement(By.id("cityVillage")).sendKeys("Hyderabad");
					driver.findElement(By.id("stateProvince")).sendKeys("Telangana");
					driver.findElement(By.id("country")).sendKeys("India");
					driver.findElement(By.id("postalCode")).sendKeys("500038");
					driver.findElement(By.id("next-button")).click();

					driver.findElement(By.name("phoneNumber")).sendKeys("9876543210");
					driver.findElement(By.id("next-button")).click();
					driver.findElement(By.id("next-button")).click();

					String actualName = driver.findElement(By.xpath("//span[text()='Name: ']//parent::p")).getText()
							.trim();
					String actualGender = driver.findElement(By.xpath("//span[text()='Gender: ']//parent::p")).getText()
							.trim();
					String actualBirthDate = driver.findElement(By.xpath("//span[text()='Birthdate: ']//parent::p"))
							.getText().trim();
					String actualAddress = driver.findElement(By.xpath("//span[text()='Address: ']//parent::p"))
							.getText().trim();
					String actualPhoneNumber = driver
							.findElement(By.xpath("//span[text()='Phone Number: ']//parent::p")).getText().trim();

					if (actualName.contains("Ganesh") && actualGender.contains("Male")
							&& actualBirthDate.contains("1990") && actualAddress.contains("Hyderabad")
							&& actualPhoneNumber.contains("9876543210")) {
							
						driver.findElement(By.cssSelector("input[value='Confirm']")).click();						
						
						if(driver.findElement(By.xpath("//em[text()='Given']//preceding-sibling::span[normalize-space(text())='Ganesh']")).isDisplayed()) {
							String patientId = driver.findElement(By.xpath("//em[text()='Patient ID']//following-sibling::span")).getText().trim();
							System.out.println(patientId);
						}
						
						
					}else {
						System.out.println("Register details are not displaying properly, Cancelling the register");
						driver.findElement(By.cssSelector("input[value='Cancel']")).click();
					}

				} else {
					System.out.println("Register patient Page is not displayed");
				}
			} else {
				System.out.println("Register Patient Tile is not available");
			}
		} else {
			System.out.println("Login Failed");
		}

//		driver.close();

	}

}
