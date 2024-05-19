package selenium.tests;

import com.opencsv.exceptions.CsvException;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.base.MainTest;
import selenium.pages.HomePage;
import selenium.pages.RegistrationPage;
import utils.CsvReader;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class RegistrationPageTest extends MainTest {
    HomePage homepage;
    RegistrationPage registrationPage;

    @DataProvider(name = "valid-email")
    public static Object[][] dataProviderCsvEnterValidEmail() throws IOException, CsvException {
        return CsvReader.readFile("src/test/resources/valid-email.csv");
    }
    @DataProvider(name = "notvalid-email")
    public static Object[][] dataProviderCsvEnterNotValidEmail() throws IOException, CsvException {
        return CsvReader.readFile("src/test/resources/notvalid-email.csv");
    }

    @Epic("User registration")
    @Feature("E-mail registration")
    @Story("Enter not valid e-mail")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "notvalid-email")
    public void NotValidEmailMessage(String email) {
        homepage = new HomePage();
        homepage.clickSignUpButton();
        registrationPage = new RegistrationPage();
        registrationPage.emailContainerDisplayed();
        registrationPage.enterEmail(email);
        registrationPage.clickContinueButton();
        assertTrue(registrationPage.notValidEmailMessage());
    }
    @Epic("User registration")
    @Feature("E-mail registration")
    @Story("Enter not valid e-mail")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "notvalid-email")
    public void NotValidEmailMessageText(String email) {
        homepage = new HomePage();
        homepage.clickSignUpButton();
        registrationPage = new RegistrationPage();
        registrationPage.emailContainerDisplayed();
        registrationPage.enterEmail(email);
        registrationPage.clickContinueButton();
        assertEquals(registrationPage.notValidEmailMessageText(), "Email is invalid or already taken");
    }

    @Epic("User registration")
    @Feature("E-mail registration")
    @Story("Enter valid e-mail")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "valid-email")
    public void enterValidEmail(String email) {
        homepage = new HomePage();
        homepage.clickSignUpButton();
        registrationPage = new RegistrationPage();
        registrationPage.emailContainerDisplayed();
        registrationPage.enterEmail(email);
        String color = registrationPage.getContinueButtonColor();
        assertEquals(color, "rgb(98, 117, 151)");
    }
    @Epic("User registration")
    @Feature("E-mail registration")
    @Story("Enter valid e-mail")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "valid-email")
    public void continueWithValidEmail(String email) {
        homepage = new HomePage();
        homepage.clickSignUpButton();
        registrationPage = new RegistrationPage();
        registrationPage.emailContainerDisplayed();
        registrationPage.enterEmail(email);
        registrationPage.clickContinueButton();
        assertFalse(registrationPage.continueButtonInvisible());

    }

}
