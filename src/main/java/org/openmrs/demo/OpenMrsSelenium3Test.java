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

		Commons.setPropertyInTestProperties("patient.id", "12345");

		navigateToOpenMrsApplication("https://demo.openmrs.org/openmrs/login.htm");

		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = new HomePage(driver);
		RegistrationPage registrationPage = new RegistrationPage(driver);
		PatientDetailsPage patientDeailsPage = new PatientDetailsPage(driver);
		FindPatientPage findPatientPage = new FindPatientPage(driver);

		loginPage.loginToOpenMrs("Admin", "Admin123", "Registration Desk");
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
						if (patientDeailsPage.verifyRegisteredPatientDetails("Ganesh")) {
							String patientId = patientDeailsPage.getPatientId();
							System.out.println(patientId);
							Commons.setPropertyInTestProperties("patient.id", patientId);
							System.out
									.println("--------------------------------Find Patient--------------------------");
							homePage.clickHomeButton();
							if (homePage.verifyTile("Find Patient Record")) {
								homePage.clickTile("Find Patient Record");
								if (findPatientPage.verifyFindPatientPage("Find Patient Record")) {
									findPatientPage.setPatientIdInSearchFiled(patientId);
									String searchedPatientID = findPatientPage
											.getFindPatientTableColumnValue("Identifier");
									if (searchedPatientID.contains(patientId)) {
										findPatientPage.clickSearchedRecord();
										String actualPatientId = patientDeailsPage.getPatientId();
										if (actualPatientId.equals(patientId)) {
											System.out.println("Patient Details are displayig properly");

											System.out.println(
													"--------------------------------Active Visits and Add Attachments --------------------------");
											patientDeailsPage.clickActiveVisits();
											patientDeailsPage.clickActiveVisitsConfirmButton();

											if (patientDeailsPage.verifyEndVisitsLink()) {
												System.out.println("Start Visits activated");
												patientDeailsPage.clickAttachmentsLink();

												if (patientDeailsPage.verifyClickDropButton()) {
													System.out.println("Click Drop button is displayed");
													patientDeailsPage.clickDropButton();
													patientDeailsPage.uploadFileFromWindowPopup("UploadFile.pdf");
													patientDeailsPage.setCaption("File1");
													patientDeailsPage.clickUploadButton();

													if (patientDeailsPage.verifyUploadedFile("File1")) {
														System.out.println("File Upload Successfull");
														System.out.println(
																"--------------------------------Delete Patient --------------------------");
														homePage.clickHomeButton();
														homePage.clickTile("Find Patient Record");
														findPatientPage.setPatientIdInSearchFiled(patientId);
														findPatientPage.clickSearchedRecord();
														patientDeailsPage.clickDeletePatientLink();
														patientDeailsPage.setDeleteReason("Other");
														patientDeailsPage.clickDeletePatientCofirmButton();
														findPatientPage.setPatientIdInSearchFiled(patientId);
														if (patientDeailsPage.verifyErrorMessage()) {
															System.out.println("Patient Record deleted successfully");
														} else {
															System.out.println("Patient Record is not deleted");
														}
													} else {
														System.out.println("File Upload Failed");
													}

												} else {
													System.out.println("Click Drop button is not displayed");
												}

											} else {
												System.out.println("Start Visits not activated");
											}

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
