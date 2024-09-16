package e2e;

import config.Config;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;


import java.io.ByteArrayInputStream;


public class ApplicationManager {
    private final Config config = new Config();
    public WebDriver driver;

    public void init() {
        driver = new EdgeDriver();
        driver.get(config.getProjectUrl());
        driver.manage().window().maximize();
    }


    public void stop(boolean testPassed) {
        if (!testPassed) {
            byte[] screenshotData = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot on failure", new ByteArrayInputStream(screenshotData));
        }
        driver.quit();
    }
}