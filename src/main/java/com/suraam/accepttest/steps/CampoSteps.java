package com.suraam.accepttest.steps;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.suraam.accepttest.utilities.Utilities;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.suraam.accepttest.modelo.Tenant;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CampoSteps {

    private static final int BY_ID = 0;
    private static final int BY_XPATH = 1;
    private WebDriver driver;
    private Map<String, String> variables;
    private Utilities utilities;
    private Properties properties;

    public CampoSteps(CucumberEnvironment environment) throws IOException {
        this.driver = environment.getDriver();
        this.variables = environment.getVariables();
        this.utilities = environment.getUtilities();
        this.properties = environment.getProperties(Tenant.Chile);
    }

    @When("^Busco en el campo por xpath \"([^\"]*)\" y agrego el valor \"([^\"]*)\" y hago un tab$")
    public void buscoCampoPorXpathEingresoValorYHagoUnTab(String xpath, String texto) {
        ingresarTextoEnCampo(properties.getProperty(xpath), texto, Keys.TAB, BY_XPATH);
    }

    @When("^Busco en el campo por xpath \"([^\"]*)\" y agrego el valor de la variable \"([^\"]*)\" y hago un tab$")
    public void buscoCampoPorXpathYAgregoLaVariableYHagoUnTab(String xpath, String nombreVariable) {
        String texto = variables.get(nombreVariable);
        ingresarTextoEnCampo(properties.getProperty(xpath), texto, Keys.TAB, BY_XPATH);
    }
    
    @When("^Busco el campo por xpath \"([^\"]*)\" y agrego el valor \"([^\"]*)\"$")
    public void buscoCampoPorXpathEingresoValor(String xpath, String texto) {
        ingresarTextoEnCampo(xpath, texto, null, BY_XPATH);
    }
    
    @When("^Busco el campo por xpath \"([^\"]*)\" y selecciono de la lista \"([^\"]*)\" el item \"([^\"]*)\"$")
    public void buscoCampoPorXpathYSeleccionoItem(String xpath, String xpathLista , String item) throws Exception {
		WebElement element = utilities.getElementByXPath(driver, xpath);
		element.sendKeys(item);
		Thread.sleep(1500);
        List<WebElement> liElements = driver.findElements(By.xpath(xpathLista));
        seleccionarItem(liElements);
        Thread.sleep(1000);
    }
    
    @When("^Ingreso en el campo \"([^\"]*)\" el valor \"([^\"]*)\" y hago un tab$")
    public void pongoEnElCampoElValorYHagoUnTab(String idCampo, String texto) {
        ingresarTextoEnCampo(idCampo, texto, Keys.TAB, BY_ID);
    }
    
    @When("^Ingreso en el campo \"([^\"]*)\" el valor \"([^\"]*)\" y hago un enter$")
    public void pongoEnElCampoElValorYHagoUnEnter(String idCampo, String texto) {
        ingresarTextoEnCampo(idCampo, texto, Keys.ENTER, BY_ID);
    }

    @When("^Completo la informacion:$")
    public void completoLaInformacion(DataTable campos) {
        List<List<String>> filas = campos.raw();

        for (int i = 1; i < filas.size(); i++) {
            List<String> fila = filas.get(i);
            ingresarTextoEnCampo(fila.get(0), fila.get(1), Keys.TAB, BY_ID);
        }
    }

    @Then("^En el campo por xpath \"([^\"]*)\" veo el texto \"([^\"]*)\"$")
    public void enElCampoVeoElTexto(String xpath, String texto) {
        WebElement span = driver.findElement(By.xpath(xpath));

        Assert.assertTrue(span.getText().contains(texto));
    }

    @Then("^En el campo por xpath \"([^\"]*)\" veo el valor de la variable \"([^\"]*)\"$")
    public void enElCampoVeoElValorDeLaVariable(String xpath, String nombreVariable) {
        WebElement span = driver.findElement(By.xpath(xpath));
        String valor = variables.get(nombreVariable);

        Assert.assertTrue(span.getText().contains(valor));
    }

    @Then("^Verifico que en el xpath esta el valor correspondiente:$")
    public void verificoQueEnElXpathEstaElValorCorrespondiente(DataTable campos) {
        List<List<String>> filas = campos.raw();

        for (int i = 1; i < filas.size(); i++) {
            List<String> fila = filas.get(i);

            String xpath = fila.get(0);
            String valor = fila.get(1);

            WebElement webElement = driver.findElement(By.xpath(xpath));

            Assert.assertEquals(valor, webElement.getText());
        }
    }

    private void seleccionarItem(List<WebElement> liElements) {
        for (WebElement element: liElements){
            if (element.isDisplayed()){
                WebDriverWait wait = new WebDriverWait(driver, CucumberEnvironment.DEFAULT_TIME_OUT_IN_SECONDS);
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
            }
        }
    }

    private void ingresarTextoEnCampo(String identifier, String texto, Keys finalKeys, int selector) {
        WebElement campo;
        if (selector == BY_ID) {
            campo = utilities.getElementById(driver, identifier);
        }
        else {
            campo = utilities.getElementByXPath(driver, identifier);
        }
        campo.clear();
        campo.sendKeys(texto);
        if(finalKeys != null) {
        	campo.sendKeys(finalKeys);
        }
    }

}
