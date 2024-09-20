package e2e.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ContactUsPage extends BasePage {

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

    @Step("Waiting for the Contact Us page to load")
    public void waitForLoadingContactUsPage() {
        getWait().forVisibility(contactBox);
        Assert.assertTrue(contactBox.isDisplayed(), "Contact box is not displayed.");

        getWait().forVisibility(nameField);
        Assert.assertTrue(nameField.isDisplayed(), "Name field is not displayed.");

        getWait().forVisibility(emailField);
        Assert.assertTrue(emailField.isDisplayed(), "Email field is not displayed.");

        getWait().forVisibility(contentField);
        Assert.assertTrue(contentField.isDisplayed(), "Content field is not displayed.");

        getWait().forVisibility(sendMessageButton);
        Assert.assertTrue(sendMessageButton.isDisplayed(), "Send Message button is not displayed.");
    }

    @Step("Filling out and submitting the contact form with name: {name}, email: {email}, content: {content}")
    public void sendContactForm(String name, String email, String content) {
        enterName(name);
        enterEmail(email);
        enterContent(content);
        clickSendMessage();
        verifySuccessMessage();
    }

    @Step("Entering name: {name}")
    private void enterName(String name) {
        nameField.sendKeys(name);
    }

    @Step("Entering email: {email}")
    private void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    @Step("Entering content: {content}")
    private void enterContent(String content) {
        contentField.sendKeys(content);
    }

    @Step("Clicking on 'Send Message' button")
    private void clickSendMessage() {
        sendMessageButton.click();
    }

    @Step("Verifying that the success message is displayed")
    private void verifySuccessMessage() {
        getWait().forVisibility(successMessage);
        Assert.assertTrue(successMessage.isDisplayed(), "Success message is not displayed after submitting the form.");
    }
}

