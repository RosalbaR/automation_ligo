package com.ligo.mobile.stepdefinitions;

import com.ligo.mobile.config.AppiumDriverFactory;
import com.ligo.mobile.data.JsonDataLoader;
import com.ligo.mobile.data.PurchaseScenario;
import com.ligo.mobile.pages.CheckoutPage;
import com.ligo.mobile.pages.ConfirmationPage;
import com.ligo.mobile.pages.LoginPage;
import com.ligo.mobile.screenplay.abilities.UseMobileApp;
import com.ligo.mobile.screenplay.actors.Actor;
import com.ligo.mobile.screenplay.questions.CheckoutIsVisibleQuestion;
import com.ligo.mobile.screenplay.tasks.AddProductToCartTask;
import com.ligo.mobile.screenplay.tasks.OpenCartTask;
import com.ligo.mobile.screenplay.tasks.OpenProductCatalogTask;
import com.ligo.mobile.screenplay.tasks.ProceedToCheckoutTask;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class MobilePurchaseStepDefinitions {
    private Actor actor;
    private AppiumDriver driver;
    private List<PurchaseScenario> purchaseScenarios;

    @Before
    public void setUp() {
        driver = AppiumDriverFactory.createDriver();
        actor = Actor.named("usuario").can(UseMobileApp.withDriver(driver));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
            actor = null;
        }
    }

    @Dado("^que el usuario carga la data del JSON$")
    public void queElUsuarioCargaLaDataDelJson() {
        purchaseScenarios = JsonDataLoader.loadPurchases("com/ligo/mobile/data/purchase_data.json");
    }

    private PurchaseScenario getCurrentScenario() {
        if (purchaseScenarios == null || purchaseScenarios.isEmpty()) {
            throw new IllegalStateException("No hay data cargada en el JSON del escenario.");
        }
        return purchaseScenarios.get(0);
    }

    @Dado("^que el usuario inicia sesión con \"([^\"]+)\" y \"([^\"]+)\"$")
    public void queElUsuarioIniciaSesionConDatos(String username, String password) {
        LoginPage.login(driver, username, password);
    }

    @Dado("^que el usuario inicia sesión con la data del JSON$")
    public void queElUsuarioIniciaSesionConDatosDelJson() {
        PurchaseScenario scenario = getCurrentScenario();
        LoginPage.login(driver, scenario.getUsername(), scenario.getPassword());
    }

    @Dado("^que el usuario está en la pantalla de productos$")
    public void queElUsuarioEstaEnLaPantallaDeProductos() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/titleTV")),
                ExpectedConditions.presenceOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/productTV")),
                ExpectedConditions.presenceOfElementLocated(By.id("com.saucelabs.mydemoapp.android:id/productRV"))
        ));
    }

    @Cuando("^selecciona el producto \"([^\"]+)\"$")
    public void seleccionaElProducto(String productName) {
        if (productName != null && !productName.isBlank()) {
            actor.attemptsTo(new OpenProductCatalogTask(productName));
        }
    }

    @Cuando("^selecciona el producto de la data del JSON$")
    public void seleccionaElProductoDesdeJson() {
        PurchaseScenario scenario = getCurrentScenario();
        if (scenario.getProductName() != null && !scenario.getProductName().isBlank()) {
            actor.attemptsTo(new OpenProductCatalogTask(scenario.getProductName()));
        }
    }

    @Cuando("^agrega el producto al carrito$")
    public void agregaElProductoAlCarrito() {
        actor.attemptsTo(new AddProductToCartTask());
    }

    @Cuando("^abre el carrito$")
    public void abreElCarrito() {
        actor.attemptsTo(new OpenCartTask());
    }

    @Cuando("^continúa con el checkout$")
    public void continuaConElCheckout() {
        actor.attemptsTo(new ProceedToCheckoutTask());
    }

    @Cuando("^completa el checkout con los datos:$")
    public void completaElCheckoutConLosDatos(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap();
        CheckoutPage.completePurchase(
                driver,
                data.get("fullName"),
                data.get("addressLine1"),
                data.get("addressLine2"),
                data.get("city"),
                data.get("state"),
                data.get("zipCode"),
                data.get("country"),
                data.getOrDefault("cardHolderName", "Rebecca Winter"),
                data.getOrDefault("cardNumber", "3258 1256 7568 7891"),
                data.getOrDefault("expirationDate", "03/25"),
                data.getOrDefault("securityCode", "123")
        );
    }

    @Cuando("^completa el checkout con la data del JSON$")
    public void completaElCheckoutConLosDatosDelJson() {
        PurchaseScenario scenario = getCurrentScenario();
        CheckoutPage.completePurchase(
                driver,
                scenario.getFullName(),
                scenario.getAddressLine1(),
                scenario.getAddressLine2(),
                scenario.getCity(),
                scenario.getState(),
                scenario.getZipCode(),
                scenario.getCountry(),
                scenario.getCardHolderName(),
                scenario.getCardNumber(),
                scenario.getExpirationDate(),
                scenario.getSecurityCode()
        );
    }

    @Cuando("^confirma la compra$")
    public void confirmaLaCompra() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text, 'Checkout Complete')]")),
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text, 'Thank you for your order')]"))
        ));
    }

    @Entonces("^se muestra el flujo de checkout$")
    public void seMuestraElFlujoDeCheckout() {
        boolean checkoutVisible = actor.asksFor(new CheckoutIsVisibleQuestion());
        Assert.assertTrue("El flujo de checkout no está visible.", checkoutVisible);
    }

    @Entonces("^se muestra la confirmación de compra$")
    public void seMuestraLaConfirmacionDeCompra() {
        Assert.assertTrue("La confirmación de compra no está visible.", ConfirmationPage.isVisible(driver));
    }
}
