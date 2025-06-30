package com.juice.hooks;

import com.juice.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {

    WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverFactory.initDriver();
    }

    @After
    public void tearDown() {
        DriverFactory.closeDriver();
    }

}
