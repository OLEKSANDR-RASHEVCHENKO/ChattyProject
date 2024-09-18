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

    @FindBy(xpath = "//form[@class='post-form']")
    WebElement postForm;
    @FindBy(xpath = "//input[@placeholder='Title']")
    WebElement titleField;
    @FindBy(xpath = "//input[@placeholder='Description']")
    WebElement descriptionField;
    @FindBy(xpath = "//textarea[@placeholder='My thoughts. No more than 1000 characters']")
    WebElement thoughtsField;
    @FindBy(xpath = "//input[@id='publishDate']")
    WebElement dateField;
    @FindBy(xpath = "//*[@accept='image/png,.png,image/jpg,.jpg,image/jpeg,.jpeg']")
    WebElement fileUpload;
    @FindBy(xpath = "//*[@data-test='uploaded-image']")
    WebElement uploadedImages;
    @FindBy(xpath = "//label[normalize-space()='Save as a draft']")
    WebElement saveAsDraftButton;
    @FindBy(xpath = "//button[normalize-space()='Submit']")
    WebElement submitButton;




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
    public void fillAndSubmitPostForm(String title, String description, String thoughts, String filePath,String date){
        createPostButton();
        getWait().forVisibility(postForm);
        Assert.assertTrue(postForm.isDisplayed());
        getWait().forVisibility(titleField);
        Assert.assertTrue(titleField.isDisplayed());
        getWait().forVisibility(descriptionField);
        Assert.assertTrue(descriptionField.isDisplayed());
        getWait().forVisibility(thoughtsField);
        Assert.assertTrue(thoughtsField.isDisplayed());
        getWait().forVisibility(dateField);
        Assert.assertTrue(dateField.isDisplayed());
        titleField.sendKeys(title);
        descriptionField.sendKeys(description);
        thoughtsField.sendKeys(thoughts);
        fileUpload.sendKeys(filePath);
        getWait().forVisibility(uploadedImages);
        dateField.clear();
        dateField.sendKeys(date);
        submitButton.click();
    }


    
}
