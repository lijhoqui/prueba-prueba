package com.suraam.accepttest.steps;

import cucumber.api.java.en.When;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class FechaSteps {

    private Map<String, String> variables;

    public FechaSteps(CucumberEnvironment environment) {
        this.variables = environment.getVariables();
    }

    @When("^Creo una fecha \"([^\"]*)\" a partir de hoy y la guardo en la variable \"([^\"]*)\"$")
    public void creoUnaFechaDiasAPartirDeHoyYLaGuardoEnLaVariable(int dias, String nombreVariable){
        LocalDate fecha = LocalDate.now().plusDays(dias);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = fecha.format(formatter);
        variables.put(nombreVariable, fechaFormateada);
    }

}
