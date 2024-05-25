package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.base.BasePage;

public class SignUpPagePassword extends BasePage {
    @FindBy(id = "password-container")
    private WebElement passwordContainer;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = ".password-validity-summary.password-validity-summary-success.mb-1")
    private WebElement validityPasswordMessage;

    @FindBy(css = ".mb-1")
    private WebElement passwordMessage;

    @FindBy(css = "[data-continue-to=username-container]")
    private WebElement passwordContinueButton;

    @Step("Verify password container was loaded")
    public Boolean passwordContainerIsDisplayed() {
        waitForElementToBeVisible(passwordContainer);
        return passwordContainer.isDisplayed();
    }

    @Step("Enter a password")
    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    @Step("Password message shown")
    public Boolean showPasswordMessage() {
        waitForElementToBeVisible(passwordMessage);
        return passwordMessage.isDisplayed();
    }

    @Step("Validity password message text")
    public String getValidityPasswordMessageText() {
        return validityPasswordMessage.getText();
    }

    @Step("Password validity message color")
    public String getValidityPasswordMessageColor() {
        return validityPasswordMessage.getCssValue("color");
    }

    @Step("Password message text")
    public String getPasswordMessageText() {
        return passwordMessage.getAttribute("textContent");
    }

    @Step("Check the password continue button color")
    public String getPasswordContinueButtonColor() {
        return passwordContinueButton.getCssValue("color");
    }

    @Step("Continue after password entering")
    public void clickContinueButton() {
        waitForElementTobeClickable(passwordContinueButton);
        passwordContinueButton.click();
    }

    @Step("Check the password continue button invisibility")
    public Boolean passwordContinueButtonInvisible() {
        return passwordContinueButton.isDisplayed();
    }

}

