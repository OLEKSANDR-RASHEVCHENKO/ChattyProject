package e2e.tests;

import com.github.javafaker.Faker;
import e2e.pages.HomePage;
import e2e.pages.LoginPage;
import e2e.pages.MyPostPage;
import e2e.pages.PostEditDeletePage;
import e2e.untils.DataProvider;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
@Epic("Post Management")
@Feature("Create, Edit, and Delete Post")
@Owner("Oleksandr")
public class CreateEditeDeletePostWorkflowPositive_Test_005 extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;
    MyPostPage myPostPage;
    PostEditDeletePage postEditDeletePage;
    Faker faker = new Faker();

    @Severity(SeverityLevel.CRITICAL)
    @Description("This test verifies that a user can successfully create, edit, and delete a post.")
    @Test(dataProvider = "dataForCreatePost_005", dataProviderClass = DataProvider.class)
    public void createPostPositiveTest(String title, String description, String content, String path, String data) {
        String email = "oleksandr@gmail.com";
        String password = "Gazmanov1234";
        String editTitle = faker.name().lastName();
        String editDescription = faker.lorem().sentence(4);
        String editContent = faker.lorem().sentence(10);
        String editPath = "C:\\Users\\OleksandrRashevchenk\\IdeaProjects\\EasyJet\\src\\test\\java\\e2e\\Files\\ninja.jpg";
        String editDate = "2022.01.01";

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoadingLoginPage();
        loginPage.loginInSystemWithCorrectlyData(email, password);
        homePage = new HomePage(app.driver);
        homePage.waitForLoadingHomePage();
        homePage.fillAndSubmitPostForm(title, description, content, path, data);
        String usernameOnHomePage = homePage.getUserNameUnderProfilePhoto();
        homePage.waitForLoadingHomePage();
        homePage.switchToMyPost();
        myPostPage = new MyPostPage(app.driver);
        myPostPage.waitForLoadingMyPostPage();
        myPostPage.verifyPostTitleAndDescription(title, description);
        myPostPage.scrollToElement(app.driver, title);
        myPostPage.clickOnPost(title);
        postEditDeletePage = new PostEditDeletePage(app.driver);
        postEditDeletePage.waitForPostEditDeletePage();
        String userName = postEditDeletePage.getUsername();
        Assert.assertEquals(userName, usernameOnHomePage);
        postEditDeletePage.editPost(editTitle, editDescription, editContent, editPath, editDate, false);
        postEditDeletePage.waitForPostEditDeletePage();
        String editedTitle = postEditDeletePage.getTitle();
        Assert.assertEquals(editedTitle, editTitle);
        postEditDeletePage.deletePost();
        homePage.switchToMyPost();
        myPostPage.waitForLoadingMyPostPage();
        myPostPage.validateOfDeletingPost(title);
    }
}
