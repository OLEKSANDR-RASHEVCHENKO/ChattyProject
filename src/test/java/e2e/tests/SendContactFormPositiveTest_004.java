package e2e.tests;

import com.github.javafaker.Faker;
import constant.HomePageOptions;
import e2e.pages.ContactUsPage;
import e2e.pages.HomePage;
import e2e.pages.LoginPage;
import org.testng.annotations.Test;

public class SendContactFormPositiveTest_004 extends BaseTest  {
    LoginPage loginPage;
    HomePage homePage;
    ContactUsPage contactUsPage;
    Faker faker;
    @Test
    public void sendContactFormPositiveTest() {
        String email = "oleksandr@gmail.com";
        String password = "Gazmanov1234";
        faker = new Faker();
        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoadingLoginPage();
        loginPage.loginInSystemWithCorrectlyData(email, password);
        homePage = new HomePage(app.driver);
        homePage.waitForLoadingHomePage();
        homePage.clickOnOneOptionFromHomePage(HomePageOptions.CONTACT);
        contactUsPage = new ContactUsPage(app.driver);
        contactUsPage.waitForLoadingContactUsPage();
        contactUsPage.sendContactForm(faker.name().username(),email, faker.lorem().paragraph());
    }
}
