package e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class UserProfile extends BasePage{
    public UserProfile(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//img[@alt='Logo']")
    WebElement logo;
    @FindBy(xpath = "//p[@class='post-header__feed']")
    WebElement header;
    @FindBy(xpath = "//span[@data-test='post-header__plus']")
    WebElement editButton;
    public void waitForLoadingUserProfile(){
        getWait().forVisibility(header);
        Assert.assertTrue(header.isDisplayed());
        getWait().forVisibility(editButton);
        Assert.assertTrue(editButton.isDisplayed());
    }
    public void clickOnLogo(){
        logo.click();
    }
}
