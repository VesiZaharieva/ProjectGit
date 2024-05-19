package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import selenium.base.BasePage;

import static org.testng.Assert.assertEquals;

public class RegistrationPage extends BasePage {

    @FindBy(id = "email-container")
    private WebElement emailContainer;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(css = ".mb-0")
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

    @Step("E-mail error message displaying")
    public Boolean notValidEmailMessage() {
        return emailErrorMessage.isDisplayed();
    }
    @Step("Get the e-mail error message text")
    public String notValidEmailMessageText(){
        return emailErrorMessage.getText();
    }


    @Step("Check the Continue button colour")
    public String getContinueButtonColor(){
        return continueButton.getCssValue("color");
    }
    @Step("Continue after e-mail entering")
    public void clickContinueButton() {
        waitForElementTobeClickable(continueButton);
        continueButton.click();
    }
    @Step("Check the continue button invisibility")
    public Boolean continueButtonInvisible(){
        return continueButton.isDisplayed();
    }
}
