package com.parabank.qa.pages;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("https://parabank.parasoft.com/parabank/index.htm")

public class LoginPage extends PageObject{

    @FindBy(name = "username")
    private  WebElementFacade usernameField;

    @FindBy(name = "password")
    private WebElementFacade passwordField;

    @FindBy(css = "input.button[value = 'Log In']")
    private WebElementFacade loginButton;

    @FindBy(css = "p.error")
    private WebElementFacade errorMessage;

    public void enterUsername(String username){
        usernameField.type(username);
    }

    public void enterPassword(String password){
        passwordField.type(password);
    }

    public void clickLogin(){
        loginButton.click();
    }

    public void loginAs(String username, String password){
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage(){
        return errorMessage.getText();
    }
}
