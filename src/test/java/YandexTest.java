import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class YandexTest {

    public WebDriver driver;

    @BeforeMethod(description = "Start browser")
     public void browserStart() {
//        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//        driver = new ChromeDriver();
        try {
            driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), DesiredCapabilities.chrome());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void login() {
        new LoginPage(driver)
                .open()
                .fillLoginField()
                .fillPasswordField()
                .clickLoginButton();
    }

    @AfterMethod
    public void driverClose() {
        new AbstractPage(driver)
                .close();
    }

    @Test(description = "Create a new mail and save it as draft")
    public void newMailCreation() throws IOException {
        new InboxPage(driver)
                .clickOnComposeButton();
        new ComposeEmailPage(driver)
                .enterMessageReceiver()
                .enterSubject()
                .enterMessageBody()
                .resizeField()
                .pressSaveButton()
                .waitForAutoSaveStatus();
        WebElement autosaveStatus = driver.findElement(ComposeEmailPage.AUTOSAVE_STATUS_LOCATOR);
        Assert.assertTrue(autosaveStatus.isDisplayed(), "Cannot save email as draft");
    }

    @Test(description = "Verify, that the mail presents in ‘Drafts’ folder")
    public void emailInDraftVerify() {
        new InboxPage(driver)
                .clickOnComposeButton();
        new ComposeEmailPage(driver)
                .enterMessageReceiver()
                .enterSubject()
                .enterMessageBody()
                .resizeField()
                .pressSaveButton()
                .waitForAutoSaveStatus();
        new DraftsPage(driver)
                .draftUrl()
                .findAnEmailInDraftFolder();
        String email = driver.findElement(DraftsPage.BUBBLE_BLOCK_TEXT_LOCATOR).getText();
        Assert.assertEquals(email, DraftsPage.receiver);
        new ScreenshotsMaker(driver).makeScreenshot();
    }

    @Test(description = "Send the mail")
    public void sendTheEmail() {
        new InboxPage(driver)
                .clickOnComposeButton();
        new ComposeEmailPage(driver)
                .enterMessageReceiver()
                .enterSubject()
                .enterMessageBody()
                .resizeField()
                .pressSaveButton()
                .waitForAutoSaveStatus();
         new ComposeEmailPage(driver)
				.sendTheEmail();
		 WebElement web = driver.findElement(ComposeEmailPage.SENT_NOTIFICATION_LOCATOR);
         Assert.assertTrue(web.isDisplayed(), "Email not sended");
    }

    @Test(description = "Verify, that the mail is in ‘Sent’ folder.")
    public void emailIsInSent() {
        new InboxPage(driver)
                .clickOnComposeButton();
        new ComposeEmailPage(driver)
                .enterMessageReceiver()
                .enterSubject()
                .enterMessageBody()
                .sendTheEmail();
        WebElement web = driver.findElement(ComposeEmailPage.SENT_NOTIFICATION_LOCATOR);
        Assert.assertTrue(web.isDisplayed(), "Email not sended");
        new SentPage(driver)
                .goToSentPage()
                .verifyThatEmailIsInSentFolder();
        String email = driver.findElement(SentPage.RECEIVER_NAME).getText();
        Assert.assertEquals(email, SentPage.receiver);
        new ScreenshotsMaker(driver).makeScreenshot();
    }

    @Test(description = "Log off")
    public void logOff() {
        new InboxPage(driver)
                .logoff();
        new LoginPage(driver)
                .userOnHomePageVerify();
        WebElement web = driver.findElement(LoginPage.HOME_LOGO_LOCATOR);
        Assert.assertTrue(web.isDisplayed(), "User can not log in");
        new ScreenshotsMaker(driver).makeScreenshot();
    }
}