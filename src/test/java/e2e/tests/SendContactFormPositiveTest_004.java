package e2e.tests;

import com.github.javafaker.Faker;
import constant.HomePageOptions;
import e2e.pages.ContactUsPage;
import e2e.pages.HomePage;
import e2e.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class SendContactFormPositiveTest_004 extends BaseTest  {
    LoginPage loginPage;
    HomePage homePage;
    ContactUsPage contactUsPage;
    Faker faker;
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify that a logged-in user can successfully send a message through the Contact Us form.")
    @Story("As a user, I should be able to send a contact form after logging in.")
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
