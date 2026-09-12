package com.ligo.mobile.screenplay.tasks;

import com.ligo.mobile.pages.ProductCatalogPage;
import com.ligo.mobile.screenplay.Task;
import com.ligo.mobile.screenplay.actors.Actor;
import com.ligo.mobile.utils.MobileActionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Base64;

public class OpenCartTask implements Task {
    @Override
    public void performAs(Actor actor) {
        WebDriverWait wait = new WebDriverWait(actor.driver(), Duration.ofSeconds(20));
        By[] locators = new By[]{
                ProductCatalogPage.CART_BUTTON,
                By.id("com.saucelabs.mydemoapp.android:id/cartBt"),
                By.xpath("//*[contains(@content-desc,'cart') or contains(translate(@text,'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'CART')]")
        };

        boolean clicked = false;
        for (By locator : locators) {
            try {
                WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
                el.click();
                MobileActionUtils.waitAfterAction();
                clicked = true;
                break;
            } catch (Exception ignored) {
            }
        }

        if (!clicked) {
            try {
                java.nio.file.Path dir = Paths.get(System.getProperty("user.dir"), "target", "appium-failures");
                Files.createDirectories(dir);
                String ts = String.valueOf(Instant.now().toEpochMilli());
                String pageSource = actor.driver().getPageSource();
                Files.writeString(dir.resolve("pagesource-open-cart-" + ts + ".xml"), pageSource);
                org.openqa.selenium.TakesScreenshot shot = (org.openqa.selenium.TakesScreenshot) actor.driver();
                byte[] img = Base64.getDecoder().decode(shot.getScreenshotAs(org.openqa.selenium.OutputType.BASE64));
                Files.write(dir.resolve("screenshot-open-cart-" + ts + ".png"), img);
            } catch (IOException e) {
                // best-effort
            }
            throw new RuntimeException("Unable to find/click Cart button. Page source and screenshot saved under target/appium-failures");
        }
    }
}
