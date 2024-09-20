package e2e.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class PostEditDeletePage extends BasePage {

    public PostEditDeletePage(WebDriver driver) {
        super(driver);
    }

    // Edit Post page
    @FindBy(xpath = "//div[@class='edit-post__section']")
    WebElement editPostSection;

    @FindBy(xpath = "//*[@class='post-content__top']//h3")
    WebElement title;

    @FindBy(xpath = "//*[@class='post-user__top']//span")
    WebElement username;

    // EditPostSectionModal
    @FindBy(xpath = "//img[@alt='edit button']")
    WebElement editButton;

    @FindBy(xpath = "//div[@class='modal-content']")
    WebElement editPostSectionModal;

    @FindBy(xpath = "//input[@placeholder='Title']")
    WebElement editTitleField;

    @FindBy(xpath = "//input[@placeholder='Description']")
    WebElement editDescriptionField;

    @FindBy(xpath = "//textarea[@placeholder='My thoughts. No more than 1000 characters']")
    WebElement editThoughtsField;

    @FindBy(xpath = "//*[@accept='image/png,.png,image/jpg,.jpg,image/jpeg,.jpeg']")
    WebElement editImageField;

    @FindBy(xpath = "//img[@alt='Uploaded']")
    WebElement uploadedImage;

    @FindBy(xpath = "//input[@id='publishDate']")
    WebElement editDateField;

    @FindBy(xpath = "//label[normalize-space()='Save as a draft']")
    WebElement editSaveAsDraftButton;

    @FindBy(xpath = "//button[normalize-space()='Submit']")
    WebElement editSubmitButton;

    // DeletePostModal
    @FindBy(xpath = "//img[@alt='delete button']")
    WebElement deleteButton;

    @Step("Waiting for the Post Edit/Delete page to load")
    public void waitForPostEditDeletePage() {
        getWait().forVisibility(editPostSection);
        Assert.assertTrue(editPostSection.isDisplayed(), "Edit post section is not displayed.");
        getWait().forVisibility(title);
        Assert.assertTrue(title.isDisplayed(), "Post title is not displayed.");
        getWait().forVisibility(username);
        Assert.assertTrue(username.isDisplayed(), "Username is not displayed.");
        getWait().forVisibility(editButton);
        Assert.assertTrue(editButton.isDisplayed(), "Edit button is not displayed.");
        getWait().forVisibility(deleteButton);
        Assert.assertTrue(deleteButton.isDisplayed(), "Delete button is not displayed.");
    }

    @Step("Getting the username")
    public String getUsername() {
        return username.getText();
    }

    @Step("Getting the post title")
    public String getTitle() {
        return title.getText();
    }

    @Step("Editing the post with title: {title}, description: {description}, thoughts: {thoughts}, date: {date}, save as draft: {saveAsDraft}")
    public void editPost(String title, String description, String thoughts, String imagePath, String date, boolean saveAsDraft) {
        editButton.click();
        getWait().forVisibility(editPostSectionModal);
        Assert.assertTrue(editPostSectionModal.isDisplayed(), "Edit post section modal is not displayed.");
        getWait().forVisibility(editTitleField);
        Assert.assertTrue(editTitleField.isDisplayed(), "Edit title field is not displayed.");
        getWait().forVisibility(editDescriptionField);
        Assert.assertTrue(editDescriptionField.isDisplayed(), "Edit description field is not displayed.");
        getWait().forVisibility(editThoughtsField);
        Assert.assertTrue(editThoughtsField.isDisplayed(), "Edit thoughts field is not displayed.");
        getWait().forVisibility(editDateField);
        Assert.assertTrue(editDateField.isDisplayed(), "Edit date field is not displayed.");
        getWait().forVisibility(editSaveAsDraftButton);
        Assert.assertTrue(editSaveAsDraftButton.isDisplayed(), "Save as draft button is not displayed.");

        editTitleField.clear();
        editTitleField.sendKeys(title);
        editDescriptionField.clear();
        editDescriptionField.sendKeys(description);
        editThoughtsField.clear();
        editThoughtsField.sendKeys(thoughts);
        editImageField.sendKeys(imagePath);
        getWait().forVisibility(uploadedImage);
        Assert.assertTrue(uploadedImage.isDisplayed(), "Uploaded image is not displayed.");
        editDateField.clear();
        editDateField.sendKeys(date);

        if (saveAsDraft) {
            editSaveAsDraftButton.click();
        } else {
            editSubmitButton.click();
        }
    }

    @Step("Deleting the post")
    public void deletePost() {
        getWait().forVisibility(deleteButton);
        deleteButton.click();
    }
}
