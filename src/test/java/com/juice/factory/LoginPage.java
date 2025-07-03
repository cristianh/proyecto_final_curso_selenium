package com.juice.factory;

import com.juice.utils.Base;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

	public WebDriver driver;

    @FindBy(id = "navbarAccount")
    private WebElement menuAccountButton;

    @FindBy(id = "navbarLoginButton")
    private WebElement menuItemLoginAccountButton;

	//Object repository
    @FindBy(xpath = "//span[text()='Dismiss']")
    private WebElement bottonModal;

    @FindBy(xpath = "//div[text()='All Products']")
    private WebElement titleDashboard;

    @FindBy(xpath = "//div[text()='Invalid email or password.']")
    private WebElement messageInvalidCredencials;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "loginButton")
    private WebElement loginButton;

    protected WebDriverWait wait;

    // constructor with PageFactory for initiate all the page objects
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        //Espera el inicio
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void navigateToLoginPage() {
        try {
            menuAccountButton.click();
            Base.overElements(menuItemLoginAccountButton);
            menuItemLoginAccountButton.click();
        } catch (Exception e) {
            throw new RuntimeException("Error al hacer clic en el menú de cuenta o login", e);
        }
    }


    public void fillOutForm(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
    }

    public void doLogin() {
        loginButton.click();
    }

    public boolean isDashboardPageDisplayed() {
        try {
            return titleDashboard.isDisplayed();
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean isInvalidCredencials() {
        try {
            return messageInvalidCredencials.isDisplayed();
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
            return false;
        }
    }


}
