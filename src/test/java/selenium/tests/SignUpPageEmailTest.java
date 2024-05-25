package selenium.tests;

import com.opencsv.exceptions.CsvException;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.base.MainTest;
import selenium.pages.HomePage;
import selenium.pages.SignUpPageEmail;
import utils.CsvReader;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class SignUpPageEmailTest extends MainTest {
    HomePage homepage;
    SignUpPageEmail signUpPageEmail;



    @DataProvider(name = "valid-email")
    public static Object[][] dataProviderCsvEnterValidEmail() throws IOException, CsvException {
        return CsvReader.readFile("src/test/resources/valid-email.csv");
    }
    @DataProvider(name = "notvalid-email")
    public static Object[][] dataProviderCsvEnterNotValidEmail() throws IOException, CsvException {
        return CsvReader.readFile("src/test/resources/notvalid-email.csv");
    }
    @BeforeMethod
    private void startSignUp() {
        homepage = new HomePage();
        homepage.clickSignUpButton();
    }
    @Epic("Sign up a new user")
    @Feature("E-mail registration")
    @Story("Enter not valid e-mail")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "notvalid-email")
    public void showNotValidEmailMessage(String email) {
        signUpPageEmail = new SignUpPageEmail();
        signUpPageEmail.emailContainerDisplayed();
        signUpPageEmail.enterNotValidEmail(email);
        assertTrue(signUpPageEmail.showNotValidEmailMessage());
    }
    @Epic("Sign up a new user")
    @Feature("E-mail registration")
    @Story("Enter not valid e-mail")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "notvalid-email")
    public void NotValidEmailMessageText(String email) {
        signUpPageEmail = new SignUpPageEmail();
        signUpPageEmail.emailContainerDisplayed();
        signUpPageEmail.enterNotValidEmail(email);
        assertEquals(signUpPageEmail.getNotValidEmailMessageText(), "Email is invalid or already taken");
    }

    @Epic("Sign up a new user")
    @Feature("E-mail registration")
    @Story("Enter valid e-mail")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "valid-email")
    public void ValidEmail(String email) {
        signUpPageEmail = new SignUpPageEmail();
        signUpPageEmail.emailContainerDisplayed();
        signUpPageEmail.enterValidEmail(email);
        String color = signUpPageEmail.getEmailContinueButtonColor();
        assertEquals(color, "rgba(98, 117, 151, 1)");
    }
    @Epic("Sign up a new user")
    @Feature("E-mail registration")
    @Story("Enter valid e-mail")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "valid-email")
    public void ValidEmailWithSpaceInfront(String email) {
        signUpPageEmail = new SignUpPageEmail();
        signUpPageEmail.emailContainerDisplayed();
        signUpPageEmail.enterValidEmailWithSpaceInfront(email);
        String color = signUpPageEmail.getEmailContinueButtonColor();
        assertEquals(color, "rgba(98, 117, 151, 1)");
    }
    @Epic("Sign up a new user")
    @Feature("E-mail registration")
    @Story("Enter valid e-mail")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "valid-email")
    public void ValidEmailWithErrorMessageShown(String email) {
        signUpPageEmail = new SignUpPageEmail();
        signUpPageEmail.emailContainerDisplayed();
        signUpPageEmail.enterValidEmailWithErrorMessageShown(email);
        String color = signUpPageEmail.getEmailContinueButtonColor();
        assertEquals(color, "rgba(98, 117, 151, 1)");
    }

    @Epic("Sign up a new user")
    @Feature("E-mail registration")
    @Story("Enter valid e-mail")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "valid-email")
    public void continueWithValidEmail(String email) {
        signUpPageEmail = new SignUpPageEmail();
        signUpPageEmail.emailContainerDisplayed();
        signUpPageEmail.enterValidEmail(email);
        signUpPageEmail.clickContinueButton();
        assertFalse(signUpPageEmail.emailContinueButtonInvisible());
    }

}
