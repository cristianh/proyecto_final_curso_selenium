package com.juice.factory;

import com.juice.utils.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Hacer clic en el botón del menú de cuenta
        wait.until(ExpectedConditions.elementToBeClickable(menuAccountButton)).click();

        // Esperar que el ítem del menú login esté visible y hacer hover
        wait.until(ExpectedConditions.visibilityOf(menuItemLoginAccountButton));
        new Actions(driver)
                .moveToElement(menuItemLoginAccountButton)
                .click()
                .perform();

        // Esperar que el link de registro esté clickeable y hacer clic
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }


    public void fillOutRegisterForm(String fname, String lname, String email, String pass, String repass, String secret) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        emailField.sendKeys(email);
        passwordField.sendKeys(pass);
        repasswordField.sendKeys(repass);

        // Esperar que el dropdown esté clickable y hacer clic
        wait.until(ExpectedConditions.elementToBeClickable(selectSecurityQuestion)).click();

        // Esperar que la opción esté presente y visible
        wait.until(ExpectedConditions.visibilityOf(opctionSelectSecurityQuestion));

        // Scroll al elemento (si es necesario)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", opctionSelectSecurityQuestion);

        // Esperar que sea clickable y hacer clic
        wait.until(ExpectedConditions.elementToBeClickable(opctionSelectSecurityQuestion)).click();

        // Ingresar la respuesta
        securityAnswerField.sendKeys(secret);
    }

    public void sendDataForNewAccount() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Scroll al botón (opcional si ya está visible, pero puede ayudar en algunos casos)
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", registerButton);

        // Esperar que el botón sea clickable
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
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
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(1));
        driver.switchTo().defaultContent();

        System.out.println("url" + this.driver.getCurrentUrl());
        // Esperar a que la URL sea exactamente una
        wait.until(ExpectedConditions.urlToBe(URLBaseLogin));
        wait.until(ExpectedConditions.visibilityOf(messageText));

        // Esperar a que la URL sea exactamente una
        wait.until(ExpectedConditions.visibilityOf(messageText));

        System.out.println("Mensaje" + messageText.getText());
        return messageText.getText();
    }

}
