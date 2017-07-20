package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ComposeEmailPage extends AbstractPage {

    private static final By TO_FIELD_LOCATOR = By.cssSelector(".js-compose-field.mail-Bubbles");
    private static final By SUBJECT_FIELD_LOCATOR = By.cssSelector(".mail-Compose-Field-Input-Controller.js-compose-field.js-editor-tabfocus-prev");
    private static final By MESSAGE_BODY_FIELD_LOCATOR = By.xpath(".//*[@id='cke_1_contents']/div");
    public static final By AUTOSAVE_STATUS_LOCATOR = By.xpath("//span[@data-key='view=compose-autosave-status']");
    private static final By RESIZE_FIELD_ELEMENT_LOCATOR = By.cssSelector("#cke_1_resizer");
    private static final By DEFAULT_FIELD_FOR_SENDING_LOCATOR = By.xpath("//div[@role='textbox']");
    public static final By SENT_NOTIFICATION_LOCATOR = By.className(" js-mail-Notification-Content");
    private static final String messageReciever = "test2.asd@yandex.ru";
    private static final String subject = "test email";
    private static final String messageBody = "test text here";

    public ComposeEmailPage(WebDriver driver) {
        super(driver);
    }

    public ComposeEmailPage enterMessageReceiver() {
        getDriver().findElement(TO_FIELD_LOCATOR).sendKeys(messageReciever);
        return this;
    }

    public ComposeEmailPage enterSubject() {
        getDriver().findElement(SUBJECT_FIELD_LOCATOR).sendKeys(subject);
        return this;
    }

    public ComposeEmailPage enterMessageBody() {
        getDriver().findElement(MESSAGE_BODY_FIELD_LOCATOR).sendKeys(messageBody);
        return this;
    }

    public ComposeEmailPage pressSaveButton() {
        String saveButton = Keys.chord(Keys.CONTROL, "s");
        getDriver().findElement(MESSAGE_BODY_FIELD_LOCATOR).sendKeys(saveButton);
        return this;
    }

    public ComposeEmailPage waitForAutoSaveStatus() {
        WebDriverWait waitForAutosaveStatusElement = new WebDriverWait(getDriver(), 12);
        waitForAutosaveStatusElement.until(ExpectedConditions.visibilityOfElementLocated(AUTOSAVE_STATUS_LOCATOR));
        waitForElementVisible(AUTOSAVE_STATUS_LOCATOR);
        return this;
    }

    public ComposeEmailPage resizeField() {
        waitForElementVisible(RESIZE_FIELD_ELEMENT_LOCATOR);
        WebElement resize = getDriver().findElement(RESIZE_FIELD_ELEMENT_LOCATOR);
        new Actions(getDriver()).clickAndHold(resize).moveByOffset(150, -1500).release().build().perform();
        return this;
    }

    public ComposeEmailPage sendTheEmail() {
        waitForElementEnabled(DEFAULT_FIELD_FOR_SENDING_LOCATOR);
        WebElement sendSaveButtons = getDriver().findElement(DEFAULT_FIELD_FOR_SENDING_LOCATOR);
        new Actions(getDriver()).sendKeys(sendSaveButtons,Keys.CONTROL, Keys.ENTER).build().perform();
        WebDriverWait waitForSentNotificationElement = new WebDriverWait(getDriver(), 5);
        waitForSentNotificationElement.until(ExpectedConditions.visibilityOfElementLocated(SENT_NOTIFICATION_LOCATOR));
        return this;
    }
}
