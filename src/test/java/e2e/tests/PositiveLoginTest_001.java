package e2e.tests;

import constant.HomePageOptions;
import e2e.pages.HomePage;
import e2e.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class PositiveLoginTest_001 extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test to verify that a user can successfully log in with valid credentials.")
    @Story("As a user, I should be able to login successfully with a valid email and password.")
    public void loginPositiveTest() throws InterruptedException {
        String email = "oleksandr@gmail.com";
        String password = "Gazmanov1234";
        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoadingLoginPage();
        loginPage.loginInSystemWithCorrectlyData(email, password);
        homePage = new HomePage(app.driver);
        homePage.waitForLoadingHomePage();
    }
}
