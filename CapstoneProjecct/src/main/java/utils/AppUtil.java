package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AppUtil {

    private WebDriver driver;
    private WebDriverWait wait;

    public AppUtil(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
    }

    public void safeClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void sendKeys(By locator, String text) {
        WebElement element = waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void selectReactDropdown(By containerLocator, String value) {
        safeClick(containerLocator);
        WebElement input = waitForVisible(containerLocator)
                .findElement(By.cssSelector("input[id^='react-select']"));
        input.sendKeys(value);
        input.sendKeys(Keys.ENTER);
    }

    public void selectReactDropdownStable(By inputLocator, String value) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(inputLocator));
        input.sendKeys(value);
        input.sendKeys(Keys.ENTER);
    }

    public void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
