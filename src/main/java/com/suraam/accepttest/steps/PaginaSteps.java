package com.suraam.accepttest.steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PaginaSteps {

	private WebDriver driver;
	
	public PaginaSteps(CucumberEnvironment environment) {
		this.driver = environment.getDriver();
	}
	
	@Given("^Estoy en la pagina \"([^\"]*)\"$")
	public void estoyEnLaUrl(String url) {
		irALaUrl(url);
	}

	@When("^Voy a la pagina \"([^\"]*)\"$")
	public void voyALaUrl(String url) {
		irALaUrl(url);
	}
	
	@Then("^Debo estar en la pagina \"([^\"]*)\"$")
	public void deboEstarEnLaPagina(String url) {
		String paginaActual = driver.getCurrentUrl();
		Assert.assertEquals(url, paginaActual);
	}

	@Then("^Debo estar en la pagina que empieza por \"([^\"]*)\"$")
	public void deboEstarEnLaPaginaQueEmpiezaPor(String url) {
		String paginaActual = driver.getCurrentUrl();
		Assert.assertTrue(paginaActual.startsWith(url));
	}
	
	private void irALaUrl(String url) {
		driver.get(url);
	}
}
