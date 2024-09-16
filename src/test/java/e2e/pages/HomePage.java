package e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//img[@class='wide_logo']")
    WebElement logo;
    @FindBy(xpath = "//img[@title='login']")
    WebElement login;
    @FindBy(xpath = "//input[@name='email_address']")
    WebElement email;
    @FindBy(xpath = "//input[@name='password']")
    WebElement password;
    @FindBy(xpath = "//button[contains(text(),'Увійти')]")
    WebElement loginButton;

    public void waitForLoading(){
        getWait().forVisibility(logo);
        Assert.assertTrue(logo.isDisplayed());
        getWait().forVisibility(login);
        Assert.assertTrue(login.isDisplayed());
    }
    public void loginInSystem(String emaill,String passwordd){
        login.click();
        getWait().forVisibility(email);
        getWait().forVisibility(password);
        getWait().forVisibility(loginButton);
        email.sendKeys(emaill);
        password.sendKeys(passwordd);
        loginButton.click();
    }

}
