package e2e.tests;

import e2e.pages.HomePage;
import e2e.pages.LoginPage;
import e2e.untils.DataProvider;
import org.testng.annotations.Test;

public class NegativeLoginTest_002 extends BaseTest{
    LoginPage loginPage;
    HomePage homePage;
    @Test(dataProvider = "invalidLoginData", dataProviderClass = DataProvider.class)
    public void negativeLoginTest(String email,String password,boolean incorrectEmail, boolean incorrectPassword, boolean incorrectEmailAndPassword){
        loginPage=new LoginPage(app.driver);
        loginPage.waitForLoadingLoginPage();
        loginPage.loginInSystemWithWrongData(email, password,incorrectEmail,incorrectPassword,incorrectEmailAndPassword);
    }
}
