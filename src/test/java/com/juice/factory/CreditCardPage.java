package com.juice.factory;

import com.juice.utils.Base;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreditCardPage {

    public WebDriver driver;

    @FindBy(id = "navbarAccount")
    private WebElement menuAccountButton;

    @FindBy(xpath = "(//*[@id=\"mat-menu-panel-0\"]//div)[3]")
    private WebElement menuItemAccountButton;

    @FindBy(xpath = "//*[contains(text(), 'My Payment Options')]")
    private WebElement menuItemAccountSaveAddressButton;


    @FindBy(xpath = "//*[contains(text(), 'Add new card')]")
    private WebElement addCardButton;

    //Object repository
    @FindBy(xpath = "(//input[@type=\"text\"])[2]")
    private WebElement nameField;

    @FindBy(xpath = "//input[@type=\"number\"]")
    private WebElement cardNumberField;

    @FindBy(xpath = "(//select)[1]")
    private WebElement experyCardMonthField;

    @FindBy(xpath = "(//select)[2]")
    private WebElement experyCardYearField;

    @FindBy(id = "mat-expansion-panel-header-0")
    private WebElement hiddenOptionsCardsButton;

    @FindBy(id = "submitButton")
    private WebElement submitButton;

    @FindBy(xpath = "//*[contains(text(),'Your card ending with') and contains(text(),'has been saved for your convenience.')]")
    private WebElement messageText;



    protected WebDriverWait wait;

    // constructor with PageFactory for initiate all the page objects
    public CreditCardPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void navigateToCreditCardMenuPage() {
        Base.isEnabledClick(menuAccountButton);
        menuAccountButton.click();
        Base.overElementsClick(menuItemAccountButton);
        //menuItemAccountButton.click();
        Base.isEnabledClick(menuItemAccountSaveAddressButton);
        Base.isVisibleElement(menuItemAccountSaveAddressButton,5000);
        Base.overElements(menuItemAccountSaveAddressButton);
        menuItemAccountSaveAddressButton.click();
    }

    public void doAddCreditCard() {
        try {
            Base.isVisibleElement(addCardButton);
            Base.isEnabledClick(addCardButton);
            addCardButton.click();

        } catch (Exception e) {
            throw new RuntimeException("Error: "+e.getMessage(), e);
        }

    }

    public void fillOutForm(String cardName, String cardNumber, String dateMoth, String dateYear) {

        Base.isVisibleElement(nameField,2770);
        Base.isEnabledClick(nameField);
        nameField.sendKeys(cardName);
        cardNumberField.sendKeys(cardNumber);
        experyCardMonthField.click();
        experyCardMonthField.sendKeys(dateMoth);
        experyCardYearField.click();
        experyCardYearField.sendKeys(dateYear);
        experyCardYearField.sendKeys(Keys.ENTER);
    }

    public void doSubmitCreditCard() {


        try {
            // espera 3 mi-segundos
            Thread.sleep(3000);
            //Scroll
            Base.scrollToElement(submitButton);
            //Si el boton de guardar esta habilitado, guardamos la card
            if (submitButton.isEnabled()) {
                Base.isEnabledClick(submitButton);
                submitButton.click();
            }
            else {
                //ocultamos el formulario de Creditcards al ingresar todo.
                Base.isEnabledClick(hiddenOptionsCardsButton);
                hiddenOptionsCardsButton.click();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public String getMessage() {
        // Esperar a que la URL sea exactamente una
        Base.isVisibleElement(messageText);
        return messageText.getText();
    }

}
