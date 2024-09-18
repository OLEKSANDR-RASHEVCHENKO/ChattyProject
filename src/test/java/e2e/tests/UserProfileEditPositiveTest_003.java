package e2e.tests;

import constant.HomePageOptions;
import e2e.pages.HomePage;
import e2e.pages.LoginPage;
import e2e.pages.UserProfile;
import e2e.untils.DataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;


public class UserProfileEditPositiveTest_003 extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;
    UserProfile userProfile;

    @Test(dataProvider = "dataForUsersEditForUserProfileEditPositiveTest_003", dataProviderClass = DataProvider.class)
    public void testUserProfileEditPositive_003(String name, String lastName, String gender, String bth, String phone) throws InterruptedException {
        String email = "oleksandr@gmail.com";
        String password = "Gazmanov1234";
        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoadingLoginPage();
        loginPage.loginInSystemWithCorrectlyData(email, password);
        homePage = new HomePage(app.driver);
        homePage.waitForLoadingHomePage();
        homePage.moveToDropDownElement();
        homePage.clickOnOneOptionFromHomePage(HomePageOptions.YOUR_PROFILE_DROPDOWN);
        userProfile = new UserProfile(app.driver);
        userProfile.waitForLoadingUserProfile();
        userProfile.editProfile(name, lastName, gender, bth, phone);
        userProfile.clickOnLogo();
        homePage = new HomePage(app.driver);
        homePage.waitForLoadingHomePage();
        String usernameUnderProfilePhoto = homePage.getUserNameUnderProfilePhoto();
        Assert.assertEquals(name,usernameUnderProfilePhoto);
    }
}
