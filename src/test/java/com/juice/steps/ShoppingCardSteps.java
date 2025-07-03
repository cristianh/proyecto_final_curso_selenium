package com.juice.steps;

import com.github.javafaker.Faker;
import com.juice.factory.ShoppingPage;
import com.juice.utils.DriverFactory;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ShoppingCardSteps {

    WebDriver driver = DriverFactory.getDriver();
    ShoppingPage shoppingpage = new ShoppingPage(driver);
    Faker fake = new Faker(new Locale("es","CO"));

    //Validamos si existen 2 elementos en el carrito y si coinciden con el nombre
    private List<String> elementsList = new ArrayList<>();

    @Cuando("el usuario busca los productos y los agrega al carrito")
    public void el_usuario_busca_los_productos_y_los_agrega_al_carrito(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> products = dataTable.asMaps(String.class,String.class);

        for (Map<String,String> product : products){
            shoppingpage.findProduct(product.get("nombre"));
            shoppingpage.goAddShoppingOptions();
        }
    }

    @Cuando("el usuario seleccione {int} elementos de manera aleatoria y los agrega al carrito")
    public void el_usuario_seleccione_elementos_de_manera_aleatoria_y_los_agrega_al_carrito(Integer steps) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));


        for (int i = 0; i < steps; i++) {
            shoppingpage.clickRandomProduct();
            Assert.assertTrue(shoppingpage.isMessageVisible());
            System.out.println(shoppingpage.isNotVisible());
            if(!shoppingpage.isNotVisible()){
                continue;
            }
        }
    }


    @Entonces("el carrito debe mostrar {string} y el total debe ser actualizado correctamente")
    public void el_carrito_debe_mostrar_y_el_total_debe_ser_actualizado_correctamente(String numeroproductos) {
        shoppingpage.doGoShoppingOptions();
        elementsList.add("banana");
        elementsList.add("apple");
        elementsList.add("T-Shirt");
        List<WebElement> listproducsPageShopping = shoppingpage.countElementsToShoppingCard();
        Assert.assertEquals(Integer.parseInt(numeroproductos),Integer.parseInt(String.valueOf(listproducsPageShopping.size())));
        for (int i = 0; i < listproducsPageShopping.size(); i++) {
            Assert.assertTrue(listproducsPageShopping.get(i).getText().toLowerCase().contains(elementsList.get(i).toLowerCase()),"Expected product to contain: " + elementsList.get(i) + " but was: " + listproducsPageShopping.get(i).getText());
        }
    }
}
