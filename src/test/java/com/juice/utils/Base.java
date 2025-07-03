package com.juice.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Base {

	public static int TIME_OUT = 3000;
	public static int TIME_EXPECTED_CONDITIONS = 15;

	public static WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(TIME_EXPECTED_CONDITIONS));

	public static void overElements(WebElement element){
		// Crear acción de MouseOver
		new Actions(DriverFactory.getDriver()).
				moveToElement(element).
				perform();
	}

	public static void overElementsClick(WebElement element){

		wait.until(ExpectedConditions.elementToBeClickable(element));

		new Actions(DriverFactory.getDriver())
				.moveToElement(element)
				.click()
				.perform();
	}

	public static void isEnabledClick(WebElement element){
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void isInVisibleElement(WebElement element,long time){
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(time));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public static void isInVisibleElement(WebElement element){
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public static void isVisibleElement(WebElement element,long time){
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void isVisibleElement(WebElement element){
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void validateUrlPage(String urlBase){
		// Esperar a que la URL pase al la pagina de registro y valide el mensaje
		wait.until(ExpectedConditions.urlToBe(urlBase));
	}

	public static void scrollToElement(WebElement element){
		// espera a que se acomode el scroll
		((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
	}





}
