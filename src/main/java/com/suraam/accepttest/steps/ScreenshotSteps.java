package com.suraam.accepttest.steps;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Then;

public class ScreenshotSteps {

	private WebDriver driver;
	private CucumberEnvironment environment;

	public ScreenshotSteps(CucumberEnvironment environment) {
		this.environment = environment;
		this.driver = environment.getDriver();
	}
	
	@Then("^Tomo un screenshot$")
	public void tomoUnScreenshot() {
		byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		environment.getScenario().embed(screenshot, "image/png");
	}
}
