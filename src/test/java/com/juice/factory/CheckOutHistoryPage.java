package com.juice.factory;

import com.juice.utils.Base;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CheckOutHistoryPage {

    public WebDriver driver;

    @FindBy(id = "navbarAccount")
    private WebElement menuAccountButton;

    @FindBy(xpath = "(//*[@id=\"mat-menu-panel-0\"]//div)[3]")
    private WebElement menuItemAccountButton;

    @FindBy(xpath = "//*[@id=\"mat-menu-panel-3\"]/div[1]/button[1]")
    private WebElement menuItemAccountOrderHistoryButton;

    protected WebDriverWait wait;

    @FindBy(xpath = "//*[contains(text(), 'Order History')]")
    private WebElement titleValdiacionPageHistory;

    //constructor
    public CheckOutHistoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateCheckOutHistoryPage() {
        menuAccountButton.click();
        Base.overElements(menuItemAccountButton);

    }

    public void doPageHistoryCheckout(){
            Base.isEnabledClick(menuItemAccountOrderHistoryButton);
            menuItemAccountOrderHistoryButton.click();
    }

    public String getMessageStatus(){
        // Formato para nombre único
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String name = "CheckOutHistoryPage";
        String safeName = name.replaceAll("[^a-zA-Z0-9_-]", "_");
        String folderName = "screenShots";
        String filePath =   folderName + File.separator + safeName + "_" + timestamp + ".png";

        // Crear carpeta si no existe
        File dir = new File(folderName);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Tomar screenshot
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(filePath);

        // Copiar archivo
        try (
                FileInputStream fis = new FileInputStream(srcFile);
                FileOutputStream fos = new FileOutputStream(destFile)
        ) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
            System.out.println("Screenshot guardado en: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error guardando el screenshot: " + e.getMessage());
        }
        return titleValdiacionPageHistory.getText();
    }


}
