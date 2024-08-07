package org.openmrs.demo;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OpenMrsSelenium3Test {

	public static WebDriver driver;

	public static void navigateToOpenMrsApplication(String url) {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//src//main//resources//DriverFiles//chrome//127//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(url);
	}

	public static void main(String[] args) {

		navigateToOpenMrsApplication("https://demo.openmrs.org/openmrs/login.htm");

		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = new HomePage(driver);
		RegistrationPage registrationPage = new RegistrationPage(driver);
		RegisteredDetailsPage registeredDetilsPage = new RegisteredDetailsPage(driver);

		loginPage.loginToOpeneMrs("Admin", "Admin123", "Registration Desk");
		if (homePage.verfyLogin("Home")) {
			System.out.println("Login Success");
			System.out.println("--------------------------------Register Patient--------------------------");
			if (homePage.verifyTile("Register a patient")) {
				homePage.clickTile("Register a patient");
				if (registrationPage.verifyRegisterPatientPage("Register a patient")) {
					registrationPage.setPatientName("Ganesh, M");
					registrationPage.clickNextButton();

					registrationPage.selectGenderByVisibleText("Male");
					registrationPage.clickNextButton();

					registrationPage.setDateOfBirth("1,January,1990");
					registrationPage.clickNextButton();

					registrationPage.setAddress("Do.No - 8/168, S R Nagar", "Hyderabad", "Telangana", "India",
							"500038");
					registrationPage.clickNextButton();

					registrationPage.setPhoneNumber("9876543210");
					registrationPage.clickNextButton();
					registrationPage.clickNextButton();

					if (registrationPage.verfyConfirmPage("Ganesh", "Male", "1990", "Hyderabad", "9876543210")) {
						registrationPage.clickConfirm();
						if (registeredDetilsPage.verifyRegisteredPatientDetails("Ganesh")) {
							String patientId = registeredDetilsPage.getPatientId();
							System.out.println(patientId);
						}
					} else {
						System.out.println("Register details are not displaying properly, Cancelling the register");
						registrationPage.clickCancel();
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
