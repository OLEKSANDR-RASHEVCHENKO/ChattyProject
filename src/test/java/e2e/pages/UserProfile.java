package e2e.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class UserProfile extends BasePage {

    public UserProfile(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//img[@alt='Logo']")
    WebElement logo;

    @FindBy(xpath = "//p[@class='post-header__feed']")
    WebElement header;

    @FindBy(xpath = "//span[@data-test='post-header__plus']")
    WebElement editButton;

    @FindBy(xpath = "//p[@data-test='profileEmail']")
    WebElement usernameOnProfile;

    @FindBy(xpath = "//input[@placeholder='Name']")
    WebElement nameField;

    @FindBy(xpath = "//input[@placeholder='Surname']")
    WebElement surnameField;

    @FindBy(xpath = "//*[@data-test='profileGender']")
    WebElement genderField;

    @FindBy(xpath = "//input[@id='birthDate']")
    WebElement dateOfBirthField;

    @FindBy(xpath = "//*[@name='phone']")
    WebElement phoneField;

    @FindBy(xpath = "//*[@accept='image/png,.png,image/jpg,.jpg,image/jpeg,.jpeg']")
    WebElement imageField;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement saveButton;

    @FindBy(xpath = "//*[@data-test='uploaded-image']")
    WebElement uploadedImageField;

    @Step("Waiting for the user profile to load")
    public void waitForLoadingUserProfile() {
        getWait().forVisibility(header);
        Assert.assertTrue(header.isDisplayed(), "Header is not displayed.");
        getWait().forVisibility(editButton);
        Assert.assertTrue(editButton.isDisplayed(), "Edit button is not displayed.");
    }

    @Step("Clicking on the logo")
    public void clickOnLogo() {
        logo.click();
    }

    @Step("Getting the username displayed on the profile")
    public String getUsernameOnProfile() {
        return usernameOnProfile.getText();
    }

    @Step("Editing the user profile with name: {name}, surname: {surname}, gender: {gender}, date of birth: {dateOfBirth}, phone: {phone}")
    public void editProfile(String name, String surname, String gender, String dateOfBirth, String phone, String filePath) {
        editButton.click();
        getWait().forClickable(nameField);
        getWait().forClickable(surnameField);
        getWait().forClickable(genderField);
        getWait().forClickable(dateOfBirthField);
        getWait().forClickable(phoneField);

        nameField.clear();
        nameField.sendKeys(name);
        surnameField.clear();
        surnameField.sendKeys(surname);

        Select select = new Select(genderField);
        select.selectByVisibleText(gender);

        dateOfBirthField.clear();
        dateOfBirthField.sendKeys(dateOfBirth);
        phoneField.clear();
        phoneField.sendKeys(phone);

        imageField.sendKeys(filePath);
        getWait().forVisibility(uploadedImageField);

        saveButton.click();

        getWait().forInClickable(nameField);
        getWait().forInClickable(surnameField);
        getWait().forInClickable(genderField);
        getWait().forInClickable(dateOfBirthField);
        getWait().forInClickable(phoneField);
    }
}
