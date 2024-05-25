package selenium.tests;

import dev.selenium.driver.DriverFactory;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import selenium.base.MainTest;
import selenium.pages.HomePage;
import selenium.pages.SignUpPageEmail;


import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class HomePageTest extends MainTest {
    HomePage homepage;
    SignUpPageEmail signUpPageEmail;

    @Epic("Home page loading")
    @Story("Successful home page opening")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void loadHomePage() {
        assertEquals(DriverFactory.getDriver().getCurrentUrl(), "https://github.com/");
    }

    @Epic("User registration")
    @Feature("Start registration")
    @Test
    public void startRegistration() {
        homepage = new HomePage();
        homepage.clickSignUpButton();
        signUpPageEmail = new SignUpPageEmail();
        assertTrue(signUpPageEmail.emailContainerDisplayed());
    }



}
