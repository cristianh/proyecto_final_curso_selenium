package com.juice.utils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverFactory {

    //@Managed(uniqueSession = true)
    private static WebDriver driver;
    public static int TIME_OUT= 60;

    public static WebDriver initDriver(){


        String browser = Reader.getAllProperties().getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")) {
            if(driver == null){
                //System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
                // Crear opciones de Firefox
                ChromeOptions options = new ChromeOptions();

                // 🔐 Configuraciones de seguridad directamente
                options.addArguments("--ignore-certificate-errors");
                options.addArguments("--allow-insecure-localhost");
                //options.addArguments("--headless");
                options.addArguments("--start-maximized");
                options.addArguments("--disable-infobars");
                options.addArguments("--remote-allow-origins=*");
                //Paso 2: instanciar las variables
                // instanciamos el navegador a utilizar.
                driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                        Long.parseLong(Reader.getAllProperties().getProperty("implicit.wait"))
                ));
                driver.get(Reader.getAllProperties().getProperty("url"));
                // seteamos las cookies para que no aparezcan los pop iniciales
                driver.manage().addCookie(new Cookie("language", "en"));
                driver.manage().addCookie(new Cookie("welcomebanner_status", "dismiss"));
                driver.manage().addCookie(new Cookie("cookieconsent_status", "dismiss"));
                driver.navigate().refresh(); // A veces es necesario para que tome efecto

            }
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                    Long.parseLong(Reader.getAllProperties().getProperty("implicit.wait"))
            ));
            driver.get(Reader.getAllProperties().getProperty("url"));
        }


        return driver;
    }

    // Obtener el WebDriver actual
    public static WebDriver getDriver() {
        return driver;
    }

    // Cerrar el WebDriver
    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
