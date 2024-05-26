package selenium.tests;

import com.opencsv.exceptions.CsvException;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.base.MainTest;
import selenium.pages.HomePage;
import selenium.pages.SignUpPageEmail;
import selenium.pages.SignUpPagePassword;
import selenium.pages.SignUpPageUsername;
import utils.CsvReader;

import java.io.IOException;

import static org.testng.Assert.*;

public class SignUpPageUsernameTest extends MainTest {
    HomePage homepage;
    SignUpPageEmail signUpPageEmail;
    SignUpPagePassword signUpPagePassword;
    SignUpPageUsername signUpPageUsername;

    @DataProvider(name = "valid-username")
    public static Object[][] dataProviderCsvEnterValidUsername() throws IOException, CsvException {
        return CsvReader.readFile("src/test/resources/valid-username.csv");
    }
    @DataProvider(name = "notvalid-username")
    public static Object[][] dataProviderCsvEnterNotValidUsername() throws IOException, CsvException {
        return CsvReader.readFile("src/test/resources/notvalid-username.csv");
    }

    @BeforeMethod
    private void startSignUp() {
        homepage = new HomePage();
        homepage.clickSignUpButton();
        signUpPageEmail = new SignUpPageEmail();
        signUpPageEmail.emailContainerDisplayed();
    }
    @Epic("Sign up a new user")
    @Feature("Username entering")
    @Story("Enter valid username")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "valid-username")
    public void enterValidUsername(String email, String password, String username, String text, String buttonColor) {
        signUpPageEmail = new SignUpPageEmail();
        signUpPagePassword = new SignUpPagePassword();
        signUpPageUsername = new SignUpPageUsername();
        signUpPageEmail.enterValidEmail(email);
        signUpPageEmail.clickContinueButton();
        signUpPagePassword.passwordContainerIsDisplayed();
        signUpPagePassword.enterPassword(password);
        signUpPagePassword.clickPasswordContinueButton();
        signUpPageUsername.usernameContainerIsDisplayed();
        signUpPageUsername.enterUsername(username);
        assertTrue(signUpPageUsername.getUsernameContinueButtonColor(buttonColor));
        assertTrue(signUpPageUsername.displayValidUsernameMessage());
        assertTrue(signUpPageUsername.getValidUsernameMessageText().contains(text));

    }
    @Epic("Sign up a new user")
    @Feature("Username entering")
    @Story("Enter not valid username")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "notvalid-username")
    public void enterNotValidUsername(String email, String password, String username, String text) {
        signUpPageEmail = new SignUpPageEmail();
        signUpPagePassword = new SignUpPagePassword();
        signUpPageUsername = new SignUpPageUsername();
        signUpPageEmail.enterValidEmail(email);
        signUpPageEmail.clickContinueButton();
        signUpPagePassword.passwordContainerIsDisplayed();
        signUpPagePassword.enterPassword(password);
        signUpPagePassword.clickPasswordContinueButton();
        signUpPageUsername.usernameContainerIsDisplayed();
        signUpPageUsername.enterUsername(username);
        assertTrue(signUpPageUsername.displayNotvalidUsernameMessage());
        assertTrue(signUpPageUsername.getNotvalidUsernameMessageText().contains(text));

    }
    @Epic("Sign up a new user")
    @Feature("Username entering")
    @Story("Continue with valid username")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "valid-username")
    public void continueWithValidUsername(String email, String password, String username, String text, String buttonColor) {
        signUpPageEmail = new SignUpPageEmail();
        signUpPagePassword = new SignUpPagePassword();
        signUpPageUsername = new SignUpPageUsername();
        signUpPageEmail.enterValidEmail(email);
        signUpPageEmail.clickContinueButton();
        signUpPagePassword.passwordContainerIsDisplayed();
        signUpPagePassword.enterPassword(password);
        signUpPagePassword.clickPasswordContinueButton();
        signUpPageUsername.usernameContainerIsDisplayed();
        signUpPageUsername.enterUsername(username);
        signUpPageUsername.clickUsernameContinueButton();
        //assertFalse(signUpPageUsername.usernameContinueButtonInvisible());

    }


}
