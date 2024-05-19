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

import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class RegistrationPageTest extends MainTest {
    HomePage homepage;
    RegistrationPage registrationPage;

    @DataProvider(name = "valid-email")
    public static Object[][] dataProviderCsvEnterValidEmail() throws IOException, CsvException {
        return CsvReader.readFile("src/test/resources/valid-email.csv");
    }

    @Epic("User registration")
    @Feature("E-mail registration")
    @Story("Enter correct e-mail")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "valid-email")
    public void enterEmail(String email) {
        homepage = new HomePage();
        homepage.clickSignUpButton();
        registrationPage = new RegistrationPage();
        registrationPage.emailContainerDisplayed();
        registrationPage.enterEmail(email);
        assertFalse(registrationPage.validateEmail());

    }
}
