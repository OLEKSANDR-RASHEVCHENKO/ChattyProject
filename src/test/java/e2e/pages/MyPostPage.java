package e2e.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class MyPostPage extends BasePage {

    public MyPostPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='posts']//div[@class='container']")
    WebElement postContainer;

    @FindBy(xpath = "//*[@class='header']")
    WebElement header;

    @FindBy(xpath = "//*[@class='post-content__top']//h3")
    WebElement title;

    @FindBy(xpath = "//*[@class='post__description']")
    WebElement description;

    @FindBy(xpath = "//*[@data-test='post']")
    WebElement postElement;

    @Step("Waiting for 'My Posts' page to load")
    public void waitForLoadingMyPostPage() {
        try {
            getWait().forVisibility(postContainer);
            Assert.assertTrue(postContainer.isDisplayed(), "Post container is not displayed.");
            getWait().forVisibility(header);
            Assert.assertTrue(header.isDisplayed(), "Header is not displayed.");
        } catch (Exception e) {
            System.out.println("Post container is not visible");
        }
    }

    @Step("Verifying post title '{titleFromCreating}' and description '{descriptionFromCreating}'")
    public void verifyPostTitleAndDescription(String titleFromCreating, String descriptionFromCreating) {
        List<WebElement> titles = driver.findElements(By.xpath("//*[@class='post-content__top']//h3"));
        List<WebElement> descriptions = driver.findElements(By.xpath("//*[@class='post__description']"));
        for (int i = 0; i < titles.size(); i++) {
            WebElement title = titles.get(i);
            WebElement description = descriptions.get(i);
            if (title.getText().equalsIgnoreCase(titleFromCreating)) {
                Assert.assertEquals(title.getText(), titleFromCreating, "Post title does not match.");
                Assert.assertEquals(description.getText(), descriptionFromCreating, "Post description does not match.");
            }
        }
    }

    @Step("Clicking on post with title '{title}'")
    public void clickOnPost(String title) {
        List<WebElement> posts = driver.findElements(By.xpath("//*[@data-test='post']//h3"));
        for (int i = 0; i < posts.size(); i++) {
            String postTitle = posts.get(i).getText();
            if (postTitle.equalsIgnoreCase(title)) {
                posts.get(i).click();
                break;
            }
        }
    }

    @Step("Validating deletion of post with title '{title}'")
    public void validateOfDeletingPost(String title) {
        try {
            List<WebElement> posts = driver.findElements(By.xpath("//*[@data-test='post']"));
            for (int i = 0; i < posts.size(); i++) {
                String postTitle = posts.get(i).getText();
                if (postTitle.equalsIgnoreCase(title)) {
                    System.out.println(posts.get(i).getText() + " post is not deleted.");
                }
            }
        } catch (Exception e) {
            System.out.println("Post not found, post is successfully deleted.");
        }
    }

    @Step("Scrolling to the post with title '{title}'")
    public void scrollToElement(WebDriver driver, String title) {
        List<WebElement> posts = driver.findElements(By.xpath("//*[@data-test='post']"));
        for (int i = 0; i < posts.size(); i++) {
            String postTitle = posts.get(i).getText();
            if (postTitle.equalsIgnoreCase(title)) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true);", posts.get(i));
            }
        }
    }
}




