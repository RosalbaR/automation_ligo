package com.ligo.web.stepdefinitions;

import com.ligo.web.config.BrowserManager;
import com.ligo.web.pages.LoginPage;
import com.ligo.web.pages.ProductsPage;
import com.ligo.web.screenplay.Actor;
import com.ligo.web.screenplay.abilities.UseTheWebApp;
import com.ligo.web.screenplay.actors.Customer;
import com.ligo.web.screenplay.questions.LoginErrorQuestion;
import com.ligo.web.screenplay.questions.OrderConfirmationQuestion;
import com.ligo.web.screenplay.tasks.AddProductToCartTask;
import com.ligo.web.screenplay.tasks.CompleteCheckoutTask;
import com.ligo.web.screenplay.tasks.LoginTask;
import com.ligo.web.screenplay.tasks.OpenShoppingCartTask;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Cuando;
import org.junit.Assert;

public class PurchaseSteps {
    private Actor customer;

    @Before
    public void setUp() {
        BrowserManager.startBrowser();
        customer = Customer.named("customer");
        customer.can(UseTheWebApp.on(BrowserManager.getPage()));
    }

    @After
    public void tearDown() {
        BrowserManager.close();
    }

    @Dado("que el usuario está en la página de login de SauceDemo")
    public void usuarioEstaEnLaPaginaDeLogin() {
        new LoginPage(BrowserManager.getPage()).open();
        BrowserManager.pause(1500);
    }

    @Cuando("inicia sesión con las credenciales estándar")
    public void iniciaSesionConCredencialesEstandar() {
        customer.attemptsTo(new LoginTask("standard_user", "secret_sauce"));
        BrowserManager.pause(1500);
    }

    @Cuando("inicia sesión con {string} y {string}")
    public void iniciaSesionConCredenciales(String username, String password) {
        customer.attemptsTo(new LoginTask(username, password));
        BrowserManager.pause(1500);
    }

    @Cuando("agrega el producto {string} al carrito")
    public void agregaProductoAlCarrito(String productName) {
        customer.attemptsTo(new AddProductToCartTask(productName));
        BrowserManager.pause(1500);
    }

    @Cuando("abre el carrito de compras")
    public void abreElCarritoDeCompras() {
        customer.attemptsTo(new OpenShoppingCartTask());
        BrowserManager.pause(1500);
    }

    @Cuando("completa el checkout con {string} {string} y código postal {string}")
    public void completaCheckoutConDatosCliente(String firstName, String lastName, String postalCode) {
        customer.attemptsTo(new CompleteCheckoutTask(firstName, lastName, postalCode));
        BrowserManager.pause(2000);
    }

    @Entonces("el mensaje de confirmación debe ser {string}")
    public void mensajeDeConfirmacionDebeSer(String expectedMessage) {
        BrowserManager.pause(1500);
        String actual = customer.asksFor(new OrderConfirmationQuestion()).trim();
        Assert.assertEquals(expectedMessage, actual);
    }

    @Entonces("el mensaje de error de login debe contener {string}")
    public void mensajeDeErrorDebeContener(String expectedText) {
        BrowserManager.pause(1500);
        String actualMessage = customer.asksFor(new LoginErrorQuestion());
        Assert.assertTrue(actualMessage.toLowerCase().contains(expectedText.toLowerCase()));
    }

    @Cuando("agrega un producto al carrito")
    public void agregaUnProductoAlCarrito() {
        new ProductsPage(BrowserManager.getPage()).addToCart("Sauce Labs Backpack");
        BrowserManager.pause(1500);
    }
}
