package org.openmrs.demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WelcomePageTest {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//src//main//resources//DriverFiles//chrome//127//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("C://Users//RAJU CHELLE//Desktop//Welcome.html");
		
//		WebElement element1= driver.findElement(null);
//		List <WebElement> elements= driver.findElements(null);
		
//		WebElement element1= driver.findElement(By.tagName("textarea"));		
//		element1.sendKeys("Hello thi is Automation Test planet provides trainings on Automation test tools");
		
		driver.findElement(By.tagName("textarea")).sendKeys("Hello thi is Automation Test planet provides trainings on Automation test tools");
		
		driver.findElement(By.id("userName")).sendKeys("Admin");
		driver.findElement(By.id("userName")).clear();
		driver.findElement(By.id("userName")).sendKeys("Admin123");
		
		
		driver.findElement(By.name("Password")).sendKeys("Admin123");		
		driver.findElement(By.className("checkbox")).click();
		
		
		driver.findElement(By.linkText("OpenMRS in New Tab")).click();
		
		
		driver.findElement(By.partialLinkText("Seperate Window")).click();
		
		
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getPageSource()); 
		
		
		System.out.println(driver.findElement(By.id("userName")).isEnabled());	
		
		System.out.println(driver.findElement(By.name("Password")).isDisplayed());
		
		System.out.println(driver.findElement(By.className("checkbox")).isEnabled());		
		
		System.out.println(driver.findElement(By.partialLinkText("Raju Chelle")).getText());
		
		System.out.println(driver.findElement(By.id("userName")).getAttribute("placeholder"));
		
		System.out.println(driver.findElement(By.tagName("textarea")).getAttribute("value"));
		
		System.out.println(driver.findElement(By.tagName("textarea")).getCssValue("background-color"));	
		
		System.out.println(driver.findElement(By.tagName("textarea")).getLocation().getX());
		System.out.println(driver.findElement(By.tagName("textarea")).getLocation().getY());
		
		
		
		
		
		
	}
}
