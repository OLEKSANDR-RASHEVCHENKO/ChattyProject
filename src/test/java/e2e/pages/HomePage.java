package e2e.pages;

import io.qameta.allure.Step;
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

    // Errors
    @Step("Checking for errors across all fields")
    public void allFieldsError() {
        List<WebElement> errors = driver.findElements(By.xpath("//*[@class='error']"));
        getWait().forAllVisibility(errors);
    }

    @Step("Checking for an error message in one field")
    public void errorForOneField() {
        WebElement error = driver.findElement(By.xpath("//*[text()='Please fill all fields']"));
        getWait().forVisibility(error);
        Assert.assertTrue(error.isDisplayed(), "Expected error message is not displayed.");
    }

    @Step("Waiting for Home Page to fully load")
    public void waitForLoadingHomePage() {
        getWait().forVisibility(headerBoxMenu);
        Assert.assertTrue(headerBoxMenu.isDisplayed(), "Header box menu is not displayed.");
    }

    @Step("Clicking on option: {option}")
    public void clickOnOneOptionFromHomePage(String option) {
        List<WebElement> elements = driver.findElements(By.xpath("//a[normalize-space()]"));
        for (WebElement element : elements) {
            if (element.getText().equalsIgnoreCase(option)) {
                element.click();
                break;
            }
        }
    }

    @Step("Hovering over dropdown element")
    public void moveToDropDownElement() {
        Actions actions = new Actions(driver);
        actions.moveToElement(dropdownName).perform();
    }

    @Step("Getting the username from dropdown")
    public String getUserNameDropDown() {
        return dropdownName.getText();
    }

    @Step("Getting the username under the profile photo")
    public String getUserNameUnderProfilePhoto() {
        WebElement sidebar = driver.findElement(By.xpath("//*[@class='sidebar__section']"));
        WebElement name = driver.findElement(By.xpath("//*[@class='sidebar__section']//p"));
        getWait().forVisibility(sidebar);
        Assert.assertTrue(sidebar.isDisplayed(), "Sidebar is not displayed.");
        getWait().forVisibility(name);
        Assert.assertTrue(name.isDisplayed(), "Username under profile photo is not displayed.");
        return name.getText();
    }

    @Step("Switching to 'My Posts'")
    public void switchToMyPost() {
        getWait().forVisibility(switchToMyPostAndBack);
        switchToMyPostAndBack.click();
    }

    @Step("Clicking on 'Create Post' button")
    public void clickCreatePostButton() {
        getWait().forVisibility(createPostButton);
        createPostButton.click();
    }

    @Step("Clicking on the logo")
    public void clickOnLogo() {
        logo.click();
    }

    @Step("Filling and submitting the post form with Title: {title}, Description: {description}, Thoughts: {thoughts}, Date: {date}")
    public void fillAndSubmitPostForm(String title, String description, String thoughts, String filePath, String date) {
        clickCreatePostButton();
        getWait().forVisibility(postForm);
        Assert.assertTrue(postForm.isDisplayed(), "Post form is not displayed.");
        getWait().forVisibility(titleField);
        Assert.assertTrue(titleField.isDisplayed(), "Title field is not displayed.");
        getWait().forVisibility(descriptionField);
        Assert.assertTrue(descriptionField.isDisplayed(), "Description field is not displayed.");
        getWait().forVisibility(thoughtsField);
        Assert.assertTrue(thoughtsField.isDisplayed(), "Thoughts field is not displayed.");
        getWait().forVisibility(dateField);
        Assert.assertTrue(dateField.isDisplayed(), "Date field is not displayed.");

        titleField.sendKeys(title);
        descriptionField.sendKeys(description);
        thoughtsField.sendKeys(thoughts);
        fileUpload.sendKeys(filePath);
        getWait().forVisibility(uploadedImages);
        dateField.clear();
        dateField.sendKeys(date);
        submitButton.click();
    }

    @Step("Filling and submitting the post form with incorrect data")
    public void fillAndSubmitPostFormWrongData(String title, String description, String thoughts, boolean titleFieldEmpty, boolean descriptionFieldEmpty, boolean thoughtsFieldEmpty) {
        clickCreatePostButton();
        getWait().forVisibility(postForm);
        titleField.sendKeys(title);
        descriptionField.sendKeys(description);
        thoughtsField.sendKeys(thoughts);
        submitButton.click();

        if (titleFieldEmpty) {
            allFieldsError();
        }
        if (descriptionFieldEmpty) {
            errorForOneField();
        }
        if (thoughtsFieldEmpty) {
            errorForOneField();
        }
    }
}

