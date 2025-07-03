package com.juice.factory;

import com.juice.utils.Base;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.juice.utils.Constants.URLBaseLogin;

public class RegisterPage {

    WebDriver driver;

    @FindBy(id = "navbarAccount")
    private WebElement menuAccountButton;

    @FindBy(id = "navbarLoginButton")
    private WebElement menuItemLoginAccountButton;

    //Object repository
    @FindBy(xpath = "//span[text()='Dismiss']")
    private WebElement bottonModal;

    @FindBy(id = "newCustomerLink")
    private WebElement registerLink;

    @FindBy(id = "emailControl")
    private WebElement emailField;

    @FindBy(id = "passwordControl")
    private WebElement passwordField;

    @FindBy(id = "repeatPasswordControl")
    private WebElement repasswordField;

    @FindBy(name = "securityQuestion")
    private WebElement selectSecurityQuestion;

    @FindBy(xpath = "//*[contains(text(), 'Name of your favorite pet?')]")
    private WebElement opctionSelectSecurityQuestion;

    @FindBy(id = "securityAnswerControl")
    private WebElement securityAnswerField;

    @FindBy(id = "registerButton")
    private WebElement registerButton;

    //@FindBy(css = "#cdk-overlay-7 mat-snack-bar-container div")
    @FindBy(xpath = "//*[@id=\"mat-snack-bar-container-live-0\"]//div[1]/simple-snack-bar[1]/div[1]")
    //@FindBy(xpath = "//*[contains(@class, 'mat-simple-snackbar')]")
    private WebElement messageText;

    //constructor
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //methods
    public void goToRegisterPage() {
        //Reutilizamos el menu
        Base.isEnabledClick(menuAccountButton);
        menuAccountButton.click();
        Base.isVisibleElement(menuItemLoginAccountButton);
        Base.overElementsClick(menuItemLoginAccountButton);
        Base.isEnabledClick(registerLink);
        registerLink.click();

    }


    public void fillOutRegisterForm(String fname, String lname, String email, String pass, String repass, String secret) {

        //llenamos el formulario de registro
        emailField.sendKeys(email);
        passwordField.sendKeys(pass);
        repasswordField.sendKeys(repass);

        //Validamos los elementos en la pagina.
        // Esperar que el botón sea clickable
        Base.isEnabledClick(selectSecurityQuestion);
        selectSecurityQuestion.click();
        Base.isVisibleElement(opctionSelectSecurityQuestion);
        // Esperar que el botón sea clickable
        Base.isEnabledClick(opctionSelectSecurityQuestion);
        opctionSelectSecurityQuestion.click();
        //Scrolling al elemento
        Base.scrollToElement(opctionSelectSecurityQuestion);
        // Ingresar la respuesta
        securityAnswerField.sendKeys(secret);
    }

    public void sendDataForNewAccount() {
        Base.scrollToElement(registerButton);
        // Esperar que el botón sea clickable
        Base.isEnabledClick(registerButton);
        registerButton.click();
    }

    public boolean isRegisterSuccessPageDisplayed() {
        try {
            return messageText.isDisplayed();
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public String getMessage() {
        // Esperar a que la URL sea exactamente una
        Base.validateUrlPage(URLBaseLogin);
        Base.isVisibleElement(messageText);
        return messageText.getText();
    }

}
