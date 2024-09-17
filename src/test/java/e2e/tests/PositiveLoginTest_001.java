package e2e.tests;

import constant.HomePageOptions;
import e2e.pages.HomePage;
import e2e.pages.LoginPage;
import org.testng.annotations.Test;

public class PositiveLoginTest_001 extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;

    @Test
    public void loginPositiveTest() throws InterruptedException {
        String email = "oleksandr@gmail.com";
        String password = "Gazmanov1234";
        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoadingLoginPage();
        loginPage.loginInSystemWithCorrectlyData(email, password);
        homePage = new HomePage(app.driver);
        homePage.waitForLoadingHomePage();
        homePage.moveToDropDownElement();
        homePage.clickOnOneOptionFromHomePage(HomePageOptions.YOUR_PROFILE_DROPDOWN);
        Thread.sleep(5000);
    }
}
