package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.base.BasePage;

public class SignUpPageUsername extends BasePage {
    @FindBy(id = "username-container")
    private WebElement usernameContainer;

    @FindBy(id = "login")
    private WebElement usernameField;

    @FindBy(css = "[data-continue-to=opt-in-container]")
    private WebElement usernameContinueButton;

    @FindBy(id = "login-err")
    private WebElement validUsernameMessage;

    @FindBy(css = "div.m-1 div:first-child")
    private WebElement notvalidUsernameMessage;


    @Step("Verify username container is displayed")
    public Boolean usernameContainerIsDisplayed() {
        return usernameContainer.isDisplayed();
    }

    @Step("Enter an username")
    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }


    @Step("Verify valid username message is displayed ")
    public Boolean displayValidUsernameMessage() {
        return validUsernameMessage.isDisplayed();
    }
    @Step("Verify valid username message text")
    public String getValidUsernameMessageText() {
        return validUsernameMessage.getText();
    }
    @Step("Verify not valid username message is displayed ")
    public Boolean displayNotvalidUsernameMessage() {
        return notvalidUsernameMessage.isDisplayed();
    }
    @Step("Verify not valid username message text")
    public String getNotvalidUsernameMessageText() {
        return notvalidUsernameMessage.getText();
    }
    @Step("Check the username continue button color")
    public Boolean getUsernameContinueButtonColor(String color) {
        waitForElementColor(usernameContinueButton, color);
        return usernameContinueButton.getCssValue("color").equals(color);
    }
    @Step("Click the username continue button")
    public void clickUsernameContinueButton() {
        waitForElementTobeClickable(usernameContinueButton);
        usernameContinueButton.click();
    }
    @Step("Verify the username continue button invisibility")
    public Boolean usernameContinueButtonInvisible() {
        waitForElementToBeStale(usernameContinueButton);
        return usernameContinueButton.isDisplayed();
    }

}
