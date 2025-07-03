package com.juice.factory;

import com.juice.utils.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private WebDriver driver;

    @FindBy(xpath = "(//mat-toolbar-row//button)[4]//span[contains(text(), 'Your Basket')]")
    private WebElement shoppingCardMenuButton;

    @FindBy(id = "checkoutButton")
    private WebElement shoppingButton;

    @FindBy(xpath = "(//input[@type='radio'])[last()]")
    private WebElement lastAddressRadioButton;

    @FindBy(xpath = "(//*[@id='card']/app-address[1]/mat-card[1]//button)[2]")
    private WebElement nextStepButton;


    @FindBy(xpath = "(//input[@type='radio'])[last()]")
    private WebElement choiseLastDeliverySpeed;

    @FindBy(css = ".mdc-card > div:last-of-type >button:last-of-type")
    private WebElement nextStepOptionButton;

    @FindBy(xpath = "(//mat-radio-button)[2]")
    private WebElement choiseFirstOptionCard;

    @FindBy(id = "checkoutButton")
    private WebElement confirmButton;

    @FindBy(css = "h1.confirmation")
    private WebElement titleConfirmShopping;

    @FindBy(css = "div.confirmation")
    private WebElement titleValideStatus;

    //Object repository
    @FindBy(id = "searchQuery")
    private WebElement findQueryButton;

    protected WebDriverWait wait;

    // constructor with PageFactory for initiate all the page objects
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void goShoppingMenu() {
        shoppingCardMenuButton.click();
    }

    public void doSeleccion() {
        // Esperar hasta que el botón esté visible y clickable (más seguro)
        Base.isEnabledClick(shoppingButton);
        Base.overElementsClick(shoppingButton);
    }

    public void choiceOptionsAddress() {
        lastAddressRadioButton.click();
        nextStepButton.click();
    }


    public void choiceOptionsnDeliveryButton() {
        choiseLastDeliverySpeed.click();
        nextStepOptionButton.click();
    }

    public void choiceOptionsCardPay() {
        choiseFirstOptionCard.click();
        nextStepOptionButton.click();
    }

    public void doConfirmPay() {
        confirmButton.click();
    }

    public String getMessage(){
        return titleConfirmShopping.getText();
    }

    public String getMessageStatus(){
        return titleValideStatus.getText();
    }
}
