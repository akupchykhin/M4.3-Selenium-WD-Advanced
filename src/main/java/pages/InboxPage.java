package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InboxPage extends AbstractPage {

    private static final By COMPOSE_BUTTON_LOCATOR = By.cssSelector(".mail-ComposeButton-Text");
    private static final By LOGOFF_SELECTOR_LOCATOR = By.xpath("//div[@class='mail-User-Name']");
    private static final By LOGOFF_BUTTON_LOCATOR = By.xpath("//a[@data-metric='Меню сервисов:Выход']");

    public InboxPage(WebDriver driver) {
        super(driver);
    }

    public ComposeEmailPage clickOnComposeButton() {
        highlightElement(COMPOSE_BUTTON_LOCATOR);
        getDriver().findElement(COMPOSE_BUTTON_LOCATOR).click();
        return new ComposeEmailPage(getDriver());
    }

    public LoginPage logoff() {
        getDriver().findElement(LOGOFF_SELECTOR_LOCATOR).click();
        WebDriverWait waitForLogoffButton = new WebDriverWait(getDriver(), 2);
        waitForLogoffButton.until(ExpectedConditions.elementToBeClickable(LOGOFF_BUTTON_LOCATOR)).click();
        return new LoginPage(getDriver());
    }
}
