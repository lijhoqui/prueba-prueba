package com.suraam.accepttest.steps;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.suraam.accepttest.utilities.Utilities;

import cucumber.api.java.en.Given;

public class ScrollSteps {
	
	private WebDriver driver;
	private Utilities utilities;

	public ScrollSteps(CucumberEnvironment environment) {
		this.driver = environment.getDriver();
		this.utilities = environment.getUtilities();
	}
	
	@Given("^Hago scroll al elemento \"([^\"]*)\"$")
	public void hagoScrollAlElemento(String elementId) {
		WebElement element = utilities.getElementById(driver, elementId);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	@Given("^Hago scroll al xpath \"([^\"]*)\"$")
	public void hagoScrollAlXpath(String xpath) {
		WebElement element = utilities.getElementByXPath(driver, xpath);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
}
