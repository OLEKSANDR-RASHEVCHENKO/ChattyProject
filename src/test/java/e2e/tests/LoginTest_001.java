package e2e.tests;

import e2e.pages.HomePage;
import org.testng.annotations.Test;

public class LoginTest_001 extends BaseTest {
    HomePage homePage;
    @Test
    public void loginPositiveTest() throws InterruptedException {
        String email = "rashevchenkoo@gmail.com";
        String password = "Gazmanov1234";
        homePage = new HomePage(app.driver);
        homePage.waitForLoading();
        homePage.loginInSystem(email,password);

    }

}
