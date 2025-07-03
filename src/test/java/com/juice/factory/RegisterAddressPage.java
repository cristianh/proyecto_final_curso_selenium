package com.juice.factory;

import com.juice.utils.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    @FindBy(xpath = "//h1[contains(text(),'Add New Address')]")
    private WebElement titleRegisterAddressForm;

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
        menuAccountButton.click();
        Base.overElements(menuItemAccountButton);
        Base.isVisibleElement(menuItemAccountSaveAddressButton, 30);
        menuItemAccountSaveAddressButton.click();
    }


    public void doRegisterAddress() {
        Base.isVisibleElement(registerNewAddressButton, 4500);
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
        Base.isVisibleElement(titleRegisterAddressForm, 13);
        countryField.click();
        countryField.sendKeys(country);
        provinceNameField.sendKeys(province);
        mobileNumberField.sendKeys(mobileNumber);
        zipCodeField.sendKeys(zipCode);
        addressField.sendKeys(address);
        cityCodeField.sendKeys(cityCode);
        stateField.sendKeys(state);
        Base.isInVisibleElement(messageValidateRegisterAddress, 3000);
    }

    public void doSubmitAddress() {
        Base.scrollToElement(submitButton);
        Base.isVisibleElement(submitButton, 3);
        submitButton.click();   // ahora intenta clic normal
    }


    public String getMessage() {
        Base.validateUrlPage(URLBaseRegisterAddress);
        Base.isVisibleElement(messageValidateRegisterAddress);
        return messageValidateRegisterAddress.getText();
    }


    public List<String> getTitlesAddress() {

        List<String> actualTexts = titlesAddressList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        return actualTexts;
    }


}
