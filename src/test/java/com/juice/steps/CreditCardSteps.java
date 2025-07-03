package com.juice.steps;

import com.github.javafaker.Faker;
import com.juice.factory.CreditCardPage;
import com.juice.utils.DriverFactory;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.testng.Assert.assertTrue;

public class CreditCardSteps {

    WebDriver driver = DriverFactory.getDriver();
    CreditCardPage creditcardpage = new CreditCardPage(driver);
    Faker fake = new Faker(new Locale("es","CO"));

    private String cardnumber="";


    @Cuando("el usuario accede a la sección de información de pago")
    public void el_usuario_accede_a_la_sección_de_información_de_pago() {
        creditcardpage.navigateToCreditCardMenuPage();
    }

    @Cuando("agrega una nueva tarjeta de crédito con detalles válidos")
    public void agrega_una_nueva_tarjeta_de_crédito_con_detalles_válidos() {

        creditcardpage.doAddCreditCard();
        String cardName = "Mastercard";
        String cardNumber = "5425233430109903";
        String dateMoth = fake.business().creditCardExpiry();
        String dateYear = "2080";
        creditcardpage.fillOutForm(cardName,cardNumber,dateMoth,dateYear);
    }

    @Cuando("agrega nuevas tarjetas de crédito con detalles válidos")
    public void agrega_nuevas_tarjeta_de_crédito_con_detalles_válidos(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> addresslist = dataTable.asMaps(String.class,String.class);
        creditcardpage.doAddCreditCard();
        for (Map<String,String> card : addresslist) {
            cardnumber= card.get("cardnumber");

            creditcardpage.fillOutForm(
                    card.get("cardname"),
                    card.get("cardnumber"),
                    card.get("datemoth"),
                    card.get("dateyear"));
            creditcardpage.doSubmitCreditCard();
        }
    }

    @Cuando("guarda la nueva tarjeta")
    public void guarda_la_nueva_tarjeta() {
        creditcardpage.doSubmitCreditCard();
    }

    @Entonces("el sistema debe mostrar un mensaje de confirmación y la tarjeta de crédito debe estar asociada a la cuenta del usuario")
    public void el_sistema_debe_mostrar_un_mensaje_de_confirmación_y_la_tarjeta_de_crédito_debe_estar_asociada_a_la_cuenta_del_usuario() {
        assertTrue(creditcardpage.getMessage().contains("Your card ending with " + cardnumber.substring(cardnumber.length() - 4) + " has been saved for your convenience."));
    }

}
