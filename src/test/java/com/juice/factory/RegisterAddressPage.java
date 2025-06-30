package com.juice.factory;

import com.juice.utils.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static com.juice.utils.Constants.URLBaseRegisterAddress;


public class RegisterAddressPage {
    public WebDriver driver;


    @FindBy(id = "navbarAccount")
    private WebElement menuAccountButton;

    @FindBy(xpath = "(//*[@id=\"mat-menu-panel-0\"]//div)[3]")
    private WebElement menuItemAccountButton;

    @FindBy(xpath = "//*[@id=\"mat-menu-panel-3\"]/div[1]/button[3]")
    private WebElement menuItemAccountSaveAddressButton;

    //Object repository
    @FindBy(className = "btn-new-address")
    private WebElement registerNewAddressButton;

    @FindBy(xpath = "(//*[@class='mat-mdc-form-field-infix']//input)[2]")
    private WebElement countryField;

    @FindBy(xpath = "//*[@placeholder='Please provide a name.']")
    private WebElement provinceNameField;

    @FindBy(xpath = "//*[@placeholder='Please provide a mobile number.']")
    private WebElement mobileNumberField;

    @FindBy(xpath = "//*[@placeholder='Please provide a ZIP code.']")
    private WebElement zipCodeField;

    @FindBy(id = "address")
    private WebElement addressField;

    @FindBy(xpath = "//*[@placeholder='Please provide a city.']")
    private WebElement cityCodeField;

    @FindBy(xpath = "//*[@placeholder='Please provide a state.']")
    private WebElement stateField;

    @FindBy(id = "submitButton")
    private WebElement submitButton;

    @FindBy(xpath = "//*[@id=\"mat-snack-bar-container-live-0\"]//div[1]/simple-snack-bar[1]/div[1]")
    private WebElement messageValidateRegisterAddress;

    @FindBy(css = "mat-row[role=\"row\"] > mat-cell:first-of-type")
    private List<WebElement> titlesAddressList;




    protected WebDriverWait wait;

    //constructor
    public RegisterAddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateRegisterAddressPage() {
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


    public void doRegisterAddress() {
        try {
            Thread.sleep(5500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(100));
        wait.until(ExpectedConditions.visibilityOf(registerNewAddressButton));
        registerNewAddressButton.click();

    }


    public void fillOutRegisterAddressForm(
            String country,
            String province,
            String mobileNumber,
            String zipCode,
            String address,
            String cityCode,
            String state

    ) {


            try {
                Thread.sleep(100);
                countryField.click();
                countryField.sendKeys(country);
                provinceNameField.sendKeys(province);
                mobileNumberField.sendKeys(mobileNumber);
                zipCodeField.sendKeys(zipCode);
                addressField.sendKeys(address);
                cityCodeField.sendKeys(cityCode);
                stateField.sendKeys(state);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

    }

    public void doSubmitAddress() {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
            Thread.sleep(2500);  // espera a que se acomode el scroll
            submitButton.click();   // ahora intenta clic normal
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    public String getMessage() {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(1));
        driver.switchTo().defaultContent();

        System.out.println("url"+ this.driver.getCurrentUrl());
        // Esperar a que la URL pase al la pagina de registro y valide el mensaje
        wait.until(ExpectedConditions.urlToBe(URLBaseRegisterAddress));
        wait.until(ExpectedConditions.visibilityOf(messageValidateRegisterAddress));
        System.out.println("Mensaje:"+ messageValidateRegisterAddress.getText());
        return messageValidateRegisterAddress.getText();
    }


    public List<String> getTitlesAddress() {

        List<String> actualTexts = titlesAddressList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        return actualTexts;
    }




}
