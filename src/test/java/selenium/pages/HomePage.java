package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.base.BasePage;

public class HomePage extends BasePage {

@FindBy(xpath = "//div[contains(@class, 'd-lg-flex')]//a[contains(@class, 'HeaderMenu-link--sign-up')]")
    private WebElement signUpButton;

@Step("Click on Sign up button")
    public void clickSignUpButton(){
    signUpButton.click();
}

}
