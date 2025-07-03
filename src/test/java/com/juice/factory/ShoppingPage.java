package com.juice.factory;

import com.juice.utils.Base;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class ShoppingPage {

    private WebDriver driver;

    //Object repository
    @FindBy(id = "searchQuery")
    private WebElement findQueryButton;

    @FindBy(xpath = "//input[@type='text']")
    private WebElement findQueryField;

    @FindBy(xpath = "(//button[@aria-label='Add to Basket'])[1]")
    private WebElement addToCardShoppindButton;

    @FindBy(xpath = "(//mat-toolbar-row//button)[4]//span[contains(text(), 'Your Basket')]")
    private WebElement shoppingCardMenuButton;

    @FindBy(id = "checkoutButton")
    private WebElement shoppingCardButton;

    @FindBy(xpath = "//h1[contains(text(), 'Your Basket')]")
    private WebElement titlePageShoppingCard;


    @FindBys(
            @FindBy(xpath = "//mat-row[@role='row']")
    )
    private List<WebElement> elementsToShoppingCard;

    @FindBys(
            @FindBy(xpath = "//button[@aria-label='Add to Basket']")
    )
    private List<WebElement> productButtonPageShopping;

    @FindBy(xpath = "//*[contains(text(),'Placed') and contains(text(),'into basket')]")
    private WebElement messageConfirmAddCardProduct;

    protected WebDriverWait wait;

    // constructor with PageFactory for initiate all the page objects
    public ShoppingPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }


    public void findProduct(String productoFind) {

        Base.isEnabledClick(findQueryButton);
        findQueryButton.click();
        Base.isVisibleElement(findQueryField);
        findQueryField.click();
        findQueryField.clear();
        findQueryField.sendKeys(productoFind);
        findQueryField.sendKeys(Keys.ENTER);
        System.out.println("agrego producto: " + productoFind);
        findQueryField.click();
        findQueryField.clear();
    }

    public void goAddShoppingOptions() {
        Base.isEnabledClick(addToCardShoppindButton);
        addToCardShoppindButton.click();
    }


    public void doGoShoppingOptions() {
        Base.isEnabledClick(shoppingCardMenuButton);
        shoppingCardMenuButton.click();
    }

    public void doGoShoppingCard() {
        Base.isEnabledClick(shoppingCardButton);
        shoppingCardButton.click();
    }

    public void clickRandomProduct() {
        // Verifica que la lista no esté vacía
        if (!productButtonPageShopping.isEmpty()) {
            Random random = new Random();
            int indexAleatorio = random.nextInt(productButtonPageShopping.size());
            WebElement elementoSeleccionado = productButtonPageShopping.get(indexAleatorio);
            // Haz clic o realiza alguna acción
            elementoSeleccionado.click();
        }
    }

    public List<WebElement> countElementsToShoppingCard() {
        Base.isVisibleElement(titlePageShoppingCard);
        return elementsToShoppingCard;
    }

    public boolean isMessageVisible() {
        Base.isVisibleElement(messageConfirmAddCardProduct);
        return messageConfirmAddCardProduct.isDisplayed();
    }

    public boolean isNotVisible() {
        try {
            return wait.until(ExpectedConditions.invisibilityOf(messageConfirmAddCardProduct));
        } catch (TimeoutException e) {
            return true;
        }
    }

}
