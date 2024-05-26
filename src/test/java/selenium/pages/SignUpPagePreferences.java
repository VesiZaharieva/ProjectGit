package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.base.BasePage;

public class SignUpPagePreferences extends BasePage {

    @FindBy(id = "opt-in-container")
    private WebElement preferencesContainer;

    @FindBy(id = "opt_in")
    private WebElement prefCheckbox;

    @FindBy(css = "[data-continue-to=captcha-and-submit-container]")
    private WebElement prefContinueButton;

    @Step("Verify preferences check-box is shown")
    public Boolean preferencesContainerIsDisplayed() {
        waitForElementToBeVisible(preferencesContainer);
        return preferencesContainer.isDisplayed();
    }

    @Step("Click on preferences check-box")
    public void clickPrefCheckbox() {
        waitForElementToBeVisible(prefCheckbox);
        prefCheckbox.click();
    }

    @Step("Verify if the preferences check-box is marked")
    public Boolean verifyCheckboxSelection() {
        return prefCheckbox.isSelected();
    }
    @Step("Click preferences continue button")
    public void clickPrefContinueButton(){
        waitForElementToBeVisible(prefContinueButton);
        prefContinueButton.click();
    }

}
