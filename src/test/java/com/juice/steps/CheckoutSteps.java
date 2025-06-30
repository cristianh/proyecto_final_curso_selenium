package com.juice.steps;

import com.github.javafaker.Faker;
import com.juice.factory.CheckoutPage;
import com.juice.utils.DriverFactory;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Locale;

public class CheckoutSteps {

	WebDriver driver = DriverFactory.getDriver();
	CheckoutPage checkoutpage = new CheckoutPage(driver);
	Faker fake = new Faker(new Locale("es","CO"));

	//Validacion carrito de compras
	@Dado("que el usuario ha agregado productos al carrito")
	public void que_el_usuario_ha_agregado_productos_al_carrito() {
		checkoutpage.goShoppingMenu();
		checkoutpage.doSeleccion();
	}
	@Dado("el usuario ha ingresado su información de envío")
	public void el_usuario_ha_ingresado_su_información_de_envío() {
		checkoutpage.choiceOptionsAddress();
		checkoutpage.choiceOptionsnDeliveryButton();
	}
	@Dado("el usuario ha seleccionado un método de pago válido")
	public void el_usuario_ha_seleccionado_un_método_de_pago_válido() {
		checkoutpage.choiceOptionsCardPay();
	}
	@Cuando("el usuario confirma la compra")
	public void el_usuario_confirma_la_compra() {
		checkoutpage.doConfirmPay();
	}
	@Entonces("el usuario debe recibir una confirmación de pedido {string}")
	public void el_usuario_debe_recibir_una_confirmación_de_pedido(String message) {
		Assert.assertEquals(checkoutpage.getMessage(),message);

	}
	@Entonces("el estado del pedido debe ser {string}")
	public void el_estado_del_pedido_debe_ser(String message) {
		Assert.assertEquals(checkoutpage.getMessageStatus(),message);
	}

}
