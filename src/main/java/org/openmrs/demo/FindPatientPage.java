package org.openmrs.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindPatientPage extends BasePage {

	public FindPatientPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "patient-search")
	WebElement serarchFieldElement;

	@FindBy(xpath = "//table[@id='patient-search-results-table']//thead/tr/th/div")
	List<WebElement> allFindPatinetColumnNameElements;

	@FindBy(xpath = "//table[@id='patient-search-results-table']//tbody/tr[1]")
	WebElement findPatientTableFirstRecord;

	public boolean verifyFindPatientPage(String pageName) {
		return driver.findElement(By.xpath("//h2[normalize-space(text())='" + pageName + "']")).isDisplayed();
	}

	public WebElement getSearchFieldElement() {
		return serarchFieldElement;
	}

	public void setPatientIdInSearchFiled(String patientId) {
		getSearchFieldElement().sendKeys(patientId);
	}

	public List<WebElement> getAllFindPatinetColumnNameElements() {
		return allFindPatinetColumnNameElements;
	}

	public Map<String, Integer> getFidnPatientTablelColumnNamesMap() {
//		List<WebElement> allFindPatinetColumnNameElements= driver.findElements(By.xpath("//table[@id='patient-search-results-table']//thead/tr/th/div"));
		Map<String, Integer> allColumnNamesMap = new HashMap<>();
		int i = 1;
		for (WebElement eachElement : getAllFindPatinetColumnNameElements()) {
			allColumnNamesMap.put(eachElement.getText().trim(), i);
			i++;
		}
		return allColumnNamesMap;
	}

	public int getFindPatientTableColumnNameIndex(String columnName) {
		return getFidnPatientTablelColumnNamesMap().get(columnName);
	}

	public String getFindPatientTableColumnValue(String columnName) {
		int index = getFindPatientTableColumnNameIndex(columnName);
		return driver.findElement(By.xpath("//table[@id='patient-search-results-table']//tbody/tr/td[" + index + "]"))
				.getText().trim();
	}

	public WebElement getFindPatientTableFirstRecordElement() {
		return findPatientTableFirstRecord;
	}

	public void clickSearchedRecord() {
		try {
//		getFindPatientTableFirstRecordElement().click();

//			WebDriverWait wait = new WebDriverWait(driver, 20);
//			wait.until(ExpectedConditions.visibilityOf(getFindPatientTableFirstRecordElement()));   // 500 milliseconds
//		wait.until(ExpectedConditions.elementToBeClickable(getFindPatientTableFirstRecordElement()));
//		wait.until(ExpectedConditions.stalenessOf(getFindPatientTableFirstRecordElement()));
			
			
			
			FluentWait wait  = new FluentWait(driver);
			wait.withTimeout(20, TimeUnit.SECONDS).pollingEvery(5000, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);			
			Thread.sleep(5000);

			Actions actions = new Actions(driver);
//		Action action = actions.moveToElement(getFindPatientTableFirstRecordElement()).click(getFindPatientTableFirstRecordElement()).build();
//		action.perform();		
//		actions.moveToElement(getFindPatientTableFirstRecordElement()).click(getFindPatientTableFirstRecordElement()).build().perform();
//			getFindPatientTableFirstRecordElement().click();
			
//			WebElement searchedRecord = (WebElement)wait.until(new Function<WebDriver, WebElement>() {
//				@Override
//				public WebElement apply(WebDriver t) {					
//					return getFindPatientTableFirstRecordElement();
//				}
//			});			
			WebElement searchedRecord = (WebElement) wait.until(driver -> getFindPatientTableFirstRecordElement());
			
			searchedRecord.click();
			
		} catch (Exception e) {
			System.out.println("Exception Occuered during click operation on searched record: " + e.getMessage());
		}

	}

}
