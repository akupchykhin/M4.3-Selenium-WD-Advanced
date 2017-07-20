package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage extends AbstractPage {

    private static final String emailUrl = "https://mail.yandex.by/";
    private static final String login  = "yalogintest";
    private static final String password  = "yalogintest123";
    private static final By LOGIN_INPUT_FIELD_LOCATOR = By.xpath(".//*[@id='nb-1']/span/input");
    private static final By PASSWORD_INPUT_FIELD_LOCATOR = By.xpath(".//*[@id='nb-6']/span/input");
    private static final By LOGIN_BUTTON_LOCATOR = By.cssSelector(".nb-button._nb-action-button.nb-group-start");
    public static final By HOME_LOGO_LOCATOR = By.cssSelector(".home-logo__default");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        getDriver().get(emailUrl);
        return this;
    }

    public LoginPage fillLoginField() {
        getDriver().findElement(LOGIN_INPUT_FIELD_LOCATOR).sendKeys(login);
        highlightElement(LOGIN_BUTTON_LOCATOR);
        return this;
    }

    public LoginPage fillPasswordField() {
        getDriver().findElement(PASSWORD_INPUT_FIELD_LOCATOR).sendKeys(password);
        return this;
    }

    public InboxPage clickLoginButton() {
        getDriver().findElement(LOGIN_BUTTON_LOCATOR).click();
        return new InboxPage(getDriver());
    }

    public LoginPage userOnHomePageVerify() {
        waitForElementPresent(HOME_LOGO_LOCATOR);
        return this;
    }
}
