package e2e.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='container']")
    WebElement container;

    @FindBy(xpath = "//a[normalize-space()='Sign up']")
    WebElement signUpButton;

    @FindBy(xpath = "//input[@placeholder='Email']")
    WebElement emailField;

    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement passwordField;

    @FindBy(xpath = "//*[@data-test='submit']")
    WebElement loginButton;

    @FindBy(xpath = "//div[normalize-space()='Incorrect email']")
    WebElement incorrectEmailError;

    @FindBy(xpath = "//div[normalize-space()='Password must be 8-100 characters and include at least one letter and one digit']")
    WebElement incorrectPasswordError;

    @Step("Waiting for Login Page to load")
    public void waitForLoadingLoginPage() {
        getWait().forVisibility(container);
        Assert.assertTrue(container.isDisplayed(), "Login page container is not displayed");
        getWait().forVisibility(signUpButton);
        Assert.assertTrue(signUpButton.isDisplayed(), "Sign up button is not displayed");
        getWait().forVisibility(emailField);
        Assert.assertTrue(emailField.isDisplayed(), "Email field is not displayed");
        getWait().forVisibility(passwordField);
        Assert.assertTrue(passwordField.isDisplayed(), "Password field is not displayed");
        getWait().forVisibility(loginButton);
        Assert.assertTrue(loginButton.isDisplayed(), "Login button is not displayed");
    }

    @Step("Logging in with email: {email} and password: {password}")
    public void loginInSystemWithCorrectlyData(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    @Step("Attempting login with incorrect email: {email} and/or password: {password}")
    public void loginInSystemWithWrongData(String email, String password, boolean incorrectEmail, boolean incorrectPassword, boolean incorrectEmailAndPassword) {
        if (incorrectEmail) {
            emailField.sendKeys(email);
            getWait().forVisibility(incorrectEmailError);
            Assert.assertTrue(incorrectEmailError.isDisplayed(), "Incorrect email error message is not displayed");
            getWait().forInClickable(loginButton);
        }
        if (incorrectPassword) {
            emailField.sendKeys(email);
            passwordField.sendKeys(password);
            getWait().forVisibility(incorrectPasswordError);
            Assert.assertTrue(incorrectPasswordError.isDisplayed(), "Incorrect password error message is not displayed");
        }
        if (incorrectEmailAndPassword) {
            emailField.sendKeys(email);
            passwordField.sendKeys(password);
            getWait().forVisibility(incorrectEmailError);
            Assert.assertTrue(incorrectEmailError.isDisplayed(), "Incorrect email error message is not displayed");
            passwordField.sendKeys(password);
            getWait().forVisibility(incorrectPasswordError);
            Assert.assertTrue(incorrectPasswordError.isDisplayed(), "Incorrect password error message is not displayed");
            getWait().forInClickable(loginButton);
        }
    }
}
