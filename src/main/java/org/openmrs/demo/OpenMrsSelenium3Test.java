package org.openmrs.demo;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OpenMrsSelenium3Test {

	public static WebDriver driver;
	static LoginPage loginPage;
	static HomePage homePage;
	static RegistrationPage registrationPage;
	static PatientDetailsPage patientDeailsPage;
	static FindPatientPage findPatientPage;
	static String patientId;

	public static void navigateToOpenMrsApplication(String url) {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//src//main//resources//DriverFiles//chrome//127//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(url);
	}

	public static void registerPatient(String name, String gender, String dateOfBirth, String streetAndLocality,
			String city, String state, String country, String pinCode, String phoneNumber) {
		System.out.println("--------------------------------Register Patient--------------------------");
		if (homePage.verifyTile("Register a patient")) {
			homePage.clickTile("Register a patient");
			if (registrationPage.verifyRegisterPatientPage("Register a patient")) {
				registrationPage.setPatientName(name);
				registrationPage.clickNextButton();

				registrationPage.selectGenderByVisibleText(gender);
				registrationPage.clickNextButton();

				registrationPage.setDateOfBirth(dateOfBirth);
				registrationPage.clickNextButton();

				registrationPage.setAddress(streetAndLocality, city, state, country, pinCode);
				registrationPage.clickNextButton();

				registrationPage.setPhoneNumber(phoneNumber);
				registrationPage.clickNextButton();
				registrationPage.clickNextButton();

				if (registrationPage.verfyConfirmPage(name, gender, dateOfBirth, city, phoneNumber)) {
					registrationPage.clickConfirm();
					if (patientDeailsPage.verifyRegisteredPatientDetails("Ganesh")) {
						patientId = patientDeailsPage.getPatientId();
						System.out.println(patientId);
						Commons.setPropertyInTestProperties("patient.id", patientId);
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
	}

	public static void findPatient() {
		System.out.println("--------------------------------Find Patient--------------------------");
		homePage.clickHomeButton();
		if (homePage.verifyTile("Find Patient Record")) {
			homePage.clickTile("Find Patient Record");
			if (findPatientPage.verifyFindPatientPage("Find Patient Record")) {
				findPatientPage.setPatientIdInSearchFiled(Commons.getPropertyInTestProperties("patient.id"));
				String searchedPatientID = findPatientPage.getFindPatientTableColumnValue("Identifier");
				if (searchedPatientID.contains(Commons.getPropertyInTestProperties("patient.id"))) {
					findPatientPage.clickSearchedRecord();
					String actualPatientId = patientDeailsPage.getPatientId();
					if (actualPatientId.equals(Commons.getPropertyInTestProperties("patient.id"))) {
						System.out.println("Patient Details are displayig properly");
					} else {
						System.out.println("Patient Details are incorrect");
					}
				} else {
					System.out.println("Searched record is displaying wrong");
				}
			} else {
				System.out.println("Find Patient page is not displayed");
			}
		} else {
			System.out.println("Find Paatient Record Tile is not available");
		}
	}

	public static void activateVisitsAndAddAttachments(String filePath, String caption) {
		System.out.println(
				"--------------------------------Active Visits and Add Attachments --------------------------");
		homePage.clickHomeButton();
		homePage.clickTile("Find Patient Record");
		findPatientPage.setPatientIdInSearchFiled(Commons.getPropertyInTestProperties("patient.id"));
		findPatientPage.clickSearchedRecord();
		patientDeailsPage.clickActiveVisits();
		patientDeailsPage.clickActiveVisitsConfirmButton();
		if (patientDeailsPage.verifyEndVisitsLink()) {
			System.out.println("Start Visits activated");
			patientDeailsPage.clickAttachmentsLink();
			if (patientDeailsPage.verifyClickDropButton()) {
				System.out.println("Click Drop button is displayed");
				patientDeailsPage.clickDropButton();
				patientDeailsPage.uploadFileFromWindowPopup(filePath);
				patientDeailsPage.setCaption(caption);
				patientDeailsPage.clickUploadButton();
				if (patientDeailsPage.verifyUploadedFile(caption)) {
					System.out.println("File Upload Successfull");
				} else {
					System.out.println("File Upload Failed");
				}
			} else {
				System.out.println("Click Drop button is not displayed");
			}
		} else {
			System.out.println("Start Visits not activated");
		}
	}

	public static void deletePatient() {
		System.out.println("--------------------------------Delete Patient --------------------------");
		homePage.clickHomeButton();
		homePage.clickTile("Find Patient Record");
		findPatientPage.setPatientIdInSearchFiled(Commons.getPropertyInTestProperties("patient.id"));
		findPatientPage.clickSearchedRecord();
		patientDeailsPage.clickDeletePatientLink();
		patientDeailsPage.setDeleteReason("Other");
		patientDeailsPage.clickDeletePatientCofirmButton();
		findPatientPage.setPatientIdInSearchFiled(Commons.getPropertyInTestProperties("patient.id"));
		if (patientDeailsPage.verifyErrorMessage()) {
			System.out.println("Patient Record deleted successfully");
		} else {
			System.out.println("Patient Record is not deleted");
		}
	}

	public static void main(String[] args) {
		navigateToOpenMrsApplication("https://demo.openmrs.org/openmrs/login.htm");

		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		registrationPage = new RegistrationPage(driver);
		patientDeailsPage = new PatientDetailsPage(driver);
		findPatientPage = new FindPatientPage(driver);

		loginPage.loginToOpenMrs("Admin", "Admin123", "Registration Desk");
		if (homePage.verfyLogin("Home")) {
			System.out.println("Login Success");
			registerPatient("Ganesh, M", "Male", "1,January,1990", "Do.No - 8/168, S R Nagar", "Hyderabad", "Telangana",
					"India", "500038", "9876543210");
			findPatient();
			activateVisitsAndAddAttachments("UploadFile.pdf", "File1");
			deletePatient();

		} else {
			System.out.println("Login Failed");
		}

//		driver.close();

	}

}
