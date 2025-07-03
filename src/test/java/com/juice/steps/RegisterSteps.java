
package com.juice.steps;

import com.github.javafaker.Faker;
import com.juice.factory.RegisterPage;
import com.juice.utils.DriverFactory;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.openqa.selenium.WebDriver;

import java.util.Locale;

import static org.testng.Assert.assertTrue;

public class RegisterSteps {

	WebDriver driver = DriverFactory.getDriver();
	RegisterPage account = new RegisterPage(driver);
	Faker fake = new Faker(new Locale("en-US"));

	@Dado("que el usuario está en la página de registro")
	public void que_el_usuario_está_en_la_página_de_registro() {
		account.goToRegisterPage();
	}

	@Cuando("el usuario completa la información válida")
	public void el_usuario_completa_la_información_válida() {
		//String pass = fake.internet().password();
		//String email = fake.internet().emailAddress();
		String email = "pruebas@gmail.com";
		String pass = "crusto2009";
		String secret = fake.internet().uuid();

		account.fillOutRegisterForm(fake.name().firstName(), fake.name().lastName(), email, pass, pass,secret);
		System.out.println("Account has been created with Email:" + email + " and Password: " + pass);
	}

	@Cuando("confirme la informacion con el boton de registrar")
	public void confirme_la_informacion_con_el_boton_de_registrar() {
		account.sendDataForNewAccount();
	}

	@Entonces("el sistema debe mostrarle mensaje de registro exitoso")
	public void el_sistema_debe_mostrarle_mensaje_de_registro_exitoso() {
			assertTrue(account.getMessage().contains("Registration completed successfully. You can now log in."));
	}

}
