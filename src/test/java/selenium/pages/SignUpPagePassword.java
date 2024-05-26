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

    @FindBy(css = ".password-validity-summary.password-validity-summary")
    private WebElement validityPasswordMessage;

    @FindBy(css = "p#password-err p:nth-child(2)")
    private WebElement additionalTextWeak;

    @FindBy(css = "p#password-err p:nth-child(3)")
    private WebElement additionalTextTooLong;

   @FindBy(css = "[data-more-than-n-chars]")
    private WebElement messageText2;

    @FindBy(css = "[data-min-chars]")
    private WebElement messageText4;

    @FindBy(css = "[data-number-requirement]")
    private WebElement messageText5;

    @FindBy(css = "[data-letter-requirement]")
    private WebElement messageText6;

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

    @Step("Verify password message is displayed")
    public Boolean displayPasswordMessage() {
        waitForElementToBeVisible(validityPasswordMessage);
        return validityPasswordMessage.isDisplayed();

    }
    @Step("Validity password message text")
    public String getValidityPasswordMessageText() {
        return validityPasswordMessage.getText();
    }

    @Step("Password validity message color")
    public String getValidityPasswordMessageColor() {
        return validityPasswordMessage.getCssValue("color");
    }
    @Step("Password message single additional text for weak")
    public String getAdditionalTextWeak() {
        return additionalTextWeak.getText();
    }
    @Step("Password message single additional text for too long")
    public String getAdditionalTextToolong() {
        return additionalTextTooLong.getText();
    }
    @Step("Password message text2")
    public String getPasswordMessageText2() {
        return messageText2.getText();
    }

    @Step("Password message text4")
    public String getPasswordMessageText4() {
        return messageText4.getText();
    }

    @Step("Password message text5")
    public String getPasswordMessageText5() {
        return messageText5.getText();
    }

    @Step("Password message text6")
    public String getPasswordMessageText6() {
        return messageText6.getText();
    }

    @Step("Verify the password continue button color")
    public Boolean verifyPasswordContinueButtonColor(String color) {
        waitForElementColor(passwordContinueButton, color);
        return passwordContinueButton.getCssValue("color").equals(color);
    }

    @Step("Click password continue button")
    public void clickPasswordContinueButton() {
        waitForElementTobeClickable(passwordContinueButton);
        passwordContinueButton.click();
    }

    @Step("Verify the password continue button invisibility")
    public Boolean passwordContinueButtonInvisible() {

        return passwordContinueButton.isDisplayed();
    }

}

