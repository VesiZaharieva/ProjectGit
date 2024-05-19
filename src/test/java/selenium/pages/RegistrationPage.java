package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.base.BasePage;

public class RegistrationPage extends BasePage {

    @FindBy(id = "email-container")
    private WebElement emailContainer;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "email-error")
    private WebElement emailErrorMessage;

    @FindBy(css = "[data-continue-to=password-container]")
    private WebElement continueButton;


    @Step("Verify registration page loading")
    public Boolean emailContainerDisplayed() {
        waitForElementToBeVisible(emailContainer);
        return emailContainer.isDisplayed();
    }

    @Step("Enter an e-mail address")
    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    @Step("Validate the entered e-mail")
    public Boolean validateEmail() {
        return emailErrorMessage.isDisplayed();
    }

    @Step("Continue after e-mail entering")
    public void clickContinueButton() {
        continueButton.click();
    }
}
