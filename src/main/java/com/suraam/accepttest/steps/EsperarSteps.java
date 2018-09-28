package com.suraam.accepttest.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EsperarSteps {
	
	private WebDriver driver;

	public EsperarSteps(CucumberEnvironment environment) {
		this.driver = environment.getDriver();
	}
	
	@Then("^Espero por \"([^\"]*)\" segundos$")
	public void esperoPorSegundos(long segundos) throws InterruptedException {
		Thread.sleep(segundos * 1000);
	}
	
	@When("^Espero que desaparezca el cargando \"([^\"]*)\"$")
	public void esperoQueDesaparezcaElCargando(String popUpCargando) {

		WebElement ventana = driver.findElement(By.id(popUpCargando));
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.invisibilityOf(ventana));
	}
}
