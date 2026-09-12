package com.ligo.mobile.screenplay.tasks;

import com.ligo.mobile.pages.CartPage;
import com.ligo.mobile.screenplay.Task;
import com.ligo.mobile.screenplay.actors.Actor;
import com.ligo.mobile.utils.MobileActionUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Base64;
import java.io.IOException;

public class ProceedToCheckoutTask implements Task {
    @Override
    public void performAs(Actor actor) {
        WebDriverWait wait = new WebDriverWait(actor.driver(), Duration.ofSeconds(20));
        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(CartPage.PROCEED_TO_CHECKOUT));
            btn.click();
            MobileActionUtils.waitAfterAction();
        } catch (Exception e) {
            // save diagnostics
            try {
                java.nio.file.Path dir = Paths.get(System.getProperty("user.dir"), "target", "appium-failures");
                Files.createDirectories(dir);
                String ts = String.valueOf(Instant.now().toEpochMilli());
                String pageSource = actor.driver().getPageSource();
                Files.writeString(dir.resolve("pagesource-proceed-" + ts + ".xml"), pageSource);
                org.openqa.selenium.TakesScreenshot shot = (org.openqa.selenium.TakesScreenshot) actor.driver();
                byte[] img = Base64.getDecoder().decode(shot.getScreenshotAs(org.openqa.selenium.OutputType.BASE64));
                Files.write(dir.resolve("screenshot-proceed-" + ts + ".png"), img);
            } catch (IOException ignored) {}
            throw new RuntimeException("Unable to locate/click Proceed to Checkout button. Saved diagnostics under target/appium-failures", e);
        }
    }
}
