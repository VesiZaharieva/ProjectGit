package selenium.tests;

import com.opencsv.exceptions.CsvException;
import dev.selenium.driver.DriverFactory;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.base.MainTest;
import selenium.pages.*;
import utils.CsvReader;

import java.io.IOException;

import static org.testng.Assert.*;

public class SignUpPagePrefencesTest extends MainTest {
    HomePage homepage;
    SignUpPageEmail signUpPageEmail;
    SignUpPagePassword signUpPagePassword;
    SignUpPageUsername signUpPageUsername;
    SignUpPagePreferences signUpPagePreferences;

    @DataProvider(name = "valid-username")
    public static Object[][] dataProviderCsvEnterValidUsername() throws IOException, CsvException {
        return CsvReader.readFile("src/test/resources/valid-username.csv");
    }

    @BeforeMethod
    private void startSignUp() {
        homepage = new HomePage();
        homepage.clickSignUpButton();
        signUpPageEmail = new SignUpPageEmail();
        signUpPageEmail.emailContainerDisplayed();
    }

    @Epic("Sign up a new user")
    @Feature("'Email peferences'")
    @Story("Mark 'Email peferences' check-box")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "valid-username")
    public void markPreferencesCheckbox(String email, String password, String username, String text, String buttonColor) {
        signUpPageEmail = new SignUpPageEmail();
        signUpPagePassword = new SignUpPagePassword();
        signUpPageUsername = new SignUpPageUsername();
        signUpPagePreferences = new SignUpPagePreferences();
        signUpPageEmail.enterValidEmail(email);
        signUpPageEmail.clickContinueButton();
        signUpPagePassword.passwordContainerIsDisplayed();
        signUpPagePassword.enterPassword(password);
        signUpPagePassword.clickPasswordContinueButton();
        signUpPageUsername.usernameContainerIsDisplayed();
        signUpPageUsername.enterUsername(username);
        signUpPageUsername.clickUsernameContinueButton();
        assertTrue(signUpPagePreferences.preferencesContainerIsDisplayed());
        signUpPagePreferences.clickPrefCheckbox();

    }
    @Epic("Sign up a new user")
    @Feature("'Email peferences'")
    @Story("Mark 'Email peferences' check-box")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "valid-username")
    public void continueWithMarkedCheckbox(String email, String password, String username, String text, String buttonColor) {
        signUpPageEmail = new SignUpPageEmail();
        signUpPagePassword = new SignUpPagePassword();
        signUpPageUsername = new SignUpPageUsername();
        signUpPagePreferences = new SignUpPagePreferences();
        signUpPageEmail.enterValidEmail(email);
        signUpPageEmail.clickContinueButton();
        signUpPagePassword.passwordContainerIsDisplayed();
        signUpPagePassword.enterPassword(password);
        signUpPagePassword.clickPasswordContinueButton();
        signUpPageUsername.usernameContainerIsDisplayed();
        signUpPageUsername.enterUsername(username);
        signUpPageUsername.clickUsernameContinueButton();
        signUpPagePreferences.clickPrefCheckbox();
        assertTrue(signUpPagePreferences.verifyCheckboxSelection());
        signUpPagePreferences.clickPrefContinueButton();
        assertEquals(DriverFactory.getDriver().getCurrentUrl(), "https://github.com/signup?ref_cta=Sign+up&ref_loc=header+logged+out&ref_page=%2F&source=header-home");
    }
    @Epic("Sign up a new user")
    @Feature("'Email peferences'")
    @Story("Mark 'Email peferences' check-box")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "valid-username")
    public void continueWithUnmarkedCheckbox(String email, String password, String username, String text, String buttonColor) {
        signUpPageEmail = new SignUpPageEmail();
        signUpPagePassword = new SignUpPagePassword();
        signUpPageUsername = new SignUpPageUsername();
        signUpPagePreferences = new SignUpPagePreferences();
        signUpPageEmail.enterValidEmail(email);
        signUpPageEmail.clickContinueButton();
        signUpPagePassword.passwordContainerIsDisplayed();
        signUpPagePassword.enterPassword(password);
        signUpPagePassword.clickPasswordContinueButton();
        signUpPageUsername.usernameContainerIsDisplayed();
        signUpPageUsername.enterUsername(username);
        signUpPageUsername.clickUsernameContinueButton();
        assertFalse(signUpPagePreferences.verifyCheckboxSelection());
        signUpPagePreferences.clickPrefContinueButton();
        assertEquals(DriverFactory.getDriver().getCurrentUrl(), "https://github.com/signup?ref_cta=Sign+up&ref_loc=header+logged+out&ref_page=%2F&source=header-home");
    }

}
