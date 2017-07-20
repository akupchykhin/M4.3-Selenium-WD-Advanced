package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SentPage extends AbstractPage {

    private static final String goToSentPage = "https://mail.yandex.by/?uid=512863941&login=yalogintest#sent";
    private static final By SENDED_ELEMENT_LOCATOR = By.cssSelector(".mail-MessageSnippet-Item.mail-MessageSnippet-Item_body.js-message-snippet-body");
    private static final By SENT_FOLDER_ELEMENT_LOCATOR = By.cssSelector(".b-mail-pager__label");
    public static final By RECEIVER_NAME = By.cssSelector(".mail-MessageSnippet-FromText");
    public static final String receiver  = "test2.asd@yandex.ru";

    public SentPage(WebDriver driver) {
        super(driver);
    }

    public SentPage goToSentPage() {
        getDriver().get(goToSentPage);
        WebElement sentFolderElement = getDriver().findElement(SENT_FOLDER_ELEMENT_LOCATOR);
        Assert.assertTrue(sentFolderElement.isDisplayed(), "Cannot reach sent folder");
        return this;
    }

    public SentPage verifyThatEmailIsInSentFolder() {
        waitForElementEnabled(SENDED_ELEMENT_LOCATOR);
        getDriver().findElement(SENDED_ELEMENT_LOCATOR).click();
        return this;
    }
}
