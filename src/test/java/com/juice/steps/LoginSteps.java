package com.juice.steps;

import com.github.javafaker.Faker;
import com.juice.factory.LoginPage;
import com.juice.utils.DriverFactory;
import io.cucumber.java.ast.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Locale;


public class LoginSteps{

	WebDriver driver = DriverFactory.getDriver();
	LoginPage loginPage = new LoginPage(driver);
	Faker fake = new Faker(new Locale("en-US"));

	@Dado("que el usuario esta en la pagina de login")
	public void que_el_usuario_esta_en_la_pagina_de_login() {
		loginPage.navigateToLoginPage();
	}


	@Cuando("el usario ingresa sus credenciales {string} y {string}")
	public void el_usario_ingresa_sus_credenciales_y(String email, String password) {
		loginPage.fillOutForm(email,password);
	}


	@Cuando("hace click en el botón de Log in")
	public void hace_click_en_el_botón_de_log_in() {
		loginPage.doLogin();
	}

	@Entonces("el sistema deberia mostrarle el mensaje {string}")
	public void el_sistema_deberia_mostrarle_credenciales_y(String mensaje) {

		if(mensaje.contains("All Products")){
			Assert.assertTrue( loginPage.isDashboardPageDisplayed());
		}else{
			Assert.assertTrue( loginPage.isInvalidCredencials());
		}
	}



}
