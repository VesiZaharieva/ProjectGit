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
        return CsvReader.readFile("src/test/resources/notvalid-password-short.csv");
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
    public void enterValidPassword(String email, String password, String validityText, String messageText, String color) {
        signUpPageEmail = new SignUpPageEmail();
        signUpPagePassword = new SignUpPagePassword();
        signUpPageEmail.enterValidEmail(email);
        signUpPageEmail.clickContinueButton();
        signUpPagePassword.passwordContainerIsDisplayed();
        signUpPagePassword.enterPassword(password);
        //assertEquals(signUpPagePassword.getPasswordContinueButtonColor(), color);
        assertTrue(signUpPagePassword.showPasswordMessage());
        assertEquals(signUpPagePassword.getValidityPasswordMessageColor(), color);
        assertEquals(signUpPagePassword.getValidityPasswordMessageText(), validityText);
        assertTrue(signUpPagePassword.getPasswordMessageText().contains(messageText));

    }

    @Epic("Sign up a new user")
    @Feature("Password entering")
    @Story("Enter not valid password")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "notvalid-password")
    public void enterNotValidPassword(String email, String password, String validityText, String messageText, String color) {
        signUpPageEmail = new SignUpPageEmail();
        signUpPagePassword = new SignUpPagePassword();
        signUpPageEmail.enterValidEmail(email);
        signUpPageEmail.clickContinueButton();
        signUpPagePassword.passwordContainerIsDisplayed();
        signUpPagePassword.enterPassword(password);
        assertTrue(signUpPagePassword.showPasswordMessage());
        //System.out.println(signUpPagePassword.getPasswordErrorMessageText());
        assertTrue(signUpPagePassword.getPasswordMessageText().contains(validityText));
        assertTrue(signUpPagePassword.getPasswordMessageText().contains(messageText));
        assertEquals(signUpPagePassword.getValidityPasswordMessageColor(), color);


    }

}
