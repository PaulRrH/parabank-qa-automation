package com.parabank.qa.stepdefinitions;

import com.parabank.qa.pages.RegisterPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class RegisterStepDefinitions {

    RegisterPage registerPage;

    @Given("que el visitante esta en la pagina de registro")
    public void queElVisitanteEstaEnLaPaginaDeRegistro(){
        registerPage.open();
    }

    @When("completa el formulario de registro con datos validos")
    public void completaElFormularioDeRegistroConDatosValidos(){
        String usernameUnico = "qa" + (System.currentTimeMillis() % 1000000);
        registerPage.registrarCliente(usernameUnico, "Password123");
    }

    @Then("deberia ver la confirmacion de que su cuenta fue creada")
    public void deberiaVerLaConfirmacionDeQueSuCuentaFueCreada(){
        Assert.assertEquals(
                "Your account was created successfully. You are now logged in.",
                registerPage.getConfirmationMessage()
        );
    }
}
