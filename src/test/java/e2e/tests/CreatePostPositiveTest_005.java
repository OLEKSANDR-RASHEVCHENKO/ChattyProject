package e2e.tests;

import e2e.pages.HomePage;
import e2e.pages.LoginPage;
import e2e.pages.MyPostPage;
import e2e.untils.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

public class CreatePostPositiveTest_005 extends BaseTest{
    LoginPage loginPage;
    HomePage homePage;
    MyPostPage myPostPage;
    @Test(dataProvider = "dataForCreatePost_005", dataProviderClass = DataProvider.class)
    public void createPostPositiveTest(String title,String description,String content,String path,String data) throws InterruptedException {
        String email = "oleksandr@gmail.com";
        String password = "Gazmanov1234";
        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoadingLoginPage();
        loginPage.loginInSystemWithCorrectlyData(email, password);
        homePage = new HomePage(app.driver);
        homePage.waitForLoadingHomePage();
        homePage.fillAndSubmitPostForm(title,description,content,path,data);
        homePage.switchToMyPost();
        myPostPage = new MyPostPage(app.driver);
        myPostPage.waitForLoadingMyPostPage();
        myPostPage.verifyPostTitleAndDescription(title,description);

        Thread.sleep(5000);
    }
}
