package org.openmrs.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class Commons extends BasePage {

	public Commons(WebDriver driver) {
		super(driver);
	}

	public static Properties testProperties = new Properties();
	static String testFilePath = System.getProperty("user.dir")+"//src//main//resources//test.properties";

	public static void setPropertyInTestProperties(String propertyName, String propertyValue) {
		try {
			FileInputStream testFileInputStream = new FileInputStream(new File(testFilePath));
			testProperties.load(testFileInputStream);
			testProperties.setProperty(propertyName, propertyValue);			
			FileOutputStream testOutputStream = new FileOutputStream(new File(testFilePath));
			testProperties.store(testOutputStream, "Updated by Raju Chelle");
		} catch (Exception e) {
			System.out.println("Exception Occured while setting the property to Test Properties: "+ e.getCause());
		}
	}

	public static String getPropertyInTestProperties(String propertyName) {
		String propertyValue = null;
		try {
			FileInputStream testFileInputStream = new FileInputStream(new File(testFilePath));
			testProperties.load(testFileInputStream);
			
			propertyValue = testProperties.getProperty(propertyName);
		} catch (Exception e) {
			System.out.println("Exception Occured while getting the property form Test Properties: "+ e.getCause());
		}
		
		return propertyValue;
	}

}
