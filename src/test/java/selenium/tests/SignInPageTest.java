package selenium.tests;

import com.opencsv.exceptions.CsvException;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import selenium.base.MainTest;
import selenium.pages.HomePage;
import selenium.pages.SignInPage;
import selenium.pages.SignUpPageEmail;
import selenium.pages.UserPage;
import utils.CsvReader;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SignInPageTest extends MainTest {
    HomePage homePage;
    SignInPage signInPage;
    UserPage userPage;

    @DataProvider(name = "valid-user")
    public static Object[][] dataProviderCsvEnterValidUser() throws IOException, CsvException {
        return CsvReader.readFile("src/test/resources/valid-user.csv");
    }
    @DataProvider(name = "notvalid-user")
    public static Object[][] dataProviderCsvEnterNotValidUser() throws IOException, CsvException {
        return CsvReader.readFile("src/test/resources/notvalid-user.csv");
    }
    @BeforeMethod
    private void startSignIn() {
        homePage = new HomePage();
        homePage.clickSignInButton();
    }

    @Epic("Sign in a user")
    @Feature("Sign In a user")
    @Story("Enter valid username and password")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "valid-user")
    public void ValidUser(String username, String password) {
        signInPage = new SignInPage();
        userPage = new UserPage();
        signInPage.enterValidUsername(username);
        signInPage.enterValidPassword(password);
        signInPage.clickSignInButton();
        assertTrue(userPage.getTitleText().contains(username));
    }
    @Epic("Sign in a user")
    @Feature("Sign In a user")
    @Story("Enter not valid username and / or password")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "notvalid-user")
    public void notValidUser(String username, String password) {
        signInPage = new SignInPage();
        userPage = new UserPage();
        signInPage.enterValidUsername(username);
        signInPage.enterValidPassword(password);
        signInPage.clickSignInButton();
        assertTrue(signInPage.errorMessageDisplayed());
        assertEquals(signInPage.getErrorMessageText(), "Incorrect username or password.");
    }

}
