package selenium.tests;

import com.opencsv.exceptions.CsvException;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.base.BasePage;
import selenium.base.MainTest;
import selenium.pages.HomePage;
import selenium.pages.SignUpPageEmail;
import selenium.pages.SignUpPagePassword;
import utils.CsvReader;

import java.io.IOException;

import static org.testng.Assert.*;

public class SignUpPagePasswordTest extends MainTest {
    HomePage homepage;
    SignUpPageEmail signUpPageEmail;
    SignUpPagePassword signUpPagePassword;

    @DataProvider(name = "valid-password")
    public static Object[][] dataProviderCsvEnterValidPassword() throws IOException, CsvException {
        return CsvReader.readFile("src/test/resources/valid-password.csv");
    }

    @DataProvider(name = "notvalid-password")
    public static Object[][] dataProviderCsvEnterNotValidPassword() throws IOException, CsvException {
        return CsvReader.readFile("src/test/resources/notvalid-password.csv");
    }
    @DataProvider(name = "notvalid-password-toolong")
    public static Object[][] dataProviderCsvEnterNotValidPasswordTooLong() throws IOException, CsvException {
        return CsvReader.readFile("src/test/resources/notvalid-password-toolong.csv");
    }
    @DataProvider(name = "notvalid-password-weak")
    public static Object[][] dataProviderCsvEnterNotValidPasswordWeak() throws IOException, CsvException {
        return CsvReader.readFile("src/test/resources/notvalid-password-weak.csv");
    }
    @BeforeMethod
    private void startSignUp() {
        homepage = new HomePage();
        homepage.clickSignUpButton();
        signUpPageEmail = new SignUpPageEmail();
        signUpPageEmail.emailContainerDisplayed();
    }

    @Epic("Sign up a new user")
    @Feature("Password entering")
    @Story("Enter valid password")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "valid-password")
    public void enterValidPassword(String email, String password, String validityText, String text1, String text2, String text3, String text4, String text5, String text6, String buttonColor, String messageColor) {
        signUpPageEmail = new SignUpPageEmail();
        signUpPagePassword = new SignUpPagePassword();
        signUpPageEmail.enterValidEmail(email);
        signUpPageEmail.clickContinueButton();
        signUpPagePassword.passwordContainerIsDisplayed();
        signUpPagePassword.enterPassword(password);
        assertTrue(signUpPagePassword.verifyPasswordContinueButtonColor(buttonColor));
        assertTrue(signUpPagePassword.displayPasswordMessage());
        assertEquals(signUpPagePassword.getValidityPasswordMessageColor(), messageColor);
        assertEquals(signUpPagePassword.getValidityPasswordMessageText(), validityText);
        assertEquals(signUpPagePassword.getPasswordMessageText2(), text2);
        assertEquals(signUpPagePassword.getPasswordMessageText4(), text4);
        assertEquals(signUpPagePassword.getPasswordMessageText5(), text5);
        assertEquals(signUpPagePassword.getPasswordMessageText6(), text6);

            }

    @Epic("Sign up a new user")
    @Feature("Password entering")
    @Story("Enter not valid password")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "notvalid-password")
    public void enterNotValidPassword1(String email, String password, String validityText, String text1, String text2, String text3, String text4, String text5, String text6, String messageColor) {
        signUpPageEmail = new SignUpPageEmail();
        signUpPageEmail = new SignUpPageEmail();
        signUpPagePassword = new SignUpPagePassword();
        signUpPageEmail.enterValidEmail(email);
        signUpPageEmail.clickContinueButton();
        signUpPagePassword.passwordContainerIsDisplayed();
        signUpPagePassword.enterPassword(password);
        assertTrue(signUpPagePassword.displayPasswordMessage());
        assertEquals(signUpPagePassword.getValidityPasswordMessageColor(), messageColor);
        assertEquals(signUpPagePassword.getValidityPasswordMessageText(), validityText);
        assertEquals(signUpPagePassword.getPasswordMessageText2(), text2);
        assertEquals(signUpPagePassword.getPasswordMessageText4(), text4);
        assertEquals(signUpPagePassword.getPasswordMessageText5(), text5);
        assertEquals(signUpPagePassword.getPasswordMessageText6(), text6);
    }
    @Epic("Sign up a new user")
    @Feature("Password entering")
    @Story("Enter not valid password")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "notvalid-password-toolong")
    public void enterNotValidPasswordTooLong(String email, String password, String validityText, String text, String messageColor) {
        signUpPageEmail = new SignUpPageEmail();
        signUpPageEmail = new SignUpPageEmail();
        signUpPagePassword = new SignUpPagePassword();
        signUpPageEmail.enterValidEmail(email);
        signUpPageEmail.clickContinueButton();
        signUpPagePassword.passwordContainerIsDisplayed();
        signUpPagePassword.enterPassword(password);
        assertTrue(signUpPagePassword.displayPasswordMessage());
        assertEquals(signUpPagePassword.getValidityPasswordMessageColor(), messageColor);
        assertEquals(signUpPagePassword.getValidityPasswordMessageText(), validityText);
        assertEquals(signUpPagePassword.getAdditionalTextToolong(), text);
    }
    @Epic("Sign up a new user")
    @Feature("Password entering")
    @Story("Enter not valid password")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "notvalid-password-weak")
    public void enterNotValidPasswordWeak(String email, String password, String validityText, String text, String messageColor) {
        signUpPageEmail = new SignUpPageEmail();
        signUpPageEmail = new SignUpPageEmail();
        signUpPagePassword = new SignUpPagePassword();
        signUpPageEmail.enterValidEmail(email);
        signUpPageEmail.clickContinueButton();
        signUpPagePassword.passwordContainerIsDisplayed();
        signUpPagePassword.enterPassword(password);
        assertTrue(signUpPagePassword.displayPasswordMessage());
        assertEquals(signUpPagePassword.getValidityPasswordMessageColor(), messageColor);
        assertEquals(signUpPagePassword.getValidityPasswordMessageText(), validityText);
        assertEquals(signUpPagePassword.getAdditionalTextWeak(), text);
    }
    @Epic("Sign up a new user")
    @Feature("Password entering")
    @Story("Continue after valid password entering")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "valid-password")
    public void ContinueValidPassword(String email, String password, String validityText, String text1, String text2, String text3, String text4, String text5, String text6, String buttonColor, String messageColor) {
        signUpPageEmail = new SignUpPageEmail();
        signUpPagePassword = new SignUpPagePassword();
        signUpPageEmail.enterValidEmail(email);
        signUpPageEmail.clickContinueButton();
        signUpPagePassword.passwordContainerIsDisplayed();
        signUpPagePassword.enterPassword(password);
        signUpPagePassword.clickPasswordContinueButton();
        assertFalse(signUpPagePassword.passwordContinueButtonInvisible());
        }



}
