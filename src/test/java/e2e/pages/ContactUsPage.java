package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ContactUsPage extends BasePage{

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[@class='contact-box']")
    WebElement contactBox;
    @FindBy(xpath = "//div[@class='success-message']")
    WebElement successMessage;
    @FindBy(xpath = "//input[@id='name']")
    WebElement nameField;
    @FindBy(xpath = "//input[@id='email']")
    WebElement emailField;
    @FindBy(xpath = "//textarea[@id='content']")
    WebElement contentField;
    @FindBy(xpath = "//button[normalize-space()='Send Message']")
    WebElement sendMessageButton;

    public void waitForLoadingContactUsPage() {
        getWait().forVisibility(contactBox);
        Assert.assertTrue(contactBox.isDisplayed());
        getWait().forVisibility(nameField);
        Assert.assertTrue(nameField.isDisplayed());
        getWait().forVisibility(emailField);
        Assert.assertTrue(emailField.isDisplayed());
        getWait().forVisibility(contactBox);
        Assert.assertTrue(contentField.isDisplayed());
        getWait().forVisibility(sendMessageButton);
        Assert.assertTrue(sendMessageButton.isDisplayed());
    }

    public void sendContactForm(String name, String email, String content) {
        nameField.sendKeys(name);
        emailField.sendKeys(email);
        contentField.sendKeys(content);
        sendMessageButton.click();
        getWait().forVisibility(successMessage);
        Assert.assertTrue(successMessage.isDisplayed());
    }
}
