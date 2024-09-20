package e2e.tests;

import constant.HomePageOptions;
import e2e.pages.HomePage;
import e2e.pages.LoginPage;
import e2e.pages.UserProfile;
import e2e.untils.DataProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;


public class UserProfileEditPositiveTest_003 extends BaseTest {
    LoginPage loginPage;
    HomePage homePage;
    UserProfile userProfile;

    @Test(dataProvider = "dataForUsersEditForUserProfileEditPositiveTest_003", dataProviderClass = DataProvider.class)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify that a user can successfully edit their profile information.")
    @Story("As a user, I should be able to update my profile information and see the changes reflected.")
    public void testUserProfileEditPositive_003(String name, String lastName, String gender, String bth, String phone,String filePath) {
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
        userProfile.editProfile(name, lastName, gender, bth, phone,filePath);
        userProfile.clickOnLogo();
        homePage = new HomePage(app.driver);
        homePage.waitForLoadingHomePage();
        String usernameUnderProfilePhoto = homePage.getUserNameUnderProfilePhoto();
        Assert.assertEquals(name,usernameUnderProfilePhoto);
    }
}
