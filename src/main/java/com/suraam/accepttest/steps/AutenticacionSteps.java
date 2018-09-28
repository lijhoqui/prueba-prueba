package com.suraam.accepttest.steps;

import com.suraam.accepttest.utilities.Utilities;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AutenticacionSteps {

    private WebDriver driver;
    private Utilities utilities;

    public AutenticacionSteps(CucumberEnvironment environment) {
        this.driver = environment.getDriver();
        this.utilities = environment.getUtilities();
    }

    @When("^Me autentico en la empresa \"([^\"]*)\" con el usuario \"([^\"]*)\" y la contrasena \"([^\"]*)\"$")
    public void meAutenticoEnLaEmpresaConElUsuarioYLaContrasena(String empresa, String usuario, String password) throws Throwable {
        autenticar(empresa, usuario, password);
    }

    @Given("^Estoy autenticado en la empresa \"([^\"]*)\" con el usuario \"([^\"]*)\" y la contrasena \"([^\"]*)\"$")
    public void estoyAutenticadoEnLaEmpresaConElUsuarioYLaContrasena(String empresa, String usuario, String password) throws Throwable {
        autenticar(empresa, usuario, password);
    }

    private void autenticar(String empresa, String usuario, String password) {
        driver.get("https://hcm17preview.sapsf.com");

        WebElement campoEmpresa = utilities.getElementById(driver, "__input0-inner");
        campoEmpresa.clear();
        campoEmpresa.sendKeys(empresa);
        campoEmpresa.sendKeys(Keys.ENTER);

        WebElement campoUsuario = utilities.getElementById(driver, "__input1-inner");
        campoUsuario.clear();
        campoUsuario.sendKeys(usuario);
        campoUsuario.sendKeys(Keys.TAB);

        WebElement campoPassword = utilities.getElementById(driver, "__input2-inner");
        campoPassword.clear();
        campoPassword.sendKeys(password);
        campoPassword.sendKeys(Keys.ENTER);
    }

}
