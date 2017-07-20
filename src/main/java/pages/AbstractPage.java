package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AbstractPage {
    public static final int WAIT_FOR_TIMEOUT_SECONDS = 10;

    private WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public void waitForElementPresent(By locator) {
        new WebDriverWait(driver, WAIT_FOR_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void waitForElementVisible(By locator) {
        new WebDriverWait(driver, WAIT_FOR_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementEnabled(By locator) {
        new WebDriverWait(driver, WAIT_FOR_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void close(){
        driver.close();
    }

    protected void highlightElement(By locator) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid green'", driver.findElement(locator));
    }

    protected void unHighlightElement(By locator) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='0px'", driver.findElement(locator));
    }
}
