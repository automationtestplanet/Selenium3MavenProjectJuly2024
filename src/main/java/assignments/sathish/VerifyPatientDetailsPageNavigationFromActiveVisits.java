package assignments.sathish;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class VerifyPatientDetailsPageNavigationFromActiveVisits {

	public static WebDriver driver;

	public static void welcometoOpenMrsPage() {
		System.out.println(System.getProperty("user.dir"));
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
				+ "//src//main//resources//driverFiles//chromedriver//127//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.google.com");
		driver.navigate().to("https://demo.openmrs.org/openmrs/login.htm");
	}

	public static void main(String[] args) {
		welcometoOpenMrsPage();
		LoginPage login = new LoginPage(driver);
		login.loginOpenMrsApplication("Admin", "Admin123", "Registration Desk");
		HomePage home = new HomePage(driver);
		ActiveVisitPage visitpage = new ActiveVisitPage(driver);
		if (home.verifyLoginPage("Home")) {
			System.out.println("Home page is Displayed");
			home.selectModule("Active Visits");
			if (visitpage.verifyActiveVisitPage()) {
				System.out.println("ActiveVisit page is Displayed");
				if (visitpage.verifyActiveVisitPage()) {
					System.out.println("ActiveVisit PatientDetails are Displayed");
					visitpage.searchPatientDetails("Satish");
					visitpage.clickPatientProfileDetails("Satish Jeja");
					if (visitpage.verifypatientdetailsInActiveVisitPage()) {
						System.out.println("Patient details are Displayed in ActiveVisit");
						
						
					} else {
						System.out.println("Patient details are not Displayed in ActiveVisit");
					}

				} else {
					System.out.println("ActiveVisit PatientDetails are not Displayed");
				}
			} else {
				System.out.println("ActiveVisit page is not Displayed");
			}
		} else {
			System.out.println("Home page is not Displayed");
		}
	}

}
