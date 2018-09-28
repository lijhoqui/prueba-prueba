package com.suraam.accepttest.steps;

import com.github.javafaker.Faker;
import cucumber.api.java.en.When;

import java.util.Locale;
import java.util.Map;

public class FakerSteps {

    private Map<String, String> variables;
    private Faker faker;

    public FakerSteps(CucumberEnvironment environment) {
        this.variables = environment.getVariables();
        this.faker = new Faker(new Locale("es-MX"));
    }

    @When("^Creo una fake address y la guardo en la variable \"([^\"]*)\"$")
    public void creoUnFakeAddressYLoGuardoEnLaVariable(String nombreVariable){
        String address = faker.address().fullAddress();
        variables.put(nombreVariable, address);
    }


}
