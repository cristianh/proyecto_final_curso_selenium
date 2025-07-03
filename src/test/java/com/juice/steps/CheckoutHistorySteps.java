package com.juice.steps;

import com.github.javafaker.Faker;
import com.juice.factory.CheckOutHistoryPage;
import com.juice.utils.DriverFactory;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Locale;

public class CheckoutHistorySteps {
    WebDriver driver = DriverFactory.getDriver();
    CheckOutHistoryPage checkouthistorypage = new CheckOutHistoryPage(driver);
    Faker fake = new Faker(new Locale("es","CO"));

    @Dado("que el usuario ha realizado pedidos anteriormente")
    public void que_el_usuario_ha_realizado_pedidos_anteriormente() {
        checkouthistorypage.navigateCheckOutHistoryPage();
    }
    @ Cuando("el usuario accede al historial de pedidos")
    public void el_usuario_accede_al_historial_de_pedidos() {
        checkouthistorypage.doPageHistoryCheckout();
    }
    @Entonces("el usuario debe ver la lista de sus pedidos anteriores con detalles")
    public void el_usuario_debe_ver_la_lista_de_sus_pedidos_anteriores_con_detalles() {
        Assert.assertEquals(checkouthistorypage.getMessageStatus(),"Order History");
    }
}
