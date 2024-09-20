package e2e.tests;

import e2e.pages.HomePage;
import e2e.pages.LoginPage;
import e2e.untils.DataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class NegativeLoginTest_002 extends BaseTest{
    LoginPage loginPage;
    HomePage homePage;
    @Test(dataProvider = "invalidLoginData", dataProviderClass = DataProvider.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify the negative login scenarios with invalid credentials. This test ensures that login fails when invalid email or password is provided.")
    @Story("As a user, I should not be able to login with invalid email, password, or both.")
    public void negativeLoginTest(String email,String password,boolean incorrectEmail, boolean incorrectPassword, boolean incorrectEmailAndPassword){
        loginPage=new LoginPage(app.driver);
        loginPage.waitForLoadingLoginPage();
        loginPage.loginInSystemWithWrongData(email, password,incorrectEmail,incorrectPassword,incorrectEmailAndPassword);
    }
}
