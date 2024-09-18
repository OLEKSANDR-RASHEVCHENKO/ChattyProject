package e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class MyPostPage extends BasePage{
    public MyPostPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[@class='posts']//div[@class='container']")
    WebElement postContainer;
    @FindBy(xpath = "//*[@class='post-content__top']//h3")
    WebElement title;
    @FindBy(xpath = "//*[@class='post__description']")
    WebElement description;
    @FindBy(xpath = "//*[@data-test='post']")
    WebElement postElement;
    public void waitForLoadingMyPostPage() {
        getWait().forVisibility(postContainer);
        Assert.assertTrue(postContainer.isDisplayed());
    }
    public void verifyPostTitleAndDescription(String titleFromCreating,String descriptionFromCreating) {
        List<WebElement> titles = driver.findElements(By.xpath("//*[@class='post-content__top']//h3"));
        List<WebElement> descriptions = driver.findElements(By.xpath("//*[@class='post__description']"));
        for (int i=0;i<titles.size();i++) {
            WebElement title = titles.get(i);
            WebElement description = descriptions.get(i);
            if (title.getText().equalsIgnoreCase(titleFromCreating)) {
                Assert.assertEquals(title.getText(), titleFromCreating);
                Assert.assertEquals(description.getText(), descriptionFromCreating);
            }
        }
        }
    }



