package com.juice.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/features",
		glue = {"com.juice","com.hooks"},
		plugin = {"pretty",
				"summary",
				"html:target/cucumber-reports/html-report.html",
				"json:target/cucumber-reports/cucumber.json",
				"junit:target/cucumber-reports/cucumber.xml",
				"timeline:target/cucumber-timeline",
				"rerun:target/failedrerun.txt",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
		tags = "@04_CreditCardSuccessList",
		monochrome = false,
		dryRun = false,
		publish = true
		)

public class TestRunner extends AbstractTestNGCucumberTests {
	//Orden de ejecucion
	/*
	@01_RegisterSuccess
	@02_LoginPageSuccess
	@03_RegisterAddressList
	@O4_CreditCardSuccessList
	@05_Checkout
	@06_ConfirmPageShopping
	@07_CheckoutRandom
	@08_CheckoutHistory
	 */
}