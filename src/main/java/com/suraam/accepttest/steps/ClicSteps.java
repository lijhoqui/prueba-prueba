package com.suraam.accepttest.steps;

import com.suraam.accepttest.modelo.Tenant;
import com.suraam.accepttest.utilities.Utilities;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ClicSteps {
	
	private WebDriver driver;
	private Utilities utilities;
	private Properties properties;
	
	
	public ClicSteps(CucumberEnvironment environment) throws IOException {
		this.driver = environment.getDriver();
		this.utilities = environment.getUtilities();
		this.properties = environment.getProperties(Tenant.Chile);
	}
	
	@When("^Hago clic en el vinculo \"([^\"]*)\"$")
	public void hagoClicEnElVinculo(String textoVinculo) throws Exception {
		hacerClicEnLink(textoVinculo);
	}

	@When("^Hago clic en el menu \"([^\"]*)\"$")
	public void hagoClicEnElMenu(String textoMenu) throws Exception {
		hacerClicEnLink(textoMenu);
	}

	@When("^Hago clic en donde estoy$")
	public void hagoClicEnDondeEstoy() {
		Actions actions = new Actions(driver);
		actions.click().build().perform();
	}

	@When("^Hago clic en el boton \"([^\"]*)\"$")
	public void hagoClicEnElBoton(String idBoton) throws Exception {
		WebElement boton = utilities.getElementById(driver, idBoton);
        utilities.clickElement(boton);
	}
	
	@Given("^Hago clic en la pestana \"([^\"]*)\"$")
	public void hagoClicEnLaPestana(String id) throws Exception {
		WebElement btnMenu = utilities.getElementById(driver, id);
        utilities.clickElement(btnMenu);
	}
	
	@Given("^Hago clic en el boton por xpath \"([^\"]*)\"$")
	public void hagoClicEnElBotonPorXpath(String xpath) throws Exception {
		WebElement btnEditar = utilities.getElementByXPath(driver, properties.getProperty(xpath));
        utilities.clickElement(btnEditar);
	}

	@When("^Hago clic en el elemento con xpath \"([^\"]*)\"$")
	public void hagoClicEnElElementoConXpath(String xpath) throws Exception {
        WebElement boton = utilities.getElementByXPath(driver, properties.getProperty(xpath));
        utilities.clickElement(boton);
	}
	
	private void hacerClicEnLink(String textoVinculo) throws Exception {
		WebElement menu = utilities.getElementByLinkText(driver, textoVinculo);
        utilities.clickElement(menu);
	}
}
