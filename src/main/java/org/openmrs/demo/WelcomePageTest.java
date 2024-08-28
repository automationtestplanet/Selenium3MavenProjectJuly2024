package org.openmrs.demo;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WelcomePageTest {

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "//src//main//resources//DriverFiles//chrome//127//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(System.getProperty("user.dir") + "//src//main//resources//html//Welcome.html");

//		WebElement element1= driver.findElement(null);
//		List <WebElement> elements= driver.findElements(null);

//		WebElement element1= driver.findElement(By.tagName("textarea"));		
//		element1.sendKeys("Hello thi is Automation Test planet provides trainings on Automation test tools");

		driver.findElement(By.tagName("textarea"))
				.sendKeys("Hello thi is Automation Test planet provides trainings on Automation test tools");

		driver.findElement(By.id("userName")).sendKeys("Admin");
		driver.findElement(By.id("userName")).clear();
		driver.findElement(By.id("userName")).sendKeys("Admin123");

		driver.findElement(By.name("Password")).sendKeys("Admin123");
		driver.findElement(By.className("checkbox")).click();

//		driver.findElement(By.linkText("OpenMRS in New Tab")).click();

//		driver.findElement(By.partialLinkText("Seperate Window")).click();

		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getPageSource());

		System.out.println(driver.findElement(By.id("userName")).isEnabled());

		System.out.println(driver.findElement(By.name("Password")).isDisplayed());

		System.out.println(driver.findElement(By.className("checkbox")).isSelected());

		System.out.println(driver.findElement(By.partialLinkText("Raju Chelle")).getText());

		System.out.println(driver.findElement(By.id("userName")).getAttribute("placeholder"));

		System.out.println(driver.findElement(By.tagName("textarea")).getAttribute("value"));

		System.out.println(driver.findElement(By.tagName("textarea")).getLocation().getX());
		System.out.println(driver.findElement(By.tagName("textarea")).getLocation().getY());

		System.out.println(driver.findElement(By.tagName("textarea")).getCssValue("background-color"));

		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = new HomePage(driver);

		String defaultWinId = driver.getWindowHandle();
		System.out.println(defaultWinId);
		driver.findElement(By.linkText("OpenMRS in New Tab")).click();

		Set<String> allWinIds = driver.getWindowHandles();

		System.out.println(allWinIds);

		for (String eachWinId : allWinIds) {
			if (!eachWinId.equals(defaultWinId)) {
				driver.switchTo().window(eachWinId);
				loginPage.loginToOpenMrs("Admin", "Admin123", "Registration Desk");
				Thread.sleep(5000);
				homePage.clickLogout();
				driver.close();
				driver.switchTo().window(defaultWinId);
			}
		}

		driver.findElement(By.linkText("OpenMRS in Seperate Window")).click();
		allWinIds = driver.getWindowHandles();

		System.out.println(allWinIds);

		for (String eachWinId : allWinIds) {
			if (!eachWinId.equals(defaultWinId)) {
				driver.switchTo().window(eachWinId);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				loginPage.loginToOpenMrs("Admin", "Admin123", "Registration Desk");
				Thread.sleep(5000);
				homePage.clickLogout();
				driver.close();
				driver.switchTo().window(defaultWinId);
			}
		}
		
		driver.switchTo().frame("frame1");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		loginPage.loginToOpenMrs("Admin", "Admin123", "Registration Desk");
		Thread.sleep(5000);
		driver.switchTo().defaultContent();
		driver.findElement(By.linkText("OpenMRS in New Tab")).click();

	}
}
