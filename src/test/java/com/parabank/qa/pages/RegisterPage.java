package com.parabank.qa.pages;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;
import java.time.Duration;
@DefaultUrl("https://parabank.parasoft.com/parabank/register.htm")

public class RegisterPage extends PageObject {

    @FindBy(id = "customer.firstName")
    private WebElementFacade firstNameField;

    @FindBy(id = "customer.lastName")
    private WebElementFacade lastNameField;

    @FindBy(id = "customer.address.street")
    private WebElementFacade streetField;

    @FindBy(id = "customer.address.city")
    private WebElementFacade cityField;

    @FindBy(id = "customer.address.state")
    private WebElementFacade stateField;

    @FindBy(id = "customer.address.zipCode")
    private WebElementFacade zipCodeField;

    @FindBy(id = "customer.phoneNumber")
    private WebElementFacade phoneField;

    @FindBy(id = "customer.ssn")
    private WebElementFacade ssnField;

    @FindBy(id = "customer.username")
    private WebElementFacade usernameField;

    @FindBy(id = "customer.password")
    private WebElementFacade passwordField;

    @FindBy(id = "repeatedPassword")
    private WebElementFacade confirmPasswordField;

    @FindBy(css = "input.button[value='Register']")
    private WebElementFacade registerButton;

    @FindBy(xpath = "//p[contains(text(),'account was created successfully')]")
    private WebElementFacade confirmationMessage;

    public void registrarCliente(String username, String password){

        firstNameField.type("Juan");
        lastNameField.type("Rojas");
        streetField.type("Calle Falsa 123");
        cityField.type("Springfield");
        stateField.type("Buenos Aires");
        zipCodeField.type("1000");
        phoneField.type("1122334455");
        ssnField.type("123-45-6789");
        usernameField.type(username);
        passwordField.type(password);
        confirmPasswordField.type(password);
        registerButton.click();
    }

    public String getConfirmationMessage(){
        return confirmationMessage.withTimeoutOf(Duration.ofSeconds(15)).waitUntilVisible().getText();
    }
}
