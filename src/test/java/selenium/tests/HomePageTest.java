package selenium.tests;

import dev.selenium.driver.DriverFactory;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import selenium.base.MainTest;
import selenium.pages.HomePage;
import selenium.pages.SignInPage;
import selenium.pages.SignUpPageEmail;


import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class HomePageTest extends MainTest {
    HomePage homepage;
    SignUpPageEmail signUpPageEmail;
    SignInPage signInPage;

    @Epic("Home page loading")
    @Story("Successful home page opening")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void loadHomePage() {
        assertEquals(DriverFactory.getDriver().getCurrentUrl(), "https://github.com/");
    }

    @Epic("User sign up")
    @Feature("Start sign up")
    @Test
    public void startSignUp() {
        homepage = new HomePage();
        homepage.clickSignUpButton();
        signUpPageEmail = new SignUpPageEmail();
        assertTrue(signUpPageEmail.emailContainerDisplayed());
    }
    @Epic("User sign in")
    @Feature("Start sign in")
    @Test
    public void startSignIn(){
        homepage = new HomePage();
        homepage.clickSignInButton();
        signInPage = new SignInPage();
        assertEquals(signInPage.getSignInTitle(), "Sign in to GitHub");
        assertEquals(signInPage.getSignInPageUrl(), "https://github.com/login" );
    }



}
