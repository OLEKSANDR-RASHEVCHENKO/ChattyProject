package e2e.pages;

import e2e.waits.Wait;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class BasePage {
    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Wait getWait() {
        return new Wait(driver);
    }

    public Select getSelect(WebElement element) {
        return new Select(element);
    }

    @Step("Check if element is displayed")
    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Enter text '{value}' into input field")
    protected void setInput(WebElement input, String value) {
        input.click();
        input.clear();
        input.sendKeys(value);
    }

    private File takeScreenshot(WebElement element) {
        File tmp;
        if (element == null) {
            tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            System.out.println("Taking a full page screenshot");
        } else {
            tmp = element.getScreenshotAs(OutputType.FILE);
            System.out.println("Taking a screenshot of the element");
        }

        return tmp;
    }

    private double calculateMaxDifferentPercentRatio() {
        Dimension windowSize = driver.manage().window().getSize();
        int width = windowSize.width;
        int height = windowSize.height;

        return 0.01 * width * height;
    }

    private Process setCompareCommandToTerminal(String refImgFilePath, String tmpFilePath) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("compare", "-metric", "AE", refImgFilePath, tmpFilePath, "null:");
        System.out.println("Setting up compare command in terminal");
        return pb.start();
    }

    private double getDifferenceFromLogs(BufferedReader reader) throws IOException {
        String line;
        double difference = 0;
        while ((line = reader.readLine()) != null) {
            difference = Integer.parseInt(line.trim());
        }
        return difference;
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenshot) {
        return screenshot;
    }

    @Step("Taking and comparing screenshot: {actualScreenshotName}")
    protected void takeAndCompareScreenshot(String actualScreenshotName, WebElement element) {
        String referenceImageFilePath = "reference/" + actualScreenshotName + ".png";
        String tmpFilePath = "reference/tmp_" + actualScreenshotName + ".png";
        File tmp = takeScreenshot(element);

        try {
            saveScreenshot(Files.readAllBytes(tmp.toPath()));
            Files.copy(tmp.toPath(), new File(tmpFilePath).toPath(), StandardCopyOption.REPLACE_EXISTING);

            File referenceImageFile = new File(referenceImageFilePath);
            if (!referenceImageFile.exists()) {
                throw new RuntimeException("Reference image file does not exist, but a temporary file is available. Remove 'tmp_' from filename: " + tmpFilePath);
            }

            double maxDiffPercent = calculateMaxDifferentPercentRatio();
            Process process = setCompareCommandToTerminal(referenceImageFilePath, tmpFilePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            double difference = getDifferenceFromLogs(reader);
            reader.close();
            process.destroy();

            if (difference > maxDiffPercent) {
                throw new RuntimeException(referenceImageFilePath + " and " + tmpFilePath + " are not identical. Difference: " + difference);
            }

            Files.deleteIfExists(new File(tmpFilePath).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Step("Deleting text from element")
    public void deleteText(WebElement element) {
        element.click();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.BACK_SPACE);
    }
}
