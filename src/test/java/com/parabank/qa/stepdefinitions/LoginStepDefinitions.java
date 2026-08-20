package com.parabank.qa.stepdefinitions;

import com.parabank.qa.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class LoginStepDefinitions {

    LoginPage loginPage;

    @Given("que el cliente esta en la pagina de login de Parabank")
    public void queElClienteEstaEnLaPaginaDeLoginDeParabank(){
        loginPage.open();
    }

    @When("ingresa el usuario {string} y la clave {string}")
    public void ingresaElUusarioYLaClave(String username, String password){
        loginPage.loginAs(username, password);
    }

    @Then("deberia ver su pagina de cuentas")
    public void deberiaVerSuPaginaDeCuentas(){
        Assert.assertTrue(
                loginPage.getDriver().getCurrentUrl().contains("overview.htm")
        );
    }

    @Then("deberia ver un mensaje de error de login")
    public void deberiaverunmensajedeerrordelogin(){
        Assert.assertEquals(
                "The username and password could not be verified.",
                loginPage.getErrorMessage()
        );
    }
}
