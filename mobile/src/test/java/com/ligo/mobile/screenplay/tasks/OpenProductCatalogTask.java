package com.ligo.mobile.screenplay.tasks;

import com.ligo.mobile.pages.ProductCatalogPage;
import com.ligo.mobile.screenplay.Task;
import com.ligo.mobile.screenplay.actors.Actor;
import com.ligo.mobile.utils.MobileActionUtils;
import org.openqa.selenium.By;

public class OpenProductCatalogTask implements Task {
    private final String productName;

    public OpenProductCatalogTask(String productName) {
        this.productName = productName;
    }

    @Override
    public void performAs(Actor actor) {
        By productTitleLocator = ProductCatalogPage.product(productName);
        By productContainerLocator = ProductCatalogPage.productContainer(productName);
        By productImageLocator = ProductCatalogPage.productImage(productName);
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(actor.driver(), java.time.Duration.ofSeconds(20));

        try {
            org.openqa.selenium.WebElement product = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(productImageLocator));
            org.openqa.selenium.Rectangle rect = product.getRect();
            clickCenter(actor.driver(), rect);
            MobileActionUtils.waitAfterAction();
            if (isDetailPage(actor.driver())) {
                return;
            }
        } catch (Exception ignored) {
        }

        try {
            org.openqa.selenium.WebElement product = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(productTitleLocator));
            product.click();
            MobileActionUtils.waitAfterAction();
            if (isDetailPage(actor.driver())) {
                return;
            }
        } catch (Exception ignored) {
        }

        try {
            org.openqa.selenium.WebElement product = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(productContainerLocator));
            product.click();
            MobileActionUtils.waitAfterAction();
            if (isDetailPage(actor.driver())) {
                return;
            }
        } catch (Exception ignored) {
        }

        for (int i = 0; i < 8; i++) {
            try {
                org.openqa.selenium.WebElement product = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(productTitleLocator));
                org.openqa.selenium.Rectangle rect = product.getRect();
                clickCenter(actor.driver(), rect);
                MobileActionUtils.waitAfterAction();
                if (isDetailPage(actor.driver())) {
                    return;
                }
            } catch (Exception ignored) {
            }

            try {
                java.util.Map<String, Object> args = new java.util.HashMap<>();
                org.openqa.selenium.Dimension size = actor.driver().manage().window().getSize();
                args.put("left", 0);
                args.put("top", (int) (size.getHeight() * 0.4));
                args.put("width", size.getWidth());
                args.put("height", (int) (size.getHeight() * 0.45));
                args.put("direction", "up");
                args.put("percent", 0.6);
                ((org.openqa.selenium.JavascriptExecutor) actor.driver()).executeScript("mobile: swipeGesture", args);
                Thread.sleep(500);
            } catch (Exception ignored) {
            }
        }

        try {
            java.nio.file.Path dir = java.nio.file.Paths.get(System.getProperty("user.dir"), "target", "appium-failures");
            java.nio.file.Files.createDirectories(dir);
            String ts = String.valueOf(java.time.Instant.now().toEpochMilli());
            String pageSource = actor.driver().getPageSource();
            java.nio.file.Files.writeString(dir.resolve("pagesource-select-" + ts + ".xml"), pageSource);
            org.openqa.selenium.TakesScreenshot shot = (org.openqa.selenium.TakesScreenshot) actor.driver();
            byte[] img = java.util.Base64.getDecoder().decode(shot.getScreenshotAs(org.openqa.selenium.OutputType.BASE64));
            java.nio.file.Files.write(dir.resolve("screenshot-select-" + ts + ".png"), img);
        } catch (java.io.IOException ignored) {}

        throw new RuntimeException("Unable to locate/click product '" + productName + "'. Saved diagnostics under target/appium-failures");
    }

    private boolean isDetailPage(org.openqa.selenium.WebDriver driver) {
        return driver.findElements(By.id("com.saucelabs.mydemoapp.android:id/cartBt")).size() > 0
                || driver.findElements(By.id("com.saucelabs.mydemoapp.android:id/addToCartBtn")).size() > 0
                || driver.findElements(By.id("com.saucelabs.mydemoapp.android:id/addToCartLL")).size() > 0;
    }

    private void clickCenter(org.openqa.selenium.WebDriver driver, org.openqa.selenium.Rectangle rect) {
        int centerX = rect.getX() + rect.getWidth() / 2;
        int centerY = rect.getY() + rect.getHeight() / 2;
        try {
            java.util.Map<String, Object> args = new java.util.HashMap<>();
            args.put("x", centerX);
            args.put("y", centerY);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("mobile: clickGesture", args);
            return;
        } catch (Exception ignored) {
        }

        try {
            io.appium.java_client.TouchAction<?> touch = new io.appium.java_client.TouchAction<>((io.appium.java_client.PerformsTouchActions) driver);
            touch.tap(io.appium.java_client.touch.offset.PointOption.point(centerX, centerY)).perform();
        } catch (Exception ignored) {
        }
    }
}
