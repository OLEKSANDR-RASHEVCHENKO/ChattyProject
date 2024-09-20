package e2e.tests;

import com.github.javafaker.Faker;
import e2e.pages.HomePage;
import e2e.pages.LoginPage;
import io.qameta.allure.*;
import org.testng.annotations.Test;
@Epic("Post Management")
@Feature("Negative Post Creation Tests")
public class CreatePostNegativeTest_006 extends BaseTest  {
    LoginPage loginPage;
    HomePage homePage;
    Faker faker = new Faker();
    public void createPostNegativeTest(String title, String description, String content, boolean emptyTitle,boolean emptyDescription, boolean emptyContent) {
        String email = "oleksandr@gmail.com";
        String password = "Gazmanov1234";
        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoadingLoginPage();
        loginPage.loginInSystemWithCorrectlyData(email, password);
        homePage = new HomePage(app.driver);
        homePage.waitForLoadingHomePage();
        homePage.fillAndSubmitPostFormWrongData(title, description, content, emptyTitle, emptyDescription, emptyContent);
    }
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that creating a post with all empty fields is not allowed.")
    @Story("User should not be able to create a post with empty title, description, and content.")
    public void createPostNegativeTestWithEmptyData() {
        createPostNegativeTest("","","", true, true, true);
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that creating a post with an empty title is not allowed.")
    @Story("User should not be able to create a post with an empty title.")
    public void createPostNegativeTestWithEmptyTitle() {
        createPostNegativeTest("",faker.lorem().sentence(5),faker.lorem().sentence(8), true, false, false);
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that creating a post with an empty description is not allowed.")
    @Story("User should not be able to create a post with an empty description.")
    public void createPostNegativeTestWithEmptyDescription() {
        createPostNegativeTest(faker.name().lastName(),"",faker.lorem().sentence(8), false, true, false);
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that creating a post with empty content is not allowed.")
    @Story("User should not be able to create a post with empty content.")
    public void createPostNegativeTestWithEmptyContent() {
        createPostNegativeTest(faker.name().lastName(),faker.lorem().sentence(5),"", false, false, true);
    }
}
