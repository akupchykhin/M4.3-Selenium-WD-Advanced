package pages;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;

public class ScreenshotsMaker extends AbstractPage {
    public ScreenshotsMaker(WebDriver driver) {
        super(driver);
    }

    public ScreenshotsMaker makeScreenshot() {
        try{
            File screenshot = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFileToDirectory(screenshot, new File("screenshots"));
                    } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }
}
