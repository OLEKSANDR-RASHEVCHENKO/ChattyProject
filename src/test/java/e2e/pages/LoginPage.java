package e2e.pages;

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
    WebElement emailFiled;
    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement passwordField;
    @FindBy(xpath = "//*[@data-test='submit']")
    WebElement loginButton;

    @FindBy(xpath = "//div[normalize-space()='Incorrect email']")
    WebElement incorrectEmailError;
    @FindBy(xpath = "//div[normalize-space()='Password must be 8-100 characters and include at least one letter and one digit']")
    WebElement incorrectPasswordError;

    public void waitForLoadingLoginPage() {
        getWait().forVisibility(container);
        Assert.assertTrue(container.isDisplayed());
        getWait().forVisibility(signUpButton);
        Assert.assertTrue(signUpButton.isDisplayed());
        getWait().forVisibility(emailFiled);
        Assert.assertTrue(emailFiled.isDisplayed());
        getWait().forVisibility(passwordField);
        Assert.assertTrue(passwordField.isDisplayed());
        getWait().forVisibility(loginButton);
        Assert.assertTrue(loginButton.isDisplayed());
    }

    public void loginInSystemWithCorrectlyData(String email, String password) {
        emailFiled.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public void loginInSystemWithWrongData(String email, String password, boolean incorrectEmail, boolean incorrectPassword, boolean incorrectEmailAndPassword) {
        if (incorrectEmail) {
            emailFiled.sendKeys(email);
            getWait().forVisibility(incorrectEmailError);
            Assert.assertTrue(incorrectEmailError.isDisplayed());
            getWait().forInClickable(loginButton);
        }
        if (incorrectPassword) {
            emailFiled.sendKeys(email);
            passwordField.sendKeys(password);
            getWait().forVisibility(incorrectPasswordError);
        }
        if (incorrectEmailAndPassword) {
            emailFiled.sendKeys(email);
            passwordField.sendKeys(password);
            getWait().forVisibility(incorrectEmailError);
            Assert.assertTrue(incorrectEmailError.isDisplayed());
            passwordField.sendKeys(password);
            getWait().forVisibility(incorrectPasswordError);
            getWait().forInClickable(loginButton);
        }
    }
}
