package com.juice.factory;

import com.juice.utils.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreditCardPage {

    public WebDriver driver;

    @FindBy(id = "navbarAccount")
    private WebElement menuAccountButton;

    @FindBy(xpath = "(//*[@id=\"mat-menu-panel-0\"]//div)[3]")
    private WebElement menuItemAccountButton;

    @FindBy(xpath = "//*[@id=\"mat-menu-panel-3\"]/div[1]/button[4]")
    private WebElement menuItemAccountSaveAddressButton;

    @FindBy(xpath = " //*[contains(text(), 'Add new card')]")
    private WebElement addCardButton;

    //Object repository
    @FindBy(xpath = "(//input[@type=\"text\"])[2]")
    private WebElement nameField;

    @FindBy(xpath = "//input[@type=\"number\"]")
    private WebElement cardNumberField;

    @FindBy(xpath = "(//select)[1]")
    private WebElement experyCardMonthField;

    @FindBy(xpath  = "(//select)[2]")
    private WebElement experyCardYearField;

    @FindBy(id  = "mat-expansion-panel-header-0")
    private WebElement hiddenOptionsCardsButton;

    @FindBy(id = "submitButton")
    private WebElement submitButton;

    protected WebDriverWait wait;

    // constructor with PageFactory for initiate all the page objects
    public CreditCardPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void navigateToCreditCardMenuPage() {
        try {
            menuAccountButton.click();
            // Crear acción de MouseOver
            Actions actions = new Actions(driver);
            actions.moveToElement(menuItemAccountButton).perform();
            Thread.sleep(10);
            menuItemAccountSaveAddressButton.click();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void doAddCreditCard() {
        try {
            Thread.sleep(2500);
            WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(100));
            wait.until(ExpectedConditions.visibilityOf(addCardButton));
            addCardButton.click();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillOutForm(String cardName, String cardNumber, String dateMoth,String dateYear) {
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
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
            Thread.sleep(8500);  // espera a que se acomode el scroll
            //Si el boton de guardar esta habilitad, guardamos la card
            if(submitButton.isEnabled()){
                submitButton.click();
            }else{
                //ocultamos el formulario de Creditcards
                hiddenOptionsCardsButton.click();
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
