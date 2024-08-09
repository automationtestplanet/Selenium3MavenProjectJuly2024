package org.openmrs.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FindPatientPage extends BasePage {

	public FindPatientPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "patient-search")
	WebElement serarchFieldElement;

	@FindBy(xpath = "//table[@id='patient-search-results-table']//thead/tr/th/div")
	List<WebElement> allFindPatinetColumnNameElements;

	@FindBy(xpath = "//table[@id='patient-search-results-table']//tbody/tr[1")
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
		getFindPatientTableFirstRecordElement().click();
	}

}
