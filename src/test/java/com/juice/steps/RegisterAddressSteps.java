package com.juice.steps;

import com.github.javafaker.Faker;
import com.juice.factory.RegisterAddressPage;
import com.juice.utils.DriverFactory;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RegisterAddressSteps {

    WebDriver driver = DriverFactory.getDriver();
    RegisterAddressPage registeraddress = new RegisterAddressPage(driver);
    Faker fake = new Faker(new Locale("es","CO"));

    private String country = fake.address().country();
    private String province = fake.address().state(); // o .stateAbbr()
    private String mobileNumber = fake.phoneNumber().cellPhone().replace("-","").replace(".","");
    private String zipCode = fake.address().zipCode();
    private String address = fake.address().fullAddress();
    private String city = fake.address().cityName();
    private String state = fake.address().cityPrefix();

    private List<String> expected = new ArrayList<>();
    private List<String> actual;

    @Dado("que el usuario está en la página de registro de direccion")
    public void que_el_usuario_está_en_la_página_de_registro_de_direccion() {
        registeraddress.navigateRegisterAddressPage();
    }

    @Cuando("el usuario completa el formulario de registro")
    public void el_usuario_completa_el_formulario_de_registro() {
        registeraddress.doRegisterAddress();
        registeraddress.fillOutRegisterAddressForm(country,province,mobileNumber,zipCode,address,city,state);
    }

    @Cuando("el usuario completa el formulario de registro con las direccion")
    public void el_usuario_completa_el_formulario_de_registro_con_las_direccion(io.cucumber.datatable.DataTable dataTable) {

        List<Map<String, String>> addresslist = dataTable.asMaps(String.class,String.class);


        for (Map<String,String> addr : addresslist){
            city = addr.get("city");
            expected.add(addr.get("province"));
            registeraddress.doRegisterAddress();
            registeraddress.fillOutRegisterAddressForm(
                    addr.get("country"),
                    addr.get("province"),
                    addr.get("mobilenumber"),
                    addr.get("zipcode"),
                    addr.get("address"),
                    addr.get("city"),
                    addr.get("state")
            );


            registeraddress.doSubmitAddress();


            //Assert.assertTrue(registeraddress.getMessage().contains("The address at " + city + " has been successfully added to your addresses."));
        }
        System.out.println(expected);
    }

    @Cuando("hace clic en el botón de enviar")
    public void hace_clic_en_el_botón_de_enviar() {
        registeraddress.doSubmitAddress();
    }


    @Entonces("el usuario debe ser redirigido a la página del login")
    public void el_usuario_debe_ser_redirigido_a_la_página_direcciones() {
        Assert.assertTrue(registeraddress.getMessage().contains("The address at " + city + " has been successfully added to your addresses."));
    }

    @Entonces("las diferentes direcciones se visualicen en la pagina")
    public void las_diferentes_direcciones_se_visualicen_en_la_pagina() {
        actual= registeraddress.getTitlesAddress();
        Assert.assertEquals(expected, actual);
    }

}

