package com.juice.hooks;

import com.juice.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {

    WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverFactory.initDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if(scenario.isFailed()){
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot,"image/png",scenario.getName());
            }
        }finally {
            DriverFactory.closeDriver();
        }

    }

}
