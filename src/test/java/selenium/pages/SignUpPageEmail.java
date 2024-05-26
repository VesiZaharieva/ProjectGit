package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.base.BasePage;

import static org.testng.Assert.assertEquals;

public class SignUpPageEmail extends BasePage {

    @FindBy(id = "email-container")
    private WebElement emailContainer;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(css = ".mb-0")
    private WebElement emailErrorMessage;

    @FindBy(css = "[data-continue-to=password-container]")
    private WebElement emailContinueButton;


    @Step("Verify SignUp page was loaded")
    public Boolean emailContainerDisplayed() {
        waitForElementToBeVisible(emailContainer);
        return emailContainer.isDisplayed();
    }
    @Step("Enter a valid e-mail")
    public void enterValidEmail(String email) {

        emailField.sendKeys(email);
    }
    @Step("Enter a valid e-mail with space in front it")
    public void enterValidEmailWithSpaceInfront(String email) {

        emailField.sendKeys(" " + email);
        }

    @Step("Enter a valid e-mail with error message shown")
    public void enterValidEmailWithErrorMessageShown(String email) {
        waitForElementToBeVisible(emailContainer);
        emailField.sendKeys("a");
        waitForElementToBeVisible(emailErrorMessage);
        emailField.sendKeys(email);
    }

    @Step("Enter a not valid e-mail address")
    public void enterNotValidEmail(String email) {

        emailField.sendKeys(email);
    }

    @Step("E-mail error message displaying")
    public Boolean showNotValidEmailMessage() {
                return emailErrorMessage.isDisplayed();
    }

    @Step("Get the e-mail error message text")
    public String getNotValidEmailMessageText() {
            return emailErrorMessage.getText();
    }

    @Step("Verify the e-mail continue button color")
    public Boolean verifyEmailContinueButtonColor(String color) {
        waitForElementColor(emailContinueButton, color);
        return emailContinueButton.getCssValue("color").equals(color);
    }

    @Step("Clich e-mail continue button")
    public void clickContinueButton() {
        waitForElementTobeClickable(emailContinueButton);
        emailContinueButton.click();
    }

    @Step("Check the e-mail continue button invisibility")
    public Boolean emailContinueButtonInvisible() {

        return emailContinueButton.isDisplayed();
    }
}
