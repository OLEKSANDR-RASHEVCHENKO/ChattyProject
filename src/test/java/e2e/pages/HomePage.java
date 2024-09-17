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
    @FindBy(xpath = "//img[@alt='Logo']")
    WebElement logo;
    @FindBy(xpath = "//div[@class='header-box']")
    WebElement headerBoxMenu;
    @FindBy(xpath = "//div[@class='header']//p[1]")
    WebElement dropdownName;
    @FindBy(xpath = "//label[normalize-space()='My Posts']")
    WebElement switchToMyPostAndBack;
    @FindBy(xpath = "//span[@data-test='post-header__plus']")
    WebElement createPostButton;



    public void waitForLoadingHomePage() {
        getWait().forVisibility(headerBoxMenu);
        Assert.assertTrue(headerBoxMenu.isDisplayed());
    }

    public void clickOnOneOptionFromHomePage(String option) {
        List<WebElement> elements = driver.findElements(By.xpath("//a[normalize-space()]"));
        for (WebElement element : elements) {
            if (element.getText().equalsIgnoreCase(option)) {
                element.click();
                break;
            }
        }
    }
    public void moveToDropDownElement(){
        Actions actions = new Actions(driver);
        actions.moveToElement(dropdownName).perform();
    }
    public String getUserNameDropDown(){
        return dropdownName.getText();
    }
    public String getUserNameUnderProfilePhoto(){
        WebElement name = driver.findElement(By.xpath("//*[@class='sidebar__section']//p"));
        getWait().forVisibility(name);
        Assert.assertTrue(name.isDisplayed());
        return name.getText();
    }
    public void switchToMyPost(){
        getWait().forVisibility(switchToMyPostAndBack);
        switchToMyPostAndBack.click();
    }
    public void createPostButton(){
        getWait().forVisibility(createPostButton);
        createPostButton.click();
    }
    public void clickOnLogo(){
        logo.click();
    }
}
