package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.base.BasePage;

public class HomePage extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'd-lg-flex')]//a[contains(@class, 'HeaderMenu-link--sign-up')]")
    private WebElement signUpButton;

    @FindBy(css = "div.mr-lg-3 [href='/login']")
    private WebElement SignInButton;

    @Step("Click login button")
    public void clickSignInButton() {
        waitForElementTobeClickable(SignInButton);
        SignInButton.click();
    }

    @Step("Click on Sign up button")
    public void clickSignUpButton() {
        signUpButton.click();
    }

}
