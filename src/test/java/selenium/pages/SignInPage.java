package selenium.pages;

import dev.selenium.driver.DriverFactory;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.base.BasePage;

public class SignInPage extends BasePage {
    @FindBy(css = "#login h1")
    private WebElement signInTitle;

    @FindBy(id = "login_field")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = "[value='Sign in']")
    private WebElement signInButton;

    @FindBy(css = "div.js-flash-alert")
    private WebElement errorMessage;


    @Step("Verify Sign In page was loaded")
    public String getSignInPageUrl() {
        return DriverFactory.getDriver().getCurrentUrl();
    }

    @Step("Verify Sign In Title")
    public String getSignInTitle() {
        waitForElementToBeVisible(signInTitle);
        return signInTitle.getText();
    }

    @Step("Enter valid user name")
    public void enterValidUsername(String username) {

        usernameField.sendKeys(username);
    }

    @Step("Enter valid password")
    public void enterValidPassword(String password) {

        passwordField.sendKeys(password);
    }

    @Step("Click Sign In button")
    public void clickSignInButton() {
        waitForElementTobeClickable(signInButton);
        signInButton.click();
    }
    @Step("Verify error message is displayed")
    public Boolean errorMessageDisplayed(){
        return errorMessage.isDisplayed();
    }
    @Step("Get error message text")
    public String getErrorMessageText(){
        return errorMessage.getText();
    }
}
